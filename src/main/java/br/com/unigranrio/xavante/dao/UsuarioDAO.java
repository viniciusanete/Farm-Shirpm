package br.com.unigranrio.xavante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.unigranrio.xavante.model.Usuario;

public class UsuarioDAO implements IDaoPadrao<Usuario>{

	public Usuario findByUsername(String username) {
		Connection con = null;
		PreparedStatement statement = null;
		Usuario usuario = null;
		String sql;
		ResultSet result = null;
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "SELECT * from usuarios where username = ? and inactive = false";
			statement = con.prepareStatement(sql);
			statement.setString(1, username);		
			result = statement.executeQuery();
			if(result.next()) {
				usuario = LerUsuario(result);
			}
		}catch (Exception e) {
			e.printStackTrace();
			usuario = null;
		}finally {
			try{
				ConnectionDAO.closeConnection(con, statement, result);
				con = null;
				statement = null;
				result = null;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return usuario;
		
	}
	
	public Boolean ExistsUsername(String username) {
		Connection con = null;
		PreparedStatement statement = null;
		Boolean returns = null;
		String sql;
		ResultSet result = null;
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "SELECT * from usuarios where username = ? and inactive = false";
			statement = con.prepareStatement(sql);
			statement.setString(1, username);		
			result = statement.executeQuery();
			if(result.next()) {
				returns = true;
			}else
				returns = false;
		}catch (Exception e) {
			e.printStackTrace();
			returns = false;
		}finally {
			try{
				ConnectionDAO.closeConnection(con, statement, result);
				con = null;
				statement = null;
				result = null;
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return returns;
		
	}
	
	@Override
	public Usuario save(Usuario usuario) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql;
		try {
			
			con = ConnectionDAO.getInstance().getConnection();
			sql = "INSERT INTO usuarios(username, password, perfil) VALUES(?, ?, ?)";
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, usuario.getUsername());
			statement.setString(2, usuario.getPassword());
			statement.setInt(3, usuario.getPerfil());
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			if(result.next()) {
				usuario.setId(result.getLong(1));
			}		
		}catch (Exception e) {
			e.printStackTrace();
			usuario = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con, statement, result);
				con = null;				
				statement = null;
				result = null;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}
	@Override
	public Boolean delete(Long id) {
		Connection con = null;
		PreparedStatement statement = null;
		
		String sql;
		Boolean retorno = null;
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "DELETE FROM USUARIOS where id = ? ";
			statement = con.prepareStatement(sql);
			statement.setLong(1, id);
			Integer rows = statement.executeUpdate();
			if(rows > 0) 
				retorno = true;
			else
				retorno = false;
		}catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		}finally {
			try {
				ConnectionDAO.closeConnection(con, statement);
				con = null;				
				statement = null;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
	@Override
	public Usuario findById(Long id) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Usuario usuario = null;
		String sql;
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "SELECT * from usuarios where id = ?";
			statement = con.prepareStatement(sql);
			statement.setLong(1, id);		
			result = statement.executeQuery();
			if(result.next()) {
				usuario = LerUsuario(result);
			}
		}catch (Exception e) {
			e.printStackTrace();
			usuario = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con, statement, result);
				con = null;				
				statement = null;
				result = null;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usuario;
		
	}
	
	
	@Override
	public Boolean setInactive(Long id) {
		Connection con = null;
		PreparedStatement statement = null;
		String sql;
		Boolean retorno = null;
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "UPDATE usuarios SET inactive = true where id = ? ";
			statement = con.prepareStatement(sql);
			statement.setLong(1, id);
			Integer rows = statement.executeUpdate();
			if(rows > 0) 
				retorno = true;
			else
				retorno = false;
		}catch (Exception e) {
			e.printStackTrace();
			retorno = false;
		}finally {
			try {
				ConnectionDAO.closeConnection(con, statement);
				con = null;				
				statement = null;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}
	@Override
	public Usuario update(Usuario usuario) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String sql;
		Usuario usuRetorno = null;
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "UPDATE usuarios SET username = ?, password = ?, perfil = ? where id = ? ";
			statement = con.prepareStatement(sql);
			statement.setString(1, usuario.getUsername());
			statement.setString(2, usuario.getPassword());
			if(usuario.getPerfil() != null)
				statement.setInt(3, usuario.getPerfil());
			else
				statement.setNull(3, java.sql.Types.INTEGER);
			
			
			result = statement.executeQuery();
			if(result.next()) 
				usuRetorno = LerUsuario(result);
			else
				usuRetorno = null;
		}catch (Exception e) {
			e.printStackTrace();
			usuRetorno = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con, statement);
				con = null;				
				statement = null;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return usuRetorno;
	}
	
	private Usuario LerUsuario(ResultSet result) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(result.getLong("id"));
		usuario.setPassword(result.getString("password"));
		usuario.setPerfil(result.getInt("perfil"));
		usuario.setUsername(result.getString("username"));
		usuario.setInactive(result.getBoolean("inactive"));
		usuario.setName(result.getString("name"));
		usuario.setEmail(result.getString("email"));
		return usuario;
	}
}
