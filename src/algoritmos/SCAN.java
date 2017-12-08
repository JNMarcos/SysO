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
public class SCAN implements Elevador {


	static int POSICAO_ATUAL = 0;
	private boolean estaIndoDireita;

	public SCAN(boolean estaIndoDireta){
		this.estaIndoDireita = estaIndoDireta;
	}

	@Override
	public boolean irPara(int p) {
		boolean foiAtePosicaoP = false;
		if (estaIndoDireita){ //se o braço já está indo para a direita
			if (p >= POSICAO_ATUAL){ 
				if (p != POSICAO_ATUAL) System.out.println(Comandos.Direita.getComando());
				else System.out.println(Comandos.NaoMover.getComando());
				
				System.out.println(Comandos.IndicarPosicao.getComando() + p);
				POSICAO_ATUAL = p;
				foiAtePosicaoP = true;
			} else { //tá começando do fim do 
				System.out.println(Comandos.IrUltimaPosicao.getComando());
				POSICAO_ATUAL = nPosicoes;
				estaIndoDireita = false;
			}
		} else {
			if (p <= POSICAO_ATUAL){
				if (p != POSICAO_ATUAL) System.out.println(Comandos.Esquerda.getComando());
				else System.out.println(Comandos.NaoMover.getComando());
				
				System.out.println(Comandos.IndicarPosicao.getComando() + p);
				POSICAO_ATUAL = p;
				foiAtePosicaoP = true;
			} else {
				System.out.println(Comandos.IrPrimeiraPosicao.getComando());
				POSICAO_ATUAL = 0;
				estaIndoDireita = true;
			}
		}
		
		return foiAtePosicaoP;
	}
}
