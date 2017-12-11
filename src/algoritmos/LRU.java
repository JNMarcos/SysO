package algoritmos;

import java.util.HashMap;

import classes_basicas.Pagina;
import classes_basicas.RequisicaoMemoria;
import main.Main;

public class LRU {
	public void algoritmo(RequisicaoMemoria r) {
		Pagina[] paginas = Main.gm.getPaginas();
		HashMap<String, Long> map = new HashMap<>();
		
		long menor = Long.MAX_VALUE;
		String menorKey = "";
		int menorIndex = 0;
		
		for (int i = 0; i < paginas.length; i++) {
			if (!map.containsKey(paginas[i].getNomeProcesso()) || (map.containsKey(paginas[i].getNomeProcesso()) && map.get(paginas[i].getNomeProcesso()) < paginas[i].getInstTempoArmazenada())) {
				map.put(paginas[i].getNomeProcesso(), paginas[i].getInstTempoArmazenada());
			}
		}
		
		for (String key : map.keySet()) {
			if (map.get(key) < menor) {
				menor = map.get(key);
				menorKey = key;
			}
		}
		
		for (int i = 0; i < paginas.length; i++) {
			if (paginas[i].getNomeProcesso().equals(menorKey) && paginas[i].getInstTempoArmazenada() <= menor) {
				menor = paginas[i].getInstTempoArmazenada();
				menorIndex = i;
			}
		}
		
		//atualiza as informações da página com a nova requisicao
		//outras infos são desnecessárias
		Main.gm.getPaginas()[menorIndex].setNomeProcesso(r.getNomeProcesso());
		Main.gm.getPaginas()[menorIndex].setInstTempoArmazenada(Main.tempo);
		Main.gm.getPaginas()[menorIndex].setReferenciado(true);
		Main.gm.getPaginas()[menorIndex].setModificado(true);
	}
}
