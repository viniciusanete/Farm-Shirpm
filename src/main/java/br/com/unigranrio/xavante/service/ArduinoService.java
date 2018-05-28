package br.com.unigranrio.xavante.service;

import org.springframework.stereotype.Service;

import br.com.unigranrio.xavante.dao.ArduinoDAO;
import br.com.unigranrio.xavante.model.Arduino;

@Service
public class ArduinoService {
	
	public Arduino cadastrarArduino(Arduino arduino) {
		ArduinoDAO arduinoDao = new ArduinoDAO();
		return arduinoDao.cadastrarArduino(arduino);
	}

	public Arduino pesquisarArduino(Long id) {
		ArduinoDAO arduinoDao = new ArduinoDAO();
		return arduinoDao.pesquisarArduino(id);
	}

	public Arduino pesquisarArduino(String  codigo) {
		ArduinoDAO arduinoDao = new ArduinoDAO();
		return arduinoDao.pesquisarArduino(codigo);
	
	}
}
