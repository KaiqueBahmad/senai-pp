import java.util.Arrays;

public class Main {
	private static LeitorBruto leitorBruto = new LeitorBruto();
	
	public static void main(String[] args) {
		System.out.println("AEIOU");
    	String[] rawData = leitorBruto.lerArquivo("./src/RelatorioDasEnfases.csv");
    	System.out.println(Arrays.toString(rawData));
	}

}
