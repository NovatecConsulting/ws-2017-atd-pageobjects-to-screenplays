package common.screenplay.consequences;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import common.elements.WebElementWrapper;
import common.screenplay.Consequence;
import common.screenplay.abilities.BrowseTheWeb;
import common.screenplay.abilities.Browser;
import common.screenplay.abilities.Locator;


/**
 * Web consequences to let the actor check for states of web elements.
 */
public class WebConsequences {

    /**
     * Checks that the actor can see a web element (is visible)
     *
     * @param function function object for evaluation
     * @return the visible consequence
     */
    public static <T extends WebElementWrapper> Consequence<Boolean> see(Function<Locator, Optional<T>> function) {
        return actor -> {
            Browser browser = BrowseTheWeb.as(actor).get();
            return function //
                .apply(browser.getLocator()) //
                .map(WebElementWrapper::isDisplayed) //
                .orElse(false);
        };
    }

    /**
     * Counts a number for an actor.
     *
     * @param function function object for evaluation
     * @return the counting consequence
     */
    public static Consequence<Integer> count(Function<Browser, Integer> function) {
        return actor -> function.apply(BrowseTheWeb.as(actor).get());
    }

    /**
     * Checks that the actor can observe a given state
     *
     * @param function function object for evaluation
     * @return the check that consequence
     */
    public static Consequence<Boolean> checkThat(Function<Browser, Boolean> function) {
        return actor -> function.apply(BrowseTheWeb.as(actor).get());
    }

    /**
     * Checks that the actor can observe a given state
     *
     * @param function function object for evaluation
     * @return the check that consequence
     */
    public static Consequence<Boolean> interactWith(Function<Browser, Boolean> function) {
        return actor -> function.apply(BrowseTheWeb.as(actor).get());
    }

    /**
     * Checks that the actor can observe a given state
     *
     * @param function function object for evaluation
     * @return the check for consequence
     */
    public static Consequence<Boolean> checkThat(BiFunction<Browser, String, Boolean> function, String valueToCheck) {
        return actor -> function.apply(BrowseTheWeb.as(actor).get(), valueToCheck);
    }

    /**
     * Gets the value for an element.
     *
     * @param function function object for evaluation
     * @return the get value consequence
     */
    public static Consequence<String> getValueOf(Function<Browser, String> function) {
        return actor -> function.apply(BrowseTheWeb.as(actor).get());
    }

}
