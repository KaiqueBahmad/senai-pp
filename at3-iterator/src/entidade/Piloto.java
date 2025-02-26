package entidade;

import java.util.Vector;

public class Piloto {
	private Integer id;
	private String nome;
	private String nacionalidade;
	private Integer idade;
	private String equipe;
	private String motor;
	private Integer pontuacao;
	
	public Piloto(String linha) {
	    String[] campos = linha.split(",");
	    this.id = Integer.parseInt(campos[0]);
	    this.nome = campos[1];
	    this.nacionalidade = campos[2];
	    this.idade = Integer.parseInt(campos[3]);
	    this.equipe = campos[4];
	    this.motor = campos[5];
	    this.pontuacao = Integer.parseInt(campos[6]);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getEquipe() {
		return equipe;
	}
	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	public Integer getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	public String[] toVector() {
	    return new String[] {
	        id.toString(),
	        nome,
	        nacionalidade,
	        idade.toString(),
	        equipe,
	        motor,
	        pontuacao.toString()
	    };
	}
}
