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
		addStartLoadPercentage(server);
		return server;
	}

	private void addStartLoadPercentage(Server server) {
		if(startLoadPercentage>0.0d){
			int load = (int)(startLoadPercentage / Server.MAXIMUM_LOAD * server.getCapacity());
			server.addVm(VmBuilder.vm().ofSize(load).build());
		}
	}

	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withLoadPercentage(double startLoadPercentage) {
		this.startLoadPercentage = startLoadPercentage;
		return this;
	}
}
