package pageobjects;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import common.User;
import pageobjects.pages.ProductsPage;
import pageobjects.pages.SearchResultPage;


class ShoppingBasketUiTest extends BaseUiTest {

    @Test
    @DisplayName("products can be added to the shopping basket")
    void addingProductsToBasket() {
        ProductsPage productsPage = openApplication()
            .goToLogin()
            .loginAs(User.JOE);

        SearchResultPage searchResultPage = productsPage.searchFor("Apple")//
            .waitUntil(page -> page.getNumberOfResults() >= 2)//
            .addToBasket("Apple Pomace")//
            .addToBasket("Apple Juice (1000ml)");

        List<String> productsInBasket = searchResultPage.goToBasket()//
            .waitUntil(page -> page.getNumberOfItems() >= 2)//
            .getNamesOfProductsInBasket();

        assertThat(productsInBasket).containsOnly("Apple Pomace", "Apple Juice (1000ml)");
    }

}
