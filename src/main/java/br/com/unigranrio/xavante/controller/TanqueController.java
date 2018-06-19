package br.com.unigranrio.xavante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.xavante.dto.TanqueDTO;
import br.com.unigranrio.xavante.model.Tanque;
import br.com.unigranrio.xavante.service.TanqueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API de controle de tanques")
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping(value="/auth/tanque")
public class TanqueController {
	
	@Autowired
	TanqueService tanqueService;
	@ApiOperation(value="Cadastro de tanques")
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity cadastrarTanque(@RequestBody TanqueDTO tanqueDTO){
		Tanque tanque = atribuirTanque(tanqueDTO);
		tanque = tanqueService.cadastrarTanque(tanque);
		
		if (tanque == null) 
			return new ResponseEntity<>("Ocorreu um erro ao salvar o tanque", HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(tanque, HttpStatus.CREATED);
	}
	@ApiOperation(value="Consulta de todos os tanques")
	@RequestMapping	(value="/tanques", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity RetornarTanques() {
		List<Tanque> tanques = tanqueService.buscarTanques();
		if (tanques != null) {
			return new ResponseEntity(tanques, HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	private Tanque atribuirTanque(TanqueDTO tanqueDTO) {
		Tanque tanque = new Tanque();
		tanque.setCapacity(tanqueDTO.getCapacity());
		tanque.setName(tanqueDTO.getName());
		tanque.setId(tanqueDTO.getId());
		return tanque;
	}
}
