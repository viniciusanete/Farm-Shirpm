package br.com.unigranrio.xavante.model;

import java.util.Date;

public class Medicao {

	private Long id;
	private String measurementType;
	private Date measurementTypeDate;
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
	
	
	
}
