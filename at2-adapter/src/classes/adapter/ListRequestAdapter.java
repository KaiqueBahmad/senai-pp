package classes.adapter;

import java.util.LinkedList;
import java.util.List;

import classes.persistence.Atividade;
import classes.persistence.NewMessageRequest;
import classes.request.ListRequest;

public class ListRequestAdapter extends NewMessageRequest {
	
	
	public ListRequestAdapter(ListRequest listRequest) {
		List<Atividade> atividades = new LinkedList<>();
		System.out.println(atividades.size());
		for (String msg: listRequest.getMensagens()) {
			Atividade mensagem = new Atividade();
			mensagem.setConteudo(msg);
			atividades.add(mensagem);
		}
		super.setAtividades(atividades);
	}
	
}
