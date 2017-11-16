package screenplays.consequences;

import screenplays.base.Actor;
import screenplays.base.Consequence;
import screenplays.base.abilities.BrowseTheWeb;
import screenplays.base.abilities.Browser;


public abstract class BrowserConsequence implements Consequence<Actor> {

    @Override
    public Actor apply(Actor actor) {
        Browser browser = BrowseTheWeb.as(actor).get();
        assertConsequence(actor, browser);
        return actor;
    }

    protected abstract void assertConsequence(Actor actor, Browser browser);

}
