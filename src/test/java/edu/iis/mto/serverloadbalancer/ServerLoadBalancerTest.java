package edu.iis.mto.serverloadbalancer;


import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ServerLoadBalancerTest {
    @Test
    public void itCompiles() {
        assertThat(true, equalTo(true));
    }

    @Test
    public void When_NoVms_Expect_ZeroLoadPercentage() {
        Server theServer = a(server().withCapacity(1));

        balance(aListOfServersWith(theServer), anEmptyListOfVms());

        assertThat(theServer, CurrentLoadPercantageMatcher.hasLoadedPercentageOf(0.0d));
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

}
