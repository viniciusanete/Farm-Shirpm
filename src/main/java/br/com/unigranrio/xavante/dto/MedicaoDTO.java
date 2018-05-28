package br.com.unigranrio.xavante.dto;

import java.util.Date;

import br.com.unigranrio.xavante.enums.TipoEnum;
import br.com.unigranrio.xavante.model.Tanque;
import br.com.unigranrio.xavante.model.Usuario;

public class MedicaoDTO {
	private Long id;
	private Date dataMedicao;
	private Long tanque;
	private String registro;
	private Integer tipo;
	private Usuario usuario;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataMedicao() {
		return dataMedicao;
	}
	public void setDataMedicao(Date dataMedicao) {
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
