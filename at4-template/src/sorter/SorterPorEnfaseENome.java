package sorter;
import java.util.Comparator;
import java.util.List;
public class SorterPorEnfaseENome extends Sorter implements Comparator {
	@Override
	public List<Aluno> sort(List<Aluno> listaAlunos) {
		listaAlunos.sort(this);
		return listaAlunos;
	}
	@Override
	public int compare(Object o1, Object o2) {
        Aluno a1 = (Aluno) o1;
        Aluno a2 = (Aluno) o2;
        int compareEnfase = a1.enfase.compareTo(a2.enfase);
        if (compareEnfase != 0) return compareEnfase;
        int comparePrimeiroNome = a1.primeiroNome.compareTo(a2.primeiroNome);
        if (comparePrimeiroNome != 0) return comparePrimeiroNome;
        int compareNomesMeio = a1.nomesMeio.compareTo(a2.nomesMeio);
        if (compareNomesMeio != 0) return compareNomesMeio;
        return a1.sobrenome.compareTo(a2.sobrenome);
	}
}