package br.com.unigranrio.xavante.service;



import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.unigranrio.xavante.dao.MedicaoDao;
import br.com.unigranrio.xavante.model.Arduino;
import br.com.unigranrio.xavante.model.Medicao;
import br.com.unigranrio.xavante.model.Tanque;
import br.com.unigranrio.xavante.util.DataUtil;

@Service
public class MedicaoService {
	
	public Medicao salvarMedicao(Long arduinoId, Medicao medicao) {
		ArduinoService arduinoService = new ArduinoService();
		MedicaoDao medicaoDao = new MedicaoDao();
		Arduino arduino;
		
		arduino = arduinoService.pesquisarArduino(arduinoId);
		
		medicao.setTanque(arduino.getTanque());
		medicao.setTipo(arduino.getTipo());
		return medicaoDao.save(medicao);
	}
	public Medicao salvarMedicao(String codigo, Medicao medicao) {
		ArduinoService arduinoService = new ArduinoService();
		MedicaoDao medicaoDao = new MedicaoDao();
		Arduino arduino;
		
		arduino = arduinoService.pesquisarArduino(codigo);
		
		medicao.setTanque(arduino.getTanque());
		//medicao.setTipo(arduino.getTipo());
		return medicaoDao.save(medicao);
	}
	public Tanque pesquisarMedicoesTanque(Long idTanque){
		
		MedicaoDao medicaoDao = new MedicaoDao();
		
		return medicaoDao.retornarMedicoesTanque(idTanque);
		
				
	}

	public Tanque pesquisarMedicoesTanque(Long idTanque, String stringData) {
		MedicaoDao medicaoDao = new MedicaoDao();
		Map<String, Date> datas;
		datas = DataUtil.retornarRangeDatas(stringData);		
		return medicaoDao.retornarMedicoesTanque(idTanque, datas.get(DataUtil.DATA_INICIAL), datas.get(DataUtil.DATA_FINAL));
	}
	public List<Tanque> pesquisarMedicoes() {
		MedicaoDao medicaoDao = new MedicaoDao();
		return medicaoDao.retornarMedicoes();
	}
	
	public List<Tanque> pesquisarMedicoes(String stringData) {
		MedicaoDao medicaoDao = new MedicaoDao();
		Map<String, Date> datas;
		datas = DataUtil.retornarRangeDatas(stringData);		
		return medicaoDao.pesquisarMedicoes(datas.get(DataUtil.DATA_INICIAL), datas.get(DataUtil.DATA_FINAL));
	}
	

}
