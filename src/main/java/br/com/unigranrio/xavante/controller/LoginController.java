package br.com.unigranrio.xavante.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.xavante.configurer.TokenFilter;
import br.com.unigranrio.xavante.dao.LoginDAO;
import br.com.unigranrio.xavante.model.Usuario;
import br.com.unigranrio.xavante.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="api para controle do login")
@RestController
@CrossOrigin("*")
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	UsuarioService usuarioService;

	
	@ApiOperation(value="Login de usuario")
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public ResponseEntity Autenticar(@RequestBody Usuario usuario){
		Usuario usuario2 = usuarioService.findByUsername(usuario.getUsername());
		
		if((usuario2 == null)||(!usuario.getPassword().equals(usuario2.getPassword()))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário e/ou senha incorreta");
		}

		String token = TokenFilter.gerarToken(usuario);
		Map<String, Object> retorno = new HashMap<>();
		
		retorno.put("token", token);
		retorno.put("id", usuario2.getId().toString());
		if (usuario2.getName() != null)
		retorno.put("name", usuario2.getName().toString());
		if (usuario2.getEmail() != null)
		retorno.put("email", usuario2.getEmail().toString());
		if (usuario2.getPerfil() != null)
		retorno.put("perfil", usuario2.getPerfil());
		//Sucesso		
			return new ResponseEntity<Map<String, Object>>(retorno, HttpStatus.OK);
		//Falso
			//return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
	}
}
