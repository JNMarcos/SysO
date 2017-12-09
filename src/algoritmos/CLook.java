package algoritmos;

import classes_basicas.Comandos;
import classes_basicas.Elevador;

import java.util.Arrays;

public class CLook implements Elevador {
    static int POSICAO_ATUAL = 0;
    int[] posicoes;

    public CLook(int[] posicoes) {
        this.posicoes = posicoes;
    }

    @Override
    public boolean irPara(int p) {
        if (p >= POSICAO_ATUAL) {
            POSICAO_ATUAL = p;
            System.out.println(Comandos.Direita.getComando());
            System.out.println(Comandos.IndicarPosicao.getComando() + p);

            return true;
        } else {
            System.out.println(Comandos.IrPrimeiraPosicao.getComando());
            POSICAO_ATUAL = Arrays.stream(posicoes).min().getAsInt();

            return false;
        }
    }
}
