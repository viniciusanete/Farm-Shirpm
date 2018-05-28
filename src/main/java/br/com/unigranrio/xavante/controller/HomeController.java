package br.com.unigranrio.xavante.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping(value="/")
	public String home() {
		return "Helllo World";
	}
	
	
	@RequestMapping(value="/teste/{msg}", method=RequestMethod.GET)
	public void privado(@PathVariable String msg) {
		System.out.println(msg);
	}
	
	
}
