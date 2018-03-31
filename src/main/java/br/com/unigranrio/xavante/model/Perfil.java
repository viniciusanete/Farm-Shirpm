package br.com.unigranrio.xavante.model;

public class Perfil {
	
	private Long id;
	private String descricao;
	private Boolean cadastroUsuario;
	private Boolean edicaoMedicoes;
	private Boolean visualizacaoMedicoes;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getCadastroUsuario() {
		return cadastroUsuario;
	}
	public void setCadastroUsuario(Boolean cadastroUsuario) {
		this.cadastroUsuario = cadastroUsuario;
	}
	public Boolean getEdicaoMedicoes() {
		return edicaoMedicoes;
	}
	public void setEdicaoMedicoes(Boolean edicaoMedicoes) {
		this.edicaoMedicoes = edicaoMedicoes;
	}
	public Boolean getVisualizacaoMedicoes() {
		return visualizacaoMedicoes;
	}
	public void setVisualizacaoMedicoes(Boolean visualizacaoMedicoes) {
		this.visualizacaoMedicoes = visualizacaoMedicoes;
	}
	
	
	
	

	
}
