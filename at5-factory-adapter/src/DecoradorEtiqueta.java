import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class DecoradorEtiqueta extends DecoradorFigura {
    private String texto;
    private Color corTexto;
    
    public DecoradorEtiqueta(Figura figuraDecorada, String texto, Color corTexto) {
        super(figuraDecorada);
        this.texto = texto;
        this.corTexto = corTexto;
    }
    
    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        figuraDecorada.draw(g2d, x, y);
        Color corOriginal = g2d.getColor();
        Font fonteOriginal = g2d.getFont();
        
        g2d.setColor(corTexto);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        
        FontMetrics fm = g2d.getFontMetrics();
        int textX = x + (getLargura() - fm.stringWidth(texto)) / 2;
        int textY = y + (getAltura() + fm.getAscent() - fm.getDescent()) / 2;
        
        g2d.drawString(texto, textX, textY);
        
        g2d.setColor(corOriginal);
        g2d.setFont(fonteOriginal);
    }
    
    @Override
    public String getDescricao() {
        return figuraDecorada.getDescricao() + " com etiqueta \"" + texto + "\"";
    }
}