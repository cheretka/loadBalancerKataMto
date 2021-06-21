package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

	private int capacity;
	private double startLoadPercentage;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		if(startLoadPercentage>0.0d){
			int load = (int)(startLoadPercentage / 100.0d * server.getCapacity());
			server.addVm(VmBuilder.vm().ofSize(load).build());
		}
		return server;
	}

	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withLoadPercentage(double startLoadPercentage) {
		this.startLoadPercentage = startLoadPercentage;
		return this;
	}
}
