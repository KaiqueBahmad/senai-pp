import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Main extends JFrame {
    private PainelDesenho painelDesenho;
    private JPanel painelControles;
    private JComboBox<String> comboFiguras;
    
    public Main() {
    	super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initComponents();
        
        setLayout(new BorderLayout());
        add(painelDesenho, BorderLayout.CENTER);
        add(painelControles, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        painelDesenho = new PainelDesenho();
        
        painelControles = new JPanel();
        painelControles.setLayout(new FlowLayout());
        
        comboFiguras = new JComboBox<>(new String[] {
            "Retângulo Simples", 
            "Retângulo com Cor", 
            "Retângulo com Borda",
            "Retângulo com Rotação",
            "Retângulo com Etiqueta", 
            "Retângulo com Todos Decoradores"
        });
        
        comboFiguras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarFigura();
            }
        });
        
        painelControles.add(new JLabel("Selecione um exemplo: "));
        painelControles.add(comboFiguras);
        
        atualizarFigura();
    }
    
    private void atualizarFigura() {
        Figura figura = null;
        
        switch (comboFiguras.getSelectedIndex()) {
            case 0:
                figura = FabricaFigura.criarRetangulo(200, 100);
                break;
                
            case 1:
                figura = new DecoradorCor(
                    FabricaFigura.criarRetangulo(200, 100),
                    new Color(100, 150, 255)
                );
                break;
                
            case 2:
                figura = new DecoradorBorda(
                    FabricaFigura.criarRetangulo(200, 100),
                    3,
                    Color.RED,
                    new float[] {10.0f, 5.0f}
                );
                break;
                
            case 3:
                figura = new DecoradorRotacao(
                    FabricaFigura.criarRetangulo(200, 100),
                    45
                );
                break;
                
            case 4:
                figura = new DecoradorEtiqueta(
                    FabricaFigura.criarRetangulo(200, 100),
                    "Olá Mundo!",
                    Color.BLUE
                );
                break;
                
            case 5:
                figura = new DecoradorEtiqueta(
                    new DecoradorBorda(
                        new DecoradorRotacao(
                            new DecoradorCor(
                                FabricaFigura.criarRetangulo(200, 100),
                                new Color(230, 230, 100)
                            ),
                            30
                        ),
                        2,
                        new Color(100, 100, 255),
                        new float[] {5.0f, 5.0f}
                    ),
                    "Decoradores Combinados",
                    Color.RED
                );
                break;
        }
        
        if (figura != null) {
            painelDesenho.setFigura(figura);
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