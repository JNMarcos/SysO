package algoritmosDisco;

import classes_basicas.Comandos;
import classes_basicas.Elevador;

import java.util.List;

public class CLook implements Elevador {
    private int posicaoAtual;
	private int somaCilindrosPercorridos;
	
	private boolean jaExecutouCaminhar;

    public CLook(int posicaoInicial) {
        this.posicaoAtual = posicaoInicial;
		this.somaCilindrosPercorridos = 0;
		this.jaExecutouCaminhar = false;
	}

    public int getPosicaoAtual() {
		return this.posicaoAtual;
	}
	
	public int getSomaCilindrosPercorridos() {
		return this.somaCilindrosPercorridos;
	}
    
	@Override
	public void caminhar(boolean sentido, List<Integer> posicoes) {
		//sentido direito = verdadeiro
		if (sentido && jaExecutouCaminhar) {
			System.out.println(Comandos.PercorrerAte.getComando() + posicoes.get(0) + ".");
			//somaCilindrosPercorridos += Math.abs(posicoes.get(0)- posicaoAtual);//pois anda por um sentido sem fazer nenh req
		} else if (!sentido && jaExecutouCaminhar) {
			System.out.println(Comandos.PercorrerAte.getComando() + posicoes.get(0) + ".");
			//somaCilindrosPercorridos += Math.abs(posicoes.get(0) - posicaoAtual);
		}
		
		for (int p: posicoes) {
			if (p != posicaoAtual) {
				if (jaExecutouCaminhar && posicoes.get(0) == p) {}
				else if (sentido) System.out.println(Comandos.Direita.getComando());
				else System.out.println(Comandos.Esquerda.getComando());
				somaCilindrosPercorridos += Math.abs(posicaoAtual - p); //a diferença entre a posicao
				posicaoAtual = p;
			}
			else System.out.println(Comandos.FicarParado.getComando());
			
			System.out.println(Comandos.IndicarPosicao.getComando() + p);
		}
		
		//inverte a cada conjunto de requisições o valor de jaExecutouCaminhar
		if (jaExecutouCaminhar) jaExecutouCaminhar = false;
		else jaExecutouCaminhar = true;
		
		
	}
}
