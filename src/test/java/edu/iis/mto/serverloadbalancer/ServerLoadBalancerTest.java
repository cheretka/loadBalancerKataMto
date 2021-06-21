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
		Server theServer = a(server().withCapacity());

		balance(aListOfServersWith(theServer), anEmptyListOfVms());

		assertThat(theServer, hasLoadPercentageOf(0.0d));
	}

}
