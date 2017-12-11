/**
 * 
 */
package gerenciadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import classes_basicas.ConfiguracoesMemoria;
import classes_basicas.Pagina;
import classes_basicas.RequisicaoMemoria;
import main.Main;

/**
 * @author jnmar
 *
 */
public class GerenciadorMemoria {
	private ConfiguracoesMemoria configuracoes;
	private Pagina[] paginas;
	private List<RequisicaoMemoria> requisicoes;
	private Random geradorNumeroPagina;

	public GerenciadorMemoria() {
		this.geradorNumeroPagina = new Random();
	}

	public GerenciadorMemoria(ConfiguracoesMemoria configuracoes, Pagina[] paginas, 
			List<RequisicaoMemoria> requisicoes) {
		super();
		this.configuracoes = configuracoes;
		this.paginas = paginas;
		this.requisicoes = requisicoes;
	}

	public ConfiguracoesMemoria getConfiguracoes() {
		return configuracoes;
	}
	public void setConfiguracoes(ConfiguracoesMemoria configuracoes) {
		this.configuracoes = configuracoes;
	}
	public Pagina[] getPaginas() {
		return paginas;
	}
	public void setPaginas(Pagina[] paginas) {
		this.paginas = paginas;
	}

	public List<RequisicaoMemoria> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<RequisicaoMemoria> requisicoes) {
		this.requisicoes = requisicoes;
	}	
	public int gerarNumeroAleatorio() {
		return geradorNumeroPagina.nextInt(configuracoes.getQtdPaginas());
	}

	//ordena as páginas pelo tempo
	public List<Integer> ordenarPaginasTempoArmazenamento() {
		List<Integer> ordemPaginasASeremRemovidas = new ArrayList<>();
		int n = 1;
		//adiciona a primeira página como a mais tempo armazenada e paga esse tempo
		ordemPaginasASeremRemovidas.add(0, 0);
		long menorInstante = Main.gm.getPaginas()[0].getInstTempoArmazenada();	
		
		//faz-se comparação com outras páginas
		for (int i = 1; i < Main.gm.getPaginas().length; i++) {
			//se for menor, indica que é mais velho
			if (Main.gm.getPaginas()[i].getInstTempoArmazenada() < menorInstante) {
				ordemPaginasASeremRemovidas.add(0, i);
				menorInstante = Main.gm.getPaginas()[i].getInstTempoArmazenada();	
			} else {
				//compara-se ordenamente com os outros, a partir do n=1
				n = 1;
				int tam = ordemPaginasASeremRemovidas.size();
				while(n < tam) {
					if (Main.gm.getPaginas()[i].getInstTempoArmazenada() < 
							Main.gm.getPaginas()[ordemPaginasASeremRemovidas.get(n)].getInstTempoArmazenada() ) {
						ordemPaginasASeremRemovidas.add(n, i);
						break;
					} else if (n == tam - 1) {//chegou no último elem e não é menorInstante q ele
						//adiciona ao fim da fila
						ordemPaginasASeremRemovidas.add(i); //os últim
					}
					n++;	
				}
			}
			
	}
		
	return ordemPaginasASeremRemovidas;
	}
}
