import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class DecoradorRotacao extends DecoradorFigura {
    private double graus;
    
    public DecoradorRotacao(Figura figuraDecorada, double graus) {
        super(figuraDecorada);
        this.graus = graus;
    }
    
    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        AffineTransform transformOriginal = g2d.getTransform();
        int centerX = x + getLargura() / 2;
        int centerY = y + getAltura() / 2;
        
        g2d.rotate(Math.toRadians(graus), centerX, centerY);
        
        figuraDecorada.draw(g2d, x, y);
        
        g2d.setTransform(transformOriginal);
    }
    
    @Override
    public String getDescricao() {
        return figuraDecorada.getDescricao() + " rotacionado em " + graus + " graus";
    }
}