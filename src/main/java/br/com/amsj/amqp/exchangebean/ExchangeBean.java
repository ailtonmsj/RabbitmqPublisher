package br.com.amsj.amqp.exchangebean;

public abstract class ExchangeBean {
	
	ExchangeBean(String name, boolean isDurable, boolean isAutoDelete){
		this.name = name;
		this.isDurable = isDurable;
		this.isAutoDelete = isAutoDelete;
	}
	
	protected final String name;
	protected final boolean isDurable;
	protected final boolean isAutoDelete;

	public String getName() {
		return name;
	}

	public boolean isDurable() {
		return isDurable;
	}

	public boolean isAutoDelete() {
		return isAutoDelete;
	}
}
