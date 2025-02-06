/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetowar.classes;

/**
 *
 * @author eugeniojulio
 */
public class Tabuleiro {
    private String identificador = "JOGO DE ESTRAGÉGIA EM TABULEIRO - WAR";
    private int quantidadeDeJogadores = 0;
    
    public  Tabuleiro(){
    }
    public String getIdentificador(){return identificador;}

    public int getQuantidadeDeJogadores() {
        return quantidadeDeJogadores;
    }

    public void setQuantidadeDeJogadores() {
        this.quantidadeDeJogadores++;
    }
    
}
