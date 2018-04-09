package br.com.unigranrio.xavante.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.unigranrio.xavante.dao.UsuarioDAO;
import br.com.unigranrio.xavante.model.Usuario;

@Service
public class UsuarioService {

	public Usuario findByUsername(String username) {
		UsuarioDAO userDao = new UsuarioDAO();
		return userDao.findByUsername(username);
	}

	public boolean verificaLogin(String username) {
		UsuarioDAO userDao = new UsuarioDAO();
		return userDao.ExistsUsername(username);
	}

	public Usuario salvarUsuario(Usuario usuario) {
		UsuarioDAO userDao = new UsuarioDAO();
		usuario = userDao.save(usuario);
		return usuario;
	}

	public boolean inativarUsuario(Long id) {
		UsuarioDAO userDao = new UsuarioDAO();
		return userDao.setInactive(id);
	}

	public Usuario findById(Long id) {
		UsuarioDAO userDao = new UsuarioDAO();
		return userDao.findById(id);
	}

	public List<Usuario> findAll() {
		UsuarioDAO userDao = new UsuarioDAO();
		return userDao.findAll();
	}
}
