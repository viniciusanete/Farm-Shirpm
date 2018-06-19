package br.com.unigranrio.xavante.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.xavante.dto.MedicaoDTO;
import br.com.unigranrio.xavante.dto.MedicaoRetornoDTO;
import br.com.unigranrio.xavante.dto.tipoEnum;
import br.com.unigranrio.xavante.enums.Acoes;
import br.com.unigranrio.xavante.enums.TipoEnum;
import br.com.unigranrio.xavante.model.AcaoWebsocket;
import br.com.unigranrio.xavante.model.Medicao;
import br.com.unigranrio.xavante.model.Tanque;
import br.com.unigranrio.xavante.model.Usuario;
import br.com.unigranrio.xavante.service.GerenteTempoReal;
import br.com.unigranrio.xavante.service.MedicaoService;
import br.com.unigranrio.xavante.util.DataUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API para controle de medição")
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping(value="/auth/medicao")
public class MedicaoController {
	
	@Autowired
	MedicaoService medicaoService;
	
    @Autowired
     SimpMessagingTemplate broker;

	@ApiOperation(value="cadastro de medição manual")
	@RequestMapping(value="/manual", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity salvarMedicaoManual(@RequestBody MedicaoDTO medicaoDto) {
		Medicao medicao = atribuirMedicao(medicaoDto);
		medicao = medicaoService.salvarMedicao(medicao);
		if (medicao != null) 
			return new ResponseEntity(medicao, HttpStatus.CREATED);
		else
			return new ResponseEntity(medicao, HttpStatus.BAD_REQUEST);
		
	}
	@ApiOperation(value="Cadastro de nedição automatica pelo arduino")
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, value="/arduino/{codigo}")
	public ResponseEntity receberMedicao(@RequestBody MedicaoDTO medicaoDto, @PathVariable String codigo) {
			Medicao medicao = atribuirMedicao(medicaoDto);
			medicao = medicaoService.salvarMedicao(codigo, medicao);
			if(medicao == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}else
				return new ResponseEntity<>(HttpStatus.CREATED);		
	}
	
	@ApiOperation(value="Cadastro de medição real pelo outro servidor")
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, value="/tanque/{codigo}/real")
	public ResponseEntity receberMedicaoServer(@RequestBody List<MedicaoDTO> medicoesDto, @PathVariable Long codigo) {
			List<Medicao> medicoes = new ArrayList<>();
			for (MedicaoDTO medicaoDto : medicoesDto ) {
				medicoes.add(atribuirMedicao(medicaoDto));
			}
			medicaoService.salvarMedicaoReal(codigo, medicoes);
				return new ResponseEntity<>(HttpStatus.OK);		
	}
	
	//passar data no get caso procure pela data
	@ApiOperation(value="Consulta de tempo real, Finalizado, passe o tanque por parametro na url que retornará as mediçoes dele, (faça um loop)")
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, value="/tanque/{idTanque}/real")
	ResponseEntity retornarMedicoes(@RequestParam(value="data", required= false) String stringData, @PathVariable Long idTanque) throws InterruptedException {
		AcaoWebsocket web = new AcaoWebsocket(Acoes.REAL.name(), idTanque);
		this.broker.convertAndSend("/topic/greetings", web);
		Thread.sleep(1000);
		GerenteTempoReal gerente = GerenteTempoReal.getGerente();
		List<MedicaoRetornoDTO> medDtolist = gerente.medicaoTanque(idTanque);
		
		return new ResponseEntity<>(medDtolist, HttpStatus.OK);
	}
//	passar data no get caso procure pela data
	@ApiOperation(value="Consulta de todas as medições de um tanque, caso deseje de uma data especifica passar no formato dd/MM/yyyy-dd/MM/yyyy na url")
	@RequestMapping(method=RequestMethod.GET , produces=MediaType.APPLICATION_JSON_VALUE, value="/tanque/{idTanque}")
	public ResponseEntity retornarMedicoesTanque(@RequestParam(value="data", required = false) String stringData, @PathVariable Long idTanque  ) {
		List<MedicaoRetornoDTO> medDto;
		if(stringData == null)
			medDto = medicaoService.pesquisarMedicoes(idTanque);
		else
			medDto = medicaoService.pesquisarMedicoes(stringData, idTanque);
		
		if (medDto == null) 
			return new ResponseEntity<>("Não foram encontradas medições", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<MedicaoRetornoDTO>>(medDto, HttpStatus.OK);
		
	}
	@ApiOperation(value="consulta dos tipos de medicao em mapa chave valor")
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, value="/mapatipos")
	public ResponseEntity<Map<TipoEnum, Integer>> retornarTiposMedicoesMapa() {
		List<tipoEnum> tipos = new ArrayList<>();
		tipoEnum  en;
		for (TipoEnum tipo : TipoEnum.values()) {
			en = new tipoEnum();
			en.id = tipo.getValor();
			en.tipo = tipo.name();
			tipos.add(en);
		}
		return new ResponseEntity(tipos, HttpStatus.OK);
	}
	
	@ApiOperation(value="consulta dos tipos de medicao")
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, value="/tipos")
	public ResponseEntity<List<TipoEnum>> retornarTiposMedicoes() {
		return new ResponseEntity(TipoEnum.values(), HttpStatus.OK);
	}
	private Medicao atribuirMedicao(MedicaoDTO medicaoDto) {
		Medicao medicao = new Medicao();
		if (medicaoDto.getDataMedicao() != null)
		medicao.setDataMedicao(DataUtil.convertDataHora(medicaoDto.getDataMedicao()));
		medicao.setId(medicaoDto.getId());
		medicao.setRegistro(medicaoDto.getRegistro());
		medicao.setTipo(medicaoDto.getTipo());
		
		
		if(medicaoDto.getTanque() != null) {
			Tanque tanque = new Tanque();
			tanque.setId(medicaoDto.getTanque());
			medicao.setTanque(tanque);
		}
			
		if (medicaoDto.getUsuario() != null) {
			Usuario usu = new Usuario();
			usu.setId(medicaoDto.getUsuario());
			medicao.setUsuario(usu);			
		}
		
		return medicao;
	}

}
