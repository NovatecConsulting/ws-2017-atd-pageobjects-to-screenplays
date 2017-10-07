package screenplays.tasks;

import static screenplays.pages.AnyPage.searchQueryField;
import static screenplays.pages.SearchResultPage.assertThatSearchResultPageIsDisplayed;

import common.screenplay.Actor;
import common.screenplay.abilities.Browser;


/**
 * Performs a search for items of the Juice Shop.
 */
public class PerformSearch extends BrowserTask {

    private final String query;

    public PerformSearch(String query) {
        this.query = query;
    }

    @Override
    protected <T extends Actor> void performAsWith(T actor, Browser browser) {
        browser.find(searchQueryField()) //
            .setText(query) //
            .pressEnter();
    }

    @Override
    protected <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser) {
        assertThatSearchResultPageIsDisplayed(browser);
    }

}
