package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

	private int capacity;
	private double loadPercentage;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);

		if (loadPercentage>0.0d){
			int load = (int)(loadPercentage/100.0d * server.getCapacity());
			server.addVm(VmBuilder.vm().ofSize(load).build());
		}

		return new Server(capacity);
	}

	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoadOf(double loadPerc) {
		this.loadPercentage = loadPerc;
		return this;
	}
}
