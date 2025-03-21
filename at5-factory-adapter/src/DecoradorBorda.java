import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class DecoradorBorda extends DecoradorFigura {
    private int espessuraBorda;
    private Color corBorda;
    private BasicStroke estiloLinha;
    
    public DecoradorBorda(Figura figuraDecorada, int espessuraBorda, Color corBorda, float[] dash) {
        super(figuraDecorada);
        this.espessuraBorda = espessuraBorda;
        this.corBorda = corBorda;
        this.estiloLinha = new BasicStroke(espessuraBorda, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
    }
    
    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        figuraDecorada.draw(g2d, x, y);
        
        Stroke strokeOriginal = g2d.getStroke();
        Color corOriginal = g2d.getColor();
        
        g2d.setStroke(estiloLinha);
        g2d.setColor(corBorda);
        g2d.drawRect(x, y, getLargura(), getAltura());
        g2d.setStroke(strokeOriginal);
        g2d.setColor(corOriginal);
    }
    
    @Override
    public String getDescricao() {
        return figuraDecorada.getDescricao() + " com borda personalizada";
    }
}