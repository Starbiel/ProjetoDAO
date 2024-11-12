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
import daoproject.entity.Colecao;

public interface ColecaoDAO {
    int inserir(Colecao colecao);
    Colecao buscarPorId(int id);
    List<Colecao> listarTodos();
    void atualizar(Colecao colecao);
    void deletar(int id);
}


