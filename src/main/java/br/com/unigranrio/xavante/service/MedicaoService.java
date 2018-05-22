package br.com.unigranrio.xavante.service;



import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.unigranrio.xavante.dao.MedicaoDao;
import br.com.unigranrio.xavante.model.Arduino;
import br.com.unigranrio.xavante.model.Medicao;
import br.com.unigranrio.xavante.model.Tanque;
import br.com.unigranrio.xavante.util.DataUtil;

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
	
	public Tanque pesquisarMedicoesTanque(Long idTanque){
		
		MedicaoDao medicaoDao = new MedicaoDao();
		
		return medicaoDao.pesquisarMedicoes(idTanque);
		
				
	}

	public Tanque pesquisarMedicoesTanque(Long idTanque, String stringData) {
		MedicaoDao medicaoDao = new MedicaoDao();
		Map<String, Date> datas;
		datas = DataUtil.retornarRangeDatas(stringData);		
		return medicaoDao.pesquisarMedicoes(idTanque, datas.get(DataUtil.DATA_INICIAL), datas.get(DataUtil.DATA_FINAL));
	}

}
