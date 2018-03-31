package br.com.unigranrio.xavante.model;

import java.util.Date;

public class Tanque {
	
	private Long  id;
	private String name;
	private Date capacity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCapacity() {
		return capacity;
	}
	public void setCapacity(Date capacity) {
		this.capacity = capacity;
	}
	
	

	
	
}
