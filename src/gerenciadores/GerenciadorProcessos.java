/**
 * 
 */
package gerenciadores;

import java.util.ArrayList;
import java.util.List;

import basico.Processo;

/**
 * @author jnmar
 *
 */
public class GerenciadorProcessos {
	private List<Processo> processos;
	
	public GerenciadorProcessos() {
		this.processos = new ArrayList<Processo>();
	}
	
	public GerenciadorProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}
	
	public void addProcesso(Processo processo) {
		this.processos.add(processo);
	}
	
}
