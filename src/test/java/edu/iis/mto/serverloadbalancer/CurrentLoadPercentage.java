package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentage extends TypeSafeMatcher<Server> {
    private double expectedPercentage;

    public CurrentLoadPercentage(double expectedPercentage) {
        this.expectedPercentage = expectedPercentage;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return expectedPercentage == server.currentLoadPercentage || Math.abs(expectedPercentage - server.currentLoadPercentage) < 0.01d;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("server with load percantage of").appendValue(expectedPercentage);
    }
}
