package leitores;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Comparator;
import entidade.Piloto;

public class LeitorArrayDeque implements Leitor {
    
    @Override
    public Iterator<Piloto> toIterator(String[] linhas) {
        ArrayDeque<Piloto> pilotosDeque = new ArrayDeque<>();
        
        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                Piloto piloto = new Piloto(linha);
                
                if (piloto.getPontuacao() > 50) {
                    pilotosDeque.addFirst(piloto);
                } else {
                    pilotosDeque.addLast(piloto);
                }
            }
        }
        
        ArrayList<Piloto> pilotosOrdenados = new ArrayList<>(pilotosDeque);
        pilotosOrdenados.sort(new Comparator<Piloto>() {
            @Override
            public int compare(Piloto p1, Piloto p2) {
                return p1.getIdade().compareTo(p2.getIdade());
            }
        });
        
        return pilotosOrdenados.iterator();
    }
}