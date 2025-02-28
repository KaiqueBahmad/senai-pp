package leitores;

import java.util.Iterator;
import java.util.LinkedHashSet;
import entidade.Piloto;

public class LeitorLinkedHashSet implements Leitor {

    @Override
    public Iterator<Piloto> toIterator(String[] linhas) {
        LinkedHashSet<Piloto> pilotos = new LinkedHashSet<>();
        
        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                Piloto piloto = new Piloto(linha);
                pilotos.add(piloto);
            }
        }
        
        return pilotos.iterator();
    }
}