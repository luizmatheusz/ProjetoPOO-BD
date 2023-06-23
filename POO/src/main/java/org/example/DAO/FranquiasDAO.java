package org.example.DAO;


import java.sql.SQLException;

public class FranquiasDAO extends ConnectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    public void listaFranquias() {
        connectToDB();
        String sql = "SELECT nome, conferencia, nJogos, vitorias, derrotas, saldoPontos FROM Franquias";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("========= Lista de franquias =========");
            while (rs.next()) {
                int nJogos, vitorias, derrotas, saldoPontos;
                String nome, conferencia;
                nome = rs.getString("nome");
                conferencia = rs.getString("conferencia");
                nJogos = rs.getInt("nJogos");
                vitorias = rs.getInt("vitorias");
                derrotas = rs.getInt("derrotas");
                saldoPontos = rs.getInt("saldoPontos");

                System.out.println(nome + " (" + conferencia + "), " + nJogos + " jogos (" + vitorias + "V/" + derrotas + "D), " + saldoPontos + " saldo de Pontos.");
            }
            System.out.println("");
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}