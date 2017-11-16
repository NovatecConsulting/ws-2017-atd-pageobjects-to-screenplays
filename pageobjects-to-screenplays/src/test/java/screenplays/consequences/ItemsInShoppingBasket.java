package screenplays.consequences;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;
import static screenplays.pages.ShoppingBasketPage.productRows;
import static screenplays.tasks.Tasks.navigateToShoppingBasket;

import java.util.List;
import java.util.Set;

import screenplays.base.Actor;
import screenplays.base.abilities.Browser;
import screenplays.pages.ShoppingBasketPage.ProductRow;


public class ItemsInShoppingBasket extends BrowserConsequence {

    private final Set<String> itemNames;

    public ItemsInShoppingBasket(String... itemNames) {
        this.itemNames = stream(itemNames).map(String::toLowerCase).collect(toSet());
    }

    @Override
    protected void assertConsequence(Actor actor, Browser browser) {
        navigateToShoppingBasket().performAs(actor);

        await("waiting for shopping basket table to display at least " + itemNames.size() + " items") //
            .atMost(TWO_SECONDS) //
            .until(() -> browser.findMany(productRows()).count() >= itemNames.size());

        List<ProductRow> items = browser.findMany(productRows()).collect(toList());

        assertThat(items) //
            .describedAs("All items should be found") //
            .extracting(productRow -> productRow.getProductName().toLowerCase()) //
            .containsAll(itemNames) //
            .hasSize(itemNames.size());
    }

}
