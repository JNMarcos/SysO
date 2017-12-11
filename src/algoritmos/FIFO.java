package algoritmos;

import java.util.List;

import classes_basicas.RequisicaoMemoria;
import main.Main;

public class FIFO {	
	public FIFO() {
		super();
	}

	public void algoritmo(RequisicaoMemoria r) {
		List<Integer> ordemPaginasMaisVelhas = Main.gm.ordenarPaginasTempoArmazenamento();
		
		//atualiza as informa��es da p�gina com a nova requisicao
		//outras infos s�o desnecess�rias
		Main.gm.getPaginas()[ordemPaginasMaisVelhas.get(0)].setNomeProcesso(r.getNomeProcesso());
		Main.gm.getPaginas()[ordemPaginasMaisVelhas.get(0)].setInstTempoArmazenada(Main.tempo);
		Main.gm.getPaginas()[ordemPaginasMaisVelhas.get(0)].setReferenciado(true);
		Main.gm.getPaginas()[ordemPaginasMaisVelhas.get(0)].setModificado(true);
		
	}

}
