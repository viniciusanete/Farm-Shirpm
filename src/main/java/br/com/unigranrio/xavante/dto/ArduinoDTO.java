package br.com.unigranrio.xavante.dto;

import br.com.unigranrio.xavante.enums.TipoEnum;
import br.com.unigranrio.xavante.model.Tanque;

public class ArduinoDTO {
	private Long id;
	
	private String codigo; 
	
	private Long tanque;
	
	private Integer tipo;
	
	private String ip;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public Long getTanque() {
		return tanque;
	}

	public void setTanque(Long tanque) {
		this.tanque = tanque;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	

}
