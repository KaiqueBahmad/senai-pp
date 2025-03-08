package classes.adapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import classes.persistence.Atividade;
import classes.persistence.NewMessageRequest;
import classes.request.FileEnterSeparatedRequest;

public class FileRequestAdapter extends NewMessageRequest {

	public FileRequestAdapter(FileEnterSeparatedRequest fileRequest) {
		Path file = fileRequest.getPath();
		List<Atividade> atividades = new LinkedList<>();
	    this.setAtividades(atividades);
	    try {
	        List<String> lines = Files.readAllLines(file);
	        
	        for (String line : lines) {
	            if (line != null && !line.trim().isEmpty()) {
	            	Atividade mensagem = new Atividade();
	                mensagem.setConteudo(line.trim());
	                atividades.add(mensagem);
	            }
	        }
	    } catch (IOException e) {
	    	System.out.println("ERRO AO LER ARQUIVO");
	    	atividades = new LinkedList<>();
	    }
	    
	}
	
}
