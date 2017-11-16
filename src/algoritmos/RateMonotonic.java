package algoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import classes_basicas.Processo;

public class RateMonotonic {
	ArrayList<Processo> processos = new ArrayList<Processo>();
	int LCM = 0;
	
	public RateMonotonic() {
		processos.add(new Processo(0, 3, null, 0, 20, 0));
		processos.add(new Processo(0, 2, null, 0, 5, 0));
		processos.add(new Processo(0, 2, null, 0, 10, 0));
		
		LCM = lcm(20, 10, 5);
		
		processos.sort((p1, p2) -> p1.getPeriodo() - p2.getPeriodo());
		
		ArrayList<int[]> linhasDoTempo = new ArrayList<>();
		
		for (int i = 0; i < processos.size(); i++) {
			int[] linhaDoTempo = new int[LCM];
			
			int start = 0;
			for (int j = 0; j < LCM; j++) {
				if (j % processos.get(i).getPeriodo() == 0) {
					linhaDoTempo[j] = i + 1;
					start = 0;
				}
				
				if (start < processos.get(i).getTempoComputacao()) {
					linhaDoTempo[j] = i + 1;
				}
				
				start++;
			}
			
			linhasDoTempo.add(linhaDoTempo);
		}
		
		int[] tabela = new int[LCM];
		tabela = linhasDoTempo.get(0);
		
		for (int i = 0; i < tabela.length; i++) {
			if (tabela[i] == 0) {
				for (int j = 1; j < linhasDoTempo.size(); j++) {
					for (int k = 0; k < linhasDoTempo.get(j).length; k++) {
						if (linhasDoTempo.get(j)[k] > 0 && tabela[i + k] == 0) {
							tabela[i + k] = linhasDoTempo.get(j)[k];
						}
					}
				}
			}
		}
		
		if (linhasDoTempo.size() == 2) {
			
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
