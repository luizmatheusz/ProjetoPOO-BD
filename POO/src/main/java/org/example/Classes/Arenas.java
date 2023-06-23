package org.example.Classes;

public class Arenas {
    private String nome;
    private String nomeFranquia;
    private int capacidade;

    public Arenas(String nome, String nomeFranquia, int capacidade) {
        this.nome = nome;
        this.nomeFranquia = nomeFranquia;
        this.capacidade = capacidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFranquia() {
        return nomeFranquia;
    }

    public void setNomeFranquia(String nomeFranquia) {
        this.nomeFranquia = nomeFranquia;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
