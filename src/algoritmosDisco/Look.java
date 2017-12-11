/**
 * 
 */
package algoritmosDisco;

import java.util.List;

import classes_basicas.Comandos;
import classes_basicas.Elevador;

/**
 * @author jnmar
 *
 */
public class Look implements Elevador {
	private int posicaoAtual;
	private int somaCilindrosPercorridos;
	

	public Look(int posicaoInicial) {
		this.posicaoAtual = posicaoInicial;
		this.somaCilindrosPercorridos = 0;
	}
	
	public int getPosicaoAtual() {
		return this.posicaoAtual;
	}
	
	public int getSomaCilindrosPercorridos() {
		return this.somaCilindrosPercorridos;
	}
	
	@Override
	public void caminhar(boolean sentido, List<Integer> posicoes) {
		for (int p: posicoes) {
			if (p != posicaoAtual) {
				if (sentido) System.out.println(Comandos.Direita.getComando());
				else System.out.println(Comandos.Esquerda.getComando());
				somaCilindrosPercorridos += Math.abs(posicaoAtual - p); //a diferença entre a posicao
				posicaoAtual = p;
			}
			else System.out.println(Comandos.FicarParado.getComando());
			
			System.out.println(Comandos.IndicarPosicao.getComando() + p);
		}
	}
}
