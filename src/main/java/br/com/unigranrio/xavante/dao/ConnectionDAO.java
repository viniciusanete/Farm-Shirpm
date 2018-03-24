package br.com.unigranrio.xavante.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
	private static ConnectionDAO conexaoDAO;
	
	public static ConnectionDAO getInstance() {
		if(conexaoDAO == null)
			conexaoDAO = new ConnectionDAO();
		return conexaoDAO;
	}
	
	public Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/aaa?user=postgres&password=postgres");
		
		return connection;
	}
}
