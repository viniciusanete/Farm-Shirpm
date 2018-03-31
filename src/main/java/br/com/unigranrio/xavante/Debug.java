package br.com.unigranrio.xavante;

import br.com.unigranrio.xavante.dao.UsuarioDAO;
import br.com.unigranrio.xavante.model.Perfil;
import br.com.unigranrio.xavante.model.Usuario;

public class Debug {

	public static void main(String[] args) {
		Usuario usu = new Usuario();
		UsuarioDAO usuDao = new UsuarioDAO();
		Perfil p = new Perfil();
		usu.setPassword("123");
		usu.setUsername("teste2");
		p.setId(1L);
		usu.setPerfil(p);
		
		usuDao.save(usu);
		usu = usuDao.findByUsername("teste2");

	}

}
