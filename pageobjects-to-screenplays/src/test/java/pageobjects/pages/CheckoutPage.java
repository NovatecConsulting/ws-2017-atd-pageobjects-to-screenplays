package pageobjects.pages;

import org.openqa.selenium.WebDriver;


/**
 * This models the Juice Shop's checkout page and all interactions possible
 * there.
 */
public class CheckoutPage extends JuiceShopPage<CheckoutPage> {

    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void assertThatCorrectPageIsDisplayed() {

    }

}
