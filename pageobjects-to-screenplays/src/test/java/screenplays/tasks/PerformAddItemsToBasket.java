package screenplays.tasks;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;
import static screenplays.pages.SearchResultPage.assertThatSearchResultPageIsDisplayed;
import static screenplays.pages.SearchResultPage.productRows;

import java.util.List;
import java.util.Set;

import common.screenplay.Actor;
import common.screenplay.abilities.Browser;
import screenplays.pages.SearchResultPage.ProductRow;


public class PerformAddItemsToBasket extends BrowserTask {

    private final Set<String> itemNames;

    public PerformAddItemsToBasket(String... itemNames) {
        this.itemNames = stream(itemNames).map(String::toLowerCase).collect(toSet());
    }

    @Override
    protected <T extends Actor> void performAsWith(T actor, Browser browser) {
        assertThatSearchResultPageIsDisplayed(browser);

        await("waiting for search result table to display at least " + itemNames.size() + " items") //
            .atMost(TWO_SECONDS) //
            .until(() -> browser.findMany(productRows()).count() >= itemNames.size());

        List<ProductRow> foundItems = browser.findMany(productRows()) //
            .filter(row -> itemNames.contains(row.getProductName().toLowerCase())) //
            .collect(toList());

        assertThat(foundItems) //
            .describedAs("All items should be found") //
            .extracting(productRow -> productRow.getProductName().toLowerCase()) //
            .containsAll(itemNames);

        for (ProductRow foundItem : foundItems) {
            foundItem.getAddToBasketButton().click();
        }
    }

    @Override
    protected <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser) {
        assertThatSearchResultPageIsDisplayed(browser);
    }

}
