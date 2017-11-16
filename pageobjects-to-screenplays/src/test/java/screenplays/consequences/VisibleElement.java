package screenplays.consequences;

import common.elements.WebElementWrapper;
import common.screenplay.Actor;
import common.screenplay.abilities.Browser;
import common.screenplay.abilities.Locator;

import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class VisibleElement<T extends WebElementWrapper> extends BrowserConsequence {

    private final Function<Locator, Optional<T>> function;

    public VisibleElement(Function<Locator, Optional<T>> function) {
        this.function = function;
    }

    @Override
    protected void assertConsequence(Actor actor, Browser browser) {
        boolean visible = this.function.apply(browser.getLocator())
                .map(WebElementWrapper::isDisplayed) //
                .orElse(false);

        assertThat(visible) //
            .describedAs("Element should be visible") //
            .isTrue();
    }

}
