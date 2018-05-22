package br.com.unigranrio.xavante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.unigranrio.xavante.model.Arduino;
import br.com.unigranrio.xavante.service.ArduinoService;

public class ArduinoController {

	@Autowired
	ArduinoService arduinoService;
	public ResponseEntity CadastrarArduino(Arduino arduino) {
		
		arduino = arduinoService.cadastrarArduino(arduino);
		if(arduino == null) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
}
