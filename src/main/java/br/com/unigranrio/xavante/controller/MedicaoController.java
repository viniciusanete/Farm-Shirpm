package br.com.unigranrio.xavante.controller;

import java.util.Date;
import java.util.List;

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

import br.com.unigranrio.xavante.dto.MedicaoDTO;
import br.com.unigranrio.xavante.model.Medicao;
import br.com.unigranrio.xavante.model.Tanque;
import br.com.unigranrio.xavante.model.Usuario;
import br.com.unigranrio.xavante.service.MedicaoService;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/auth/medicao")
public class MedicaoController {
	
	@Autowired
	MedicaoService medicaoService;

	@RequestMapping(value="/manual", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity salvarMedicaoManual(@RequestBody MedicaoDTO medicaoDto) {
		Medicao medicao = atribuirMedicao(medicaoDto);
		medicao = medicaoService.salvarMedicao(medicao);
		if (medicao != null) 
			return new ResponseEntity(medicao, HttpStatus.CREATED);
		else
			return new ResponseEntity(medicao, HttpStatus.BAD_REQUEST);
		
	}
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, value="/arduino/{codigo}")
	public ResponseEntity receberMedicao(@RequestBody MedicaoDTO medicaoDto, @PathVariable String codigo) {
			Medicao medicao = atribuirMedicao(medicaoDto);
			medicao = medicaoService.salvarMedicao(codigo, medicao);
			if(medicao == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}else
				return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	//passar data no get caso procure pela data

	@RequestMapping(method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity retornarMedicoes(@RequestParam(value="data", required= false) String stringData) {
		List<Tanque> tanques;
				if(stringData == null ) 
					tanques = medicaoService.pesquisarMedicoes(stringData);
				else
					tanques = medicaoService.pesquisarMedicoes();
				
				if (tanques == null) 
					return new ResponseEntity<>("Não foram encontradas medições", HttpStatus.NO_CONTENT);
				else
					return new ResponseEntity<>(tanques, HttpStatus.OK);
	}
	//passar data no get caso procure pela data
	@RequestMapping(method=RequestMethod.GET , consumes=MediaType.APPLICATION_JSON_VALUE, value="/tanque/{idTanque}")
	public ResponseEntity retornarMedicoesTanque(@RequestParam(value="data", required = false) String stringData, @PathVariable Long idTanque  ) {
		Tanque tanque; 
		if(stringData == null)
			tanque = medicaoService.pesquisarMedicoesTanque(idTanque);
		else
			tanque = medicaoService.pesquisarMedicoesTanque(idTanque, stringData);
		
		if (tanque == null) 
			return new ResponseEntity<>("Não foram encontradas medições", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Tanque>(tanque, HttpStatus.OK);
		
	}
	
	
	private Medicao atribuirMedicao(MedicaoDTO medicaoDto) {
		Medicao medicao = new Medicao();
		if (medicaoDto.getDataMedicao() != null)
		medicao.setDataMedicao(medicaoDto.getDataMedicao());
		medicao.setId(medicaoDto.getId());
		medicao.setRegistro(medicaoDto.getRegistro());
		medicao.setTipo(medicaoDto.getTipo());
		
		Tanque tanque = new Tanque();
		tanque.setId(medicaoDto.getTanque());
		medicao.setTanque(tanque);
		
		if (medicaoDto.getUsuario() != null) {
			Usuario usu = new Usuario();
			usu.setId(medicaoDto.getUsuario());
			medicao.setUsuario(usu);			
		}
		
		return medicao;
	}
	
	
}
