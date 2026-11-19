package DAO;

import config.Conexao;
import enums.Cargo;
import enums.Categoria;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Despesa;
import model.ItemDespesa;
import model.Politico;
import model.ServicoRecurso;

public class DespesaDAO {

    private Conexao conexao;
    private Connection conn;

    public DespesaDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public boolean inserir(Despesa despesa) {
        if (despesa.getItens() == null || despesa.getItens().isEmpty()) {
            System.out.println("Erro: A despesa precisa ter pelo menos um item.");
            return false;
        }

        despesa.recalcularTotal();
        String sqlDespesa = "INSERT INTO despesa (id_politico, valor, descricao, data_registro) VALUES (?, ?, ?, ?)";
        String sqlItem = "INSERT INTO item_despesa (id_despesa, id_servico, quantidade, valor_unitario) VALUES (?, ?, ?, ?)";

        try {
            this.conn.setAutoCommit(false); //abre pra regirstro

            int idDespesaGerado = 0;
            try (PreparedStatement stmt = this.conn.prepareStatement(sqlDespesa, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, despesa.getPolitico().getId());
                stmt.setBigDecimal(2, despesa.getValor());
                stmt.setString(3, despesa.getDescricao());
                stmt.setObject(4, despesa.getDataRegistro());
                stmt.executeUpdate();

                try (ResultSet rsKeys = stmt.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        idDespesaGerado = rsKeys.getInt(1);
                        despesa.setId(idDespesaGerado);
                    }
                }
            }

            try (PreparedStatement stmtItem = this.conn.prepareStatement(sqlItem)) {
                for (ItemDespesa item : despesa.getItens()) {
                    stmtItem.setInt(1, idDespesaGerado);
                    stmtItem.setInt(2, item.getServicoRecurso().getId());
                    stmtItem.setInt(3, item.getQuantidade());
                    stmtItem.setBigDecimal(4, item.getValorUnitario());
                    stmtItem.addBatch();
                }
                stmtItem.executeBatch();
            }

            this.conn.commit(); //realiza o registro
            System.out.println("Despesa e itens cadastrados com sucesso!");
            return true;

        } catch (SQLException ex) {
            try {
                this.conn.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Erro no rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Erro ao inserir a despesa: " + ex.getMessage());
            return false;
        } finally {
            try {
                this.conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Erro ao restaurar autoCommit: " + e.getMessage());
            }
        }
    }

