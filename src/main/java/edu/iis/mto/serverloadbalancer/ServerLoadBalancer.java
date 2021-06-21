package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {
        for (Vm vm : vms) {
            Server lessLoadedServer = getlessLoadedServer(servers);
            lessLoadedServer.addVm(vm);
        }

    }

    private Server getlessLoadedServer(Server[] servers) {
        Server lessLoadedServer = null;
        for (Server s : servers) {
            if (lessLoadedServer == null || (s.currentLoadPecentage < lessLoadedServer.currentLoadPecentage)) {
                lessLoadedServer = s;
            }
        }
        return lessLoadedServer;
    }

}
