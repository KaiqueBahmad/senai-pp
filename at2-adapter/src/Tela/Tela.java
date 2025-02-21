package Tela;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;
import java.util.Vector;

public class Tela extends JFrame {
    private JTextArea areaMensagens;
    private JTextField campoEntrada;
    private JComboBox<String> seletorTipo;
    private JTextField campoDelimitador;
    private Vector<String> mensagens;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaItens;
    
    public Tela() {
        mensagens = new Vector<>();
        setTitle("Sistema de Mensagens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());
        
        areaMensagens = new JTextArea();
        areaMensagens.setEditable(false);
        add(new JScrollPane(areaMensagens), BorderLayout.CENTER);
        
        JPanel painelEntrada = new JPanel(new BorderLayout());
        String[] tiposEntrada = {"Lista", "CSV", "Arquivo"};
        seletorTipo = new JComboBox<>(tiposEntrada);
        
        JPanel painelSeletor = new JPanel();
        painelSeletor.add(new JLabel("Tipo de entrada:"));
        painelSeletor.add(seletorTipo);
        
        JPanel painelCartoes = new JPanel(new CardLayout());
        
        modeloLista = new DefaultListModel<>();
        listaItens = new JList<>(modeloLista);
        campoEntrada = new JTextField();
        JButton botaoAdicionar = new JButton("Adicionar");
        JButton botaoRemover = new JButton("Remover");
        JButton botaoEnviarLista = new JButton("Enviar Lista");
        
        JPanel painelLista = new JPanel(new BorderLayout());
        JPanel painelControle = new JPanel(new BorderLayout());
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoEnviarLista);
        painelControle.add(campoEntrada, BorderLayout.CENTER);
        painelControle.add(painelBotoes, BorderLayout.EAST);
        painelLista.add(new JScrollPane(listaItens), BorderLayout.CENTER);
        painelLista.add(painelControle, BorderLayout.SOUTH);
        
        JPanel painelCSV = new JPanel(new BorderLayout());
        campoDelimitador = new JTextField(",", 3);
        JPanel painelDelimitador = new JPanel();
        painelDelimitador.add(new JLabel("Delimitador:"));
        painelDelimitador.add(campoDelimitador);
        JTextField entradaCSV = new JTextField();
        JButton botaoEnviarCSV = new JButton("Enviar CSV");
        JPanel painelEntradaCSV = new JPanel(new BorderLayout());
        painelEntradaCSV.add(entradaCSV, BorderLayout.CENTER);
        painelEntradaCSV.add(botaoEnviarCSV, BorderLayout.EAST);
        painelCSV.add(painelDelimitador, BorderLayout.NORTH);
        painelCSV.add(painelEntradaCSV, BorderLayout.SOUTH);
        
        JPanel painelArquivo = new JPanel(new BorderLayout());
        JTextField campoCaminho = new JTextField();
        campoCaminho.setEditable(false);
        JButton botaoEscolher = new JButton("Escolher Arquivo");
        JButton botaoEnviarArquivo = new JButton("Enviar Arquivo");
        JPanel painelBotoesArquivo = new JPanel();
        painelBotoesArquivo.add(botaoEscolher);
        painelBotoesArquivo.add(botaoEnviarArquivo);
        painelArquivo.add(campoCaminho, BorderLayout.CENTER);
        painelArquivo.add(painelBotoesArquivo, BorderLayout.EAST);
        
        painelCartoes.add(painelLista, "Lista");
        painelCartoes.add(painelCSV, "CSV");
        painelCartoes.add(painelArquivo, "Arquivo");
        
        painelEntrada.add(painelSeletor, BorderLayout.NORTH);
        painelEntrada.add(painelCartoes, BorderLayout.CENTER);
        add(painelEntrada, BorderLayout.SOUTH);
        
        botaoAdicionar.addActionListener(e -> {
            String texto = campoEntrada.getText().trim();
            if (!texto.isEmpty()) {
                modeloLista.addElement(texto);
                campoEntrada.setText("");
            }
        });
        
        botaoRemover.addActionListener(e -> {
            int indice = listaItens.getSelectedIndex();
            if (indice != -1) modeloLista.remove(indice);
        });
        
        botaoEnviarLista.addActionListener(e -> {
            if (modeloLista.getSize() > 0) {
                for (int i = 0; i < modeloLista.getSize(); i++) {
                    mensagens.add(modeloLista.getElementAt(i));
                }
                atualizarExibicao();
                modeloLista.clear();
            }
        });
        
        botaoEnviarCSV.addActionListener(e -> {
            String entrada = entradaCSV.getText().trim();
            if (!entrada.isEmpty()) {
                String[] valores = entrada.split(campoDelimitador.getText());
                for (String valor : valores) {
                    mensagens.add(valor.trim());
                }
                atualizarExibicao();
                entradaCSV.setText("");
            }
        });
        
        botaoEscolher.addActionListener(e -> {
            JFileChooser seletor = new JFileChooser();
            if (seletor.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                campoCaminho.setText(seletor.getSelectedFile().getAbsolutePath());
            }
        });
        
        botaoEnviarArquivo.addActionListener(e -> {
            if (!campoCaminho.getText().isEmpty()) {
                try {
                    String conteudo = new String(java.nio.file.Files.readAllBytes(Paths.get(campoCaminho.getText())));
                    String[] linhas = conteudo.split("\n");
                    for (String linha : linhas) {
                        mensagens.add(linha.trim());
                    }
                    atualizarExibicao();
                    campoCaminho.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao ler arquivo: " + ex.getMessage());
                }
            }
        });
        
        seletorTipo.addActionListener(e -> {
            CardLayout cl = (CardLayout) painelCartoes.getLayout();
            cl.show(painelCartoes, (String) seletorTipo.getSelectedItem());
        });
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void atualizarExibicao() {
        StringBuilder exibicao = new StringBuilder();
        for (String msg : mensagens) {
            exibicao.append(msg).append("\n");
        }
        areaMensagens.setText(exibicao.toString());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tela());
    }
}