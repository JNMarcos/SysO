/**
 * 
 */
package classes_basicas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Main;

/**
 * @author jnmar
 *
 */
public class ES {
	private static Scanner sc;
	private static String[] nomeArquivosLeitura = {"docs\\entradaProcessos.txt", "docs\\entradaConfigMemória.txt",
	"docs\\entradaReqMemória.txt"};

	public ES() {

	}
	
	public void ler() {
		int maiorTempoArmazenamento = 0;
		int i;
		String linha = "";
		String[] param;
		for (i = 0; i < nomeArquivosLeitura.length; i++) {
			try {
				sc = new Scanner(new File(nomeArquivosLeitura[i]));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch(i) {
				case 0:
					while (sc.hasNext()) {
						linha = sc.nextLine().replace("ï»¿", "");
						System.out.println(linha);
						param = linha.split(" ");
						
						String[] tempIO = param[2].split(";");
						List<TempoIO> temposIO = new ArrayList<>();
						if (linha.contains(":")) {
							temposIO = new ArrayList<TempoIO>();
							for (String s: tempIO) {
								s = s.replaceFirst("\\Q[\\E", "");
								s = s.replaceFirst("\\Q]\\E", "");
								TempoIO t = new TempoIO(Integer.parseInt(s.split(":")[0]), Integer.parseInt(s.split(":")[1]));
								temposIO.add(t);
							}
						}
						
						Processo p = new Processo(Integer.parseInt(param[0]), Integer.parseInt(param[1]), temposIO, Integer.parseInt(param[3]), Integer.parseInt(param[4]),
								Integer.parseInt(param[5]));
						System.out.println(p.toString());
						Main.gp.addProcesso(p);
					}
					break;
				case 1:
					linha = sc.nextLine().replace("ï»¿", "");
					System.out.println(linha);
					param = linha.split(";");
					
					ConfiguracoesMemoria configuracoes = new ConfiguracoesMemoria(Integer.parseInt(param[0]),
							Integer.parseInt(param[1]));
					
					//cria-se um conjunto de páginas, que representam a memória
					Pagina[] paginas = new Pagina[configuracoes.getQtdPaginas()];
					
					while (sc.hasNext()) {
						linha = sc.nextLine().replace("ï»¿", "");
						System.out.println(linha);
						param = linha.split(" ");
	
						boolean resRefereciado = (param[4]).equals("1")? true : false;
						boolean resModificado = (param[5]).equals("1")? true : false;
						
						//faz uma comparação
						maiorTempoArmazenamento = Integer.parseInt(param[2]) > maiorTempoArmazenamento ? 
								Integer.parseInt(param[2]) : maiorTempoArmazenamento;

						//cria a página na memória, com as configurações existentes no arquivo
						paginas[Integer.parseInt(param[0])] = new Pagina(
								param[1], Integer.parseInt(param[2]), Integer.parseInt(param[3]),
								resRefereciado, resModificado);
						System.out.println(paginas[Integer.parseInt(param[0])].toString());
					}
					
					Main.gm.setConfiguracoes(configuracoes);
					Main.gm.setPaginas(paginas);
					break;
				case 2:
					List<Requisicao> requisicoes = new ArrayList<>();
					
					while (sc.hasNext()) {
						linha = sc.nextLine().replace("ï»¿", "");
						System.out.println(linha);
						param = linha.split(" ");
						
						requisicoes.add(new Requisicao(param[0], Integer.parseInt(param[1])));
						System.out.println(requisicoes.get(requisicoes.size() - 1).toString());
					}
					Main.gm.setRequisicoes(requisicoes);
					break;
				default:
					System.out.println("Olha o problema aparecendo");
			}
		}
		
		Main.tempo = maiorTempoArmazenamento;
	}
}
