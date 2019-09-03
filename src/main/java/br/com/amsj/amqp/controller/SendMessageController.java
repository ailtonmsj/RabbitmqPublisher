package br.com.amsj.amqp.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SendMessageController {
	
	@Autowired
	RabbitTemplate rabbitTemplate;	
	
	final String EXCHANGE_NAME = "test_exchange_1";
	final String ROUTING_KEY = "route_1";
	
	@RequestMapping(value="/send/", method=RequestMethod.POST)
	public void sendMessage(@RequestBody String message) {
		
		rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
	}
}