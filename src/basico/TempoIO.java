/**
 * 
 */
package basico;

/**
 * @author jnmar
 *
 */
public class TempoIO {
	private int inicioIntervalo;
	private int fimIntervalo;
	
	public TempoIO(){
		
	}
	
	public TempoIO(int inicioIntervalo, int fimIntervalo){
		this.inicioIntervalo = inicioIntervalo;
		this.fimIntervalo = fimIntervalo;
	}
	
	public int getInicioIntervalo() {
		return inicioIntervalo;
	}
	public void setInicioIntervalo(int inicioIntervalo) {
		this.inicioIntervalo = inicioIntervalo;
	}
	public int getFimIntervalo() {
		return fimIntervalo;
	}
	public void setFimIntervalo(int fimIntervalo) {
		this.fimIntervalo = fimIntervalo;
	}

	@Override
	public String toString() {
		return "TempoIO [inicioIntervalo=" + inicioIntervalo + ", fimIntervalo=" + fimIntervalo + "]";
	}
}
