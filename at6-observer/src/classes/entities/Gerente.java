package classes.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import observer.RecebeComissao;

public class Gerente implements RecebeComissao {
    private final String id = UUID.randomUUID().toString();
	public String getId() {
		return id;
	}
	private CEO chefe;
	private List<Vendedor> vendedores = new LinkedList<>();
	private double aReceber = 0.0;
	private String nome;
	
	@Override
	public void update(Venda venda) {
		if (!this.id.equals(venda.getVendedor().getChefe().getId())) {
			return;
		}
		System.out.printf("Gerente (%s) somando comissao\n", this.getNome());
		this.aReceber += venda.getLucro() * 0.3;
	}
	
	public Gerente(CEO chefe, String nome) {
		super();
		this.chefe = chefe;
		this.nome = nome;
		chefe.getGerentes().add(this);
	}

	public CEO getChefe() {
		return chefe;
	}

	public void setChefe(CEO chefe) {
		this.chefe = chefe;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
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
