package br.com.unigranrio.xavante.model;

import java.util.Date;

public class Medicao {

	private String codeNumber;
	private String measurementType;
	private Date measurementTypeDate;
	public String getCodeNumber() {
		return codeNumber;
	}
	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
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
