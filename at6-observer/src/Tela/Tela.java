package Tela;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import classes.entities.CEO;
import classes.entities.Gerente;
import classes.entities.Vendedor;
import observer.VendasObserver;
import classes.entities.Produto;
import classes.entities.Venda;

public class Tela {
    static CEO ceo;
    static List<Gerente> gerentes;
    static List<Vendedor> vendedores;
    static List<Produto> produtos;
    static VendasObserver vendasObserver = new VendasObserver();
    public static void setup() {
        Tela.ceo = new CEO("Sofia Mendes");

        Tela.gerentes = new ArrayList<>();
        gerentes.add(new Gerente(ceo, "Lucas Oliveira"));
        gerentes.add(new Gerente(ceo, "Beatriz Almeida"));
        gerentes.add(new Gerente(ceo, "Gabriel Santos"));
        gerentes.add(new Gerente(ceo, "Carolina Costa"));

        Tela.vendedores = new ArrayList<>();
        vendedores.add(new Vendedor(gerentes.get(0), "Mateo Fernandez"));
        vendedores.add(new Vendedor(gerentes.get(0), "Rafael Silva"));
        vendedores.add(new Vendedor(gerentes.get(1), "Mariana Pereira"));
        vendedores.add(new Vendedor(gerentes.get(1), "Daniel Rodrigues"));
        vendedores.add(new Vendedor(gerentes.get(2), "Isabela Martins"));
        vendedores.add(new Vendedor(gerentes.get(2), "Tomás Ribeiro"));
        vendedores.add(new Vendedor(gerentes.get(3), "Valentina Carvalho"));
        vendedores.add(new Vendedor(gerentes.get(3), "Guilherme Sousa"));
        vendedores.add(new Vendedor(gerentes.get(3), "Camila Ferreira"));
        vendedores.add(new Vendedor(gerentes.get(0), "Pedro Nunes"));
        
        Tela.produtos = new ArrayList<>();
        produtos.add(new Produto(1500.0, 900.0, "Smartphone X1"));
        produtos.add(new Produto(3200.0, 2100.0, "Notebook Pro"));
        produtos.add(new Produto(450.0, 280.0, "Fone de Ouvido Premium"));
        produtos.add(new Produto(2800.0, 1700.0, "Smart TV 4K"));
        
        vendasObserver.subscribe(ceo);
        gerentes.forEach(vendasObserver::subscribe);
        vendedores.forEach(vendasObserver::subscribe);
    }

