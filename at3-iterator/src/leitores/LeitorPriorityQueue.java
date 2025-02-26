package leitores;

import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.Comparator;
import java.util.ArrayList;
import entidade.Piloto;

public class LeitorPriorityQueue implements Leitor {
    
    @Override
    public Iterator<Piloto> toIterator(String[] linhas) {
        PriorityQueue<Piloto> filaOriginal = new PriorityQueue<>(new Comparator<Piloto>() {
            @Override
            public int compare(Piloto p1, Piloto p2) {
                return p2.getPontuacao().compareTo(p1.getPontuacao());
            }
        });
        
        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                filaOriginal.add(new Piloto(linha));
            }
        }
        
        ArrayList<Piloto> listaPilotos = new ArrayList<>();
        while (!filaOriginal.isEmpty()) {
            listaPilotos.add(filaOriginal.poll());
        }
        
        return listaPilotos.iterator();
    }
}