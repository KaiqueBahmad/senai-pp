package classes.persistence;

import java.util.LinkedList;
import java.util.List;

public class AtividadeBox {
	private List<Atividade> mensagens = new LinkedList<>();

	public List<Atividade> getMensagens() {
		return mensagens;
	}
	
	public void addMensagens(NewMessageRequest request) {
		this.mensagens.addAll(request.getAtividades());
	}

}