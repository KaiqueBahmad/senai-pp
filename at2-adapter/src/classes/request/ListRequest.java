package classes.request;

import java.util.List;

public class ListRequest implements IListRequest {
	private List<String> mensagens;

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}
	
	
}
