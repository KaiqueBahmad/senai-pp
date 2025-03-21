import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PainelDesenho extends JPanel {
    private Figura figura;
    
    public PainelDesenho() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
    }
    
    public void setFigura(Figura figura) {
        this.figura = figura;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (figura != null) {
            int centerX = (getWidth() - figura.getLargura()) / 2;
            int centerY = (getHeight() - figura.getAltura()) / 2;
            figura.draw(g2d, centerX, centerY);
            
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
            String desc = figura.getDescricao();
            int textWidth = g2d.getFontMetrics().stringWidth(desc);
            g2d.drawString(desc, (getWidth() - textWidth) / 2, getHeight() - 20);
        }
    }
}