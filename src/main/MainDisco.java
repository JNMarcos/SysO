/**
 * 
 */
package main;

import algoritmos.Look;
import algoritmos.SCAN;
import classes_basicas.Elevador;

/**
 * @author jnmar
 *
 */
public class MainDisco {
static int[] posicoes  = {1, 0, 8, 8, 2, 7, 1, 9, 6};
	
	public static void main(String[] args){
		Elevador eScan = new SCAN(true);
		Elevador eLook = new Look();
		
		int posicaoArray = 0;
		boolean vaiParaProximoP = true;
		System.out.println("\nSCAN");
		while (posicaoArray <= posicoes.length - 1){
			vaiParaProximoP = eScan.irPara(posicoes[posicaoArray]);
			if (vaiParaProximoP) posicaoArray++;
		}
		
		posicaoArray = 0;
		System.out.println("\n\nLOOK");
		while (posicaoArray <= posicoes.length - 1){
			eLook.irPara(posicoes[posicaoArray]);
			posicaoArray++;
		}
		
	}
}
