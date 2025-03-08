package classes.persistence;

public class Atividade {
	private String conteudo;
	private Prioridade prioridade;
	private boolean concluida = false;

	public boolean isConcluida() {
	    return concluida;
	}

	public void setConcluida(boolean concluida) {
	    this.concluida = concluida;
	}
	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
	   this.prioridade = descobrirPrioridade(conteudo);
	   this.conteudo = conteudo.substring(0, conteudo.length() - 2);
	}
	
	private Prioridade descobrirPrioridade(String texto) {
		   String fim = texto.substring(texto.length() - 2);
		   
		   if (fim.equals("!1")) return Prioridade.BAIXA;
		   if (fim.equals("!2")) return Prioridade.MEDIA;  
		   if (fim.equals("!3")) return Prioridade.ALTA;
		   
		   return Prioridade.MEDIA; 
		}

}
