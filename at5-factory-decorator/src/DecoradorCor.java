import java.awt.Color;
import java.awt.Graphics2D;

public class DecoradorCor extends DecoradorFigura {
    private Color cor;
    
    public DecoradorCor(Figura figuraDecorada, Color cor) {
        super(figuraDecorada);
        this.cor = cor;
    }
    
    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        Color corOriginal = g2d.getColor();
        
        figuraDecorada.draw(g2d, x, y);
        
        g2d.setColor(cor);
        g2d.fillRect(x+1, y+1, getLargura()-1, getAltura()-1);
        
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, getLargura(), getAltura());
        g2d.setColor(corOriginal);
    }
    
    @Override
    public String getDescricao() {
        return figuraDecorada.getDescricao() + " com cor " + cor.toString();
    }
}