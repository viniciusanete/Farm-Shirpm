package br.com.unigranrio.xavante.model;

import java.util.Date;

public class Tanque {
	private String codeNumber;
	private String identificator;
	private Date initialCultureDate;
	
	
	public String getCodeNumber() {
		return codeNumber;
	}
	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}
	public String getIdentificator() {
		return identificator;
	}
	public void setIdentificator(String identificator) {
		this.identificator = identificator;
	}
	public Date getInitialCultureDate() {
		return initialCultureDate;
	}
	public void setInitialCultureDate(Date initialCultureDate) {
		this.initialCultureDate = initialCultureDate;
	}
	
	
	
}
