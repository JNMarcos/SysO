/**
 * 
 */
package classes_basicas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jnmar
 *
 */
public class RequisicoesDisco {
	List<Integer> conjReq;
	List<Integer> pAntesPInicial;
	List<Integer> pDepoisPInicial;
	
	
	public RequisicoesDisco() {
		super();
		conjReq = new ArrayList<Integer>();
		pAntesPInicial = new ArrayList<Integer>();
		pDepoisPInicial = new ArrayList<Integer>();
	}
	
	public void organizarRequisicoes(int posicaoInicial, int tipo) {
		pDepoisPInicial.clear();
		pAntesPInicial.clear();
		
		for (int e: conjReq) {
			if (e >= posicaoInicial) pDepoisPInicial.add(e);
			else pAntesPInicial.add(e);
		}
		
		if (tipo == 0) {//se for scan ou look
			Collections.sort(pAntesPInicial);
			Collections.reverse(pAntesPInicial);
			Collections.sort(pDepoisPInicial);
		} else if (tipo == 1) {//se for circular para a direita
			Collections.sort(pAntesPInicial);
			Collections.sort(pDepoisPInicial);
		} else {//se for circular para a esquerda
			Collections.sort(pAntesPInicial);
			Collections.reverse(pAntesPInicial);
			Collections.sort(pDepoisPInicial);
			Collections.reverse(pDepoisPInicial);
		}
	}
	
	public List<Integer> getconjReq() {
		return conjReq;
	}
	public void setconjReq(List<Integer> conjReq) {
		this.conjReq = conjReq;
	}
	public List<Integer> getpAntesPInicial() {
		return pAntesPInicial;
	}
	public void setpAntesPInicial(List<Integer> pAntesPInicial) {
		this.pAntesPInicial = pAntesPInicial;
	}
	public List<Integer> getpDepoisPInicial() {
		return pDepoisPInicial;
	}
	public void setpDepoisPInicial(List<Integer> pDepoisPInicial) {
		this.pDepoisPInicial = pDepoisPInicial;
	}
	
	
}
