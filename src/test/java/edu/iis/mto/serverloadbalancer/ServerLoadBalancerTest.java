package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingAServer_noVms_serverStaysEmpty() {
		Server theServer = a(server().withCapacity(1));

		balance(aListOfServersWith(theServer), anEmptyListOfVms());

		assertThat(theServer, hasLoadPercentageOf(0.0d));
	}

	@Test
	public void When_ServerAndVmHaveSameSize_Expect_FullLoadPercentage() {
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(VmBuilder.vm().withSize(1));

		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasLoadPercentageOf(100.0d));
		assertThat("server contains vm", theServer.contains(theVm));
	}

//	private Vm a(VmBuilder builder) {
//		return builder.build();
//	}

	private Vm[] aListOfVmsWith(Vm vm) {
		return new Vm[]{vm};
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aListOfServersWith(Server server) {
		return new Server[] { server };
	}

//	private Server a(ServerBuilder builder) {
//		return builder.build();
//	}

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}


}
