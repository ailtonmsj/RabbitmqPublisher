package br.com.amsj.amqp.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SendMessageController {
	
	SendMessageController(@Value("${amqp.testExchange1}") String exchangeName, 
			@Value("${amqp.route1}")String routingKey){
		
		this.exchangeName = exchangeName;
		this.routingKey = routingKey;
	}
	
	@Autowired
	RabbitTemplate rabbitTemplate;	
	
	private final String exchangeName;
	private final String routingKey;
	
	@RequestMapping(value="/send/", method=RequestMethod.POST)
	public ResponseEntity<Void> sendMessage(@RequestBody String message) {
		
		ResponseEntity<Void> responseEntity = null;
		
		try {
			rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
			responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return responseEntity; 
	}
}