package br.com.unigranrio.xavante.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tanque {
	
	private Long  id;
	private String name;
	private Integer capacity;
	private List<Medicao> medicao; 
	
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
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public List<Medicao> getMedicao() {
		if (this.medicao == null){
			this.medicao = new ArrayList<Medicao>();
		}
		return medicao;
	}
	public void setMedicao(List<Medicao> medicao) {
		this.medicao = medicao;
	}

	
	

	
	
}
