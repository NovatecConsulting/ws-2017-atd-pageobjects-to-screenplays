package screenplays.tasks;

import screenplays.base.Actor;
import screenplays.base.abilities.Browser;


/**
 * Performs a registration for the actor. If a user is logged in, a logout is
 * performed first.
 */
public class PerformRegistration extends BrowserTask {

    @Override
    protected <T extends Actor> void performAsWith(T actor, Browser browser) {
        // TODO: implement
    }

    @Override
    protected <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser) {
        // TODO: implement
    }

}
