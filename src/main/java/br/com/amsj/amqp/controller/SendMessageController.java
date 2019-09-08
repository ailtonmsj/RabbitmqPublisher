package br.com.amsj.amqp.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Void> sendMessage(@RequestBody String message) {
		
		ResponseEntity<Void> responseEntity = null;
		
		try {
			rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
			responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return responseEntity; 
	}
}