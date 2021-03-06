package br.com.unigranrio.xavante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.xavante.model.Arduino;
import br.com.unigranrio.xavante.model.Tanque;

public class ArduinoDAO {
	
	public Arduino cadastrarArduino(Arduino arduino) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		String sql = null;
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "insert into registro.arduino (codigo, tipo, tanq_id, ip) values( ?, ?, ?, ?)";
			
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, arduino.getCodigo());
			statement.setInt(2, arduino.getTipo().getValor() );
			statement.setLong(3,arduino.getTanque().getId());
			statement.setString(4,arduino.getIp());

			
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			
			if(result.next()) {
				arduino.setId(result.getLong(1));
			}else {
				arduino = null;
			}
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
			arduino = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return arduino;
	}
	
	public Arduino pesquisarArduino(Long id) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		String sql = null;
		Arduino arduino;
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "select t.tanq_id, t.tanq_nome, t.tanq_capacidade, ar.arduino_id, ar.codigo, ar.tipo, ar.tanq_id, ar.ip as id_tanque, ar.ip"
					+ " from registro.arduino ar join registro.tanque t where ar.arduino_id = ?";
			
			statement =con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, id);

			
			result = statement.executeQuery();
			
			if(result.next()) {
				arduino  = lerArduino(result);
			}else {
				arduino = null;
			}		
			
			
		} catch (Exception e) {
			e.printStackTrace();
			arduino = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return arduino;
	}
	public List<Arduino> findAll(){
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		String sql = null;
		List<Arduino> ardList = new ArrayList<>();
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "select ar.arduino_id, ar.codigo, ar.tipo, ar.tanq_id as id_tanque, ar.ip"
					+ " from registro.arduino ar ";
			
			statement = con.prepareStatement(sql);
			
			result = statement.executeQuery();
			
			while(result.next()) {
				ardList.add(lerArduinoComTanque(result));
			}
			if(ardList.size() <= 0) {
				ardList = null; 
			}				
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			ardList = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ardList;
	}

	
	
	public Arduino pesquisarArduino(String codigo) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		String sql = null;
		Arduino arduino;
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "select t.tanq_id, t.tanq_nome, t.tanq_capacidade, ar.arduino_id, ar.codigo, ar.tipo, ar.tanq_id as id_tanque, ar.ip"
					+ " from registro.arduino ar join registro.tanque t on (t.tanq_id = ar.tanq_id) where ar.codigo = ? ";
			
			statement = con.prepareStatement(sql);
			statement.setString(1, codigo);
			
			result = statement.executeQuery();
			
			if(result.next()) {
				arduino  = lerArduino(result);
			}else {
				arduino = null;
			}		
			
			
		} catch (Exception e) {
			e.printStackTrace();
			arduino = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return arduino;
	}

	private Arduino lerArduinoSemTanque(ResultSet result) throws SQLException {
		Arduino arduino = new Arduino();
		arduino.setCodigo(result.getString("codigo"));
		arduino.setId(result.getLong("arduino_id"));
		arduino.setTipo(result.getInt("tipo"));
//		Tanque tanque = new Tanque();
//		tanque.setId(result.getLong("tanq_id"));
//		arduino.setTanque(tanque);		
		return arduino;
	}
	private Arduino lerArduinoComTanque(ResultSet result) throws SQLException {
		Arduino arduino = new Arduino();
		arduino.setCodigo(result.getString("codigo"));
		arduino.setId(result.getLong("arduino_id"));
		arduino.setTipo(result.getInt("tipo"));
		Tanque tanque = new Tanque();
		tanque.setId(result.getLong("id_tanque"));
		arduino.setTanque(tanque);		
		arduino.setIp(result.getString("ip"));
		return arduino;
	}
	
	private Arduino lerArduino(ResultSet result) throws SQLException {
		Arduino arduino = new Arduino();
		arduino.setCodigo(result.getString("codigo"));
		arduino.setId(result.getLong("arduino_id"));
		arduino.setTipo(result.getInt("tipo"));
		arduino.setTanque(TanqueDAO.lerTanqueSemMedicao(result));		
		return arduino;
	}

}
