package br.com.unigranrio.xavante.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.unigranrio.xavante.Teste;

@RestController
public class HomeController {
	
	@RequestMapping(value="/")
	public String home() {
		return "Helllo World";
	}
	
	
	@RequestMapping(value="/teste/{id}", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	public void privado(@PathVariable String id) {
		System.out.println(id);
	}
	

	
}
