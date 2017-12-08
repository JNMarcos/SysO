/**
 * 
 */
package algoritmos;

import classes_basicas.Comandos;
import classes_basicas.Elevador;

/**
 * @author jnmar
 *
 */
public class Look implements Elevador {
	static int POSICAO_ATUAL = 0;

	@Override
	public boolean irPara(int p) {
		if (p > POSICAO_ATUAL){ 
			System.out.println(Comandos.Direita.getComando());
		} else if (p < POSICAO_ATUAL){
			System.out.println(Comandos.Esquerda.getComando());
		}
		if (p == POSICAO_ATUAL) System.out.println(Comandos.NaoMover.getComando());
		System.out.println(Comandos.IndicarPosicao.getComando() + p);
		
		POSICAO_ATUAL = p;
		return true;
	}
}
