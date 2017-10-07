package pageobjects.pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.User;
import common.elements.Button;
import common.elements.Link;
import common.elements.PasswordField;
import common.elements.TextField;


/**
 * This models the Juice Shop's login page and all interactions possible
 * there.
 */
public class LoginPage extends JuiceShopPage<LoginPage> {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void assertThatCorrectPageIsDisplayed() {
        WebElement headline = findByCss("PerformLoginTask Headline", "h3[translate=TITLE_LOGIN]");
        assertThat(headline.isDisplayed()).isTrue();
    }

    /**
     * Executes the login for the given {@link User}.
     * <p>
     * This method expects the user to exist in the Juice Shop's user directory.
     * Invoking this method for a non-existing user will result in an exception
     * since the resulting page will not be the expected {@link ProductsPage}.
     */
    public ProductsPage loginAs(User user) {
        findEmailField().setText(user.email);
        findPasswordField().setText(user.password);
        findLoginButton().click();
        return create(ProductsPage.class);
    }

    private TextField findEmailField() {
        return new TextField(findByCss("E-Mail Field", "#userEmail"));
    }

    private PasswordField findPasswordField() {
        return new PasswordField(findByCss("Password Field", "#userPassword"));
    }

    private Button findLoginButton() {
        return new Button(findByCss("PerformLoginTask Button", "#loginButton"));
    }

    /**
     * Navigates to the {@link RegistrationPage} by clicking on the corresponding
     * link within the login dialog.
     */
    public RegistrationPage goToRegistration() {
        findRegistrationLink().click();
        return create(RegistrationPage.class);
    }

    private Link findRegistrationLink() {
        return new Link(findByCss("Registration Link", "a[translate=NO_CUSTOMER]"));
    }

}
