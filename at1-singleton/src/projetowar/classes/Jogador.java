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
public class Jogador {
    private String nome = "";
    private Tabuleiro tabuleiro = null;
    
    public Jogador(String nome, Tabuleiro jogo){
        this.nome = nome;
        tabuleiro = jogo;
        jogo.setQuantidadeDeJogadores();
    }
    public String descricao(){
        String saida = "\n\nDados do Jogador \n";
        saida += "Nome: " + nome + "\n";
        saida += "Tabuleiro: " + tabuleiro.getIdentificador() + "\n";
        saida += "Quantidade de Jogadores: " + tabuleiro.getQuantidadeDeJogadores() + "\n";
        saida += "Codigo: " + tabuleiro.hashCode();
        return saida;
    }
    
}
