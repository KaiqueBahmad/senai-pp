import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Main extends JFrame {
    private PainelDesenho painelDesenho;
    private JPanel painelControles;
    private JPanel painelInputs;
    
    private JTextField txtLargura;
    private JTextField txtAltura;
    private JCheckBox chkCor;
    private JButton btnSelecionarCor;
    private Color corSelecionada = new Color(100, 150, 255);
    private JCheckBox chkBorda;
    private JTextField txtEspessuraBorda;
    private JButton btnCorBorda;
    private Color corBorda = Color.RED;
    private JCheckBox chkRotacao;
    private JSlider sliderRotacao;
    private JCheckBox chkEtiqueta;
    private JTextField txtEtiqueta;
    private JButton btnCorEtiqueta;
    private Color corEtiqueta = Color.BLUE;
    
    public Main() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComponents();
        
        setLayout(new BorderLayout());
        add(painelDesenho, BorderLayout.CENTER);
        add(painelControles, BorderLayout.SOUTH);
        
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        painelDesenho = new PainelDesenho();
        
        painelControles = new JPanel();
        painelControles.setLayout(new BorderLayout());
        
        painelInputs = new JPanel();
        painelInputs.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelInputs.add(new JLabel("Dimensões: "), gbc);
        
        JPanel painelDimensoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelDimensoes.add(new JLabel("Largura:"));
        txtLargura = new JTextField("200", 5);
        painelDimensoes.add(txtLargura);
        painelDimensoes.add(new JLabel("Altura:"));
        txtAltura = new JTextField("100", 5);
        painelDimensoes.add(txtAltura);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        painelInputs.add(painelDimensoes, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        painelInputs.add(new JLabel("Cor: "), gbc);
        
        JPanel painelCor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chkCor = new JCheckBox("Aplicar cor", false);
        btnSelecionarCor = new JButton("Escolher cor");
        btnSelecionarCor.setBackground(corSelecionada);
        btnSelecionarCor.setForeground(Color.WHITE);
        
        btnSelecionarCor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color novaCor = JColorChooser.showDialog(Main.this, "Escolha uma cor", corSelecionada);
                if (novaCor != null) {
                    corSelecionada = novaCor;
                    btnSelecionarCor.setBackground(corSelecionada);
                    btnSelecionarCor.setForeground(getContrastColor(corSelecionada));
                    atualizarFigura();
                }
            }
        });
        
        chkCor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSelecionarCor.setEnabled(chkCor.isSelected());
                atualizarFigura();
            }
        });
        
        btnSelecionarCor.setEnabled(chkCor.isSelected());
        painelCor.add(chkCor);
        painelCor.add(btnSelecionarCor);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        painelInputs.add(painelCor, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        painelInputs.add(new JLabel("Borda: "), gbc);
        
        JPanel painelBorda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chkBorda = new JCheckBox("Aplicar borda", false);
        
        painelBorda.add(chkBorda);
        painelBorda.add(new JLabel("Espessura:"));
        txtEspessuraBorda = new JTextField("3", 3);
        painelBorda.add(txtEspessuraBorda);
        
        btnCorBorda = new JButton("Cor da borda");
        btnCorBorda.setBackground(corBorda);
        btnCorBorda.setForeground(getContrastColor(corBorda));
        
        btnCorBorda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color novaCor = JColorChooser.showDialog(Main.this, "Escolha a cor da borda", corBorda);
                if (novaCor != null) {
                    corBorda = novaCor;
                    btnCorBorda.setBackground(corBorda);
                    btnCorBorda.setForeground(getContrastColor(corBorda));
                    atualizarFigura();
                }
            }
        });
        
        chkBorda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selecionado = chkBorda.isSelected();
                txtEspessuraBorda.setEnabled(selecionado);
                btnCorBorda.setEnabled(selecionado);
                atualizarFigura();
            }
        });
        
        txtEspessuraBorda.setEnabled(chkBorda.isSelected());
        btnCorBorda.setEnabled(chkBorda.isSelected());
        
        painelBorda.add(btnCorBorda);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        painelInputs.add(painelBorda, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        painelInputs.add(new JLabel("Rotação: "), gbc);
        
        JPanel painelRotacao = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chkRotacao = new JCheckBox("Aplicar rotação", false);
        sliderRotacao = new JSlider(JSlider.HORIZONTAL, 0, 360, 45);
        sliderRotacao.setMajorTickSpacing(90);
        sliderRotacao.setMinorTickSpacing(15);
        sliderRotacao.setPaintTicks(true);
        sliderRotacao.setPaintLabels(true);
        
        chkRotacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sliderRotacao.setEnabled(chkRotacao.isSelected());
                atualizarFigura();
            }
        });
        
        sliderRotacao.addChangeListener(e -> {
            if (!sliderRotacao.getValueIsAdjusting()) {
                atualizarFigura();
            }
        });
        
        sliderRotacao.setEnabled(chkRotacao.isSelected());
        
        painelRotacao.add(chkRotacao);
        painelRotacao.add(sliderRotacao);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        painelInputs.add(painelRotacao, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        painelInputs.add(new JLabel("Etiqueta: "), gbc);
        
        JPanel painelEtiqueta = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chkEtiqueta = new JCheckBox("Aplicar etiqueta", false);
        painelEtiqueta.add(chkEtiqueta);
        
        painelEtiqueta.add(new JLabel("Texto:"));
        txtEtiqueta = new JTextField("Olá Mundo!", 15);
        painelEtiqueta.add(txtEtiqueta);
        
        btnCorEtiqueta = new JButton("Cor do texto");
        btnCorEtiqueta.setBackground(corEtiqueta);
        btnCorEtiqueta.setForeground(getContrastColor(corEtiqueta));
        
        btnCorEtiqueta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color novaCor = JColorChooser.showDialog(Main.this, "Escolha a cor do texto", corEtiqueta);
                if (novaCor != null) {
                    corEtiqueta = novaCor;
                    btnCorEtiqueta.setBackground(corEtiqueta);
                    btnCorEtiqueta.setForeground(getContrastColor(corEtiqueta));
                    atualizarFigura();
                }
            }
        });
        
        chkEtiqueta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selecionado = chkEtiqueta.isSelected();
                txtEtiqueta.setEnabled(selecionado);
                btnCorEtiqueta.setEnabled(selecionado);
                atualizarFigura();
            }
        });
        
        txtEtiqueta.setEnabled(chkEtiqueta.isSelected());
        btnCorEtiqueta.setEnabled(chkEtiqueta.isSelected());
        
        painelEtiqueta.add(btnCorEtiqueta);
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        painelInputs.add(painelEtiqueta, gbc);
        
        JButton btnAplicar = new JButton("Aplicar Alterações");
        btnAplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarFigura();
            }
        });
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        painelInputs.add(btnAplicar, gbc);
        
        painelControles.add(painelInputs, BorderLayout.CENTER);
        
        atualizarFigura();
    }
    
    private Color getContrastColor(Color cor) {
        double luminancia = 0.299 * cor.getRed() + 0.587 * cor.getGreen() + 0.114 * cor.getBlue();
        return luminancia > 128 ? Color.BLACK : Color.WHITE;
    }
    
    private void atualizarFigura() {
        try {
            int largura = Integer.parseInt(txtLargura.getText());
            int altura = Integer.parseInt(txtAltura.getText());
            
            Figura figura = FabricaFigura.criarRetangulo(largura, altura);
            
            if (chkCor.isSelected()) {
                figura = new DecoradorCor(figura, corSelecionada);
            }
            
            if (chkBorda.isSelected()) {
                int espessura = Integer.parseInt(txtEspessuraBorda.getText());
                figura = new DecoradorBorda(figura, espessura, corBorda, new float[] {5.0f, 5.0f});
            }
            
            if (chkRotacao.isSelected()) {
                figura = new DecoradorRotacao(figura, sliderRotacao.getValue());
            }
            
            if (chkEtiqueta.isSelected()) {
                figura = new DecoradorEtiqueta(figura, txtEtiqueta.getText(), corEtiqueta);
            }
            
            painelDesenho.setFigura(figura);
        } catch (NumberFormatException e) {
        }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}