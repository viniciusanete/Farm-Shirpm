package br.com.unigranrio.xavante.controller;

import java.util.ArrayList;
import java.util.List;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Api para manipulação do arduino")
@RestController
@RequestMapping(value="/auth/arduino")
public class ArduinoController {

	@Autowired
	ArduinoService arduinoService;
	
	@ApiOperation(value="Cadastro de arduino")
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity CadastrarArduino(@RequestBody ArduinoDTO arduinoDTO) {
		Arduino arduino = atribuirArduino(arduinoDTO);
		arduino = arduinoService.cadastrarArduino(arduino);
		if(arduino == null) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(arduino, HttpStatus.CREATED);
		
	}
	
	@ApiOperation(value="Retorno dos arduinos")
	@RequestMapping(method=RequestMethod.GET,value="/all", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Arduino>> retornarArduinos(){
		List<Arduino> arduinos = new ArrayList<>();
		arduinos = arduinoService.pesquisarArduinos();
		
		if(arduinos != null) {
			return new ResponseEntity<List<Arduino>>(arduinos, HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
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
