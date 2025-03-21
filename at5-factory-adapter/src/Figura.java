import java.awt.Graphics2D;

public interface Figura {
    void draw(Graphics2D g2d, int x, int y);
    double getArea();
    String getDescricao();
    int getLargura();
    int getAltura();
}