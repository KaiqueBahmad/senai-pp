package classes.entities;

import java.util.UUID;

import observer.RecebeComissao;

public class Vendedor implements RecebeComissao {
    private final String id = UUID.randomUUID().toString();
	public String getId() {
		return id;
	}

	private Gerente chefe;
	private double aReceber = 0.0;
	private String nome;
	
	@Override
	public void update(Venda venda) {
		if (!this.id.equals(venda.getVendedor().getId())) {
			return;
		}
		System.out.printf("Vendedor (%s) somando comissao\n", this.getNome());
		this.aReceber += venda.getLucro() * 0.4;
	}
	
	public Vendedor(Gerente chefe, String nome) {
		this.chefe = chefe;
		this.nome = nome;
		chefe.getVendedores().add(this);
	}

	public Gerente getChefe() {
		return chefe;
	}

	public void setChefe(Gerente chefe) {
		this.chefe = chefe;
	}

	public double getaReceber() {
		return aReceber;
	}

	public void setaReceber(double aReceber) {
		this.aReceber = aReceber;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}
