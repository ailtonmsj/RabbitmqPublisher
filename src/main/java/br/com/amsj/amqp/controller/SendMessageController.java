package br.com.amsj.amqp.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SendMessageController {
	
	@Value("${amqp.routingkey.routeOne}")
	private String routeOne;
	
	@Value("${amqp.routingkey.routeTwo}")
	private String routeTwo;
	
	@Value("${amqp.routingkey.routeThree}")
	private String routeThree;
	
	@Value("${amqp.exchange.exchangeOne.name}")
	private String exchangeOneName;
	
	@Value("${amqp.exchange.exchangeTwo.name}")
	private String exchangeTwoName;
	
	
	@Autowired
	RabbitTemplate rabbitTemplate;	
	
	@RequestMapping(value="/send/", method=RequestMethod.POST)
	public ResponseEntity<Void> sendMessage(@RequestHeader("routing-key") String routingKey, @RequestBody String message) {
		
		ResponseEntity<Void> responseEntity = null;
		
		if(!verifyRoutingKey(routingKey)) {
			responseEntity = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
		
			try {
				rabbitTemplate.convertAndSend(exchangeOneName, routingKey, message);
				responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
			}catch (Exception e) {
				e.printStackTrace();
				responseEntity = new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		return responseEntity; 
	}
	
	private boolean verifyRoutingKey(String routingKey) {
		
		if(routeOne != null && (routeOne.equals(routingKey) || routeTwo.equals(routingKey))) {
			return true;
		}else {
			return false;
		}
	}
}