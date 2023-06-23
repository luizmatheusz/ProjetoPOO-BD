package org.example.Classes;

import java.util.Date;

public abstract class Pessoa {
    protected int id;
    protected String nome;
    protected String identificacao;
    protected String data_nasc;
    protected int idade;
    protected String pais;

    public Pessoa(String nome, String identificacao, String data_nasc, String pais) {
        this.nome = nome;
        this.identificacao = identificacao;
        this.data_nasc = data_nasc;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
