package br.com.unigranrio.xavante;

import br.com.unigranrio.xavante.dao.UsuarioDAO;
import br.com.unigranrio.xavante.model.Usuario;

public class Debug {

	public static void main(String[] args) {
		Usuario usu = new Usuario();
		UsuarioDAO usuDao = new UsuarioDAO();
		
		usu.setPassword("123");
		usu.setUsername("1hugo");
		usu.setPerfil(1);
		
		usuDao.save(usu);

	}

}
