package edu.iis.mto.serverloadbalancer;

public class Server {
    public double currentLoadPercentage;
    private int capacity;

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
