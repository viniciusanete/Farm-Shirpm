package br.com.unigranrio.xavante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.xavante.dao.ArduinoDAO;
import br.com.unigranrio.xavante.dto.ArduinoDTO;
import br.com.unigranrio.xavante.model.Arduino;
import br.com.unigranrio.xavante.model.Tanque;
import br.com.unigranrio.xavante.service.ArduinoService;

@RestController
@RequestMapping(value="/auth/arduino")
public class ArduinoController {

	@Autowired
	ArduinoService arduinoService;
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity CadastrarArduino(@RequestBody ArduinoDTO arduinoDTO) {
		Arduino arduino = atribuirArduino(arduinoDTO);
		arduino = arduinoService.cadastrarArduino(arduino);
		if(arduino == null) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(arduino, HttpStatus.CREATED);
		
	}
	
	private Arduino atribuirArduino(ArduinoDTO ardDto) {
		Arduino arduino = new Arduino();
		Tanque tanque = new Tanque();
		arduino.setCodigo(ardDto.getCodigo());
		arduino.setId(ardDto.getId());
		tanque.setId(ardDto.getTanque());
		arduino.setTanque(tanque);
		arduino.setTipo(ardDto.getTipo());
		return arduino;
		
	}
	
}
