package classes_basicas;

public enum Comandos {
	Direita("O braço moverá para a direita."),
	Esquerda("O braço moverá para a esquerda."),
	IndicarPosicao("O braço está na posição "),
	FicarParado("O braço permaneceu na mesma posição."),
	ChegarAUltimaPosicao("O braço chegou à última posição do cilindro."),
	ChegarAPrimeiraPosicao("O braço chegou à primeira posição do cilindro."),
	PercorrerAteOInicio("O braço está indo para a posição inicial."),
	PercorrerAteOFim("O braço está indo para a posição final."),
	PercorrerAte("O braço está indo para "),
	SomarNPosicoesPercorridas(" posições foram percorridas até ");

	private String comando;

	Comandos(String comando){
		this.comando = comando;
	}

	public String getComando(){
		return comando;
	}

}
