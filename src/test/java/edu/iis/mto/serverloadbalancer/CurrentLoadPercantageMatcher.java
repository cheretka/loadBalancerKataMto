package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercantageMatcher extends TypeSafeMatcher<Server> {
    private double expectedLoadPercentage;

    public CurrentLoadPercantageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }


    @Override
    protected boolean matchesSafely(Server server) {
        return server.currentLoadPercentage == this.expectedLoadPercentage || Math.abs(server.currentLoadPercentage-expectedLoadPercentage) < 0.01d;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a server with load percantage of ").appendValue(expectedLoadPercentage);
    }
}
