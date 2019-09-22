package br.com.amsj.amqp.exchangebean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("exchangeBeanOne")
public class ExchangeBeanOne extends ExchangeBean {

	ExchangeBeanOne(
			@Value("${amqp.exchange.exchangeOne.name}") String name,
			@Value("${amqp.exchange.exchangeOne.isDurable}") boolean isDurable,
			@Value("${amqp.exchange.exchangeOne.isAutoDelete}") boolean isAutoDelete) {
		super(name, isDurable, isAutoDelete);
	}
}
