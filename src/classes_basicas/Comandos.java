package classes_basicas;

public enum Comandos {
	Direita("O braço moverá para a direita."),
	Esquerda("O braço moverá para a esquerda."),
	IndicarPosicao("O braço está na posição "),
	NaoMover("O braço não se moveu."),
	IrUltimaPosicao("O braço está na última posição do cilindro."),
	IrPrimeiraPosicao("O braço está na primeira posição do cilindro.");

	private String comando;

	Comandos(String comando){
		this.comando = comando;
	}

	public String getComando(){
		return comando;
	}

}
