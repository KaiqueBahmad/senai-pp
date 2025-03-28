package classes.entities;

public class Produto {
	private Double preco;
	private Double custo;
	private String nome;
	
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Double getCusto() {
		return custo;
	}
	public void setCusto(Double custo) {
		this.custo = custo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Produto(Double preco, Double custo, String nome) {
		this.preco = preco;
		this.custo = custo;
		this.nome = nome;
	}
	
	
}
