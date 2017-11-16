package screenplays.tasks;

import static screenplays.pages.LoginPage.registrationLink;
import static screenplays.pages.RegistrationPage.assertThatRegistrationPageIsDisplayed;

import screenplays.base.Actor;
import screenplays.base.abilities.Browser;


/**
 * Navigates to the registration page by first going to the login page and
 * clicking on the the registration link. If the user is already logged in,
 * a logout is performed before navigating to the login page.
 */
public class NavigateToRegistration extends BrowserTask {

    @Override
    protected <T extends Actor> void performAsWith(T actor, Browser browser) {
        Tasks.forceNavigationToLoginPage().performAs(actor);
        browser.find(registrationLink()).click();
    }

    @Override
    protected <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser) {
        assertThatRegistrationPageIsDisplayed(browser);
    }

}
