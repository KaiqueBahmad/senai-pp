package classes.persistence;

import java.util.List;

public class NewMessageRequest {
	private List<Atividade> atividades;

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
}
