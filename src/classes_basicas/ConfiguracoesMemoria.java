/**
 * 
 */
package classes_basicas;

/**
 * @author jnmar
 *
 */
public class ConfiguracoesMemoria {
	//ambos os tamanhos estão em KB
	private int tamanhoMemoria;
	private int tamanhoPagina;
	private int qtdPaginas;
	
	public ConfiguracoesMemoria() {
		
	}
	
	public ConfiguracoesMemoria(int tamanhoMemoria, int tamanhoPagina) {
		super();
		this.tamanhoMemoria = tamanhoMemoria;
		this.tamanhoPagina = tamanhoPagina;
		this.qtdPaginas = calcularQtdPaginas();
	}
	
	private int calcularQtdPaginas() {
		return (int) (Math.round((double)(this.tamanhoMemoria)/this.tamanhoPagina));
	}
	
	public int getTamanhoMemoria() {
		return tamanhoMemoria;
	}
	public void setTamanhoMemoria(int tamanhoMemoria) {
		this.tamanhoMemoria = tamanhoMemoria;
	}
	public int getTamanhoPagina() {
		return tamanhoPagina;
	}
	public void setTamanhoPagina(int tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}
	public int getQtdPaginas() {
		return qtdPaginas;
	}
	public void setQtdPaginas(int qtdPaginas) {
		this.qtdPaginas = qtdPaginas;
	}
}
