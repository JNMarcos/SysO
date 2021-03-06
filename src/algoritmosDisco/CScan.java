package algoritmosDisco;

import java.util.List;

import classes_basicas.Comandos;
import classes_basicas.Elevador;

public class CScan implements Elevador {
    private int posicaoAtual;
	private int somaCilindrosPercorridos;

	private boolean jaExecutouCaminhar;
	
    public CScan(int posicaoInicial){
        this.posicaoAtual = posicaoInicial;
		this.somaCilindrosPercorridos = 0;
        this.jaExecutouCaminhar = false;
    }

    public int getPosicaoAtual() {
		return this.posicaoAtual;
	}
	
	public int getSomaCilindrosPercorridos() {
		return this.somaCilindrosPercorridos;
	}
	
	@Override
	public void caminhar(boolean sentido, List<Integer> posicoes) {
		for (int p: posicoes) {
			if (p != posicaoAtual) {
				if (sentido) System.out.println(Comandos.Direita.getComando());
				else System.out.println(Comandos.Esquerda.getComando());
				somaCilindrosPercorridos += Math.abs(posicaoAtual - p); //a diferen�a entre a posicao
				posicaoAtual = p;
			}
			else System.out.println(Comandos.FicarParado.getComando());
			
			System.out.println(Comandos.IndicarPosicao.getComando() + p);
		}
		
		//inverte a cada conjunto de requisi��es o valor de jaExecutouCaminhar
		if (jaExecutouCaminhar) jaExecutouCaminhar = false;
		else jaExecutouCaminhar = true;
					
		//direita = verdadeiro
		if (sentido && jaExecutouCaminhar) {
			System.out.println(Comandos.ChegarAUltimaPosicao.getComando());
			somaCilindrosPercorridos += ((nPosicoes - 1) - posicaoAtual);
			System.out.println(Comandos.PercorrerAteOInicio.getComando());
			somaCilindrosPercorridos += (nPosicoes - 1);//pois anda por um sentido sem fazer nenh req
			posicaoAtual = 0; //chega � posi��o 0
		} else if (!sentido && jaExecutouCaminhar) {
			System.out.println(Comandos.ChegarAPrimeiraPosicao.getComando());
			somaCilindrosPercorridos += posicaoAtual;
			System.out.println(Comandos.PercorrerAteOFim.getComando());
			somaCilindrosPercorridos += (nPosicoes - 1);
			posicaoAtual = nPosicoes - 1;
		}
		jaExecutouCaminhar = true;		
	}
}
