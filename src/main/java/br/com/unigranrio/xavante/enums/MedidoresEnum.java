package br.com.unigranrio.xavante.enums;



public enum MedidoresEnum{
	PH(1), CLARIDADE(2), SALINIDADE(3);
	
	private final int valor;
	private MedidoresEnum(int valorOpcao) {
		valor = valorOpcao;
	}
	public int getValor() {
		return valor;
	}
}
