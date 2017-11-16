package screenplays.tasks;

import static screenplays.pages.AnyPage.assertThatAnyPageIsDisplayed;

import screenplays.base.Actor;
import screenplays.base.abilities.Browser;


/**
 * Opens the Juice Shop application on the landing page.
 * <p>
 * This {@link BrowserTask} can be executed on any page and is considered
 * successful if the application name "OWASP Juice Shop" is displayed.
 */
public class OpenApplication extends BrowserTask {

    @Override
    protected <T extends Actor> void performAsWith(T actor, Browser browser) {
        browser.open("https://atd-juiceshop.herokuapp.com");
    }

    @Override
    protected <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser) {
        assertThatAnyPageIsDisplayed(browser);
    }

}