    public static void main(String[] args) {
        setup();
        
        JFrame frame = new JFrame("Sistema de Gestão");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JPanel vendedorPanel = new JPanel();
        vendedorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        vendedorPanel.add(new JLabel("Vendedor:"));
        
        JComboBox<String> vendedorCombo = new JComboBox<>();
        vendedorCombo.addItem("Selecione um vendedor");
        for (Vendedor v : vendedores) {
            vendedorCombo.addItem(v.getNome());
        }
        vendedorPanel.add(vendedorCombo);
        
        JPanel vendasPanel = new JPanel();
        vendasPanel.setBorder(BorderFactory.createTitledBorder("Vendas"));
        vendasPanel.setLayout(new GridLayout(3, 1, 0, 5));
        
        JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linha1.add(new JLabel("Selecione um Produto:"));
        
        JComboBox<String> produtoCombo = new JComboBox<>();
        produtoCombo.addItem("Selecione um produto");
        for (Produto p : produtos) {
            produtoCombo.addItem(p.getNome());
        }
        linha1.add(produtoCombo);
        
        linha1.add(new JLabel("Quantidade:"));
        
        JComboBox<Integer> quantidadeCombo = new JComboBox<>();
        quantidadeCombo.addItem(0);
        for (int i = 1; i <= 10; i++) {
            quantidadeCombo.addItem(i);
        }
        quantidadeCombo.setEnabled(false);
        linha1.add(quantidadeCombo);
        
        JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        linha2.add(new JLabel("Preço Unitário:"));
        JTextField precoUnitario = new JTextField(8);
        precoUnitario.setEditable(false);
        linha2.add(precoUnitario);
        
        linha2.add(new JLabel("Total:"));
        JTextField precoTotal = new JTextField(8);
        precoTotal.setEditable(false);
        linha2.add(precoTotal);
        
        JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnEfetuarVenda = new JButton("Efetuar Venda");
        linha3.add(btnEfetuarVenda);
        
        produtoCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = produtoCombo.getSelectedIndex();
                
                quantidadeCombo.setSelectedIndex(0);
                
                if (indice > 0) {
                    quantidadeCombo.setEnabled(true);
                    
                    Produto produtoSelecionado = produtos.get(indice - 1);
                    precoUnitario.setText(String.format("R$ %.2f", produtoSelecionado.getPreco()));
                    
                    precoTotal.setText("R$ 0,00");
                } else {
                    quantidadeCombo.setEnabled(false);
                    precoUnitario.setText("");
                    precoTotal.setText("");
                }
            }
        });
        
        quantidadeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceProduto = produtoCombo.getSelectedIndex();
                
                if (indiceProduto > 0) {
                    Produto produtoSelecionado = produtos.get(indiceProduto - 1);
                    int quantidade = (Integer) quantidadeCombo.getSelectedItem();
                    
                    double total = produtoSelecionado.getPreco() * quantidade;
                    precoTotal.setText(String.format("R$ %.2f", total));
                }
            }
        });
        
        btnEfetuarVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceVendedor = vendedorCombo.getSelectedIndex();
                int indiceProduto = produtoCombo.getSelectedIndex();
                Integer quantidade = (Integer) quantidadeCombo.getSelectedItem();
                if (indiceVendedor <= 0 || indiceProduto <= 0 || quantidade <= 0) {
                    JOptionPane.showMessageDialog(frame, 
                        "Por favor, selecione um vendedor, um produto e uma quantidade maior que zero!", 
                        "Dados incompletos", 
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                
                Vendedor vendedorSelecionado = vendedores.get(indiceVendedor - 1);
                Produto produtoSelecionado = produtos.get(indiceProduto - 1);
                
                Venda venda = new Venda();
                venda.setVendedor(vendedorSelecionado);
                venda.setProduto(produtoSelecionado);
                venda.setQuantidade(quantidade);
                
                System.out.println(vendedorSelecionado.getNome());
                
                vendedorCombo.setSelectedIndex(0);
                produtoCombo.setSelectedIndex(0);
                quantidadeCombo.setSelectedIndex(0);
                quantidadeCombo.setEnabled(false);
                precoUnitario.setText("");
                precoTotal.setText("");
                vendasObserver.notify(venda);
                JOptionPane.showMessageDialog(frame, "Venda efetuada com sucesso!");
            }
        });
        
        vendasPanel.add(linha1);
        vendasPanel.add(linha2);
        vendasPanel.add(linha3);
        
        JPanel hierarquiaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnMostrarHierarquia = new JButton("Mostrar Hierarquia");
        hierarquiaPanel.add(btnMostrarHierarquia);
        
        btnMostrarHierarquia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaHierarquia();
            }
        });
        
        JPanel conteudoPanel = new JPanel();
        conteudoPanel.setLayout(new BorderLayout());
        conteudoPanel.add(vendedorPanel, BorderLayout.NORTH);
        conteudoPanel.add(vendasPanel, BorderLayout.CENTER);
        conteudoPanel.add(hierarquiaPanel, BorderLayout.SOUTH);
        
        panel.add(conteudoPanel, BorderLayout.CENTER);
        frame.add(panel);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private static void mostrarTelaHierarquia() {
        JFrame frame = new JFrame("Hierarquia");
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        
        StringBuilder sb = new StringBuilder();
        sb.append("CEO: ").append(String.format("%s (%.2f)", ceo.getNome(), ceo.getaReceber())).append("\n\n");
        
        for (Gerente gerente : gerentes) {
            sb.append("Gerente: ").append(String.format("%s (%.2f)", gerente.getNome(), gerente.getaReceber())).append("\n");
            
            for (Vendedor vendedor : vendedores) {
                if (vendedor.getChefe() == gerente) {
                    sb.append("  └─ ").append(String.format("%s (%.2f)", vendedor.getNome(), vendedor.getaReceber())).append("\n");
                }
            }
            sb.append("\n");
        }
        
        textArea.setText(sb.toString());
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}