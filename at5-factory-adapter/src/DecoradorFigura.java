import java.awt.Graphics2D;

public abstract class DecoradorFigura implements Figura {
    protected Figura figuraDecorada;
    
    public DecoradorFigura(Figura figuraDecorada) {
        this.figuraDecorada = figuraDecorada;
    }
    
    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        figuraDecorada.draw(g2d, x, y);
    }
    
    @Override
    public double getArea() {
        return figuraDecorada.getArea();
    }
    
    @Override
    public String getDescricao() {
        return figuraDecorada.getDescricao();
    }
    
    @Override
    public int getLargura() {
        return figuraDecorada.getLargura();
    }
    
    @Override
    public int getAltura() {
        return figuraDecorada.getAltura();
    }
}