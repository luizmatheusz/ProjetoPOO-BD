package org.example;

import org.example.Classes.Jogadores;
import org.example.Classes.Jogos;
import org.example.DAO.FranquiasDAO;
import org.example.DAO.JogadoresDAO;
import org.example.DAO.JogosDAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // menu
        boolean flag = true;
        Scanner in = new Scanner(System.in);
        int menu;

        // integração com BD
        JogadoresDAO jogadoresDAO = new JogadoresDAO();
        JogosDAO jogosDAO = new JogosDAO();
        FranquiasDAO franquiasDAO = new FranquiasDAO();

        // menu
        while (flag){
            System.out.println("========= MENU =========");
            System.out.println("1. Cadastrar jogador");
            System.out.println("2. Cadastrar jogo");
            System.out.println("3. Transferir jogador");
            System.out.println("4. Aposentar jogador");
            System.out.println("5. Obter a lista de jogadores na NBA");
            System.out.println("6. Obter a lista de franquias na NBA");
            System.out.println("7. Sair");

            System.out.print("Digite sua escolha: ");
            menu = in.nextInt();
            in.nextLine();

            switch (menu){
                // cadastro de jogadores
                case 1:
                    String nome, identificacao, posicao, sigla_posicao, data_nasc, pais, nomeFranquia, jogaFranquia;

                    System.out.println("========= Cadastro de jogador =========");
                    System.out.print("Nome: ");
                    nome = in.nextLine();
                    System.out.print("Identificação: ");
                    identificacao = in.nextLine();
                    System.out.print("Posição: ");
                    posicao = in.nextLine();
                    System.out.print("Sigla posição: ");
                    sigla_posicao = in.nextLine();
                    System.out.print("Data de nascimento: ");
                    data_nasc = in.nextLine();
                    System.out.print("País: ");
                    pais = in.nextLine();
                    System.out.print("Joga por alguma franquia? (S/N): ");
                    jogaFranquia = in.nextLine();

                    if(jogaFranquia.equals("S")){
                        System.out.print("Nome da franquia: ");
                        nomeFranquia = in.nextLine();
                    }

                    else{
                        nomeFranquia = null;
                    }

                    Jogadores j = new Jogadores(nome,identificacao,data_nasc,pais,posicao,sigla_posicao,nomeFranquia);
                    jogadoresDAO.insertJogador(j);
                    break;

                // cadastro de jogos
                case 2:
                    int idArbitro, pontuacao_franquia1, pontuacao_franquia2;
                    String fase, nomeFranquia1, nomeFranquia2, data;

                    System.out.println("========= Cadastro de jogo =========");
                    System.out.print("ID do árbitro: ");
                    idArbitro = in.nextInt();
                    in.nextLine();
                    System.out.print("Fase: ");
                    fase = in.nextLine();
                    System.out.print("Nome da franquia 1: ");
                    nomeFranquia1 = in.nextLine();
                    System.out.print("Pontuacão da franquia 1: ");
                    pontuacao_franquia1 = in.nextInt();
                    in.nextLine();
                    System.out.print("Nome da franquia 2: ");
                    nomeFranquia2 = in.nextLine();
                    System.out.print("Pontuação da franquia 2: ");
                    pontuacao_franquia2 = in.nextInt();
                    in.nextLine();
                    System.out.print("Data: ");
                    data = in.nextLine();

                    Jogos jogo = new Jogos(idArbitro, fase, nomeFranquia1, nomeFranquia2, pontuacao_franquia1, pontuacao_franquia2,data);
                    jogosDAO.insertJogo(jogo);
                    break;

                // transferir jogador
                case 3:
                    String franquiaTransferencia;
                    int idJogador;
                    System.out.println("========= Transferência de jogador =========");
                    System.out.print("Nome da franquia: ");
                    franquiaTransferencia = in.nextLine();
                    System.out.print("ID do jogador: ");
                    idJogador = in.nextInt();
                    in.nextLine();
                    jogadoresDAO.updateJogador(franquiaTransferencia,idJogador);
                    System.out.println("");
                    break;

                // aposentar jogador
                case 4:
                    int idJogadorAposentadoria;

                    System.out.println("========= Aposentadoria de jogador =========");
                    System.out.print("ID do jogador: ");
                    idJogadorAposentadoria = in.nextInt();
                    in.nextLine();

                    jogadoresDAO.deleteJogador(idJogadorAposentadoria);
                    System.out.println("");
                    break;

                // lista de jogadores
                case 5:
                    jogadoresDAO.listaJogadores();
                    break;

                // lista de franquias
                case 6:
                    franquiasDAO.listaFranquias();
                    break;

                // sair
                case 7:
                    System.out.println("Saindo...");
                    flag = false;
                    break;
            }
        }

        in.close();
    }
}