    public Despesa getDespesa(int id) {
        String sql = "SELECT d.*, p.nome AS politico_nome, p.partido, p.cargo, p.cpf "
                   + "FROM despesa d "
                   + "INNER JOIN politico p ON d.id_politico = p.id "
                   + "WHERE d.id = ?";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Despesa d = mapearDespesa(rs);
                    d.setItens(getItensPorDespesa(d.getId()));
                    return d;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar despesa por ID: " + ex.getMessage());
        }
        return null;
    }

    public boolean editar(Despesa despesa) {
        if (despesa.getItens() == null || despesa.getItens().isEmpty()) {
            System.out.println("Erro: A despesa precisa ter pelo menos um item.");
            return false;
        }

        despesa.recalcularTotal();
        String sqlUpdate = "UPDATE despesa SET id_politico = ?, valor = ?, descricao = ?, data_registro = ? WHERE id = ?";
        String sqlDeleteItens = "DELETE FROM item_despesa WHERE id_despesa = ?";
        String sqlInsertItem = "INSERT INTO item_despesa (id_despesa, id_servico, quantidade, valor_unitario) VALUES (?, ?, ?, ?)";

        try {
            this.conn.setAutoCommit(false);

            try (PreparedStatement stmt = this.conn.prepareStatement(sqlUpdate)) {
                stmt.setInt(1, despesa.getPolitico().getId());
                stmt.setBigDecimal(2, despesa.getValor());
                stmt.setString(3, despesa.getDescricao());
                stmt.setObject(4, despesa.getDataRegistro());
                stmt.setInt(5, despesa.getId());
                stmt.executeUpdate();
            }

            
            try (PreparedStatement stmtDel = this.conn.prepareStatement(sqlDeleteItens)) {
                stmtDel.setInt(1, despesa.getId());
                stmtDel.executeUpdate();
            }

            try (PreparedStatement stmtItem = this.conn.prepareStatement(sqlInsertItem)) {
                for (ItemDespesa item : despesa.getItens()) {
                    stmtItem.setInt(1, despesa.getId());
                    stmtItem.setInt(2, item.getServicoRecurso().getId());
                    stmtItem.setInt(3, item.getQuantidade());
                    stmtItem.setBigDecimal(4, item.getValorUnitario());
                    stmtItem.addBatch();
                }
                stmtItem.executeBatch();
            }

            this.conn.commit();
            return true;

        } catch (SQLException ex) {
            try {
                this.conn.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Erro no rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Erro ao atualizar despesa: " + ex.getMessage());
            return false;
        } finally {
            try {
                this.conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Erro ao restaurar autoCommit: " + e.getMessage());
            }
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM despesa WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir despesa: " + ex.getMessage());
        }
    }

    public List<Despesa> getAllDespesas() {
        String sql = "SELECT d.*, p.nome AS politico_nome, p.partido, p.cargo, p.cpf "
                   + "FROM despesa d "
                   + "INNER JOIN politico p ON d.id_politico = p.id "
                   + "ORDER BY d.id DESC";
        List<Despesa> listaDespesas = new ArrayList<>();

        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Despesa d = mapearDespesa(rs);
                d.setItens(getItensPorDespesa(d.getId()));
                listaDespesas.add(d);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar todas as despesas: " + ex.getMessage());
        }
        return listaDespesas;
    }

    public List<Despesa> getDespesasPorCategoria(Categoria categoria) {
        String sql = "SELECT DISTINCT d.*, p.nome AS politico_nome, p.partido, p.cargo, p.cpf "
                   + "FROM despesa d "
                   + "INNER JOIN politico p ON d.id_politico = p.id "
                   + "INNER JOIN item_despesa i ON d.id = i.id_despesa "
                   + "INNER JOIN servico_recurso s ON i.id_servico = s.id "
                   + "WHERE s.categoria = ?";
        List<Despesa> listaDespesas = new ArrayList<>();

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Despesa d = mapearDespesa(rs);
                    d.setItens(getItensPorDespesa(d.getId()));
                    listaDespesas.add(d);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao filtrar despesas por categoria: " + ex.getMessage());
        }
        return listaDespesas;
    }

    public List<ItemDespesa> getItensPorDespesa(int idDespesa) {
        String sql = "SELECT i.*, s.descricao AS servico_desc, s.categoria, s.valor_referencia "
                   + "FROM item_despesa i "
                   + "INNER JOIN servico_recurso s ON i.id_servico = s.id "
                   + "WHERE i.id_despesa = ?";
        List<ItemDespesa> itens = new ArrayList<>();

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setInt(1, idDespesa);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ServicoRecurso s = new ServicoRecurso();
                    s.setId(rs.getInt("id_servico"));
                    s.setDescricao(rs.getString("servico_desc"));
                    String catStr = rs.getString("categoria");
                    if (catStr != null) {
                        s.setCategoria(Categoria.valueOf(catStr));
                    }
                    s.setValorReferencia(rs.getBigDecimal("valor_referencia"));

                    ItemDespesa item = new ItemDespesa();
                    item.setId(rs.getInt("id"));
                    item.setIdDespesa(idDespesa);
                    item.setServicoRecurso(s);
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setValorUnitario(rs.getBigDecimal("valor_unitario"));

                    itens.add(item);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar itens da despesa: " + ex.getMessage());
        }
        return itens;
    }

   //dashboard

    public BigDecimal getTotalDespesas() {
        String sql = "SELECT SUM(valor) AS total FROM despesa";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                BigDecimal total = rs.getBigDecimal("total");
                return total != null ? total : BigDecimal.ZERO;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao calcular total: " + ex.getMessage());
        }
        return BigDecimal.ZERO;
    }

    public int getQuantidadePoliticos() {
        String sql = "SELECT COUNT(*) AS total FROM politico";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao contar políticos: " + ex.getMessage());
        }
        return 0;
    }

    public BigDecimal getMaiorGastoDoMes() {
        String sql = "SELECT MAX(valor) AS maior FROM despesa "
                   + "WHERE MONTH(data_registro) = MONTH(CURRENT_DATE()) "
                   + "AND YEAR(data_registro) = YEAR(CURRENT_DATE())";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                BigDecimal maior = rs.getBigDecimal("maior");
                return maior != null ? maior : BigDecimal.ZERO;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar maior gasto: " + ex.getMessage());
        }
        return BigDecimal.ZERO;
    }

    public List<Despesa> getUltimasDespesas(int limite) {
        String sql = "SELECT d.*, p.nome AS politico_nome, p.partido, p.cargo, p.cpf "
                   + "FROM despesa d "
                   + "INNER JOIN politico p ON d.id_politico = p.id "
                   + "ORDER BY d.id DESC LIMIT ?";
        List<Despesa> lista = new ArrayList<>();

        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setInt(1, limite);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Despesa d = mapearDespesa(rs);
                    d.setItens(getItensPorDespesa(d.getId()));
                    lista.add(d);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar últimas despesas: " + ex.getMessage());
        }
        return lista;
    }

    private Despesa mapearDespesa(ResultSet rs) throws SQLException {
        Politico p = new Politico();
        p.setId(rs.getInt("id_politico"));
        p.setNome(rs.getString("politico_nome"));
        p.setPartido(rs.getString("partido"));
        p.setCargo(Cargo.valueOf(rs.getString("cargo")));
        p.setCpf(rs.getString("cpf"));

        Despesa d = new Despesa();
        d.setId(rs.getInt("id"));
        d.setPolitico(p);
        d.setValor(rs.getBigDecimal("valor"));
        d.setDescricao(rs.getString("descricao"));
        d.setDataRegistro(rs.getObject("data_registro", LocalDate.class));
        return d;
    }
}