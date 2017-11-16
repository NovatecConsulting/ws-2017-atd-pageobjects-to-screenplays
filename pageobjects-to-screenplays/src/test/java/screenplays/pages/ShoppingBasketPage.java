package screenplays.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.elements.Text;
import common.elements.WebElementWrapper;
import screenplays.base.abilities.Browser;
import screenplays.base.abilities.Locator;


public class ShoppingBasketPage {

    public static void assertThatShoppingBasketPageIsDisplayed(Browser browser) {
        Locator locator = browser.getLocator();
        assertThat(shoppingBasketHeadline().apply(locator)) //
            .describedAs("Shopping Basket headline is displayed") //
            .isPresent();
    }

    private static Function<Locator, Optional<Text>> shoppingBasketHeadline() {
        return locator -> locator //
            .findByCss("Shopping Basket Headline", "span[translate=TITLE_BASKET]") //
            .map(Text::new);
    }

    public static Function<Locator, Stream<ProductRow>> productRows() {
        return locator -> locator //
            .findManyByCss("tr[data-ng-repeat='product in products']") //
            .map(ProductRow::new);
    }

    public static class ProductRow extends WebElementWrapper {

        public ProductRow(WebElement webElement) {
            super(webElement);
        }

        public String getProductName() {
            return webElement.findElement(By.xpath("td[1]")).getText();
        }

    }

}
