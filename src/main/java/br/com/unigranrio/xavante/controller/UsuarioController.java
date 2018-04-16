package br.com.unigranrio.xavante.controller;

import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.xavante.model.Usuario;
import br.com.unigranrio.xavante.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping(value="/auth/user")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity gravarUsuario(@RequestBody Usuario usuario){
		
		
		if(usuario.getPassword() == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha precisa ser preenchida");
		}else if(usuario.getUsername() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario precisa ser preenchido");
		}else {
			if(usuarioService.verificaLogin(usuario.getUsername())) {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario Existente");
			}else {
				usuario = usuarioService.salvarUsuario(usuario);
				if(usuario == null) {
					return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao salvar o usuario");
				}else {
					return  new ResponseEntity<Usuario>(HttpStatus.OK);
				}
			}
		}			
	}
	@RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity alterarUsuario(@RequestBody Usuario usuario) {
		if(usuario.getPassword() == null){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha precisa ser preenchida");
		}else if(usuario.getUsername() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario precisa ser preenchido");
		}else {
			if(!usuarioService.verificaLogin(usuario.getUsername())) {
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não existe");
			}else {
				usuario = usuarioService.alterarUsuario(usuario);
				if(usuario == null) {
					return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao salvar o usuario");
				}else {
					return  new ResponseEntity<Usuario>(HttpStatus.OK);
				}
			}
		}		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity inativarUsuario(@PathVariable Long id){
		boolean exclusao;
		exclusao = usuarioService.inativarUsuario(id);
		if(exclusao) {
			return  ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado");
		}else
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao Excluir usuario");
	}
	
	@RequestMapping(value="/username", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity buscarUsuarioPorUsername(@RequestBody String username){
		Usuario usuario;
		usuario = usuarioService.findByUsername(username);
		if(usuario == null) {
			return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario não encontrado");
		}else {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity buscarUsuarioPorId(@PathVariable Long id){
		Usuario usuario;
		usuario = usuarioService.findById(id);
		if(usuario == null) {
			return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario não encontrado");
		}else {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> buscarTodos(){
		List<Usuario> usuList;
		usuList = usuarioService.findAll();
		if(usuList == null) {
			return new ResponseEntity<List<Usuario>>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<Usuario>>(usuList, HttpStatus.OK);
		}
		
	}
}