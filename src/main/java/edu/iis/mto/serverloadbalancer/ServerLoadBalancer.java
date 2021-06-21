package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			addToLessLoadedServer(servers, vm);
		}

	}

	private void addToLessLoadedServer(Server[] servers, Vm vm) {
		List<Server> serversThatCanFitVm = getServersWithEnoughCapacity(servers, vm);

		Server lessLoaded = extractLessLoadedServer(serversThatCanFitVm);
		if(lessLoaded!=null)
			lessLoaded.addVm(vm);
	}

	private List<Server> getServersWithEnoughCapacity(Server[] servers, Vm vm) {
		List<Server> serversThatCanFitVm = new ArrayList<>();
		for(Server server: servers){
			if(server.canFit(vm))
				serversThatCanFitVm.add(server);
		}
		return serversThatCanFitVm;
	}

	private Server extractLessLoadedServer(List<Server> servers) {
		Server lessLoaded = null;
		for(Server server : servers){
			if(lessLoaded == null || lessLoaded.currentLoadPecentage > server.currentLoadPecentage){
				lessLoaded = server;
			}
		}
		return lessLoaded;
	}

}
