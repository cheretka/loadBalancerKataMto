package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private int expectedVmsCount;

    public ServerVmsCountMatcher(int expectedVmsCount) {
        this.expectedVmsCount = expectedVmsCount;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return server.vmsCount() == expectedVmsCount;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("server with vms count of ").appendValue(expectedVmsCount);
    }
}
