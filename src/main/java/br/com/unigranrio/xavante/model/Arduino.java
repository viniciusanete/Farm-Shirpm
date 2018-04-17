package br.com.unigranrio.xavante.model;

import br.com.unigranrio.xavante.enums.MedidoresEnum;

public class Arduino {

	private Long id;
	
	private Tanque tanque;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Tanque getTanque() {
		return tanque;
	}
	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}
	
}
