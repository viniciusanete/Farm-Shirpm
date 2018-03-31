package br.com.unigranrio.xavante.dao;	
	
import java.sql.Connection;	
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;	
	
public class ConnectionDAO {	
	private static ConnectionDAO conexaoDAO;	
		
	public static ConnectionDAO getInstance() {	
		if(conexaoDAO == null)	
			conexaoDAO = new ConnectionDAO();	
		return conexaoDAO;	
	}	
		
	public Connection getConnection() throws SQLException {	
		//Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shrimp?user=postgres&password=root");	
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shrimp?user=postgres&password=postgres");
			
		return connection;	
	}	
	
	public static void closeConnection(Connection con) throws SQLException {
		if(con != null) {
			con.close();
		}
	}
	public static void closeConnection(Connection con, PreparedStatement statement) throws SQLException {
		if(statement != null) {
			statement.close();
		}
		closeConnection(con);
	}
	public static void closeConnection(Connection con, PreparedStatement statement, ResultSet result) throws SQLException {
		if(result != null) {
			result.close();
		}
		closeConnection(con, statement);
	}
}
