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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FIFO fifo = new FIFO();
		LRU lru = new LRU();
		
		for (int a = 0; a < 2; a++) {
			//entrada-saída
			ES io = new ES();
			io.ler();
			
			//apenas para testar o algoritmo
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
						//se não há página (ocupada), cria (passa a ocupar)
						if (gm.getPaginas()[numDaPagina] == null) {
							gm.getPaginas()[numDaPagina] = new Pagina(req.getNomeProcesso(), tempo, tempo, true, true); 
							paginaJaAdicionada = true;
							break;
						}						
						
	
					} while(paginasSorteadas.size() < gm.getConfiguracoes().getQtdPaginas());
					// System.out.println("Qtd page sorteadas: " + paginasSorteadas.size());
					//se chegou até aqui, é pq todas as páginas foram sorteadas. Memória cheia
					//usar algoritmo de substituição
					
					if (!paginaJaAdicionada) {
						switch(numAlgoritmo) {
							case 0:
								fifo.algoritmo(req);
								break;
							case 1:
								lru.algoritmo(req);
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
}
