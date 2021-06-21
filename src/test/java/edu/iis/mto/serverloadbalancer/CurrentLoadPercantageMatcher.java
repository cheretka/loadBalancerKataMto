package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercantageMatcher extends TypeSafeMatcher<Server> {
    public static final double EPSILON = 0.01d;
    private double expectedLoadPercentage;

    public CurrentLoadPercantageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    public static Matcher<? super Server> hasLoadedPercentageOf(double expectedLoadPercentage) {
        return new CurrentLoadPercantageMatcher(expectedLoadPercentage);
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return equalsDouble(server.currentLoadPercentage, expectedLoadPercentage);
    }

    private boolean equalsDouble(double number1, double number2) {
        return number1 == number2 || Math.abs(number1 - number2) < EPSILON;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a server with load percantage of ").appendValue(expectedLoadPercentage);
    }
}
