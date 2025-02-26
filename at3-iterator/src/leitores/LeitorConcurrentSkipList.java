package leitores;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.Iterator;
import java.util.Comparator;
import entidade.Piloto;

public class LeitorConcurrentSkipList implements Leitor {
    
    @Override
    public Iterator<Piloto> toIterator(String[] linhas) {
        ConcurrentSkipListSet<Piloto> pilotos = new ConcurrentSkipListSet<>(new Comparator<Piloto>() {
            @Override
            public int compare(Piloto p1, Piloto p2) {
                int comp = p1.getNacionalidade().compareTo(p2.getNacionalidade());
                if (comp == 0) {
                    return p1.getId().compareTo(p2.getId());
                }
                return comp;
            }
        });
        
        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                pilotos.add(new Piloto(linha));
            }
        }
        
        return pilotos.iterator();
    }
}