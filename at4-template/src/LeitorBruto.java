
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LeitorBruto {
    
    public String[] lerArquivo(String caminho) {
        StringBuilder conteudo = new StringBuilder();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(caminho));
            String linha;
            
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
            
            reader.close();
            
            if (conteudo.length() > 0) {
                conteudo.setLength(conteudo.length() - 1);
            }
            
            return conteudo.toString().split("\n");
            
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) {
    	System.out.println("Diretório atual: " + System.getProperty("user.dir"));
	}
}