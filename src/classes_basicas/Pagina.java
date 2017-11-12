/**
 * 
 */
package classes_basicas;

/**
 * @author jnmar
 *
 */
public class Pagina {
	private String nomeProcesso;
	//em ambos são os últimos instantes de tempo
	private int instTempoArmazenada;
	private int instTempoReferenciada;
	
	//quando 1 = referenciado/modificado
	private boolean isReferenciado;
	private boolean isModificado;
	
	
	public Pagina() {
		
	}
	
	public Pagina(String nomeProcesso, int instTempoArmazenada, int instTempoReferenciada, boolean isReferenciado,
			boolean isModificado) {
		super();
		this.nomeProcesso = nomeProcesso;
		this.instTempoArmazenada = instTempoArmazenada;
		this.instTempoReferenciada = instTempoReferenciada;
		this.isReferenciado = isReferenciado;
		this.isModificado = isModificado;
	}
	
	public String getNomeProcesso() {
		return nomeProcesso;
	}
	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}
	public int getInstTempoArmazenada() {
		return instTempoArmazenada;
	}
	public void setInstTempoArmazenada(int instTempoArmazenada) {
		this.instTempoArmazenada = instTempoArmazenada;
	}
	public int getInstTempoReferenciada() {
		return instTempoReferenciada;
	}
	public void setInstTempoReferenciada(int instTempoReferenciada) {
		this.instTempoReferenciada = instTempoReferenciada;
	}
	public boolean isReferenciado() {
		return isReferenciado;
	}
	public void setReferenciado(boolean isReferenciado) {
		this.isReferenciado = isReferenciado;
	}
	public boolean isModificado() {
		return isModificado;
	}
	public void setModificado(boolean isModificado) {
		this.isModificado = isModificado;
	}

	@Override
	public String toString() {
		return "Pagina [nomeProcesso=" + nomeProcesso + ", instTempoArmazenada=" + instTempoArmazenada
				+ ", instTempoReferenciada=" + instTempoReferenciada + ", isReferenciado=" + isReferenciado
				+ ", isModificado=" + isModificado + "]";
	}
	
	
}
