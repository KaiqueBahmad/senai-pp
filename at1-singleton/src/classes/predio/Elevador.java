package classes.predio;

public class Elevador {
	private Elevador() {
		// restringindo o uso do construtor
	}
	private static Elevador instance = null;
	
	public static Elevador getInstance() {
		if (instance == null) {
			instance = new Elevador();
			instance.transicao = 0;
		}
		return instance;
	}
	
	private double transicao; // via entre -1 e 1;
	private Andar andar; // andar atual
	private Andar andarAlvo;
	private final double velocidade = 0.2; // Andares / segundo
	
	public double getTransicao() {
		return transicao;
	}
	public void setTransicao(double transicao) {
		this.transicao = transicao;
	}
	public Andar getAndar() {
		return andar;
	}
	public void setAndar(Andar andar) {
		this.andar = andar;
	}
	public Andar getAndarAlvo() {
		return andarAlvo;
	}
	public void setAndarAlvo(Andar andarAlvo) {
		this.andarAlvo = andarAlvo;
	}

	public static void instalar(Andar andar) {
		Elevador.getInstance();
		instance.andar = andar;
		instance.andarAlvo = null;
		instance.transicao = 0;
	}
}
