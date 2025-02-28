import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entidade.Piloto;
import leitores.Leitor;
import leitores.LeitorArrayDeque;
import leitores.LeitorBruto;
import leitores.LeitorConcurrentSkipList;
import leitores.LeitorHashMap;
import leitores.LeitorPriorityQueue;
import leitores.LeitorTreeSet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class Tela extends JFrame {
    
    private JComboBox<String> comboEstrutura;
    private JButton botaoLer;
    private JTable tabelaPilotos;
    private DefaultTableModel modelo;
    
    public Tela() {
        setTitle("Sistema de Pilotos");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel painelControle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] estruturas = ESTRUTURAS.keySet().toArray(new String[0]);
        comboEstrutura = new JComboBox<>(estruturas);
        botaoLer = new JButton("Ler Pilotos");
        
        painelControle.add(new JLabel("Estrutura:"));
        painelControle.add(comboEstrutura);
        painelControle.add(botaoLer);
        
        add(painelControle, BorderLayout.NORTH);
        
        String[] colunas = {"ID", "Nome", "Nacionalidade", "Equipe", "Motor", "Pontos"};
        modelo = new DefaultTableModel(colunas, 0);
        tabelaPilotos = new JTable(modelo);
        
        add(new JScrollPane(tabelaPilotos), BorderLayout.CENTER);
        
        botaoLer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarPilotos();
            }
        });
    }
    
    private static Map<String, Class<?>> ESTRUTURAS = new HashMap<String, Class<?>>() {{
        put("HashMap", HashMap.class);
        put("TreeSet", TreeSet.class);
        put("PriorityQueue", PriorityQueue.class);
        put("ArrayDeque", ArrayDeque.class);
    }};

    private static Map<Class<?>, Leitor> LEITORES = new HashMap<Class<?>, Leitor>() {{
        put(HashMap.class, new LeitorHashMap());
        put(TreeSet.class, new LeitorTreeSet());
        put(PriorityQueue.class, new LeitorPriorityQueue());
        put(ArrayDeque.class, new LeitorArrayDeque());
    }};
    
	private void carregarPilotos() {
        modelo.setRowCount(0);
    	Class<?> classeAlvo = ESTRUTURAS.get((String)comboEstrutura.getSelectedItem());
    	Leitor leitor = LEITORES.get(classeAlvo);
    	LeitorBruto leitorBruto = new LeitorBruto();
    	String[] rawData = leitorBruto.lerArquivo("./src/dadosPilotos.csv");
    	Iterator<Piloto> iterator = leitor.toIterator(rawData);
    	while (iterator.hasNext()) {
    		modelo.addRow(iterator.next().toVector());
    	}
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }
}