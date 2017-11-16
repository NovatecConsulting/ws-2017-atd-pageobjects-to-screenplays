package screenplays.pages;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Optional;
import java.util.function.Function;

import common.elements.Button;
import common.elements.PasswordField;
import common.elements.SelectionField;
import common.elements.TextField;
import screenplays.base.abilities.Browser;
import screenplays.base.abilities.Locator;


/**
 * This page provides access to find functions for elements of the Juice Shop's registration page.
 */
public class RegistrationPage {

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

    public static Function<Locator, Optional<PasswordField>> passwordRepeatField() {
        return locator -> locator //
            .findByCss("Password Repeat Field", "#userPasswordRepeat") //
            .map(PasswordField::new);
    }

    public static Function<Locator, Optional<SelectionField>> securityQuestionSelectionField() {
        return locator -> locator //
            .findByCss("Security Question Select", "#securityQuestion") //
            .map(SelectionField::new);
    }

    public static Function<Locator, Optional<TextField>> securityQuestionAnswerField() {
        return locator -> locator //
            .findByCss("Security Question Answer Field", "#securityAnswer") //
            .map(TextField::new);
    }

    public static Function<Locator, Optional<Button>> registerButton() {
        return locator -> locator //
            .findByCss("Register Button", "#registerButton") //
            .map(Button::new);
    }

    public static void assertThatRegistrationPageIsDisplayed(Browser browser) {
        Locator locator = browser.getLocator();
        assertSoftly(softly -> {
            softly.assertThat(emailField().apply(locator)) //
                .describedAs("E-Mail field is displayed") //
                .isPresent();
            softly.assertThat(registerButton().apply(locator)) //
                .describedAs("Register button is displayed") //
                .isPresent();
        });
    }

}
