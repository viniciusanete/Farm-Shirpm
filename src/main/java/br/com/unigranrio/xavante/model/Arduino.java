package br.com.unigranrio.xavante.model;
 
import br.com.unigranrio.xavante.enums.TipoEnum;

public class Arduino {

	private Long id;
	
	private String codigo; 
	
	private Tanque tanque;
	
	private TipoEnum tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Tanque getTanque() {
		return tanque;
	}
	public void setTanque(Tanque tanque) {
		this.tanque = tanque;
	}
	public void setTanque(Long id) {
		Tanque tanque = new Tanque();
		tanque.setId(id);
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
		Arduino other = (Arduino) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
