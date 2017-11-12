/**
 * 
 */
package gerenciadores;

import java.util.List;

import classes_basicas.ConfiguracoesMemoria;
import classes_basicas.Pagina;
import classes_basicas.Requisicao;

/**
 * @author jnmar
 *
 */
public class GerenciadorMemoria {
	private ConfiguracoesMemoria configuracoes;
	private Pagina[] paginas;
	private List<Requisicao> requisicoes;
	
	public GerenciadorMemoria() {
		
	}
	
	public GerenciadorMemoria(ConfiguracoesMemoria configuracoes, Pagina[] paginas, 
			List<Requisicao> requisicoes) {
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

	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}	
}
