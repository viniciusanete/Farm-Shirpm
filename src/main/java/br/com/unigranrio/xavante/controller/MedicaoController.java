package br.com.unigranrio.xavante.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.xavante.model.Medicao;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/auth/medicao")
public class MedicaoController {

	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity receberMedicao(@ResponseBody Medicao medicao) {
		
	}
}
