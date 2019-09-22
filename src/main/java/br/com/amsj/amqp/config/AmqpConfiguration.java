package br.com.amsj.amqp.config;

import java.util.HashMap;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.amsj.amqp.exchangebean.ExchangeBean;
import br.com.amsj.amqp.queuebean.QueueBean;

@Configuration
public class AmqpConfiguration {
	
	public AmqpConfiguration(
			@Value("${amqp.configuration.host}") String rabbitmqHost, 
			@Value("${amqp.configuration.port}") Integer rabbitmqPort, 
			@Value("${amqp.configuration.username}") String rabbitmqUsername,
			@Value("${amqp.configuration.password}") String rabbitmqPassword) {
		
		this.rabbitmqHost = rabbitmqHost;
		this.rabbitmqPort = rabbitmqPort;
		this.rabbitmqUsername = rabbitmqUsername;
		this.rabbitmqPassword = rabbitmqPassword;
	}
	
	private final String rabbitmqHost;
	private final Integer rabbitmqPort;
	private final String rabbitmqUsername;
	private final String rabbitmqPassword;
	
	@Bean
	ConnectionFactory connectionFactory() {
		
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rabbitmqHost, rabbitmqPort);
		cachingConnectionFactory.setUsername(rabbitmqUsername);
		cachingConnectionFactory.setPassword(rabbitmqPassword);
		
		return cachingConnectionFactory;
	}
	
	@Autowired
	private QueueBean queueBeanOne;
	
	@Autowired
	private QueueBean queueBeanTwo;
	
	@Autowired
	private QueueBean queueBeanThree;
	
	@Autowired
	private ExchangeBean exchangeBeanOne;
	
	@Autowired
	private ExchangeBean exchangeBeanTwo;
	
	@Bean
	public Queue queueOne() {
		return QueueBuilder.nonDurable(queueBeanOne.getName()).build();
	}
	
	@Bean
	public Queue queueTwo() {
		return new Queue(queueBeanTwo.getName(), queueBeanTwo.isDurable());
	}
	
	@Bean
	public Queue queueThree() {
		return new Queue(queueBeanThree.getName(), queueBeanThree.isDurable());
	}
	
	@Bean
	public Exchange exchangeOne() {
		return new DirectExchange(exchangeBeanOne.getName(), exchangeBeanOne.isDurable(), exchangeBeanOne.isAutoDelete());
	}
	
	@Bean
	public Exchange exchangeTwo() {
		return ExchangeBuilder.directExchange(exchangeBeanTwo.getName()).durable(exchangeBeanTwo.isDurable()).build();
	}
	
	@Bean
	public Binding bindingQueueOne(Queue queueOne, Exchange exchangeOne, @Value("${amqp.routingkey.routeOne}") String routingKey) {
		return BindingBuilder.bind(queueOne).to(exchangeOne).with(routingKey).noargs();
	}
	
	@Bean
	public Binding bindigQueueTwo(@Value("${amqp.routingkey.routeTwo}") String routingKey) {
		return new Binding(queueBeanTwo.getName(), DestinationType.QUEUE, exchangeBeanOne.getName(), routingKey, new HashMap<>());
	}
	
	@Bean
	public Binding bindigQueueThree(@Value("${amqp.routingkey.routeThree}") String routingKey) {
		return new Binding(queueBeanThree.getName(), DestinationType.QUEUE, exchangeBeanTwo.getName(), routingKey, new HashMap<>());
	}
}