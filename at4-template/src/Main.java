import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Main {
    static JFrame frame;
    static JButton btnOrdenar;
    static JTable tabela;
    static DefaultTableModel modeloTabela;
    static LeitorBruto leitorBruto = new LeitorBruto();
    static JComboBox<String> comboOrdenacao;
    static List<Aluno> listaAlunos = new ArrayList<>();

    public static void main(String[] args) {
        frame = new JFrame("Relatório das Ênfases");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel painelControle = new JPanel(new FlowLayout(FlowLayout.LEFT));

        String[] opcoesOrdenacao = {
            "Por Nome",
            "Por Sobrenome",
            "Por Situação e Nome",
            "Por Curso e Nome",
            "Por Ênfase e Nome",
            "Por Curso, Ênfase e Nome",
            "Por Ênfase, Curso e Nome"
        };
        comboOrdenacao = new JComboBox<>(opcoesOrdenacao);
        painelControle.add(comboOrdenacao);

        btnOrdenar = new JButton("Ordenar");
        painelControle.add(btnOrdenar);

        String[] colunas = {"Nome", "Curso", "Situação", "Ênfase"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);

        tabela.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tabela.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tabela);

        frame.add(painelControle, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        JLabel statusLabel = new JLabel("");
        frame.add(statusLabel, BorderLayout.SOUTH);

        String[] rawData = leitorBruto.lerArquivo("./src/RelatorioDasEnfases.csv");
        
        carregarAlunos(rawData);
        String opcaoSelecionada = (String) comboOrdenacao.getSelectedItem();
        ordenarAlunos(opcaoSelecionada);
        atualizarTabela();
        statusLabel.setText(listaAlunos.size() + "");

        btnOrdenar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String opcaoSelecionada = (String) comboOrdenacao.getSelectedItem();
                ordenarAlunos(opcaoSelecionada);
                atualizarTabela();
                statusLabel.setText(listaAlunos.size() + "");
            }
        });

        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });
    }

    static void carregarAlunos(String[] rawData) {
        listaAlunos.clear();

        for (String linha : rawData) {
            linha = linha.replaceAll("^\\[|\\]$", "");
            String[] entradas = linha.split(", ");

            for (String entrada : entradas) {
                if (!entrada.startsWith("Nome")) {
                    String[] campos = entrada.split(";");
                    if (campos.length >= 4) {
                        Aluno aluno = new Aluno(campos);
                        listaAlunos.add(aluno);
                    }
                }
            }
        }
    }

    static void ordenarAlunos(String opcaoOrdenacao) {
        Collections.sort(listaAlunos, Ordenadores.getComparator(opcaoOrdenacao));
    }

    static void atualizarTabela() {
        modeloTabela.setRowCount(0); // isso aqui é pra limpar a lista e nao ficar readicionando
        for (Aluno aluno : listaAlunos) {
            modeloTabela.addRow(aluno.toVector());
        }
    }
}