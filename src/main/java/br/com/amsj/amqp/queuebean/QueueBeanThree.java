package br.com.amsj.amqp.queuebean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("queueBeanThree")
public class QueueBeanThree extends QueueBean {

	QueueBeanThree(
			@Value("${amqp.queue.queueThree.name}") String name, 
			@Value("${amqp.queue.queueThree.durable}")boolean isDurable) {
		super(name, isDurable);
	}
}
