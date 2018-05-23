package br.com.unigranrio.xavante.service;

import br.com.unigranrio.xavante.dao.ArduinoDAO;
import br.com.unigranrio.xavante.model.Arduino;

public class ArduinoService {
	
	public Arduino cadastrarArduino(Arduino arduino) {
		ArduinoDAO arduinoDao = new ArduinoDAO();
		return arduinoDao.cadastrarArduino(arduino);
	}

	public Arduino pesquisarArduino(Long id) {
		ArduinoDAO arduinoDao = new ArduinoDAO();
		return arduinoDao.pesquisarArduino(id);
	}

	public Arduino pesquisarArduino(String  codigo, Integer tipo) {
		ArduinoDAO arduinoDao = new ArduinoDAO();
		return arduinoDao.pesquisarArduino(codigo, tipo);
	
	}
}
