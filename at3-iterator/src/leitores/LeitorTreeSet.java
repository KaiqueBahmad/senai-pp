package leitores;

import java.util.TreeSet;
import java.util.Iterator;
import java.util.Comparator;
import entidade.Piloto;

public class LeitorTreeSet implements Leitor {
    
    @Override
    public Iterator<Piloto> toIterator(String[] linhas) {
        TreeSet<Piloto> pilotos = new TreeSet<>(new Comparator<Piloto>() {
            @Override
            public int compare(Piloto p1, Piloto p2) {
                return p2.getPontuacao().compareTo(p1.getPontuacao());
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