package br.com.unigranrio.xavante.model;

import java.util.Date;

import br.com.unigranrio.xavante.enums.MedidoresEnum;

public class Medicao {

	private Long id;
	private String measurementType;
	private Date measurementTypeDate;
	private Tanque tanque;
	private MedidoresEnum tipo;
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMeasurementType() {
		return measurementType;
	}
	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}
	public Date getMeasurementTypeDate() {
		return measurementTypeDate;
	}
	public void setMeasurementTypeDate(Date measurementTypeDate) {
		this.measurementTypeDate = measurementTypeDate;
	}
	public Tanque getTanque() {
		return tanque;
	}
	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}
	public MedidoresEnum getTipo() {
		return tipo;
	}
	public void setTipo(MedidoresEnum tipo) {
		this.tipo = tipo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
