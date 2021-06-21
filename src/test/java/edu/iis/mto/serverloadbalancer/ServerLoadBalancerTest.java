package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void When_NoVm_Expect_ZeroLoadPercentage(){
		Server theServer = a(server().withCapacity(1));

		balance(aListOfServersWith(theServer), anEmptyListOfVms());

		assertThat(theServer, hasLoadPercentageOf(0.0d));
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Matcher<? super Server> hasLoadPercentageOf(double expectedPercentage) {
		return new CurrentLoadPercentage(expectedPercentage);
	}

	private Server[] aListOfServersWith(Server server) {
		return new Server[]{server};
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}


}
