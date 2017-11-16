/**
 * 
 */
package algoritmos;

import java.util.ArrayList;
import java.util.List;

import classes_basicas.Processo;
import classes_basicas.TemposProcessamento;
import main.Main;

/**
 * @author jnmar
 *
 */
public class SJF {
	private List<Integer> processosOrdenados;
	private int tempoComputacaoAlgoritmo = 0;
	private List<TemposProcessamento> tempoProcessamento;
	
	
	public SJF() {
		super();
		processosOrdenados = new ArrayList<Integer>();
		this.tempoProcessamento = new ArrayList<TemposProcessamento>();

	}
	
	public void instanciarTempos() {
		for (int i = 0; i < Main.gp.getProcessos().size(); i++) {
			this.tempoProcessamento.add(new TemposProcessamento());
			this.tempoProcessamento.get(i).setTempoRestante(Main.gp.getProcessos().get(i).getTempoComputacao());
			//é para ser 0 - tempo de chegada mesmo
			this.tempoProcessamento.get(i).setTempoPronto(0 - Main.gp.getProcessos().get(i).getTempoChegada());
		}
	}
	public void ordenarPorTempoExecucaoCrescente() {
		int n = 1;
		//adiciona o primeiro processo como a maior tempo de execução e paga esse tempo
		processosOrdenados.add(0, 0);
		long tempoExec = Main.gp.getProcessos().get(0).getTempoEstimadoProcessamento();
		int tempoCheg = Main.gp.getProcessos().get(0).getTempoChegada();
		
		//processo p e seu tempo estimado de computação
		Processo p;
		int tempoEstimadoComp;
		int tempoChegada;
		//faz-se comparação com outros processos
		for (int i = 1; i < Main.gp.getProcessos().size(); i++) {
			p = Main.gp.getProcessos().get(i);
			
			//calcula-se o tempo de execução estimado (tee).
			//tee = tempo computacação + tempo de io
			tempoEstimadoComp = p.getTempoEstimadoProcessamento();
			tempoChegada = p.getTempoChegada();//do p
			
			//se o tempo estimado for menor, tem prioridade
			if (tempoEstimadoComp < tempoExec && tempoChegada <= tempoCheg) {
				processosOrdenados.add(0, i);
				tempoExec = tempoEstimadoComp;	
			} else {
				//compara-se ordenamente com os outros, a partir do n=1
				n = 1;
				int tam = processosOrdenados.size();
				while(n < tam) {
					if (tempoEstimadoComp < 
							Main.gp.getProcessos().get(processosOrdenados.get(n)).getTempoEstimadoProcessamento()
							&& tempoChegada <= tempoCheg) {
						processosOrdenados.add(n, i);
						break;
					} else if (n == tam - 1) {//chegou no último elem e não é tempoExec q ele
						//adiciona ao fim da fila
						processosOrdenados.add(i); //os últim
					}
					n++;	
				}
			}
			
	}
	}
	
	public void ordenarTempoRestante() {
		//for () {
			
		//}
	}

	public void algoritmo() {
		int processosFinalizados = 0;
		//está na ordem de chegada
		instanciarTempos();
		ordenarPorTempoExecucaoCrescente();
		Processo p;
		Integer pr;
		TemposProcessamento tp;

		int tempoIO;
		int tempoInIO;
		int tempoComputado;
		while(processosFinalizados != Main.gp.getProcessos().size()) {
			for (int i = 0; i < Main.gp.getProcessos().size(); i++) 
			{
				//mesmo tamanho q o número de processos
				pr = processosOrdenados.get(i);
				p = Main.gp.getProcessos().get(pr);
				tp = tempoProcessamento.get(i);
				tempoIO = 0;
				
				
				//somente se o tempo de computacao restante for maior que 0 e 
				//o tempo de chegada
				int tempoRestComp = tp.getTempoRestante();
				if (p.getTempoChegada() <= tempoComputacaoAlgoritmo &&  tempoRestComp > 0 ) {
					tempoInIO = p.getTemposIO().size() >= 1 ? p.getTemposIO().get(0).getInicioIntervalo() : -1;
					tempoComputado = p.getTempoComputacao() - tempoRestComp;

					System.out.println(p.getTemposIO().size() + " tempoInIO " + tempoInIO);
					//calcula-se o tempoPronto
					tp.setTempoPronto(
							tp.getTempoPronto() + tempoComputacaoAlgoritmo - (tp.ultInstComp + tp.ultTempoIO)
							);
					//setar para zero o tempo de io após usar, pois não há io durante todo procedimento
					tp.ultTempoIO = 0;

					//se for menor, indica que que está dentro do intervalo, logo vai ser precisar fazer o io
					//espera-se que os intervalos estejam organizados
					if(tempoInIO > 0) {
					//if(tempoInIO > 0 && tempoInIO > tempoComputado) {	//quer dizer que vai ter um io
						tempoIO = (p.getTemposIO().get(0).getFimIntervalo() - tempoInIO);
						//tmpRest = tempRest_anterior + tempComputação
						//tempComputação = diferença entre o tempoComp (tempoComputado) e o tempoIO de entrada
						int tempoComp = (tempoInIO + tp.ultTempoIO) - tempoComputado;
						tp.ultTempoIO = tempoIO;
						tempoComputacaoAlgoritmo += tempoComp;
						tp.setTempoRestante(tp.getTempoRestante() - tempoComp);
						tp.setTempoIO(tp.getTempoIO() + tempoIO);
						//remove o tempo io da lista
						p.getTemposIO().remove(0);
					} else {
						//se não tem io, executa tudo que tem direito
						tempoComputacaoAlgoritmo += tempoRestComp;
						tp.setTempoResposta(tempoComputacaoAlgoritmo);
						tp.setTempoRestante(0);
						processosFinalizados++;
					}
					tp.ultInstComp = tempoComputacaoAlgoritmo;
				}
			}
			
			for (int j = 0; j < tempoProcessamento.size(); j++) {
				System.out.println(j + " tempoComptTotalAlgo " + tempoComputacaoAlgoritmo);
				System.out.println("Tempo Pronto: " + tempoProcessamento.get(j).getTempoPronto());
				System.out.println("Tempo Resposta: " + tempoProcessamento.get(j).getTempoResposta());
				System.out.println("Tempo Restante: " + tempoProcessamento.get(j).getTempoRestante());
				System.out.println("Tempo IO: " + tempoProcessamento.get(j).getTempoIO());
				System.out.println("throughpt: " + (double)(processosFinalizados)/tempoComputacaoAlgoritmo);
			}
		}
	}

}
