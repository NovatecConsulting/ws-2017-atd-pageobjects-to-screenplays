package screenplays.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.elements.Button;
import common.elements.Text;
import common.elements.WebElementWrapper;
import screenplays.base.abilities.Browser;
import screenplays.base.abilities.Locator;


public class SearchResultPage {

    public static void assertThatSearchResultPageIsDisplayed(Browser browser) {
        Locator locator = browser.getLocator();
        assertThat(searchResultHeadline().apply(locator)) //
            .describedAs("Search Result headline is displayed") //
            .isPresent();
    }

    private static Function<Locator, Optional<Text>> searchResultHeadline() {
        return locator -> locator //
            .findByCss("Search Result Headline", "span[translate=TITLE_SEARCH_RESULTS]") //
            .map(Text::new);
    }

    public static Function<Locator, Stream<ProductRow>> productRows() {
        return locator -> locator //
            .findManyByCss("tr[data-ng-repeat='product in products']") //
            .map(ProductRow::new);
    }

    public static class ProductRow extends WebElementWrapper {

        ProductRow(WebElement webElement) {
            super(webElement);
        }

        public String getProductName() {
            return webElement.findElement(By.xpath("td[2]")).getText();
        }

        public Button getAddToBasketButton() {
            WebElement webElement = this.webElement.findElement(By.cssSelector("a[ng-click='addToBasket(product.id)']"));
            return new Button(webElement);
        }

    }

}
