package screenplays.consequences;

import common.elements.WebElementWrapper;
import screenplays.base.Actor;
import screenplays.base.abilities.Browser;
import screenplays.base.abilities.Locator;
import org.awaitility.Awaitility;
import org.awaitility.Duration;

import java.util.Optional;
import java.util.function.Function;

public class NotVisibleElement<T extends WebElementWrapper> extends BrowserConsequence {

    private final  Function<Locator, Optional<T>> function;

    public NotVisibleElement(Function<Locator, Optional<T>> function) {
        this.function = function;
    }

    @Override
    protected void assertConsequence(Actor actor, Browser browser) {

        Awaitility
                .await("Element should not be visible")
                .atMost(Duration.TWO_SECONDS)
                .pollDelay(Duration.FIVE_HUNDRED_MILLISECONDS)
                .until(() -> !this.function.apply(
                        browser.getLocator()).map(WebElementWrapper::isDisplayed).orElse(false));
    }

}
