package screenplays.pages;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Optional;
import java.util.function.Function;

import common.elements.Button;
import common.elements.Link;
import common.elements.PasswordField;
import common.elements.TextField;
import common.screenplay.abilities.Browser;
import common.screenplay.abilities.Locator;


/**
 * This page provides access to find functions for elements of the Juice Shop's login page.
 */
public class LoginPage {

    public static Function<Locator, Optional<TextField>> emailField() {
        return locator -> locator //
            .findByCss("E-Mail Field", "#userEmail") //
            .map(TextField::new);
    }

    public static Function<Locator, Optional<PasswordField>> passwordField() {
        return locator -> locator //
            .findByCss("Password Field", "#userPassword") //
            .map(PasswordField::new);
    }

    public static Function<Locator, Optional<Button>> loginButton() {
        return locator -> locator //
            .findByCss("Login Button", "#loginButton") //
            .map(Button::new);
    }

    public static Function<Locator, Optional<Link>> registrationLink() {
        return locator -> locator //
            .findByCss("Registration Link", "a[translate=NO_CUSTOMER]") //
            .map(Link::new);
    }

    public static void assertThatLoginPageIsDisplayed(Browser browser) {
        Locator locator = browser.getLocator();
        assertSoftly(softly -> {
            softly.assertThat(emailField().apply(locator)) //
                .describedAs("E-Mail field is displayed") //
                .isPresent();
            softly.assertThat(loginButton().apply(locator)) //
                .describedAs("Login button is displayed") //
                .isPresent();
        });
    }

}
