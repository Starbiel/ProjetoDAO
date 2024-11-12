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
import daoproject.entity.Faixa;
import daoproject.interfaceDAO.FaixaDAO;

public class FaixaDAOImpl implements FaixaDAO {
    private Connection connection;

    public FaixaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Faixa faixa) {
        String sql = "INSERT INTO faixas (titulo, duracao, disco_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, faixa.getTitulo());
            stmt.setInt(2, faixa.getDuracao());
            stmt.setInt(3, faixa.getDiscoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Faixa buscarPorId(int id) {
        String sql = "SELECT * FROM faixas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Faixa faixa = new Faixa();
                faixa.setId(rs.getInt("id"));
                faixa.setTitulo(rs.getString("titulo"));
                faixa.setDuracao(rs.getInt("duracao"));
                faixa.setDiscoId(rs.getInt("disco_id"));
                return faixa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Faixa> listarPorDiscoId(int discoId) {
        List<Faixa> faixas = new ArrayList<>();
        String sql = "SELECT * FROM faixas WHERE disco_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, discoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Faixa faixa = new Faixa();
                faixa.setId(rs.getInt("id"));
                faixa.setTitulo(rs.getString("titulo"));
                faixa.setDuracao(rs.getInt("duracao"));
                faixa.setDiscoId(rs.getInt("disco_id"));
                faixas.add(faixa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faixas;
    }

    @Override
    public void atualizar(Faixa faixa) {
        String sql = "UPDATE faixas SET titulo = ?, duracao = ?, disco_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, faixa.getTitulo());
            stmt.setInt(2, faixa.getDuracao());
            stmt.setInt(3, faixa.getDiscoId());
            stmt.setInt(4, faixa.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM faixas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

