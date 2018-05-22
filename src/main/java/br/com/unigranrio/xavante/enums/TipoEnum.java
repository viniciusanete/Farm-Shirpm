package br.com.unigranrio.xavante.enums;



public enum TipoEnum{
	PH(0), CLARIDADE(1), SALINIDADE(2);
	
	private final int valor;
	TipoEnum(int valorOpcao) {
		valor = valorOpcao;
	}
	public int getValor() {
		return valor;
	}
}
