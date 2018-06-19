package br.com.unigranrio.xavante.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.unigranrio.xavante.model.AcaoWebsocket;

@Controller
@CrossOrigin(allowCredentials = "true")
public class AcaoWebsocketController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public AcaoWebsocket acao(String acao, Long tanque) {
		return new AcaoWebsocket(acao, tanque);
	}
}
