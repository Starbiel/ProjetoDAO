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
import daoproject.interfaceDAO.DiscoDAO;
import daoproject.entity.Disco;


public class DiscoDAOImpl implements DiscoDAO {
    private Connection connection;

    public DiscoDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int inserir(Disco disco) {
        String sql = "INSERT INTO discos (titulo, artista, colecao_id) VALUES (?, ?, ?)";
        int generatedId = -1;
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, disco.getTitulo());
            stmt.setString(2, disco.getArtista());
            stmt.setInt(3, disco.getColecaoId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
                disco.setId(generatedId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    @Override
    public Disco buscarPorId(int id) {
        String sql = "SELECT * FROM discos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Disco disco = new Disco();
                disco.setId(rs.getInt("id"));
                disco.setTitulo(rs.getString("titulo"));
                disco.setArtista(rs.getString("artista"));
                disco.setColecaoId(rs.getInt("colecao_id"));
                return disco;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Disco> listarTodos() {
        List<Disco> discos = new ArrayList<>();
        String sql = "SELECT * FROM discos";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Disco disco = new Disco();
                disco.setId(rs.getInt("id"));
                disco.setTitulo(rs.getString("titulo"));
                disco.setArtista(rs.getString("artista"));
                disco.setColecaoId(rs.getInt("colecao_id"));
                discos.add(disco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discos;
    }

    @Override
    public List<Disco> listarPorColecaoId(int colecaoId) {
        List<Disco> discos = new ArrayList<>();
        String sql = "SELECT * FROM discos WHERE colecao_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, colecaoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Disco disco = new Disco();
                disco.setId(rs.getInt("id"));
                disco.setTitulo(rs.getString("titulo"));
                disco.setArtista(rs.getString("artista"));
                disco.setColecaoId(rs.getInt("colecao_id"));
                discos.add(disco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discos;
    }

    @Override
    public void atualizar(Disco disco) {
        String sql = "UPDATE discos SET titulo = ?, artista = ?, colecao_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, disco.getTitulo());
            stmt.setString(2, disco.getArtista());
            stmt.setInt(3, disco.getColecaoId());
            stmt.setInt(4, disco.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM discos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


