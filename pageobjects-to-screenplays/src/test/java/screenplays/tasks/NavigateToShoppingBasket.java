package screenplays.tasks;

import static screenplays.pages.AnyPage.basketMenuAction;

import common.screenplay.Actor;
import common.screenplay.abilities.Browser;
import screenplays.pages.ShoppingBasketPage;


public class NavigateToShoppingBasket extends BrowserTask {

    @Override
    protected <T extends Actor> void performAsWith(T actor, Browser browser) {
        browser.find(basketMenuAction()).click();
    }

    @Override
    protected <T extends Actor> void assertTaskHadExpectedResult(T actor, Browser browser) {
        ShoppingBasketPage.assertThatShoppingBasketPageIsDisplayed(browser);
    }

}
