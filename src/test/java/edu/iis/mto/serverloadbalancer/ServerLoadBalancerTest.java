package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasVmsCountOf;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {
    @Test
    public void itCompiles() {
        assertThat(true, equalTo(true));
    }

    @Test
    public void When_NoVm_Expect_ZeroLoadPercentage() {
        Server theServer = build(server().withCapacity(1));

        balance(aListOfServersWith(theServer), anEmptyListOfVms());

        assertThat(theServer, hasLoadPercentageOf(0.0d));
    }

    @Test
    public void When_ServerAndVmAreSameSize_Expect_FullLoadPercentage() {
        Server theServer = build(server().withCapacity(1));
        Vm theVm = build(vm().withSize(1));

        balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

        assertThat(theServer, hasLoadPercentageOf(100.0d));
        assertThat("the server should contain vm", theServer.contains(theVm));
    }

    @Test
    public void When_ServerAndVm_Expect_PartLoadPercentage() {
        Server theServer = build(server().withCapacity(10));
        Vm theVm = build(vm().withSize(1));

        balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

        assertThat(theServer, hasLoadPercentageOf(10.0d));
        assertThat("the server should contain vm", theServer.contains(theVm));
    }

    @Test
    public void When_ServerAndTwoVm_Expect_PartLoadPercentage() {
        Server theServer = build(server().withCapacity(100));
        Vm theFirstVm = build(vm().withSize(1));
        Vm theSecondVm = build(vm().withSize(1));

        balance(aListOfServersWith(theServer), aListOfVmsWith(theFirstVm, theSecondVm));

        assertThat(theServer, hasVmsCountOf(2));
        assertThat("the server should contain vm", theServer.contains(theFirstVm));
        assertThat("the server should contain vm", theServer.contains(theSecondVm));
    }

    @Test
    public void When_TwoServersAndVm_Expect_BalancedOnLessLoadedServer() {
        Server lessLoadedServer = build(server().withCapacity(100).withCurrentLoadOf(45.0d));
        Server moreLoadedServer = build(server().withCapacity(100).withCurrentLoadOf(50.0d));
        Vm theVm = build(vm().withSize(10));

        balance(aListOfServersWith(moreLoadedServer, lessLoadedServer), aListOfVmsWith(theVm));

        assertThat("the less loaded server should contain vm", lessLoadedServer.contains(theVm));
    }

    @Test
    public void When_VmsSizeIsBiggerThanServersCapacity_Expect_NotFillServer() {
        Server theServer = build(server().withCapacity(10).withCurrentLoadOf(90.0d));
        Vm theVm = build(vm().withSize(2));

        balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

        assertThat("the less loaded server should not contain vm", !theServer.contains(theVm));
    }

    @Test
    public void When_MultipleServersAndVms_Expect_balancedBetweenServers() {
        Server server1 = build(server().withCapacity(4));
        Server server2 = build(server().withCapacity(6));
        Vm vm1 = build(vm().withSize(1));
        Vm vm2 = build(vm().withSize(4));
        Vm vm3 = build(vm().withSize(2));

        balance(aListOfServersWith(server1, server2), aListOfVmsWith(vm1, vm2, vm3));

        assertThat("The server 1 should contain the vm 1", server1.contains(vm1));
        assertThat("The server 2 should contain the vm 2", server2.contains(vm2));
        assertThat("The server 1 should contain the vm 3", server1.contains(vm3));
        assertThat(server1, hasLoadPercentageOf(75.0d));
        assertThat(server2, hasLoadPercentageOf(66.66d));
    }


    private void balance(Server[] servers, Vm[] vms) {
        new ServerLoadBalancer().balance(servers, vms);
    }

    private Vm[] aListOfVmsWith(Vm... vms) {
        return vms;
    }

    private Server[] aListOfServersWith(Server... servers) {
        return servers;
    }

    private Vm[] anEmptyListOfVms() {
        return new Vm[0];
    }

    private <T> T build(Builder<T> builder) {
        return builder.build();
    }

}
