package screenplays.consequences;

import common.screenplay.Actor;
import common.screenplay.Consequence;
import common.screenplay.abilities.BrowseTheWeb;
import common.screenplay.abilities.Browser;


public abstract class BrowserConsequence implements Consequence<Actor> {

    @Override
    public Actor apply(Actor actor) {
        Browser browser = BrowseTheWeb.as(actor).get();
        assertConsequence(actor, browser);
        return actor;
    }

    protected abstract void assertConsequence(Actor actor, Browser browser);

}
