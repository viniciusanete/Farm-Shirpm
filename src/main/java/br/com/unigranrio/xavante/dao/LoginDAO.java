package br.com.unigranrio.xavante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.unigranrio.xavante.model.Usuario;

public class LoginDAO  {
	
	

	public Usuario Autenticar(String user, String pass) {
		Connection con = null;
		Usuario usuario = new Usuario();
		PreparedStatement statement = null;
		ResultSet result = null;
		String select;
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			select = "SELECT AQUI WHERE A = ? AND B  = ?";
			statement = con.prepareStatement(select);
			statement.setString(1, user);
			statement.setString(2, pass);
			
			result = statement.executeQuery(); 
			while(result.next()) {
				//Inserir o dado dentro dos get da Classe de Login
			}
			
			return usuario;
		}catch (Exception e){
			
			return usuario;
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
	}

	public void testeConexao() {
		try{
			Connection con  = ConnectionDAO.getInstance().getConnection();
			System.out.println("conectado");
			con.close();
		}catch(Exception e) {
			
	}
		
	}
}
