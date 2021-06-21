package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private int expectedCount;

    public ServerVmsCountMatcher(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    static Matcher<? super Server> hasVmsCountOf(int expectedCount) {
        return new ServerVmsCountMatcher(expectedCount);
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return server.vmsCount() == expectedCount;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("server with vms count of").appendValue(expectedCount);
    }
}
