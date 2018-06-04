package br.com.unigranrio.xavante.dto;

import java.util.Date;

import br.com.unigranrio.xavante.enums.TipoEnum;
import br.com.unigranrio.xavante.model.Tanque;
import br.com.unigranrio.xavante.model.Usuario;

public class MedicaoDTO {
	private Long id;
	private String dataMedicao;
	private Long tanque;
	private String registro;
	private Integer tipo;
	private Long usuario;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDataMedicao() {
		return dataMedicao;
	}
	public void setDataMedicao(String dataMedicao) {
		this.dataMedicao = dataMedicao;
	}
	public Long getTanque() {
		return tanque;
	}
	public void setTanque(Long tanque) {
		this.tanque = tanque;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Long getUsuario() {
		return usuario;
	}
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	
	
}
