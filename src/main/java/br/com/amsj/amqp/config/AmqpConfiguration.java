package br.com.amsj.amqp.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
	
	private static final String RABBITMQ_URL = "localhost";
	private static final String RABBITMQ_USERNAME = "guest";
	private static final String RABBITMQ_PASSWORD = "guest1";
	
	@Bean
	ConnectionFactory connectionFactory() {
		
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(RABBITMQ_URL);
		cachingConnectionFactory.setUsername(RABBITMQ_USERNAME);
		cachingConnectionFactory.setPassword(RABBITMQ_PASSWORD);
		
		return cachingConnectionFactory;
	}

}
