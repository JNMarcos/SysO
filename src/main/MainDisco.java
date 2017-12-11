/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import algoritmosDisco.CLook;
import algoritmosDisco.CScan;
import algoritmosDisco.Look;
import algoritmosDisco.Scan;
import classes_basicas.Comandos;
import classes_basicas.RequisicoesDisco;
import classes_basicas.Elevador;

/**
 * @author jnmar
 *
 */
public class MainDisco {
	//a presen�a de -1 no array posi��es indica que h� uma outra fila de requisi��es
	//simula a chegada de novas req ap�s o primeiro conjunto de requisi��es
	// ou seja,  "din�mico"
	//n�o encerre um array com -1. -1 somente se tiver um novo conj de requisi��es

	//ao colocar valores aqui, lembrar se nenhum deles chega ou ultrapassa ao valor em
	//Elevador.nPosicoes
	static int[] posicoes  = {98, 183, 37, 122, 14, 124, 65, 67};
	static String[] nomesAlgoritmos = {"SCAN", "C-SCAN", "LOOK", "C-LOOK"};

	public static void main(String[] args){
		List<RequisicoesDisco> conjReq = new ArrayList<>();


		Random rand = new Random();
		int pInicial = rand.nextInt(Elevador.nPosicoes);

		//comente a linha a seguir se deseja um pInicial aleat�rio
		pInicial = 53;

		Elevador elev = null;

		//obt�m o n�mero de conjuntos de requisi��es que existe
		int nConjuntosRequisicoes = (int) (Arrays.stream(posicoes).filter(e -> e == -1).count() + 1);
		RequisicoesDisco req; 

		int iniciarArrayEm = 0;
		for (int i = 0; i < nConjuntosRequisicoes; i++) {
			req = new RequisicoesDisco();
			for (int j = iniciarArrayEm; j < posicoes.length; j++) {
				//se encontrar um -1, � pq � outro conjunto de requisi��es
				if (posicoes[j] != -1) req.getconjReq().add(posicoes[j]);
				else {
					iniciarArrayEm = j + 1;
					break;
				}
			}
			conjReq.add(req);
		}

		int posicaoInicial;

		//diz se o algoritmo vai come�ar indo para a direita ou esquerda
		//possibilita que haja uma certa aleatoriedade
		boolean primeiroVaParaDireita = rand.nextBoolean();

		//comente linha abaixo se quer algo aleat�rio
		primeiroVaParaDireita = false;

		//indica como vai ser organizado os vetores antes e depois da posi��o inicial
		//a depender do algoritmo, a organiza��o � diferente
		//ver m�todo organizarRequisicoes(int, int) em RequisicoesDisco
		int tipoOrganizacao = 0;

		for (int i = 0; i < nomesAlgoritmos.length; i++) {
			//pInicial � usado para outros algoritmos
			posicaoInicial = pInicial;

			System.out.println("\n\nAlgoritmo " + nomesAlgoritmos[i]);

			switch(i) {
			case 0: elev = new Scan(pInicial); break;
			case 1: elev = new CScan(pInicial); break;
			case 2: elev = new Look(pInicial); break;
			case 3: elev = new CLook(pInicial); break;
			default: elev = new CScan(pInicial);
			}

			if (i == 0 || i == 2) tipoOrganizacao = 0;
			else if (primeiroVaParaDireita) tipoOrganizacao = 1;
			else if (!primeiroVaParaDireita) tipoOrganizacao = 2;

			for (int j = 0; j < nConjuntosRequisicoes; j++) {
				req = conjReq.get(j);
				//� importante q seja feito para cada algoritmo
				req.organizarRequisicoes(posicaoInicial, tipoOrganizacao);
				System.out.println("Posi��o Inicial: " + posicaoInicial);
				if ((i == 0 || i == 2) && primeiroVaParaDireita) {
					elev.caminhar(true, req.getpDepoisPInicial());
					elev.caminhar(false, req.getpAntesPInicial());
				} else if ((i == 0 || i == 2) && !primeiroVaParaDireita) {
					elev.caminhar(false, req.getpAntesPInicial());
					elev.caminhar(true, req.getpDepoisPInicial());
				} else if ((i == 1 || i == 3) && primeiroVaParaDireita) {
					//s� vai para a direita
					elev.caminhar(true, req.getpAntesPInicial());
					elev.caminhar(true, req.getpDepoisPInicial());
				} else {
					//s� vai para a esquerda
					elev.caminhar(false, req.getpAntesPInicial());
					elev.caminhar(false, req.getpDepoisPInicial());
				}

				System.out.println("\n" + elev.getSomaCilindrosPercorridos() + 
						Comandos.SomarNPosicoesPercorridas.getComando() + 
						(j+1) + "� conjunto de requisi��es.\n");
				posicaoInicial = elev.getPosicaoAtual();
			}
		}
	}
}
