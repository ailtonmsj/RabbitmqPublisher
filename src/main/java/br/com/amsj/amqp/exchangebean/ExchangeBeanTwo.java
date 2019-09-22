package br.com.amsj.amqp.exchangebean;

import org.springframework.beans.factory.annotation.Value;

public class ExchangeBeanTwo extends ExchangeBean {

	ExchangeBeanTwo(
			@Value("${amqp.exchange.exchangeTwo.name}") String name, 
			@Value("${amqp.exchange.exchangeTwo.isDurable}")boolean isDurable, 
			@Value("${amqp.exchange.exchangeTwo.isAutoDelete}")boolean isAutoDelete) {
		super(name, isDurable, isAutoDelete);
	}
}
