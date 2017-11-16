/**
 * 
 */
package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import algoritmos.FIFO;
import algoritmos.LRU;
import algoritmos.RateMonotonic;
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
	public static final int N_ALGORITMOS_PROCESSOS = 3;
	public static final int N_ALGORITMOS_MEMORIA = 2;

	static FIFO fifo = new FIFO();
	static LRU lru = new LRU();
	static RoundRobin rb = new RoundRobin();
	static SJF sjf = new SJF();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// gerenciarMemoria();
		gerenciarProcessos();		
	}
	
	public static void writeFile(String filename, String content) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write(content);
			
			System.out.println(filename + " written");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void gerenciarProcessos() {
		for (int a = 0; a < N_ALGORITMOS_PROCESSOS; a++) {
			ES io = new ES();
			io.ler();
			
			StringBuilder saida = new StringBuilder();
			saida.append((a == 0 ? "FIFO" : "LRU") + "\n");
			
			switch(a) {
				case 0:
					//rb.algoritmo();
					break;
				case 1:
					sjf.algoritmo();
					break;
				case 2:
					//new RateMonotonic();
					break;
				default:
	
			}
		}
	}

	public static void gerenciarMemoria() {
		for (int a = 0; a < 2; a++) {
			//entrada-sa�da
			ES io = new ES();
			io.ler();
		
			int numAlgoritmo = a;
			int numPaginas;
			
			StringBuilder saida = new StringBuilder();
			saida.append((a == 0 ? "FIFO" : "LRU") + "\n");
			
			boolean paginaJaAdicionada;
			for (Requisicao req: gm.getRequisicoes()) {
				numPaginas = (int) Math.round((double) (req.getTamanhoRequisicao()) / gm.getConfiguracoes().getTamanhoPagina());
				// System.out.println("Num pages: " + numPaginas);
				for (int i = 0; i < numPaginas; i++) {
					tempo = tempo + addTempo.nextInt(100);
					// System.out.println("Requisicao:" +  req.getNomeProcesso() + i);
					saida.append("Requisicao: " +  req.getNomeProcesso() + i + "\n");
					
					Set<Integer> paginasSorteadas = new HashSet<>();
					paginaJaAdicionada = false;
					
					int numDaPagina;
					do {
						numDaPagina = gm.gerarNumeroAleatorio();
						paginasSorteadas.add(numDaPagina);
						//se n�o h� p�gina (ocupada), cria (passa a ocupar)
						if (gm.getPaginas()[numDaPagina] == null) {
							gm.getPaginas()[numDaPagina] = new Pagina(req.getNomeProcesso(), tempo, tempo, true, true); 
							paginaJaAdicionada = true;
							break;
						}						
						
	
					} while(paginasSorteadas.size() < gm.getConfiguracoes().getQtdPaginas());
					// System.out.println("Qtd page sorteadas: " + paginasSorteadas.size());
					//se chegou at� aqui, � pq todas as p�ginas foram sorteadas. Mem�ria cheia
					//usar algoritmo de substitui��o
					
					if (!paginaJaAdicionada) {
						switch(numAlgoritmo) {
							case 0:
								//fifo.algoritmo(req);
								break;
							case 1:
								//lru.algoritmo(req);
								break;
							default:
									
						}
					}
					
					for (int j = 0; j < gm.getPaginas().length; j++) {
						if (gm.getPaginas()[j] != null) {
							saida.append(
									gm.getPaginas()[j].getNomeProcesso() + " " +
									gm.getPaginas()[j].getInstTempoArmazenada() + " " +
									gm.getPaginas()[j].getInstTempoReferenciada() + " " + 
									gm.getPaginas()[j].isReferenciado() + " " + 
									gm.getPaginas()[j].isModificado() + "\n");
						} else {
							saida.append("Vazio\n");
						}
					}
				}
			}
			
			writeFile("out/" + (a == 0 ? "fifo" : "lru") + ".txt", saida.toString());
		}
	}
}
