package common.screenplay.abilities;


import common.screenplay.Ability;
import common.screenplay.Actor;

/**
 * Ability to browse the web.
 */
public class BrowseTheWeb implements Ability<Browser> {

    private Browser browser;

    /**
     * Browses the web using the given actor.
     *
     * @param actor actor to browse the web with
     * @return the browse the web ability
     */
    public static BrowseTheWeb as(Actor actor) {
        if (actor.hasAbilityTo(BrowseTheWeb.class)) {
            return actor.abilityTo(BrowseTheWeb.class);
        } else {
            throw new IllegalStateException(
                    "The actor " + actor.getName() + " does not have the ability to browse the web");
        }
    }

    /**
     * Browse the web with given theBrowser.
     *
     * @param browser the {@link Browser}
     * @return the browse the web ability
     */
    public static BrowseTheWeb with(Browser browser) {
        return new BrowseTheWeb(browser);
    }

    /**
     * Constructor.
     *
     * @param browser the {@link Browser}
     */
    private BrowseTheWeb(Browser browser) {
        this.browser = browser;
    }

    @Override
    public Browser get() {
        return this.browser;
    }
}
