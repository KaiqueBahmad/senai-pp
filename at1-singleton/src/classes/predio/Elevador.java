package classes.predio;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class Elevador {
    private Elevador() {
        iniciarThreadControle();
    }
    
    private static Elevador instance = null;
    private Set<Andar> chamando = Collections.synchronizedSet(new HashSet<>());
    private boolean descendo = false;
    private boolean subindo = false;
    
    public static Elevador getInstance() {
        if (instance == null) {
            instance = new Elevador();
            instance.transicao = 0;
        }
        return instance;
    }
    
    private double transicao; // vai entre -1 e 1;
    private Andar andar; // andar atual
    private Andar andarAlvo;
    private final double velocidade = 0.5; // Andares / segundo
    private int time_entre_ticks= 1000;
    private void iniciarThreadControle() {
    	Thread thread;
        thread = new Thread(() -> {
            while (true) {
                try {
                    tick();
                    Thread.sleep(time_entre_ticks);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    
    private void tick() throws InterruptedException {
    	if (andarAlvo == null && !chamando.isEmpty()) {
    		andarAlvo = decidirProximo();
    	}
    	
    	if (andarAlvo == null) {
    		subindo = false;
    		descendo = false;
    		transicao = 0;
    		return;
    	}
    	
    	double fator = 0;
    	if (andarAlvo.getPiso() > andar.getPiso()) {
    		subindo = true;
    		descendo = false;
    		fator = 1;
    	}
    	
    	if (andarAlvo.getPiso() == andar.getPiso()) {
    		if (chamando.isEmpty()) {
    			subindo = false;
    			descendo = false;
    		}
    	}
    	
    	if (andarAlvo.getPiso() < andar.getPiso()) {
    		subindo = false;
    		descendo = true;
    		fator = -1;
    	}
    	
    	if (fator == 0) {
    		if (transicao == 0 && andar == andarAlvo) {
    			chamando.remove(andarAlvo);
    			andarAlvo = null;
    		}
    		return;
    	}
    	
    	transicao += fator * ((time_entre_ticks/ 1000) * velocidade);
    	if (transicao <= -1.0) {
    		transicao = 0;
    		andar = andar.getAbaixo();
    	}
    	if (transicao >= 1) {
    		transicao = 0;
    		andar = andar.getAcima();
    	}
    	
    	if (chamando.contains(andar)) {
			Thread.sleep(1000);
    		chamando.remove(andar);
    	}
    	
    	if (andar == andarAlvo && transicao == 0) {
    		andarAlvo = null;
    	}
    	
    }
    
    private Andar decidirProximo() {
    	Andar maior = null;
    	Andar menor = null;
    	Andar maisProximo = null;
    	maior = chamando.iterator().next();
    	menor = maior;
    	maisProximo = maior;
    	int andarAtual = this.andar.getPiso();
    	for (Andar candidato:chamando) {
    		if (candidato.getPiso() > maior.getPiso()) {
    			maior = candidato;
    		}
    		if (candidato.getPiso() < menor.getPiso()) {
    			menor = candidato;
    		}
    		if (Math.abs( andarAtual - candidato.getPiso()) < Math.abs(andarAtual - maisProximo.getPiso())) {
    			maisProximo = candidato;
    		}
    	}
    	
    	if (subindo) {
    		return maior;
    	}
    	if (descendo) {
    		return menor;
    	}
    	
		return maisProximo;
	}

	public double getTransicao() {
        return transicao;
    }
    
    public void setTransicao(double transicao) {
        this.transicao = transicao;
    }
    
    public Andar getAndar() {
        return andar;
    }
    
    public void setAndar(Andar andar) {
        this.andar = andar;
    }
    
    public Andar getAndarAlvo() {
        return andarAlvo;
    }
    
    public void setAndarAlvo(Andar andarAlvo) {
        this.andarAlvo = andarAlvo;
    }
    
    public static void instalar(Andar andar) {
        Elevador.getInstance();
        instance.andar = andar;
        instance.andarAlvo = null;
        instance.transicao = 0;
    }
    
    public void addAndarChamando(Andar andar) {
        chamando.add(andar);
    }
    
    public Set<Andar> getChamando() {
        return this.chamando;
    }
}