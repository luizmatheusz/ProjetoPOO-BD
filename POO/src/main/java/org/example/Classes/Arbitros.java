package org.example.Classes;

public class Arbitros extends Pessoa{
    int nJogos;
    public Arbitros(String nome, String identificacao, String data_nasc, String pais) {
        super(nome, identificacao, data_nasc, pais);
    }

    public int getnJogos() {
        return nJogos;
    }
}
