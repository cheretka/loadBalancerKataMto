package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void When_NoVm_Expect_ZeroLoadPercentage(){
		Server theServer = a(ServerBuilder.server().withCapacity(1));

		balance(aListOfServersWith(theServer), anEmptyListOfVms());

		assertThat(theServer, CurrentLoadPercentageMatcher.hasLoadPercentageOf(0.0d));
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Server[] aListOfServersWith(Server server) {
		return new Server[]{server};
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}


}
