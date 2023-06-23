package org.example.DAO;


import org.example.Classes.Jogadores;

import java.sql.SQLException;

public class JogadoresDAO extends ConnectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou
    //INSERT
    public boolean insertJogador(Jogadores jogador) {
        connectToDB();
        String sql = "INSERT INTO Jogadores (nome, identificacao, posicao, sigla_posicao, data_nasc, pais, nomeFranquia) VALUES(?,?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, jogador.getNome());
            pst.setString(2, jogador.getIdentificacao());
            pst.setString(3, jogador.getPosicao());
            pst.setString(4, jogador.getSigla_posicao());
            pst.setString(5, jogador.getData_nasc());
            pst.setString(6, jogador.getPais());
            pst.setString(7, jogador.getNomeFranquia());
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
    //UPDATE
    public boolean updateJogador(String nomeFranquia, int id) {
        connectToDB();
        String sql = "UPDATE Jogadores SET nomeFranquia=? where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeFranquia);
            pst.setInt(2, id);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
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
    //DELETE
    public boolean deleteJogador(int id) {
        connectToDB();
        String sql = "DELETE FROM Jogadores where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
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

    public void listaJogadores() {
        connectToDB();
        String sql = "SELECT id, nome, sigla_posicao, idade, pais, nomeFranquia FROM Jogadores";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("========= Lista de jogadores =========");
            while (rs.next()) {
                int id, idade;
                String nome, sigla_posicao, pais, nomeFranquia;
                id = rs.getInt("id");
                nome = rs.getString("nome");
                sigla_posicao = rs.getString("sigla_posicao");
                idade = rs.getInt("idade");
                pais = rs.getString("pais");
                nomeFranquia = rs.getString("nomeFranquia");

                if(nomeFranquia == null){
                    nomeFranquia = "Sem time";
                }

                System.out.println(id + ". " + nome + "[" + sigla_posicao + "], " + idade + " anos, " + pais + ", " + nomeFranquia + ".");
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