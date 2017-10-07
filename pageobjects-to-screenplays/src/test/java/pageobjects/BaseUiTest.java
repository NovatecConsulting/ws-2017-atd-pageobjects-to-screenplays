package pageobjects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import pageobjects.pages.ProductsPage;


class BaseUiTest {

    protected static WebDriver webDriver;

    @BeforeAll
    static void initializeWebDriver() {
        //System.setProperty("webdriver.chrome.driver", "/Users/SLU/bin/chromedriver");
        webDriver = new ChromeDriver();
    }

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

    protected ProductsPage openApplication() {
        webDriver.get("https://atd-juiceshop.herokuapp.com");
        return PageFactory.initElements(webDriver, ProductsPage.class);
    }

}
