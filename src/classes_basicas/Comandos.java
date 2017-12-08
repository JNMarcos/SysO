package classes_basicas;

public enum Comandos {
	Direita("O bra�o mover� para a direita."),
	Esquerda("O bra�o mover� para a esquerda."),
	IndicarPosicao("O bra�o est� na posi��o "),
	NaoMover("O bra�o n�o se moveu."),
	IrUltimaPosicao("O bra�o est� na �ltima posi��o do cilindro."),
	IrPrimeiraPosicao("O bra�o est� na primeira posi��o do cilindro.");

	private String comando;

	Comandos(String comando){
		this.comando = comando;
	}

	public String getComando(){
		return comando;
	}

}
