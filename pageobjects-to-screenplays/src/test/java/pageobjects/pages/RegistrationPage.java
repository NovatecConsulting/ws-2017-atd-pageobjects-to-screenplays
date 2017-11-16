package pageobjects.pages;

import org.openqa.selenium.WebDriver;


/**
 * This models the Juice Shop's registration page and all interactions possible
 * there.
 */
public class RegistrationPage extends JuiceShopPage<RegistrationPage> {

    public RegistrationPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void assertThatCorrectPageIsDisplayed() {
        // TODO: implement
    }

}
