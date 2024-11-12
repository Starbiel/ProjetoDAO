/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daoproject.interfaceDAO;

/**
 *
 * @author User
 */
import java.util.List;
import daoproject.entity.Faixa;

public interface FaixaDAO {
    void inserir(Faixa faixa);
    Faixa buscarPorId(int id);
    List<Faixa> listarPorDiscoId(int discoId);
    void atualizar(Faixa faixa);
    void deletar(int id);
}

