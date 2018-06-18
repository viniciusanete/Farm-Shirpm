package br.com.unigranrio.xavante.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.jsonwebtoken.lang.Arrays;

public enum TipoEnum{
	PH(0), CLARIDADE(1), SALINIDADE(2), TEMPERATURA(3), TRANSPARENCIA(4), MATERIA_ORGANICA(5), 
	OXIGENIO_DISSOLVIDO(6), ALCALINIDADE(7), DUREZA(8), AMONIA_TOTAL(9), NITRITO(10), NITRATO(11), 
	H2S(12), SILICATO(13);
	
	private final Integer valor;
	TipoEnum(int valorOpcao) {
		valor = valorOpcao;
	}
	public int getValor() {
		return valor;
	}
	
	@JsonCreator
	public static TipoEnum create(Integer value) {
		for (TipoEnum tipo : values()) {
			if (tipo.valor == value) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Unknown enum type " + value);
	}
	 @Override
	    public String toString() {
	        return valor.toString();
	    }
	
}
