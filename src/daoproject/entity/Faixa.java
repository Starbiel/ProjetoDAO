/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoproject.entity;

/**
 *
 * @author User
 */
public class Faixa {
    
    private int id;
    private String titulo;
    private int duracao;
    private int discoId;

    public Faixa() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getDiscoId() {
        return discoId;
    }

    public void setDiscoId(int discoId) {
        this.discoId = discoId;
    }
    
    
    
}
