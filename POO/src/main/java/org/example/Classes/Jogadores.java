package org.example.Classes;

public class Jogadores extends Pessoa{
    private String posicao;
    private String sigla_posicao;
    private String nomeFranquia;
    private int nJogos;
    private int totalMinutos;
    private float mediaMinutos;
    private int totalPontos;
    private float mediaPontos;
    private int totalRebotes;
    private float mediaRebotes;
    private int totalAssistencias;
    private float mediaAssistencias;

    public Jogadores(String nome, String identificacao, String data_nasc, String pais, String posicao, String sigla_posicao, String nomeFranquia) {
        super(nome, identificacao, data_nasc, pais);
        this.posicao = posicao;
        this.sigla_posicao = sigla_posicao;
        this.nomeFranquia = nomeFranquia;
    }
    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getSigla_posicao() {
        return sigla_posicao;
    }

    public void setSigla_posicao(String sigla_posicao) {
        this.sigla_posicao = sigla_posicao;
    }

    public String getNomeFranquia() {
        return nomeFranquia;
    }

    public void setNomeFranquia(String nomeFranquia) {
        this.nomeFranquia = nomeFranquia;
    }

    public int getnJogos() {
        return nJogos;
    }

    public void setnJogos(int nJogos) {
        this.nJogos = nJogos;
    }

    public int getTotalMinutos() {
        return totalMinutos;
    }

    public void setTotalMinutos(int totalMinutos) {
        this.totalMinutos = totalMinutos;
    }

    public float getMediaMinutos() {
        return mediaMinutos;
    }

    public void setMediaMinutos(float mediaMinutos) {
        this.mediaMinutos = mediaMinutos;
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

    public int getTotalRebotes() {
        return totalRebotes;
    }

    public void setTotalRebotes(int totalRebotes) {
        this.totalRebotes = totalRebotes;
    }

    public float getMediaRebotes() {
        return mediaRebotes;
    }

    public void setMediaRebotes(float mediaRebotes) {
        this.mediaRebotes = mediaRebotes;
    }

    public int getTotalAssistencias() {
        return totalAssistencias;
    }

    public void setTotalAssistencias(int totalAssistencias) {
        this.totalAssistencias = totalAssistencias;
    }

    public float getMediaAssistencias() {
        return mediaAssistencias;
    }

    public void setMediaAssistencias(float mediaAssistencias) {
        this.mediaAssistencias = mediaAssistencias;
    }
}