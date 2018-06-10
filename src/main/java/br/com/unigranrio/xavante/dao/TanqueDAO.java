package br.com.unigranrio.xavante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.unigranrio.xavante.model.Medicao;
import br.com.unigranrio.xavante.model.Tanque;

public class TanqueDAO  {
	
	public Tanque CadastrarTanque(Tanque tanque) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		String sql = null;
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "insert into registro.tanque (tanq_nome, tanq_capacidade) values( ?, ? )";
			
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, tanque.getName());
			statement.setInt(2, tanque.getCapacity() );
			
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			
			if(result.next()) {
				tanque.setId(result.getLong(1));
			}else {
				tanque = null;
			}
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
			tanque = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return tanque;
	}
	
	public Tanque pesquisarTanque(Long id) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		String sql = null;
		Tanque tanque;
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "select t.tanq_id, t.tanq_nome, t.tanq_capacidade"
					+ " from registro.tanque t where t.tanq_id = ?";
			
			statement = con.prepareStatement(sql);
			statement.setLong(1, id);

			
			result = statement.executeQuery();
			
			if(result.next()) {
				tanque  = lerTanqueSemMedicao(result);
			}else {
				tanque = null;
			}		
			
			
		} catch (Exception e) {
			e.printStackTrace();
			tanque = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return tanque;
	}
	
	public static Tanque lerTanqueSemMedicao(ResultSet result) throws SQLException {
		Tanque tanque = new Tanque();
		tanque.setCapacity(result.getInt("tanq_capacidade"));
		tanque.setId(result.getLong("tanq_id"));
		tanque.setName(result.getString("tanq_nome"));
		return tanque;
	//tanque.setMedicao();

	}
	public static Tanque LerTanqueComMedicao(ResultSet result) throws SQLException {
		Tanque tanque = new Tanque();
		Medicao medicao;
		tanque.setCapacity(result.getInt("tanq_capacidade"));
		tanque.setId(result.getLong("tanq_id"));
		tanque.setName(result.getString("tanq_nome"));
		do {
			if (result.getLong("med_tanque") != tanque.getId()) {
				result.absolute(result.getRow() - 1);
				break;
				
			}
				
			
			medicao = new Medicao();
			medicao.setDataMedicao(result.getTimestamp("med_datahora"));
			medicao.setId(result.getLong("med_id"));
			medicao.setRegistro(result.getString("med_registro"));
			//evitar circular
			//medicao.setTanque(tanque);
			medicao.setTipo(result.getInt("med_tipo"));
			//medicao.setUsuario(result.);
			tanque.getMedicao().add(medicao);
		}while(result.next());
		return tanque;
	}

	public List<Tanque> buscarTodos() {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		String sql = null;
		List<Tanque> tanque = new ArrayList<>();
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "select t.tanq_id, t.tanq_nome, t.tanq_capacidade"
					+ " from registro.tanque t";
			
			statement = con.prepareStatement(sql);

			
			result = statement.executeQuery();
			
			while(result.next()) {
				tanque.add(lerTanqueSemMedicao(result));
			}
			if (tanque.size() <= 0){
				tanque = null;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			tanque = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return tanque;
	}

}
