package screenplays.tasks;

import screenplays.base.Actor;
import screenplays.base.Task;
import screenplays.base.abilities.BrowseTheWeb;
import screenplays.base.abilities.Browser;


public abstract class BrowserTask implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        Browser browser = BrowseTheWeb.as(actor).get();
        performAsWith(actor, browser);
        try {
            assertTaskHadExpectedResult(actor, browser);
        } catch (RuntimeException | AssertionError e) {
            String message = "Task '" + getClass().getSimpleName() + "' could not be executed successfully:";
            throw new IllegalStateException(message, e);
        }
    }

    protected abstract <T extends Actor> void performAsWith(T actor, Browser browser);

    protected abstract <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser);

}
