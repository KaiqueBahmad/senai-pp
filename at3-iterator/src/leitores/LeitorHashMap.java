package leitores;

import java.util.HashMap;
import java.util.Iterator;

import entidade.Piloto;

public class LeitorHashMap implements Leitor {
    
    @Override
    public Iterator<Piloto> toIterator(String[] linhas) {
        HashMap<Integer, Piloto> pilotos = new HashMap<>();
        
        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                Piloto piloto = new Piloto(linha);
                pilotos.put(piloto.getId(), piloto);
            }
        }
        
        return pilotos.values().iterator();
    }
}