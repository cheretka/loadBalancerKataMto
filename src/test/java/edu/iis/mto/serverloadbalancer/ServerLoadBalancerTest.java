package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

import org.hamcrest.Matcher;
import org.junit.Test;


public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void When_NoVms_Expect_ZeroLoadPercentage(){
		Server theServer = a(server().withCapacity(1));

		balance(aListOfServersWith(theServer), anEmptyListOfVms());

		assertThat(theServer, hasLoadedPercentageOf(0.0d));
	}

	private Matcher<? super Server> hasLoadedPercentageOf(double expectedLoadPercentage) {
		return new CurrentLoadPercantageMatcher(expectedLoadPercentage);
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aListOfServersWith(Server theServer) {
		return new Server[]{theServer};
	}


	private ServerBuilder server() {
		return new ServerBuilder();
	}


}
