package Tela;

import javax.swing.*;

import classes.adapter.ListRequestAdapter;
import classes.adapter.CSVRequestAdapter;
import classes.adapter.FileRequestAdapter;

import classes.persistence.Atividade;
import classes.persistence.AtividadeBox;
import classes.persistence.NewMessageRequest;
import classes.request.CSVRequest;
import classes.request.FileEnterSeparatedRequest;
import classes.request.ListRequest;
import java.util.List;
import java.awt.*;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Vector;

public class Tela extends JFrame {
    private JTextField campoEntrada;
    private JComboBox<String> seletorTipo;
    private JTextField campoDelimitador;
    private AtividadeBox atividadeBox;
    
    private DefaultListModel<String> modeloLista;
    private JList<String> listaItens;
    private ListRequestAdapter adapter;
    private JPanel painelTarefas;
    private JScrollPane scrollTarefas;
    
    public Tela() {
        atividadeBox = new AtividadeBox();
        setTitle("Gerenciador de Atividades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());
        
        JLabel aviso = new JLabel("Para definir a prioridade use !1 para baixa, !2 média, !3 alta. No final da mensagem", SwingConstants.CENTER);
        add(aviso, BorderLayout.NORTH);
        
        painelTarefas = new JPanel();
        painelTarefas.setLayout(new BoxLayout(painelTarefas, BoxLayout.Y_AXIS));
        scrollTarefas = new JScrollPane(painelTarefas);
        add(scrollTarefas, BorderLayout.CENTER);
        
        JPanel painelEntrada = new JPanel(new BorderLayout());
        String[] tiposEntrada = {"Lista", "CSV", "Arquivo"};
        seletorTipo = new JComboBox<>(tiposEntrada);
        
        JPanel painelSeletor = new JPanel();
        painelSeletor.add(new JLabel("Tipo de entrada:"));
        painelSeletor.add(seletorTipo);
        
        JPanel painelTipoEntrada = new JPanel();
        painelTipoEntrada.add(new JLabel("Tipo de entrada:"));
        painelTipoEntrada.add(seletorTipo);

        painelSeletor.add(painelTipoEntrada);
        
        JPanel painelCartoes = new JPanel(new CardLayout());
        
        modeloLista = new DefaultListModel<>();
        listaItens = new JList<>(modeloLista);
        campoEntrada = new JTextField();
        JButton botaoAdicionar = new JButton("Adicionar");
        JButton botaoEnviarLista = new JButton("Enviar Lista");
        
        JPanel painelLista = new JPanel(new BorderLayout());
        JPanel painelControle = new JPanel(new BorderLayout());
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(botaoAdicionar);
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
        
        botaoEnviarLista.addActionListener(e -> {
            if (modeloLista.getSize() > 0) {
                ListRequest listRequest = new ListRequest();
                List<String> msgs = new LinkedList<>();
                for (int i = 0; i < modeloLista.getSize(); i++) {
                    msgs.add(modeloLista.getElementAt(i));
                }
                listRequest.setMensagens(msgs);

                NewMessageRequest request = new ListRequestAdapter(listRequest);
            	System.out.println(request.getAtividades().size());

                atividadeBox.addMensagens(request);
                atualizarExibicao();
                modeloLista.clear();
            }
        });
        
        botaoEnviarCSV.addActionListener(e -> {
            String entrada = entradaCSV.getText().trim();
            if (!entrada.isEmpty()) {
                CSVRequest csvRequest = new CSVRequest();
                csvRequest.setContent(entrada);
                csvRequest.setDelimiter(campoDelimitador.getText());
                
            	NewMessageRequest request = new CSVRequestAdapter(csvRequest);
                atividadeBox.addMensagens(request);
                
                atualizarExibicao();
                entradaCSV.setText("");
            }
        });
        
        botaoEnviarArquivo.addActionListener(e -> {
            if (!campoCaminho.getText().isEmpty()) {
                try {
                    FileEnterSeparatedRequest fileRequest = new FileEnterSeparatedRequest();
                    fileRequest.setPath(Paths.get(campoCaminho.getText()));
                    
                    NewMessageRequest request = new FileRequestAdapter(fileRequest);
                    atividadeBox.addMensagens(request);
                    
                    atualizarExibicao();
                    campoCaminho.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao ler arquivo: " + ex.getMessage());
                }
            }
        });
        
        botaoEscolher.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                campoCaminho.setText(fileChooser.getSelectedFile().getAbsolutePath());
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
        painelTarefas.removeAll();
        
        for (Atividade msg : atividadeBox.getMensagens()) {
            JPanel painelMensagem = new JPanel(new BorderLayout());
            JCheckBox checkbox = new JCheckBox();
            
            checkbox.setSelected(msg.isConcluida());
            
            checkbox.addActionListener(e -> {
                msg.setConcluida(checkbox.isSelected());
            });
            
            String prioridadeText = switch (msg.getPrioridade()) {
                case ALTA -> "[ALTA] ";
                case MEDIA -> "[MÉDIA] ";
                case BAIXA -> "[BAIXA] ";
            };
            
            JLabel labelMensagem = new JLabel(prioridadeText + msg.getConteudo());
            
            painelMensagem.add(checkbox, BorderLayout.WEST);
            painelMensagem.add(labelMensagem, BorderLayout.CENTER);
            
            painelMensagem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
            painelMensagem.setMaximumSize(new Dimension(Integer.MAX_VALUE, painelMensagem.getPreferredSize().height));
            
            painelTarefas.add(painelMensagem);
        }
        painelTarefas.revalidate();
        painelTarefas.repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tela());
    }
}