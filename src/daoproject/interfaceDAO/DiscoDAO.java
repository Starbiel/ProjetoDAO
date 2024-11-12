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
import daoproject.entity.Disco;

public interface DiscoDAO {
    int inserir(Disco disco);
    Disco buscarPorId(int id);
    List<Disco> listarTodos();
    List<Disco> listarPorColecaoId(int colecaoId);
    void atualizar(Disco disco);
    void deletar(int id);
}
