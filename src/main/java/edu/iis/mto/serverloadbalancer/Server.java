package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

	public static final double MAXIMUM_LOAD = 100.0d;
	private double currentLoadPercentage;
	private final int capacity;
	private List<Vm> vms = new ArrayList<>();

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm vm) {
		return vms.contains(vm);
	}

	public void addVm(Vm vm) {
		this.vms.add(vm);
		this.currentLoadPercentage += loadOfVm(vm);
	}

	private double loadOfVm(Vm vm) {
		return (double) vm.size / (double) this.capacity * MAXIMUM_LOAD;
	}

	public int vmsCount() {
		return vms.size();
	}

	public int getCapacity() {
		return capacity;
	}

	public boolean canFit(Vm vm) {
		return currentLoadPercentage + loadOfVm(vm) <= MAXIMUM_LOAD;
	}

	public double getCurrentLoadPercentage() {
		return currentLoadPercentage;
	}
}
