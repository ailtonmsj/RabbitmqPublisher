package br.com.amsj.amqp.queuebean;

public abstract class QueueBean {
	
	QueueBean(String name, boolean isDurable){
		this.name = name;
		this.isDurable = isDurable;
	}
	
	protected final String name;
	protected final boolean isDurable;

	public final boolean isDurable() {
		return isDurable;
	}
	public final String getName() {
		return name;
	}
}
