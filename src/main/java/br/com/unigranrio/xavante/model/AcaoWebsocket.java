package br.com.unigranrio.xavante.model;

public class AcaoWebsocket {
	private String acao;
	private Long idTanque;
	
	public AcaoWebsocket(String acao, Long idTanque) {
		this.acao = acao;
		this.idTanque = idTanque;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Long getIdTanque() {
		return idTanque;
	}
	public void setIdTanque(Long idTanque) {
		this.idTanque = idTanque;
	}
	
}
