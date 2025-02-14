package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import classes.persistence.Mensagem;
import classes.request.CSVRequest;
import classes.request.FileEnterSeparatedRequest;
import classes.request.ListRequest;

public class NewMessagesAdapter {
	public List<Mensagem> readMessages(ListRequest lista) {
		List<Mensagem> retorno = new LinkedList<>();
		for (String msg: lista.getMensagens()) {
			Mensagem mensagem = new Mensagem();
			mensagem.setConteudo(msg);
			retorno.add(mensagem);
		}
		return retorno;
	}
	
	public List<Mensagem> readMessages(CSVRequest csvRequest) {
		String raw = csvRequest.getContent();
		List<Mensagem> mensagens = new LinkedList<Mensagem>();
		for (String msg: raw.split(csvRequest.getDelimiter())) {
			Mensagem mensagem = new Mensagem();
			mensagem.setConteudo(msg);
			mensagens.add(mensagem);
		}
		return mensagens;
	}
	
	public List<Mensagem> readMessages(FileEnterSeparatedRequest fileRequest) {
	    Path file = fileRequest.getPath();
	    List<Mensagem> mensagens = new LinkedList<>();
	    
	    try {
	        List<String> lines = Files.readAllLines(file);
	        
	        for (String line : lines) {
	            if (line != null && !line.trim().isEmpty()) {
	                Mensagem mensagem = new Mensagem();
	                mensagem.setConteudo(line.trim());
	                mensagens.add(mensagem);
	            }
	        }
	    } catch (IOException e) {
	    	System.out.println("ERRO AO LER ARQUIVO");
	    	return new LinkedList<>();
	    }
	    
	    return mensagens;
	}
	
	public static void main(String[] args) {
		CSVRequest req = new CSVRequest();
		req.setContent("aseiou,dsaldmlsa,dlsmadlsa");
		req.setDelimiter(",");
		NewMessagesAdapter adapter = new NewMessagesAdapter();
		adapter.readMessages(req);
		
	}
	
	
}








