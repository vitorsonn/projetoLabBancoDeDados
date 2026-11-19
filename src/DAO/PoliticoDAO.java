package DAO;

import config.Conexao;
import enums.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Politico;

public class PoliticoDAO {

    private Conexao conexao;
    private Connection conn;

    public PoliticoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

   public boolean inserir(Politico p) {
        String sql = "INSERT INTO politico (nome, partido, cargo, cpf) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getPartido());
            stmt.setString(3, p.getCargo().name());
            stmt.setString(4, p.getCpf());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no banco de dados: " + ex.getMessage());
            return false;
        }
    }

    public Politico getPolitico(int id) {
        String sql = "SELECT * FROM politico WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPolitico(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar político por ID: " + ex.getMessage());
        }
        return null;
    }

    public List<Politico> getAllPoliticos() {
        String sql = "SELECT * FROM politico ORDER BY nome";
        List<Politico> lista = new ArrayList<>();
        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearPolitico(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar políticos: " + ex.getMessage());
        }
        return lista;
    }

    public void editar(Politico p) {
        String sql = "UPDATE politico SET nome = ?, partido = ?, cargo = ?, cpf = ? WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getPartido());
            stmt.setString(3, p.getCargo().name());
            stmt.setString(4, p.getCpf());
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar político: " + ex.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM politico WHERE id = ?";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir político: " + ex.getMessage());
        }
    }

    private Politico mapearPolitico(ResultSet rs) throws SQLException {
        Politico p = new Politico();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setPartido(rs.getString("partido"));
        p.setCargo(Cargo.valueOf(rs.getString("cargo")));
        p.setCpf(rs.getString("cpf"));
        return p;
    }
}