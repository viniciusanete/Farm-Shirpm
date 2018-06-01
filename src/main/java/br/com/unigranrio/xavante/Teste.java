package br.com.unigranrio.xavante;

public  class Teste {
	private String texto;
	private int quantidade;
	
	public Teste(String texto, int quantidade) {
		this.texto = texto;
		this.quantidade = quantidade;			
	}
	public Teste() {
		
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getTexto() {
		return texto;
	}

	public int getQuantidade() {
		return quantidade;
	}
	@Override
	public String toString() {
		return "Teste [texto=" + texto + ", quantidade=" + quantidade + "]";
	}
	
}