package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

    private int capacity;
    private double loadPercentage;

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server = new Server(capacity);

        addInitialLoad(server);

        return new Server(capacity);
    }

    private void addInitialLoad(Server server) {
        if (loadPercentage > 0.0d) {
            int load = (int) (loadPercentage / Server.MAXIMUM_LOAD * server.getCapacity());
            server.addVm(VmBuilder.vm().ofSize(load).build());
        }
    }

    public ServerBuilder withCurrentLoadOf(double loadPerc) {
        this.loadPercentage = loadPerc;
        return this;
    }
}
