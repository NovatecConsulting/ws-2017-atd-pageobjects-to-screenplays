package pageobjects;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import common.User;


class ShoppingBasketUiTest extends BaseUiTest {

    @Test
    @DisplayName("adding items to basket")
    void addItemsToBasket() {
        List<String> productsInBasket = openApplication()//
            .goToLogin()
            .loginAs(User.JOE)
            .searchFor("Apple")
            .waitUntil(page -> page.getNumberOfResults() >= 2)
            .addToBasket("Apple Pomace")
            .addToBasket("Apple Juice (1000ml)")
            .goToBasket()
            .waitUntil(page -> page.getNumberOfItems() >= 2)
            .getNamesOfProductsInBasket();
        assertThat(productsInBasket).containsOnly("Apple Pomace", "Apple Juice (1000ml)");
    }

}
