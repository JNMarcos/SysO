/**
 * 
 */
package classes_basicas;

import java.util.List;

/**
 * @author jnmar
 *
 */
public interface Elevador {
	static final int nPosicoes = 200;
	public void caminhar(boolean sentido, List<Integer> posicoes);
	public int getSomaCilindrosPercorridos();
	public int getPosicaoAtual();
}
