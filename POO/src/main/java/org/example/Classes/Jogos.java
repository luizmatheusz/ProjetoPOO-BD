package org.example.Classes;

public class Jogos {
    private int id;
    private int idArbitro;
    private String fase;
    private String nomeFranquia1;
    private String nomeFranquia2;
    private int pontuacao_franquia1;
    private int pontuacao_franquia2;
    private String data;

    public Jogos(int idArbitro, String fase, String nomeFranquia1, String nomeFranquia2, int pontuacao_franquia1, int pontuacao_franquia2, String data) {
        this.idArbitro = idArbitro;
        this.fase = fase;
        this.nomeFranquia1 = nomeFranquia1;
        this.nomeFranquia2 = nomeFranquia2;
        this.pontuacao_franquia1 = pontuacao_franquia1;
        this.pontuacao_franquia2 = pontuacao_franquia2;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public int getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getNomeFranquia1() {
        return nomeFranquia1;
    }

    public void setNomeFranquia1(String nomeFranquia1) {
        this.nomeFranquia1 = nomeFranquia1;
    }

    public String getNomeFranquia2() {
        return nomeFranquia2;
    }

    public void setNomeFranquia2(String nomeFranquia2) {
        this.nomeFranquia2 = nomeFranquia2;
    }

    public int getPontuacao_franquia1() {
        return pontuacao_franquia1;
    }

    public void setPontuacao_franquia1(int pontuacao_franquia1) {
        this.pontuacao_franquia1 = pontuacao_franquia1;
    }

    public int getPontuacao_franquia2() {
        return pontuacao_franquia2;
    }

    public void setPontuacao_franquia2(int pontuacao_franquia2) {
        this.pontuacao_franquia2 = pontuacao_franquia2;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
