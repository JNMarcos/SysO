/**
 * 
 */
package basico;

import java.util.List;

/**
 * @author jnmar
 *
 */
public class Processo {
	private int tempoChegada;
	private int tempoComputacao;
	private List<TempoIO> temposIO;
	private int prioridade;
	private int periodo;
	private int deadline;
	
	public Processo(){
		
	}
	
	public Processo(int tempoChegada, int tempoComputacao, List<TempoIO> temposIO, int prioridade, int periodo, int deadline){
		this.tempoChegada = tempoChegada;
		this.tempoComputacao = tempoComputacao;
		this.temposIO = temposIO;
		this.prioridade = prioridade;
		this.periodo = periodo;
		this.deadline = deadline;
	}
	
	public int getTempoChegada() {
		return tempoChegada;
	}
	public void setTempoChegada(int tempoChegada) {
		this.tempoChegada = tempoChegada;
	}
	public int getTempoComputacao() {
		return tempoComputacao;
	}
	public void setTempoComputacao(int tempoComputacao) {
		this.tempoComputacao = tempoComputacao;
	}
	public List<TempoIO> getTemposIO() {
		return temposIO;
	}
	public void setTemposIO(List<TempoIO> temposIO) {
		this.temposIO = temposIO;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public int getDeadline() {
		return deadline;
	}
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	@Override
	public String toString() {
		return "Processo [tempoChegada=" + tempoChegada + ", tempoComputacao=" + tempoComputacao + ", temposIO="
				+ temposIO + ", prioridade=" + prioridade + ", periodo=" + periodo + ", deadline=" + deadline + "]";
	}
}
