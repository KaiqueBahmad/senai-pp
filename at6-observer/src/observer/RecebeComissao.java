package observer;

import classes.entities.Venda;

public interface RecebeComissao {
	void update(Venda venda);
	double getaReceber();
}
