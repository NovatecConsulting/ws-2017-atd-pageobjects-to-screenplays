package screenplays.pages;

import common.elements.Button;
import common.elements.Text;
import common.elements.TextField;
import common.screenplay.abilities.Browser;
import common.screenplay.abilities.Locator;

import java.util.Optional;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;


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

    public static Function<Locator, Optional<TextField>> searchQueryField() {
        return locator -> locator //
            .findByCss("Search Query Field", "input[ng-model=searchQuery]") //
            .map(TextField::new);
    }

    public static Function<Locator, Optional<Button>> basketMenuAction() {
        return locator -> locator //
            .findByCss("Basket Menu Action", "span[translate=TITLE_BASKET]") //
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
