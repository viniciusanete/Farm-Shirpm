package br.com.unigranrio.xavante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.unigranrio.xavante.model.Usuario;

public class UsuarioDAO {

	public Usuario findByUsername(String username) {
		Connection con = null;
		PreparedStatement statement = null;
		Usuario usuario = null;
		String sql;
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "SELECT * from usuarios where username = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, username);		
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				usuario = LerUsuario(result);
			}
		}catch (Exception e) {
			e.printStackTrace();
			usuario = null;
		}
		return usuario;
		
	}
	private Usuario LerUsuario(ResultSet result) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(result.getLong("id"));
		usuario.setPassword(result.getString("password"));
		usuario.setPerfil(result.getInt("perfil"));
		usuario.setUsername(result.getString("username"));
		return usuario;
	}
}
