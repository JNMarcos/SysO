/**
 * 
 */
package classes_basicas;

/**
 * @author jnmar
 *
 */
public class TemposProcessamento {
	private int tempoRestante;
	private int tempoResposta;
	private int tempoIO;
	private int tempoPronto;
	public int ultInstComp;
	public int ultTempoIO;
	
	public TemposProcessamento() {
		super();
		this.tempoIO = 0;
		this.ultInstComp = 0;
		this.ultTempoIO = 0;
	}

	public TemposProcessamento(int tempoRestante, int tempoResposta, int tempoIO, int tempoPronto) {
		super();
		this.tempoRestante = tempoRestante;
		this.tempoResposta = tempoResposta;
		this.tempoIO = tempoIO;
		this.tempoPronto = tempoPronto;
	}

	public int getTempoRestante() {
		return tempoRestante;
	}
	public void setTempoRestante(int tempoRestante) {
		this.tempoRestante = tempoRestante;
	}
	public int getTempoResposta() {
		return tempoResposta;
	}
	public void setTempoResposta(int tempoResposta) {
		this.tempoResposta = tempoResposta;
	}
	public int getTempoIO() {
		return tempoIO;
	}
	public void setTempoIO(int tempoIO) {
		this.tempoIO = tempoIO;
	}
	public int getTempoPronto() {
		return tempoPronto;
	}
	public void setTempoPronto(int tempoPronto) {
		this.tempoPronto = tempoPronto;
	}
	
	
}
