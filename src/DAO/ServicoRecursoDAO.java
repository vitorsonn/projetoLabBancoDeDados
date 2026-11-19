package DAO;

import config.Conexao;
import enums.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ServicoRecurso;

public class ServicoRecursoDAO {

    private Conexao conexao;
    private Connection conn;

    public ServicoRecursoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(ServicoRecurso s) {
        String sql = "INSERT INTO servico_recurso (descricao, categoria, valor_referencia) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, s.getDescricao());
            stmt.setString(2, s.getCategoria().name());
            stmt.setBigDecimal(3, s.getValorReferencia());
            stmt.executeUpdate();
            System.out.println("Serviço/Recurso cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir serviço: " + ex.getMessage());
        }
    }

    public ServicoRecurso getServico(int id) {
        String sql = "SELECT * FROM servico_recurso WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearServico(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar serviço por ID: " + ex.getMessage());
        }
        return null;
    }

    public List<ServicoRecurso> getAllServicos() {
        String sql = "SELECT * FROM servico_recurso ORDER BY descricao";
        List<ServicoRecurso> lista = new ArrayList<>();
        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearServico(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar serviços: " + ex.getMessage());
        }
        return lista;
    }

    public void editar(ServicoRecurso s) {
        String sql = "UPDATE servico_recurso SET descricao = ?, categoria = ?, valor_referencia = ? WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, s.getDescricao());
            stmt.setString(2, s.getCategoria().name());
            stmt.setBigDecimal(3, s.getValorReferencia());
            stmt.setInt(4, s.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar serviço: " + ex.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM servico_recurso WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir serviço: " + ex.getMessage());
        }
    }

    private ServicoRecurso mapearServico(ResultSet rs) throws SQLException {
        ServicoRecurso s = new ServicoRecurso();
        s.setId(rs.getInt("id"));
        s.setDescricao(rs.getString("descricao"));
        String catStr = rs.getString("categoria");
        if (catStr != null) {
            s.setCategoria(Categoria.valueOf(catStr));
        }
        s.setValorReferencia(rs.getBigDecimal("valor_referencia"));
        return s;
    }
}