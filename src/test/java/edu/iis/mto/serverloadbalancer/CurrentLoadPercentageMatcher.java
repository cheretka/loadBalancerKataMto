package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    private double expectedPercentage;

    public CurrentLoadPercentageMatcher(double expectedPercentage) {
        this.expectedPercentage = expectedPercentage;
    }

    static Matcher<? super Server> hasLoadPercentageOf(double expectedPercentage) {
        return new CurrentLoadPercentageMatcher(expectedPercentage);
    }

    @Override
    protected boolean matchesSafely(Server server) {
        double number1 = this.expectedPercentage;
        double number2 = server.currentLoadPercentage;
        return isDoubleNumbersAreEquals(number1, number2);
    }

    private boolean isDoubleNumbersAreEquals(double number1, double number2) {
        return number1 == number2 || Math.abs(number1 - number2) < 0.01d;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("server with load percantage of").appendValue(expectedPercentage);
    }
}
