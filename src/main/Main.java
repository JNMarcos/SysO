/**
 * 
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import basico.Processo;
import basico.TempoIO;
import gerenciadores.GerenciadorMemoria;
import gerenciadores.GerenciadorProcessos;

/**
 * @author jnmar
 *
 */
public class Main {
	private static Scanner sc;
	private static String[] nomeArquivosLeitura = {"docs\\entradaProcessos.txt", "docs\\entradaConfigMemória.txt",
	"docs\\entradaReqMemória.txt"};
	
	private static GerenciadorProcessos gp = new GerenciadorProcessos();
	private static GerenciadorMemoria gm = new GerenciadorMemoria();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
					gp.addProcesso(p);
				}
			default:
			}
		}
	}

}
