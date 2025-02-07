package classes.persistence;

import java.util.LinkedList;
import java.util.List;

public class MessageBox {
	private List<Mensagem> mensagens = new LinkedList<>();

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
}