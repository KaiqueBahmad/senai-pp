package classes.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import observer.RecebeComissao;

public class CEO implements RecebeComissao {
	private final String id = UUID.randomUUID().toString();
	public String getId() {
		return id;
	}
	private double aReceber = 0.0;
	private List<Gerente> gerentes = new LinkedList<Gerente>();
	private String nome;
	
	@Override
	public void update(Venda venda) {
		if (!this.id.equals(venda.getVendedor().getChefe().getChefe().getId())) {
			return;
		}
		System.out.printf("CEO (%s) somando comissao\n", this.getNome());
		this.aReceber += venda.getLucro() * 0.2;
	}
	
	public CEO(String nome) {
		this.nome = nome;
	}

	public double getaReceber() {
		return aReceber;
	}

	public void setaReceber(double aReceber) {
		this.aReceber = aReceber;
	}

	public List<Gerente> getGerentes() {
		return gerentes;
	}

	public void setGerentes(List<Gerente> gerentes) {
		this.gerentes = gerentes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
