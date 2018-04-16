package br.com.unigranrio.xavante.model;

import br.com.unigranrio.xavante.enums.MedidoresEnum;

public class Arduino {

	private Long id;
	private MedidoresEnum tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MedidoresEnum getTipo() {
		return tipo;
	}
	public void setTipo(MedidoresEnum tipo) {
		this.tipo = tipo;
	}
	
}
