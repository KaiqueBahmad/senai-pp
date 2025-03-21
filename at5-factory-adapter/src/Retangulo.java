import java.awt.Color;
import java.awt.Graphics2D;

public class Retangulo implements Figura {
    private int largura;
    private int altura;
    
    public Retangulo(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }
    
    @Override
    public void draw(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, largura, altura);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, largura, altura);
    }
    
    @Override
    public double getArea() {
        return largura * altura;
    }
    
    @Override
    public String getDescricao() {
        return "Retângulo";
    }
    
    @Override
    public int getLargura() {
        return largura;
    }
    
    @Override
    public int getAltura() {
        return altura;
    }
}