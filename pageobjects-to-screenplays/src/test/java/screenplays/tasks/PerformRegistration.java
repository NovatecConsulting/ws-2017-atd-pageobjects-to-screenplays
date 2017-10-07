package screenplays.tasks;

import static screenplays.pages.LoginPage.assertThatLoginPageIsDisplayed;
import static screenplays.pages.RegistrationPage.emailField;
import static screenplays.pages.RegistrationPage.passwordField;
import static screenplays.pages.RegistrationPage.passwordRepeatField;
import static screenplays.pages.RegistrationPage.registerButton;
import static screenplays.pages.RegistrationPage.securityQuestionAnswerField;
import static screenplays.pages.RegistrationPage.securityQuestionSelectionField;

import common.User;
import common.screenplay.Actor;
import common.screenplay.abilities.Browser;
import common.screenplay.abilities.Login;


/**
 * Performs a registration for the actor. If a user is logged in, a logout is
 * performed first.
 */
public class PerformRegistration extends BrowserTask {

    @Override
    protected <T extends Actor> void performAsWith(T actor, Browser browser) {
        Tasks.forceNavigationToRegistrationPage().performAs(actor);

        User user = Login.as(actor).get();

        browser.find(emailField()).setText(user.email);
        browser.find(passwordField()).setText(user.password);
        browser.find(passwordRepeatField()).setText(user.password);
        browser.find(securityQuestionSelectionField()).selectIndex(user.questionIndex);
        browser.find(securityQuestionAnswerField()).setText(user.questionAnswer);
        browser.find(registerButton()).click();
    }

    @Override
    protected <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser) {
        assertThatLoginPageIsDisplayed(browser);
    }

}
