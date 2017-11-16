package pageobjects.pages;

import org.openqa.selenium.WebDriver;


/**
 * This is the base class for all Juice Shop page objects. It offers interaction
 * methods available on all pages of the Juice Shop. Like logging in and out
 * of the application.
 */
public abstract class JuiceShopPage<T extends JuiceShopPage> extends BasePage<T> {

    protected JuiceShopPage(WebDriver webDriver) {
        super(webDriver);
    }

}
