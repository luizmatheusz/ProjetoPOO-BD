package org.example.Classes;

public class Premiacoes {
    private int id;
    private String nome;
    private int idJogador;

    public Premiacoes(int id, String nome, int idJogador) {
        this.id = id;
        this.nome = nome;
        this.idJogador = idJogador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(int idJogador) {
        this.idJogador = idJogador;
    }
}
