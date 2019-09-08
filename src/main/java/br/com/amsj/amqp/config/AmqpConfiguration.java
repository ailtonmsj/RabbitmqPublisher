package br.com.amsj.amqp.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
	
	public AmqpConfiguration(@Value("${amqp.host}") String rabbitmqHost, 
			@Value("${amqp.port}") Integer rabbitmqPort, 
			@Value("${amqp.username}") String rabbitmqUsername,
			@Value("${amqp.password}") String rabbitmqPassword) {
		
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
}
