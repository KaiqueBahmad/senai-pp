package classes.predio;

import java.util.HashMap;
import java.util.Map;

public class Predio {
	
	private static Predio instance;
	private Map<Integer, Andar> andares = new HashMap<Integer, Andar>();
	private int lastNumAndares = 0;
	
	public static Predio getInstance() {
		if (instance == null) {
			instance = new Predio();
		}
		
		return instance;
	}
	
	public void setup(int numAndares) {
		if (instance.lastNumAndares > numAndares) {
			System.out.println("Não é possível diminuir o número de andares!");
			return;
		}
		
		
		
		int i = instance.lastNumAndares;
		
		while (i < numAndares) {
			instance.andares.put(i, new Andar(i));
			i++;
		}
		
		i = instance.lastNumAndares;
		while (i < numAndares) {
			if (i != 0) {
				instance.andares.get(i).setAbaixo(instance.andares.get(i - 1));
			}
			if (i != numAndares - 1) {
				instance.andares.get(i).setAcima(instance.andares.get(i + 1));
			}
			i++;
		}
		
		if (numAndares > 0) {
			Elevador.instalar(Andar.getInstance(0));
		}
		
		instance.lastNumAndares = numAndares;
	}

	protected Map<Integer, Andar> getAndares() {
		return andares;
	}
	
	
	
}
