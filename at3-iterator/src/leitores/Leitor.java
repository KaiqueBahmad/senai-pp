package leitores;

import java.util.Iterator;

import entidade.Piloto;

public interface Leitor {
	Iterator<Piloto> toIterator(String[] linhas);
}
