package classes_basicas;

public enum Comandos {
	Direita("O bra�o mover� para a direita."),
	Esquerda("O bra�o mover� para a esquerda."),
	IndicarPosicao("O bra�o est� na posi��o "),
	FicarParado("O bra�o permaneceu na mesma posi��o."),
	ChegarAUltimaPosicao("O bra�o chegou � �ltima posi��o do cilindro."),
	ChegarAPrimeiraPosicao("O bra�o chegou � primeira posi��o do cilindro."),
	PercorrerAteOInicio("O bra�o est� indo para a posi��o inicial."),
	PercorrerAteOFim("O bra�o est� indo para a posi��o final."),
	PercorrerAte("O bra�o est� indo para "),
	SomarNPosicoesPercorridas(" posi��es foram percorridas at� ");

	private String comando;

	Comandos(String comando){
		this.comando = comando;
	}

	public String getComando(){
		return comando;
	}

}
