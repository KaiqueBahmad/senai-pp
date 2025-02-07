package classes;

import java.util.LinkedList;
import java.util.List;

public class Chat {
	private static Chat instance = new Chat();
	public static Chat getInstance() {
		return Chat.instance;
	}
	
	private Chat() {
		
	}
	
	private List<Usuario> usuarios = new LinkedList<>();
	private List<Mensagem> mensagems = new LinkedList<>();
	public List<Mensagem> getMensagems() {
		return mensagems;
	}
	public void setMensagems(List<Mensagem> mensagems) {
		this.mensagems = mensagems;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
