/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoproject.entity;

/**
 *
 * @author User
 */
public class Disco {
    private int id;
    private String titulo;
    private String artista;
    private int colecaoId;

    public Disco() {
    }
    
    public Disco(String titulo, String artista, int colecaoId) {
        this.titulo = titulo;
        this.artista = artista;
        this.colecaoId = colecaoId;
    }

    public int getColecaoId() {
        return colecaoId;
    }

    public void setColecaoId(int colecaoId) {
        this.colecaoId = colecaoId;
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

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }
    
    
}
