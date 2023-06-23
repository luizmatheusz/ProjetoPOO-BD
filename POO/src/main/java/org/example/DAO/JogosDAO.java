package org.example.DAO;


import org.example.Classes.Jogos;

import java.sql.SQLException;

public class JogosDAO extends ConnectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou
    //INSERT
    public boolean insertJogo(Jogos jogo) {
        connectToDB();
        String sql = "INSERT INTO Jogos VALUES(default, ?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, jogo.getIdArbitro());
            pst.setString(2, jogo.getFase());
            pst.setString(3, jogo.getNomeFranquia1());
            pst.setString(4, jogo.getNomeFranquia2());
            pst.setInt(5, jogo.getPontuacao_franquia1());
            pst.setInt(6, jogo.getPontuacao_franquia2());
            pst.setString(7, jogo.getData());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
    /*
    public ArrayList<Jogadores> selectUser() {
        ArrayList<Jogadores> users = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Jogadores";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Jogadores: ");
            while (rs.next()) {
                Jogadores jogadorAux = new Jogadores(rs.getString("nome"),rs.getString("cpf"));
                System.out.println("nome = " + jogadorAux.getNome());
                System.out.println("cpf = " + userAux.getCpf());
                System.out.println("--------------------------------");
                users.add(userAux);
            }
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
        return users;
    }*/
}