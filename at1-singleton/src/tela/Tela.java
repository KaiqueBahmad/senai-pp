package tela;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import classes.*;

public class Tela extends JFrame {
    private JTextField txtNome;
    private JButton btnCadastrar;
    private JList<Usuario> listaUsuarios;
    private DefaultListModel<Usuario> modelUsuarios;
    private JTextArea txtMensagem;
    private JButton btnEnviar;
    private JTextArea txtHistorico;
    private Usuario usuarioSelecionado;
    
    public Tela() {
        setTitle("Sistema de Chat");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel painelEsquerdo = new JPanel(new BorderLayout(5, 5));
        JPanel painelCadastro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        txtNome = new JTextField(15);
        btnCadastrar = new JButton("Cadastrar");
        painelCadastro.add(new JLabel("Nome: "));
        painelCadastro.add(txtNome);
        painelCadastro.add(btnCadastrar);
        
        modelUsuarios = new DefaultListModel<>();
        listaUsuarios = new JList<>(modelUsuarios);
        listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        painelEsquerdo.add(painelCadastro, BorderLayout.NORTH);
        painelEsquerdo.add(new JScrollPane(listaUsuarios), BorderLayout.CENTER);
        
        JPanel painelDireito = new JPanel(new BorderLayout(5, 5));
        
        txtMensagem = new JTextArea(3, 30);
        btnEnviar = new JButton("Enviar");
        JPanel painelEnvio = new JPanel(new BorderLayout(5, 5));
        painelEnvio.add(new JScrollPane(txtMensagem), BorderLayout.CENTER);
        painelEnvio.add(btnEnviar, BorderLayout.EAST);
        
        txtHistorico = new JTextArea(20, 30);
        txtHistorico.setEditable(false);
        
        painelDireito.add(painelEnvio, BorderLayout.SOUTH);
        painelDireito.add(new JScrollPane(txtHistorico), BorderLayout.CENTER);
        
        add(painelEsquerdo, BorderLayout.WEST);
        add(painelDireito, BorderLayout.CENTER);
        
        btnCadastrar.addActionListener(e -> cadastrarUsuario());
        btnEnviar.addActionListener(e -> enviarMensagem());
        listaUsuarios.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                usuarioSelecionado = listaUsuarios.getSelectedValue();
                atualizarBotaoEnviar();
            }
        });
        
        atualizarBotaoEnviar();
        atualizarHistorico();
    }
    
    private void cadastrarUsuario() {
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um nome.");
            return;
        }
        
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        Chat.getInstance().getUsuarios().add(usuario);
        modelUsuarios.addElement(usuario);
        
        txtNome.setText("");
        txtNome.requestFocus();
    }
    
    private void enviarMensagem() {
        if (usuarioSelecionado == null || txtMensagem.getText().trim().isEmpty()) {
            return;
        }
        
        Mensagem mensagem = new Mensagem();
        mensagem.setUsuario(usuarioSelecionado);
        mensagem.setConteudo(txtMensagem.getText().trim());
        
        Chat.getInstance().getMensagems().add(mensagem);
        
        txtMensagem.setText("");
        atualizarHistorico();
    }
    
    private void atualizarHistorico() {
        StringBuilder sb = new StringBuilder();
        for (Mensagem msg : Chat.getInstance().getMensagems()) {
            sb.append(msg.getUsuario().getNome())
              .append(": ")
              .append(msg.getConteudo())
              .append("\n");
        }
        txtHistorico.setText(sb.toString());
    }
    
    private void atualizarBotaoEnviar() {
        btnEnviar.setEnabled(usuarioSelecionado != null);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tela tela = new Tela();
            tela.setVisible(true);
        });
    }
}