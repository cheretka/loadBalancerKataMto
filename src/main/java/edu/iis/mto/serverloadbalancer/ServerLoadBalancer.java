package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			Server lessLoadedServer = null;
			for(Server s: servers){
				if(lessLoadedServer==null||(s.currentLoadPecentage<lessLoadedServer.currentLoadPecentage)){
					lessLoadedServer = s;
				}
			}
			lessLoadedServer.addVm(vm);
		}

	}

}
