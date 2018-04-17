package br.com.unigranrio.xavante.enums;



public enum MedidoresEnum{
	PH(0), CLARIDADE(1), SALINIDADE(2);
	
	private final int valor;
	private MedidoresEnum(int valorOpcao) {
		valor = valorOpcao;
	}
	public int getValor() {
		return valor;
	}
}
