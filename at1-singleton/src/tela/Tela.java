package tela;

import java.util.LinkedList;
import java.util.List;

import classes.predio.Andar;
import classes.predio.Elevador;
import classes.predio.Predio;

public class Tela {
    public static void main(String[] args) {
    	Predio.setup(10);
    	Andar.getInstance(4).chamarElevador();
//    	System.out.println(Andar.getInstance(4).getPiso());
//    	System.out.println(Andar.getInstance(4).getAcima().getPiso());    	
    }
}