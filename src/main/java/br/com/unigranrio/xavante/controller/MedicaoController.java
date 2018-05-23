package br.com.unigranrio.xavante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.xavante.model.Medicao;
import br.com.unigranrio.xavante.model.Tanque;
import br.com.unigranrio.xavante.service.MedicaoService;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/auth/medicao")
public class MedicaoController {
	
	@Autowired
	MedicaoService medicaoService;

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, value="/arduino/{codigo}/tipo/{tipo}")
	public ResponseEntity receberMedicao(@RequestBody Medicao medicao, @PathVariable String codigo, Integer tipo) {
		
			medicao = medicaoService.salvarMedicao(codigo, tipo, medicao);
			if(medicao == null) {
				return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			}else
				return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	//passar data no get caso procure pela data
	
	public  ResponseEntity retornarMedicoesTanques() {
		
	}
	//passar data no get caso procure pela data
	@RequestMapping(method=RequestMethod.GET , consumes=MediaType.APPLICATION_JSON_VALUE, value="/tanque/{idTanque}}")
	public ResponseEntity retornarMedicoesTanque(@RequestParam(value="data", required = false) String stringData, @PathVariable Long idTanque  ) {
		Tanque tanque; 
		if(stringData == null)
			tanque = medicaoService.pesquisarMedicoesTanque(idTanque);
		else
			tanque = medicaoService.pesquisarMedicoesTanque(idTanque, stringData);
		
		if (tanque == null) 
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<Tanque>(tanque, HttpStatus.OK);
		
	}
	//passar data no get caso procure pela data
	public ResponseEntity retornarTodos() {
		
	}
	
	
}
