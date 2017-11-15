package pageobjects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import pageobjects.pages.ProductsPage;


/**
 * This is the base class for all of the Page Object based tests.
 * <p>
 * Its main purpose is to manage the creation and closing of the {@link WebDriver}.
 * In 'real' projects this construct might not scale with the number of tests and
 * the different testing scenarios. But for the purpose of this example it is enough.
 */
class BaseUiTest {

    protected static WebDriver webDriver;

    @BeforeAll
    static void initializeWebDriver() {
        webDriver = new ChromeDriver();
    }

    /**
     * Wait for a couple of seconds after each test to allow you to see the last page
     * the test landed on. Without this the browser would be closed faster than you can
     * visually evaluate any result.
     * <p>
     * This is NOT something you would or should do in a 'real' project!
     */
    @AfterEach
    void waitShortly() throws InterruptedException {
        Thread.sleep(2000L);
    }

    @AfterAll
    static void closeWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    /**
     * Opens the browser (if not already opened) and navigates to the landing page of
     * the example application.
     *
     * @return the landing page ({@link ProductsPage})
     */
    protected ProductsPage openApplication() {
        webDriver.get("https://atd-juiceshop.herokuapp.com");
        return PageFactory.initElements(webDriver, ProductsPage.class);
    }

}
