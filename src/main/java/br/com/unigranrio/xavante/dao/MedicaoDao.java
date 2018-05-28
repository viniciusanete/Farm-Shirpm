package br.com.unigranrio.xavante.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.unigranrio.xavante.model.Medicao;
import br.com.unigranrio.xavante.model.Tanque;

public class MedicaoDao implements IDaoPadrao<Medicao> {

	@Override
	public Medicao save(Medicao medicao) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		String sql = null;
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "insert into registro.medicao( med_datahora, med_registro, med_tanque, med_tipo ) values( ?, ?, ?, ?)";
			
			statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setTimestamp(1, new Timestamp(medicao.getDataMedicao().getTime()));
			statement.setString(2, medicao.getRegistro());
			statement.setLong(3, medicao.getTanque().getId());
			statement.setInt(4, medicao.getTipo().getValor());			
			statement.executeUpdate();
			result = statement.getGeneratedKeys();
			
			if(result.next()) {
				medicao.setId(result.getLong(1));
			}else {
				medicao = null;
			}
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
			medicao = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return medicao;
	}
	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medicao findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean setInactive(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medicao update(Medicao object) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tanque retornarMedicoesTanque(Long idTanque) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		Tanque tanque;
		String sql = null;
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "select tanq_id, tanq_capacidade, tanq_nome,med_id, med_datahora, med_registro, med_tanque, med_registro, med_tipo"
					+ " from registro.medicao m join registro.tanque t on t.tanq_id = m.med_tanque "
					+ "where t.tanq_id = ?";
			
			statement = con.prepareStatement(sql);
			statement.setLong(1, idTanque);
					
			result = statement.executeQuery();

			
			if (result.next()) {
				tanque = TanqueDAO.LerTanqueComMedicao(result);
			}
			else 
				tanque = null;
			
			
			
			
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

	public Tanque retornarMedicoesTanque(Long idTanque, Date datainicial, Date dataFinal) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		Tanque tanque;
		String sql = null;
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "select tanq_id, tanq_capacidade, tanq_nome, med_datahora, med_registro, med_tanque, med_registro"
					+ " from registro.medicao m join registro.tanque t on m.tanq_id = med_tanque "
					+ "where (t.tanq_id = ?) and (med_datahora between ? and ?)";
			
			statement = con.prepareStatement(sql);
			statement.setLong(1, idTanque);
			statement.setDate(2, new java.sql.Date(datainicial.getTime()));
			statement.setDate(3, new java.sql.Date(dataFinal.getTime()));
					
			result = statement.executeQuery();
			
			if (result.next()) {
				tanque = TanqueDAO.LerTanqueComMedicao(result);
			}
			else 
				tanque = null;
			
			
			
			
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

	public List<Tanque> retornarMedicoes() {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		List<Tanque> tanques = new ArrayList<>();
		String sql = null;
		Integer quantidade;
		
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "select tanq_id, tanq_capacidade, tanq_nome, med_datahora, med_registro, med_tanque, med_registro"
					+ " from registro.medicao m join registro.tanque t on m.tanq_id = med_tanque order by tanq_id ";
			
			con.prepareStatement(sql);
			result = statement.executeQuery();
			
			result.last();
			quantidade = result.getRow();
			result.beforeFirst();
			
			if(quantidade <= 0)
				tanques = null;
			
			while (result.next()) {
				tanques.add(TanqueDAO.LerTanqueComMedicao(result));
			}									
		} catch (Exception e) {
			e.printStackTrace();
			tanques = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return tanques;
	}

	public List<Tanque> pesquisarMedicoes(Date datainicial, Date dataFinal) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null; 
		List<Tanque> tanques = new ArrayList<>();
		String sql = null;
		Integer quantidade;
		
		
		try {
			con = ConnectionDAO.getInstance().getConnection();
			sql = "select tanq_id, tanq_capacidade, tanq_nome, med_datahora, med_registro, med_tanque, med_registro"
					+ " from registro.medicao m join registro.tanque t on m.tanq_id = med_tanque "
					+ "where (med_datahora between ? and ?) order by tanq_id, med_datahora";
			
			con.prepareStatement(sql);
			
			statement.setDate(2, new java.sql.Date(datainicial.getTime()));
			statement.setDate(3, new java.sql.Date(dataFinal.getTime()));
					
			result = statement.executeQuery();
			
			result.last();
			quantidade = result.getRow();
			result.beforeFirst();
			
			if(quantidade <= 0)
				tanques = null;
			
			while (result.next()) {
				tanques.add(TanqueDAO.LerTanqueComMedicao(result));
			}
						
			
		} catch (Exception e) {
			e.printStackTrace();
			tanques = null;
		}finally {
			try {
				ConnectionDAO.closeConnection(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return tanques;
	}

}



