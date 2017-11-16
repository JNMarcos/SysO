package algoritmos;

import java.util.ArrayList;
import java.util.List;

import classes_basicas.Processo;
import classes_basicas.TemposProcessamento;
import main.Main;

public class RoundRobin {
	private int quantum = 2;
	private int tempoComputacaoAlgoritmo = 0;
	private List<TemposProcessamento> tempoProcessamento;

	public RoundRobin() {
		super();
		this.tempoProcessamento = new ArrayList<TemposProcessamento>();
	}

	public void instanciarTempos() {
		for (int i = 0; i < Main.gp.getProcessos().size(); i++) {
			this.tempoProcessamento.add(new TemposProcessamento());
			this.tempoProcessamento.get(i).setTempoRestante(Main.gp.getProcessos().get(i).getTempoComputacao());
			this.tempoProcessamento.get(i).setTempoPronto(0 - Main.gp.getProcessos().get(i).getTempoChegada());
		}
	}
	public void algoritmo() {
		//está na ordem de chegada
		instanciarTempos();
		Processo p;
		TemposProcessamento tp;

		int processosFinalizados = 0;
		int tempoIO;
		int tempoInIO;
		int tempoComputado;
		while (processosFinalizados < Main.gp.getProcessos().size()) {
			for (int i = 0; i < Main.gp.getProcessos().size(); i++) {
				p = Main.gp.getProcessos().get(i);
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
					if(tempoInIO > 0 && tempoInIO < (tempoComputado + quantum)) {
						tempoIO = (p.getTemposIO().get(0).getFimIntervalo() - tempoInIO);
						tp.ultTempoIO = tempoIO;
						//tmpRest = tempRest_anterior + tempComputação
						//tempComputação = diferença entre o tempoComp (tempoComputado) e o tempoIO de entrada
						int tempoComp = tempoInIO - tempoComputado;
						tempoComputacaoAlgoritmo += tempoComp;
						tp.setTempoRestante(tp.getTempoRestante() - tempoComp);
						tp.setTempoIO(tp.getTempoIO() + tempoIO);
						//remove o tempo io da lista
						p.getTemposIO().remove(0);
					} else {
						if (tempoRestComp > quantum) {
							tp.setTempoRestante(tp.getTempoRestante() - quantum);
							tempoComputacaoAlgoritmo += quantum;
						}
						else {
							tempoComputacaoAlgoritmo += tempoRestComp;
							tp.setTempoResposta(tempoComputacaoAlgoritmo);
							tp.setTempoRestante(0);
							processosFinalizados++;
						}
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
