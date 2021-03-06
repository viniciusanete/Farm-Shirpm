package br.com.unigranrio.xavante.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.unigranrio.xavante.dto.MedicaoDTO;
import br.com.unigranrio.xavante.dto.MedicaoRetornoDTO;
import br.com.unigranrio.xavante.enums.TipoEnum;
import br.com.unigranrio.xavante.util.DataUtil;

public class Medicao {

	private Long id;
	private Date dataMedicao;
	private Tanque tanque;
	private String registro;
	private TipoEnum tipo;
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@JsonIgnore
	public Tanque getTanque() {
		return tanque;
	}
	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}
	public TipoEnum getTipo() {
		return tipo;
	}
	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}
	public void setTipo(Integer valor) {
		for (TipoEnum enume : TipoEnum.values()) {
			if (enume.getValor() == valor){
				this.tipo = enume;
				break;
			}
		}
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDataMedicao() {
		return dataMedicao;
	}
	public void setDataMedicao(Date dataMedicao) {
		this.dataMedicao = dataMedicao;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public MedicaoRetornoDTO devolveDto() {
		MedicaoRetornoDTO dto = new MedicaoRetornoDTO();
		dto.setDataMedicao(DataUtil.parseDataHora(this.dataMedicao));
		dto.setId(this.getId());
		dto.setRegistro(this.registro);
		dto.setTipo(this.tipo);
		dto.setTanque(this.getTanque().getId());
		return dto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicao other = (Medicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
	
}
