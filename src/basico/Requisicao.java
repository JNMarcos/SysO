/**
 * 
 */
package basico;

/**
 * @author jnmar
 *
 */
public class Requisicao {
	private String nomeProcesso;//poderia ser char (como no pr�prio enunciado diz)
	private int tamanhoRequisicao;//em kb e para c�lculo de quantidade de p�ginas a serem alocadas
	
	public Requisicao(){
		
	}
	
	public Requisicao(String nomeProcesso, int tamanhoRequisicao){
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
