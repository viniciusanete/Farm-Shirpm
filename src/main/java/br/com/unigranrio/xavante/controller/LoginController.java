package br.com.unigranrio.xavante.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.xavante.dao.LoginDAO;
import br.com.unigranrio.xavante.model.Usuario;

@RestController
@RequestMapping(value="/login")
public class LoginController {

	@RequestMapping(value="/teste", method=RequestMethod.POST)
	public String Teste() {
		LoginDAO logindao = new LoginDAO();
		logindao.testeConexao();
		return null;
		
	}
	
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public ResponseEntity<Usuario> Autenticar(){
		
		// Verifica os dados
		
		//Sucesso		
			return new ResponseEntity<Usuario>(HttpStatus.OK);
		//Falso
			//return new ResponseEntity<Usuario>(HttpStatus.UNAUTHORIZED);
	}
}
