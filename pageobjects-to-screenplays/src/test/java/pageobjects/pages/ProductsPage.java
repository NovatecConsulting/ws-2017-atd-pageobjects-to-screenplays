package pageobjects.pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * This models the Juice Shop's "all products" page and all interactions possible
 * there. This is the landing page of the application as well as the one the user
 * is delegated to after the login.
 */
public class ProductsPage extends JuiceShopPage<ProductsPage> {

    public ProductsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void assertThatCorrectPageIsDisplayed() {
        WebElement headline = findByCss("Products Headline", "h3[translate=TITLE_ALL_PRODUCTS]");
        assertThat(headline.isDisplayed()).isTrue();
    }

}
