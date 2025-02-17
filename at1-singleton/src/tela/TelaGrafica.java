package tela;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

import classes.predio.Andar;
import classes.predio.Elevador;
import classes.predio.Predio;

public class TelaGrafica extends JFrame {
    private static final int ALTURA_ANDAR = 60;
    private static final int LARGURA_PREDIO = 300;
    private static final int LARGURA_ELEVADOR = 50;
    private static final int MARGEM = 20;
    private static final int BOTAO_ALTURA = 30;
    private static final int BOTAO_LARGURA = 70;
    
    private final JLayeredPane layeredPane;
    private final JPanel painelPredio;
    private final int numAndares;
    private final JButton[] botoes;
    private Timer updateTimer;
    
    public TelaGrafica(int numAndares) {
        this.numAndares = numAndares;
        this.botoes = new JButton[numAndares];
        
        setTitle("Elevador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        int larguraTotal = LARGURA_PREDIO + (2 * MARGEM);
        int alturaTotal = (ALTURA_ANDAR * numAndares) + (2 * MARGEM);
        
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(larguraTotal, alturaTotal));
        
        painelPredio = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                desenharPredio((Graphics2D) g);
            }
        };

        painelPredio.setBounds(0, 0, larguraTotal, alturaTotal);
        painelPredio.setOpaque(true);
        layeredPane.add(painelPredio, JLayeredPane.DEFAULT_LAYER);
        
        for (int i = 0; i < numAndares; i++) {
            JButton botao = new JButton("Chamar");
            botao.setFont(new Font("Arial", Font.BOLD, 12));
            botao.setMargin(new Insets(0, 0, 0, 0));
            
            int y = MARGEM + (ALTURA_ANDAR * (numAndares - 1 - i)) + ((ALTURA_ANDAR - BOTAO_ALTURA) / 2);
            int x = MARGEM + LARGURA_PREDIO - LARGURA_ELEVADOR - BOTAO_LARGURA - 10;
            botao.setBounds(x, y, BOTAO_LARGURA, BOTAO_ALTURA);
            
            final int andar = i;
            botao.addActionListener(e -> {
                Andar.getInstance(andar).chamarElevador();
                atualizarBotoesChamando();
            });
            
            botoes[i] = botao;
            layeredPane.add(botao, JLayeredPane.PALETTE_LAYER);
        }
        
        add(layeredPane);
        
        updateTimer = new Timer(50, e -> {
            painelPredio.repaint();
            atualizarBotoesChamando();
        });
        updateTimer.start();
        
        pack();
    }
    
    private void atualizarBotoesChamando() {
        Elevador elevador = Elevador.getInstance();
        Set<Andar> chamando = elevador.getChamando();
        
        for (int i = 0; i < botoes.length; i++) {
            JButton botao = botoes[i];
            if (chamando.contains(Andar.getInstance(i))) {
                botao.setEnabled(false);
                botao.setBackground(Color.YELLOW);
            } else {
                botao.setEnabled(true);
                botao.setBackground(null);
            }
        }
    }
    
    private void desenharPredio(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (int i = 0; i < numAndares; i++) {
            int y = MARGEM + (ALTURA_ANDAR * (numAndares - 1 - i));
            
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(MARGEM, y, LARGURA_PREDIO - (2 * MARGEM), ALTURA_ANDAR);
            g.setColor(Color.BLACK);
            g.drawRect(MARGEM, y, LARGURA_PREDIO - (2 * MARGEM), ALTURA_ANDAR);
            
            g.setFont(new Font("Arial", Font.BOLD, 16));
            String numeroAndar = String.valueOf(i);
            FontMetrics fm = g.getFontMetrics();
            int textY = y + ((ALTURA_ANDAR + fm.getHeight()) / 2);
            g.drawString(numeroAndar, MARGEM + 10, textY);
        }
        
        Elevador elevador = Elevador.getInstance();
        if (elevador.getAndar() != null) {
            int andarAtual = elevador.getAndar().getPiso();
            int yBase = MARGEM + (ALTURA_ANDAR * (numAndares - 1 - andarAtual));
            int yOffset = (int)(elevador.getTransicao() * ALTURA_ANDAR);
            
            g.setColor(Color.BLUE);
            g.fillRect(
                MARGEM + LARGURA_PREDIO - LARGURA_ELEVADOR - 50,
                yBase - yOffset,
                LARGURA_ELEVADOR,
                ALTURA_ANDAR
            );
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Predio.getInstance().setup(10);
            new TelaGrafica(10).setVisible(true);
        });
    }
}