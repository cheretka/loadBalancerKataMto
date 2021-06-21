package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private int expectCount;

    public ServerVmsCountMatcher(int expectCount) {
        this.expectCount = expectCount;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return server.vmsCount() == expectCount;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("server with vms count of").appendValue(expectCount);
    }
}
