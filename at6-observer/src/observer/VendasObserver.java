package observer;

import java.util.ArrayList;
import java.util.List;

import classes.entities.Venda;

// Avisa toda a hierarquia relacionado sobre uma nova venda
public class VendasObserver {
	List<RecebeComissao> listeners = new ArrayList<>();
	
	public void subscribe(RecebeComissao listener) {
		listeners.add(listener);
    }
	
    public void unsubscribe(RecebeComissao listener) {
    	listeners.remove(listener);
    }

    public void notify(Venda venda) {
        for (RecebeComissao listener : listeners) {
            listener.update(venda);
        }
    }
	

}
