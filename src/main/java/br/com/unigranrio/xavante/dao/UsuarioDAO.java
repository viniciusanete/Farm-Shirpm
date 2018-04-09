package br.com.unigranrio.xavante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.xavante.model.Perfil;
import br.com.unigranrio.xavante.model.Telefone;
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
			sql = "SELECT * from C001 u join perfis p on (u.USU_PERFIL = p.P_ID) left join telefones t on (u.usu_tel = t.tel_id)  where USU_USERNAME = ? and USU_INACTIVE = false";
			statement = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	
	public List<Usuario> findAll() {
		Connection con = null;
		PreparedStatement statement = null;
		List<Usuario> usuarioList = null;
		Integer usuId = null;
		String sql;
		ResultSet result = null;
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "SELECT * from C001 u join perfis p on (u.USU_PERFIL = p.P_ID) left join telefones t on (u.usu_tel = t.tel_id)  and USU_INACTIVE = false ORDER BY USU_ID";
			statement = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			result = statement.executeQuery();
			while(result.next()) {
				if(result.getLong("USU_ID") != usuId) {	
					usuarioList.add(LerUsuario(result));
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			usuarioList = null;
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
		return usuarioList;
		
	}
	
	public Boolean ExistsUsername(String username) {
		Connection con = null;
		PreparedStatement statement = null;
		Boolean returns = null;
		String sql;
		ResultSet result = null;
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "SELECT * from C001 u where USU_USERNAME = ? and USU_INACTIVE = false";
			statement = con.prepareStatement(sql,  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
			sql = "INSERT INTO C001(USU_USERNAME, USU_PASSWORD, USU_PERFIL) VALUES(?, ?, ?)";
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, usuario.getUsername());
			statement.setString(2, usuario.getPassword());
			statement.setLong(3, usuario.getPerfil().getId());
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
			sql = "DELETE FROM C001 where USU_ID = ? ";
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
			sql = "SELECT * from C001 u join on perfis p on (u.USU_PERFIL = p.P_ID) left join telefones t on (u.usu_tel = t.tel_id) where USU_ID = ? ORDER BY USU_ID";
			statement = con.prepareStatement(sql,  ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
			sql = "UPDATE C001 SET USU_INACTIVE = true where USU_ID = ? ";
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
			sql = "UPDATE C001 SET USU_USERNAME = ?, USU_PASSWORD = ?, USU_PERFIL = ? where USU_ID = ? ORDER BY USU_ID";
			statement = con.prepareStatement(sql);
			statement.setString(1, usuario.getUsername());
			statement.setString(2, usuario.getPassword());
			if(usuario.getPerfil() != null)
				statement.setLong(3, usuario.getPerfil().getId());
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
		Perfil perfil = new Perfil();
		Usuario usuario = new Usuario();
		List<Telefone> tel = new ArrayList<>();
		usuario.setId(result.getLong("USU_ID"));
		usuario.setPassword(result.getString("USU_PASSWORD"));
		usuario.setUsername(result.getString("USU_USERNAME"));
		usuario.setInactive(result.getBoolean("USU_INACTIVE"));
		usuario.setName(result.getString("USU_NAME"));
		usuario.setEmail(result.getString("USU_EMAIL"));
		lerPerfil(result, perfil);
		lerTelefone(result, tel, usuario.getId());
		usuario.setPerfil(perfil);
		usuario.setTelefone(tel);
		return usuario;
	}

	private Perfil lerPerfil( ResultSet result,  Perfil  perfil ) throws SQLException {
		perfil.setId(result.getLong("P_ID"));
		perfil.setCadastroUsuario(result.getBoolean("P_CAD_USER"));
		perfil.setDescricao(result.getString("P_DESCRICAO"));
		perfil.setEdicaoMedicoes(result.getBoolean("P_EDT_MED"));
		perfil.setVisualizacaoMedicoes(result.getBoolean("P_VISU_MED"));
		return perfil;		
	}
	private  List<Telefone> lerTelefone(ResultSet result, List<Telefone> telList,  Long userId) throws SQLException {
		Integer i;
		i = result.getRow();
		result.beforeFirst();
		Telefone tel;
		while(result.next()) {
			if(result.getLong("usu_tel") == userId) {
				tel = new Telefone();
				tel.setId(result.getLong("tel_id"));
				tel.setDdd(result.getString("tel_ddd"));
				tel.setNumber(result.getString("tel_number"));
				tel.setDdi(result.getString("tel_ddi"));
				telList.add(tel);
			}
			
		}
		result.absolute(i);
		return telList;
	}
}
