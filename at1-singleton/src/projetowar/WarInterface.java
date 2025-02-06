package projetowar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import projetowar.classes.*;

public class WarInterface extends JFrame {
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private Tabuleiro tabuleiro = new Tabuleiro();
    private JPanel painelJogadores;
    private JLabel labelContador;
    private JTextField campoNome;
    
    public WarInterface() {
        setTitle("War");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel painel = new JPanel();
        
        campoNome = new JTextField(15);
        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(e -> adicionarJogador());

        painelJogadores = new JPanel();
        painelJogadores.setLayout(new GridLayout(0, 1));
        JScrollPane scroll = new JScrollPane(painelJogadores);
        scroll.setPreferredSize(new Dimension(250, 200));
        
        labelContador = new JLabel("0/6");
        
        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);
        painel.add(botaoAdicionar);
        painel.add(scroll);
        painel.add(labelContador);
        
        add(painel);
        setVisible(true);
    }
    
    private void adicionarJogador() {
        if (jogadores.size() >= 6) {
            JOptionPane.showMessageDialog(null, "Máximo de jogadores!");
            return;
        }
        
        String nome = campoNome.getText();
        if (!nome.isEmpty()) {
            Jogador j = new Jogador(nome, tabuleiro);
            jogadores.add(j);
            
            JPanel p = new JPanel();
            p.add(new JLabel(nome));
            JButton remover = new JButton("X");
            remover.addActionListener(e -> {
                jogadores.remove(j);
                painelJogadores.remove(p);
                labelContador.setText(jogadores.size() + "/6");
            });
            p.add(remover);
            
            painelJogadores.add(p);
            painelJogadores.revalidate();
            campoNome.setText("");
            labelContador.setText(jogadores.size() + "/6");
        }
    }
    
    public static void main(String[] args) {
        new WarInterface();
    }
}