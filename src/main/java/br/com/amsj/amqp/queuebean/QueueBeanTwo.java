package br.com.amsj.amqp.queuebean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("queueBeanTwo")
public class QueueBeanTwo extends QueueBean {

	QueueBeanTwo(
			@Value("${amqp.queue.queueTwo.name}") String name, 
			@Value("${amqp.queue.queueTwo.durable}") boolean isDurable) {
		super(name, isDurable);
	}
}
