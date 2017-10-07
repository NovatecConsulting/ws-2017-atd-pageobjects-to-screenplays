package pageobjects.pages;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

import common.elements.Button;
import common.elements.TextField;


/**
 * This is the base class for all Juice Shop page objects. It offers interaction
 * methods available on all pages of the Juice Shop. Like logging in and out
 * of the application.
 */
public abstract class JuiceShopPage<T extends JuiceShopPage> extends BasePage<T> {

    public JuiceShopPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Navigates to the login page by clicking on the corresponding button in
     * the page's header. If the user is already logged in, a logout is performed
     * before navigating to the login page.
     */
    public LoginPage goToLogin() {
        findOptionalLoginButton()//
            .orElseGet(() -> {
                findLogoutButton().click();
                return findLoginButton();
            }).click();
        return create(LoginPage.class);
    }

    private Button findLoginButton() {
        return findOptionalLoginButton().get();
    }

    private Optional<Button> findOptionalLoginButton() {
        return findOptionallyByCss("PerformLoginTask Button", "span[translate=TITLE_LOGIN]").map(Button::new);
    }

    public Button findLogoutButton() {
        return new Button(findByCss("Logout Button", "span[translate=TITLE_LOGOUT]"));
    }

    /**
     * Executes a search for the given query string by filling the corresponding
     * field in the page's header and triggering the search by pressing ENTER.
     */
    public SearchResultPage searchFor(String query) {
        findSearchQueryField()//
            .setText(query)//
            .pressEnter();
        return create(SearchResultPage.class);
    }

    private TextField findSearchQueryField() {
        return new TextField(findByCss("Search Query Field", "input[ng-model=searchQuery]"));
    }

    /**
     * Navigates to the basket page by clicking on the corresponding button in
     * the page's header.
     */
    public ShoppingBasketPage goToBasket() {
        findBasketButton().click();
        return create(ShoppingBasketPage.class);
    }

    private Button findBasketButton() {
        return new Button(findByCss("Basket Button", "span[translate=TITLE_BASKET]"));
    }

}
