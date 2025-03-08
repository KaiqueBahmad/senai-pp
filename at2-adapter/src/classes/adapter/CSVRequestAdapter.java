package classes.adapter;

import java.util.LinkedList;
import java.util.List;

import classes.persistence.Atividade;
import classes.persistence.NewMessageRequest;
import classes.request.CSVRequest;

public class CSVRequestAdapter extends NewMessageRequest {

	public CSVRequestAdapter(CSVRequest csvRequest) {
		String raw = csvRequest.getContent();
		List<Atividade> atividades = new LinkedList<>();
		super.setAtividades(atividades);
		for (String atv: raw.split(csvRequest.getDelimiter())) {
			Atividade atividade = new Atividade();
			atividade.setConteudo(atv);
			atividades.add(atividade);
		}
	}
	
	
	
}
