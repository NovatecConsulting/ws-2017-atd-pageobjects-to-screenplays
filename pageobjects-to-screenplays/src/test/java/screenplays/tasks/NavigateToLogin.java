package screenplays.tasks;

import static screenplays.pages.AnyPage.loginMenuAction;
import static screenplays.pages.AnyPage.logoutMenuAction;
import static screenplays.pages.LoginPage.assertThatLoginPageIsDisplayed;

import common.screenplay.Actor;
import common.screenplay.abilities.Browser;


/**
 * Navigates to the login page by clicking on the corresponding button in
 * the page's header. If the user is already logged in, a logout is performed
 * before navigating to the login page.
 */
public class NavigateToLogin extends BrowserTask {

    @Override
    public <T extends Actor> void performAsWith(T actor, Browser browser) {
        browser.findOptional(loginMenuAction()) //
            .orElseGet(() -> {
                browser.find(logoutMenuAction()).click();
                return browser.find(loginMenuAction());
            }).click();
    }

    @Override
    protected <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser) {
        assertThatLoginPageIsDisplayed(browser);
    }

}
