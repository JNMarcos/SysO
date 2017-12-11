/**
 * 
 */
package classes_basicas;

/**
 * @author jnmar
 *
 */
public class RequisicaoMemoria {
	private String nomeProcesso;//poderia ser char (como no próprio enunciado diz)
	private int tamanhoRequisicao;//em kb e para cálculo de quantidade de páginas a serem alocadas
	
	public RequisicaoMemoria(){
		
	}
	
	public RequisicaoMemoria(String nomeProcesso, int tamanhoRequisicao){
		this.nomeProcesso = nomeProcesso;
		this.tamanhoRequisicao = tamanhoRequisicao;
		
	}
	
	public String getNomeProcesso() {
		return nomeProcesso;
	}
	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}
	public int getTamanhoRequisicao() {
		return tamanhoRequisicao;
	}
	public void setTamanhoRequisicao(int tamanhoRequisicao) {
		this.tamanhoRequisicao = tamanhoRequisicao;
	}

	@Override
	public String toString() {
		return "Requisicao [nomeProcesso=" + nomeProcesso + ", tamanhoRequisicao=" + tamanhoRequisicao + "]";
	}
}
