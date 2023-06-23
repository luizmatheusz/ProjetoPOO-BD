package org.example.Classes;

public class Franquias {
    private String nome;
    private String conferencia;
    private String mascote;
    private int nJogos;
    private int vitorias;
    private int derrotas;
    private int totalPontos;
    private float mediaPontos;
    private int saldoPontos;

    public Franquias(String nome, String conferencia, String mascote, int nJogos, int vitorias, int derrotas, int totalPontos, float mediaPontos, int saldoPontos) {
        this.nome = nome;
        this.conferencia = conferencia;
        this.mascote = mascote;
        this.nJogos = nJogos;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.totalPontos = totalPontos;
        this.mediaPontos = mediaPontos;
        this.saldoPontos = saldoPontos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public String getMascote() {
        return mascote;
    }

    public void setMascote(String mascote) {
        this.mascote = mascote;
    }

    public int getnJogos() {
        return nJogos;
    }

    public void setnJogos(int nJogos) {
        this.nJogos = nJogos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getTotalPontos() {
        return totalPontos;
    }

    public void setTotalPontos(int totalPontos) {
        this.totalPontos = totalPontos;
    }

    public float getMediaPontos() {
        return mediaPontos;
    }

    public void setMediaPontos(float mediaPontos) {
        this.mediaPontos = mediaPontos;
    }

    public int getSaldoPontos() {
        return saldoPontos;
    }

    public void setSaldoPontos(int saldoPontos) {
        this.saldoPontos = saldoPontos;
    }
}
