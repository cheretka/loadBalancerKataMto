package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

	private static final double MAXIMUM_LOAD = 100.0d;
	public double currentLoadPecentage;
	private int capacity;
	public List<Vm> vms = new ArrayList<>();

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm vm) {
		return true;
	}

	public void addVm(Vm vm) {
		vms.add(vm);
		this.currentLoadPecentage = (double) vm.size / (double) this.capacity
				* MAXIMUM_LOAD;
	}

	public int vmsCount() {
		return vms.size();
	}
}
