/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoproject;

/**
 *
 * @author User
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import daoproject.interfaceDAO.ColecaoDAO;
import daoproject.entity.Colecao;

public class ColecaoDAOImpl implements ColecaoDAO {
    private Connection connection;

    public ColecaoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int inserir(Colecao colecao) {
        String sql = "INSERT INTO colecoes (nome) VALUES (?)";
        int generatedId = -1;
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, colecao.getNome());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
                colecao.setId(generatedId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    @Override
    public Colecao buscarPorId(int id) {
        String sql = "SELECT * FROM colecoes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Colecao colecao = new Colecao();
                colecao.setId(rs.getInt("id"));
                colecao.setNome(rs.getString("nome"));
                return colecao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Colecao> listarTodos() {
        List<Colecao> colecoes = new ArrayList<>();
        String sql = "SELECT * FROM colecoes";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Colecao colecao = new Colecao();
                colecao.setId(rs.getInt("id"));
                colecao.setNome(rs.getString("nome"));
                colecoes.add(colecao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colecoes;
    }

    @Override
    public void atualizar(Colecao colecao) {
        String sql = "UPDATE colecoes SET nome = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, colecao.getNome());
            stmt.setInt(2, colecao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM colecoes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

