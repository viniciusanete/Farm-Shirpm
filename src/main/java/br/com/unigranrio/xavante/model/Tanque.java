package br.com.unigranrio.xavante.model;

import java.util.Date;
import java.util.List;

public class Tanque {
	
	private Long  id;
	private String name;
	private Date capacity;
	private List<Arduino> arduino; 
	
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
	public List<Arduino> getArduino() {
		return arduino;
	}
	public void setArduino(List<Arduino> arduino) {
		this.arduino = arduino;
	}
	
	

	
	
}
