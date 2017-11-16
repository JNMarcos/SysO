package algoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import classes_basicas.Processo;
import classes_basicas.TemposProcessamento;
import main.Main;

public class RateMonotonic {
	ArrayList<Processo> processos = new ArrayList<Processo>();
	int LCM = 0;
	
	public RateMonotonic() {
//		processos.add(new Processo(0, 10, null, 0, 20, 0));
//		processos.add(new Processo(0, 6, null, 0, 20, 0));
//		processos.add(new Processo(0, 8, null, 0, 15, 0));
//		
//		processos.add(new Processo(5, 3, null, 0, 20, 0));
//		processos.add(new Processo(0, 2, null, 0, 5, 0));
//		processos.add(new Processo(2, 2, null, 0, 10, 0));
		processos = (ArrayList<Processo>) Main.gp.getProcessos();
		
		double V1 = 0;
		double V2 = 0;
		
		int[] values = new int[processos.size()];
		for (int i = 0; i < processos.size(); i++) {
			values[i] = processos.get(i).getPeriodo();
			
			V1 += (double)processos.get(i).getTempoComputacao() / (double)processos.get(i).getPeriodo();
		}
		
		V2 = (double)processos.size() * (Math.pow(2, 1 / (double)processos.size()) - 1);
		
		if (V1 < V2) {
			System.out.println("O sistema é programável");
		}
		
		LCM = lcm(values);
		
		processos.sort((p1, p2) -> p1.getPeriodo() - p2.getPeriodo());
		
		ArrayList<ArrayList<Integer>> linhasDoTempo = new ArrayList<>();
		
		for (int i = 0; i < processos.size(); i++) {
			ArrayList<Integer> linhaDoTempo = new ArrayList<>();
			
			int start = 0;
			for (int j = 0; j < LCM; j++) {
				if (j % processos.get(i).getPeriodo() == 0) {
					linhaDoTempo.add(i + 1);
					start = 0;
				} else if (start < processos.get(i).getTempoComputacao()) {
					linhaDoTempo.add(i + 1);
				} else {
					linhaDoTempo.add(0);
				}
				
				start++;
			}
			
			for (int j = 0; j < processos.get(i).getTempoChegada(); j++) {
				linhaDoTempo.add(0, 0);
				linhaDoTempo.remove(linhaDoTempo.size() - 1);
			}
			
			linhasDoTempo.add(linhaDoTempo);
		}
		
		ArrayList<Integer> tabela = new ArrayList<>();
		tabela = linhasDoTempo.get(0);
		
		int j = 1;
		int jZeros = 1;
		for (int i = 0; i < tabela.size(); i++) {
			if (jZeros == linhasDoTempo.size()) {
				break;
			}
			
			int zeros = 0;
			for (int k = 0; k < linhasDoTempo.get(j).size(); k++) {
				if (linhasDoTempo.get(j).get(k) == 0) {
					zeros++;
				}
			}
			
			if (zeros == linhasDoTempo.get(j).size()) {
				j++;
				jZeros++;
				continue;
			}
			
			if (tabela.get(i) == 0) {
				for (int k = 0; k < linhasDoTempo.get(j).size(); k++) {
					if (linhasDoTempo.get(j).get(k) > 0 && k < tabela.size() && tabela.get(k) == 0) {
						tabela.set(k, linhasDoTempo.get(j).get(k));
						linhasDoTempo.get(j).set(k, 0);
					}
				}
				
				int remove = linhasDoTempo.get(j).size() - LCM;
				int count = 0;
				while (count < remove) {
					linhasDoTempo.get(j).remove(0);
					count++;
				}
				
				j++;
				
				if (j >= linhasDoTempo.size()) {
					j = 1;
				}
				
				i = -1;
			} else {
				if (linhasDoTempo.get(j).get(i) > 0)
					linhasDoTempo.get(j).add(0, 0);
			}
		}
		
		TemposProcessamento[] tp = new TemposProcessamento[processos.size()];
		for (int i = 0; i < tabela.size(); i++) {
			for (int k = 0; k < processos.size(); k++) {
				if (tabela.get(i) == k + 1) {
					if (tp[k] == null) {
						tp[k] = new TemposProcessamento();
					}
					
					tp[k].setTempoIO(processos.get(k).getSomaIO());
					tp[k].setTempoResposta(tp[k].getTempoResposta() + 1);
					tp[k].setTempoPronto(tp[k].getTempoResposta() - tp[k].getTempoIO());
				}
			}
		}
		
		for (int i = 0; i < processos.size(); i++) {
			System.out.println((i + 1) + ", " + tp[i].getTempoResposta() + " " + tp[i].getTempoIO() + " " + tp[i].getTempoPronto());
		}
		
		/*int tempoNoProcesso = 0;
		int[] vezEmProcesso = new int[processos.size()];
		int processoAtual = 0;
		
		int[] tabela = new int[LCM]; 
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		int[] vezesExecutar = new int[processos.size()];
		int[][][] tempoOcupado = new int[processos.size()][][];
		for (int i = 0; i < vezesExecutar.length; i++) {
			vezesExecutar[i] = LCM / processos.get(i).getPeriodo();
			tempoOcupado[i] = new int[vezesExecutar[i]][];
			
			for (int k = 0; k < tempoOcupado[i].length; k++) {
				if (!map.containsKey(processos.get(i).getPeriodo() * k)) {
					map.put(processos.get(i).getPeriodo() * k, i);
				}
				
				tempoOcupado[i][k] = new int[processos.get(i).getTempoComputacao()];
			}
		}
		
		// 0, 5, 10, 15
		
		for (int tempo = 0; tempo < LCM; tempo++) {
			if (map.containsKey(tempo)) {
				processoAtual = map.get(tempo);
				map.remove(tempo);
				tempoNoProcesso = 0;
			}
			
			tempoOcupado[processoAtual][vezEmProcesso[processoAtual]][tempoNoProcesso] = 1;
			
			tabela[tempo] = processoAtual;
			tempoNoProcesso++;
			
			if (tempoNoProcesso == processos.get(processoAtual).getTempoComputacao()) {
				vezEmProcesso[processoAtual]++;
				
				processoAtual++;
				tempoNoProcesso = 0;
			}
		}
		
		System.out.println(tabela.toString());*/
	}
	
	private int gcd(int x, int y) {
	    return (y == 0) ? x : gcd(y, x % y);
	}

	public int gcd(int... numbers) {
	    return Arrays.stream(numbers).reduce(0, (x, y) -> gcd(x, y));
	}

	public int lcm(int... numbers) {
	    return Arrays.stream(numbers).reduce(1, (x, y) -> x * (y / gcd(x, y)));
	}
}
