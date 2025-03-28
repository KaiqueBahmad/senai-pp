package classes.entities;

public class Venda {
	private Produto produto;
	private Vendedor vendedor;
	private int quantidade;
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public double getLucro() {
		return (this.produto.getPreco() - this.produto.getCusto()) * this.quantidade;
	}
	
}
