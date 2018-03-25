package br.com.unigranrio.xavante.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.xavante.configurer.TokenFilter;
import br.com.unigranrio.xavante.dao.LoginDAO;
import br.com.unigranrio.xavante.model.Usuario;
import br.com.unigranrio.xavante.service.UsuarioService;

@RestController
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	UsuarioService usuarioService;

	@RequestMapping(value="/teste", method=RequestMethod.POST)
	public String Teste() {
		LoginDAO logindao = new LoginDAO();
		logindao.testeConexao();
		return null;
		
	}
	
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public ResponseEntity Autenticar(@RequestBody Usuario usuario){
		Usuario usuario2 = usuarioService.findByUsername(usuario.getUsername());
		
		if((usuario2 == null)||(!usuario.getPassword().equals(usuario2.getPassword()))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario ou senha incorreta");
		}

		String token = TokenFilter.gerarToken(usuario);
		Map<String, String> retorno = new HashMap<>();
		
		retorno.put("token", token);
		retorno.put("perfil", usuario2.getPerfil().toString());
		retorno.put("id", usuario2.getId().toString());
		//Sucesso		
			return new ResponseEntity<Map<String, String>>(retorno, HttpStatus.OK);
		//Falso
			//return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
	}
}
