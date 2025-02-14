package classes.predio;

public class Andar {
	private int piso;
	
	private Andar abaixo;
	private Andar acima;
	
	protected Andar(int piso) {
		this.piso = piso;
	}
	
	public static Andar getInstance(int andar) {
		if (!Predio.getInstance().getAndares().containsKey(andar)) {
			System.out.println("Esse andar não existe");
			return null;
		}
		return Predio.getInstance().getAndares().get(andar);
	}
	
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public Andar getAbaixo() {
		return abaixo;
	}
	public void setAbaixo(Andar abaixo) {
		this.abaixo = abaixo;
	}
	public Andar getAcima() {
		return acima;
	}
	public void setAcima(Andar acima) {
		this.acima = acima;
	}

	public void chamarElevador() {
		
		
	}
	
	
	
}
