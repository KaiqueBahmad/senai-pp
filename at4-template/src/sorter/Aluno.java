package sorter;
import java.util.Vector;

public class Aluno {
        String curso;
        String situacao;
        String enfase;
        
        String sobrenome;
        String primeiroNome;
        String nomesMeio;
        
        
        public Aluno(String[] campos) {
            String[] nomes = campos[0].split(" ");
            
            this.primeiroNome = nomes[0];
            
            if (nomes.length > 1) {
                this.sobrenome = nomes[nomes.length - 1];
            } else {
                this.sobrenome = "";
            }
            
            nomesMeio = "";
            for (int i = 1; i < nomes.length - 1; i++) {
                nomesMeio += " " + nomes[i];
            }
            
            this.curso = campos[1];
            this.situacao = campos[2];
            this.enfase = campos[3];
            if (this.curso == null) {
            	return;
            }
            if (this.curso.equals("28")) {
            	this.curso = "ADS";
            } else if (this.curso.equals("33")) {
            	this.curso = "Engenharia de Software";
            }
        }
        
        public String getNome() { return this.sobrenome + ", " + this.primeiroNome + nomesMeio; }
        public String getCurso() { return curso; }
        public String getSituacao() { return situacao; }
        public String getEnfase() { return enfase; }
        
        public Vector<String> toVector() {
            Vector<String> v = new Vector<>();
            v.add(getNome());
            v.add(curso);
            v.add(situacao);
            v.add(enfase);
            return v;
        }
    }