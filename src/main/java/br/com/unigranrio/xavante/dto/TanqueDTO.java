package br.com.unigranrio.xavante.dto;

import java.util.List;

import br.com.unigranrio.xavante.model.Medicao;

public class TanqueDTO {
	private Long  id;
	private String name;
	private Integer capacity;
	private List<Integer> medicao;
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
	public List<Integer> getMedicao() {
		return medicao;
	}
	public void setMedicao(List<Integer> medicao) {
		this.medicao = medicao;
	} 
	
	
}
