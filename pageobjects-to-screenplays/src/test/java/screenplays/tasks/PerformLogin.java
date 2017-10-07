package screenplays.tasks;

import static org.assertj.core.api.Assertions.assertThat;
import static screenplays.pages.AnyPage.logoutMenuAction;
import static screenplays.pages.LoginPage.emailField;
import static screenplays.pages.LoginPage.loginButton;
import static screenplays.pages.LoginPage.passwordField;

import common.User;
import common.screenplay.Actor;
import common.screenplay.abilities.Browser;
import common.screenplay.abilities.Login;


/**
 * Performs a login for the actor. If a user is logged in, a logout is performed first.
 */
public class PerformLogin extends BrowserTask {

    @Override
    protected <T extends Actor> void performAsWith(T actor, Browser browser) {
        Tasks.forceNavigationToLoginPage().performAs(actor);

        User user = Login.as(actor).get();

        browser.find(emailField()).setText(user.email);
        browser.find(passwordField()).setText(user.password);
        browser.find(loginButton()).click();
    }

    @Override
    protected <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser) {
        assertThat(browser.findOptional(logoutMenuAction())) //
            .describedAs("Logout button should be displayed after login.") //
            .isPresent();
    }

}
