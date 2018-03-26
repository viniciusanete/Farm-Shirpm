package br.com.unigranrio.xavante.dao;

public interface IDaoPadrao<T> {

	public T save(T object);
	public Boolean delete(Long id);
	public T findById(Long id);
	public Boolean setInactive(Long id);
	public T update(T object);
}
