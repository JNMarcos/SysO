/**
 * 
 */
package main;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import algoritmos.FIFO;
import algoritmos.RoundRobin;
import algoritmos.SJF;
import classes_basicas.ES;
import classes_basicas.Pagina;
import classes_basicas.Requisicao;
import gerenciadores.GerenciadorMemoria;
import gerenciadores.GerenciadorProcessos;

/**
 * @author jnmar
 *
 */
public class Main {	
	public static GerenciadorProcessos gp = new GerenciadorProcessos();
	public static GerenciadorMemoria gm = new GerenciadorMemoria();
	public static int tempo;
	static Random addTempo = new Random();

	static FIFO fifo = new FIFO();
	static RoundRobin rb = new RoundRobin();
	static SJF sjf = new SJF();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//entrada-saída
		ES io = new ES();
		io.ler();


		//0 = fifo, 1 = lru
		gerenciarMemoria();
		gerenciarProcessos(0);
	}

	public static void gerenciarProcessos(int numAlgoritmo) {
		switch(numAlgoritmo) {
		case 0:
			rb.algoritmo();
			break;
		case 1:
			break;
		case 2:
			break;
		default:

		}
	}

	public static void gerenciarMemoria() {
		//apenas para testar o algoritmo
		int numPaginas;


		boolean paginaJaAdicionada;


		for (Requisicao req: gm.getRequisicoes()) {
			numPaginas = (int) Math.round((double) (req.getTamanhoRequisicao()) / gm.getConfiguracoes().getTamanhoPagina());
			System.out.println("Num pages: " + numPaginas);
			for (int i = 0; i < numPaginas; i++) {
				tempo = tempo + addTempo.nextInt(100);
				System.out.println("Requisicao:" +  req.getNomeProcesso() + i);

				Set<Integer> paginasSorteadas = new HashSet<>();
				paginaJaAdicionada = false;

				int numDaPagina;
				do {
					numDaPagina = gm.gerarNumeroAleatorio();
					paginasSorteadas.add(numDaPagina);
					//se não há página (ocupada), cria (passa a ocupar)
					if (gm.getPaginas()[numDaPagina] == null) {
						gm.getPaginas()[numDaPagina] = new Pagina(req.getNomeProcesso(), tempo, tempo, true, true); 
						paginaJaAdicionada = true;
						break;
					}						


				} while(paginasSorteadas.size() < gm.getConfiguracoes().getQtdPaginas());
				System.out.println("Qtd page sorteadas: " + paginasSorteadas.size());
				//se chegou até aqui, é pq todas as páginas foram sorteadas. Memória cheia
				//usar algoritmo de substituição

				if (!paginaJaAdicionada) {
					fifo.algoritmo(req);
					//outro algoritmo aqui?

				}
			}

			for (int j = 0; j < gm.getPaginas().length; j++) {
				System.out.println(gm.getPaginas()[j].getNomeProcesso());
				System.out.println(gm.getPaginas()[j].getInstTempoArmazenada());
				System.out.println(gm.getPaginas()[j].getInstTempoReferenciada());
				System.out.println(gm.getPaginas()[j].isReferenciado());
				System.out.println(gm.getPaginas()[j].isModificado());
			}
		}
	}
}
