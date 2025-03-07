import java.util.Comparator;

public class Ordenadores {
    
    public static Comparator<Aluno> porNome = new Comparator<Aluno>() {
        @Override
        public int compare(Aluno a1, Aluno a2) {
            return a1.getNome().compareTo(a2.getNome());
        }
    };
    
    public static Comparator<Aluno> porSobrenome = new Comparator<Aluno>() {
        @Override
        public int compare(Aluno a1, Aluno a2) {
            String sobrenome1 = getSobrenome(a1.getNome());
            String sobrenome2 = getSobrenome(a2.getNome());
            return sobrenome1.compareTo(sobrenome2);
        }
        
        private String getSobrenome(String nomeCompleto) {
            String[] partes = nomeCompleto.split(" ");
            return partes.length > 1 ? partes[partes.length - 1] : "";
        }
    };
    
    public static Comparator<Aluno> porSituacaoENome = new Comparator<Aluno>() {
        @Override
        public int compare(Aluno a1, Aluno a2) {
            int compareSituacao = a1.getSituacao().compareTo(a2.getSituacao());
            return compareSituacao != 0 ? compareSituacao : porNome.compare(a1, a2);
        }
    };
    
    public static Comparator<Aluno> porCursoENome = new Comparator<Aluno>() {
        @Override
        public int compare(Aluno a1, Aluno a2) {
            int compareCurso = a1.getCurso().compareTo(a2.getCurso());
            return compareCurso != 0 ? compareCurso : porNome.compare(a1, a2);
        }
    };
    
    public static Comparator<Aluno> porEnfaseENome = new Comparator<Aluno>() {
        @Override
        public int compare(Aluno a1, Aluno a2) {
            int compareEnfase = a1.getEnfase().compareTo(a2.getEnfase());
            return compareEnfase != 0 ? compareEnfase : porNome.compare(a1, a2);
        }
    };
    
    public static Comparator<Aluno> porCursoEnfaseENome = new Comparator<Aluno>() {
        @Override
        public int compare(Aluno a1, Aluno a2) {
            int compareCurso = a1.getCurso().compareTo(a2.getCurso());
            if (compareCurso != 0) return compareCurso;
            
            int compareEnfase = a1.getEnfase().compareTo(a2.getEnfase());
            return compareEnfase != 0 ? compareEnfase : porNome.compare(a1, a2);
        }
    };
    
    public static Comparator<Aluno> porEnfaseCursoENome = new Comparator<Aluno>() {
        @Override
        public int compare(Aluno a1, Aluno a2) {
            int compareEnfase = a1.getEnfase().compareTo(a2.getEnfase());
            if (compareEnfase != 0) return compareEnfase;
            
            int compareCurso = a1.getCurso().compareTo(a2.getCurso());
            return compareCurso != 0 ? compareCurso : porNome.compare(a1, a2);
        }
    };
    
    public static Comparator<Aluno> getComparator(String opcao) {
        switch (opcao) {
            case "Por Nome": 
                return porNome;
            case "Por Sobrenome": 
                return porSobrenome;
            case "Por Situação e Nome": 
                return porSituacaoENome;
            case "Por Curso e Nome": 
                return porCursoENome;
            case "Por Ênfase e Nome": 
                return porEnfaseENome;
            case "Por Curso, Ênfase e Nome": 
                return porCursoEnfaseENome;
            case "Por Ênfase, Curso e Nome": 
                return porEnfaseCursoENome;
            default: 
                return porNome;
        }
    }
}