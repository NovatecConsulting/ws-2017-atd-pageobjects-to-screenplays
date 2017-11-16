package screenplays.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.function.Function;

import common.elements.Button;
import common.elements.Text;
import screenplays.base.abilities.Browser;
import screenplays.base.abilities.Locator;


public class AnyPage {

    public static Function<Locator, Optional<Button>> loginMenuAction() {
        return locator -> locator //
            .findByCss("Login Menu Action", "span[translate=TITLE_LOGIN]") //
            .map(Button::new);
    }

    public static Function<Locator, Optional<Button>> logoutMenuAction() {
        return locator -> locator //
            .findByCss("Logout Menu Action", "span[translate=TITLE_LOGOUT]") //
            .map(Button::new);
    }

    private static Function<Locator, Optional<Text>> applicationName() {
        return locator -> locator //
            .findByCss("Application Name", "span[ng-bind=applicationName]") //
            .map(Text::new);
    }

    public static void assertThatAnyPageIsDisplayed(Browser browser) {
        Locator locator = browser.getLocator();
        assertThat(applicationName().apply(locator)) //
            .describedAs("Application name is displayed") //
            .isPresent();
    }

}
