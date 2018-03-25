package br.com.unigranrio.xavante.service;

import org.springframework.stereotype.Service;

import br.com.unigranrio.xavante.dao.UsuarioDAO;
import br.com.unigranrio.xavante.model.Usuario;

@Service
public class UsuarioService {

	public Usuario findByUsername(String username) {
		UsuarioDAO userDao = new UsuarioDAO();
		return userDao.findByUsername(username);
	}

}
