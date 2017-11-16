package screenplays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import screenplays.base.abilities.Browser;


class BaseStory {

    protected static Browser theBrowser;

    @BeforeAll
    static void initializeWebDriver() {
        //System.setProperty("webdriver.chrome.driver", "/Users/SLU/bin/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        theBrowser = new Browser(webDriver);
    }

    @AfterEach
    void waitShortly() throws InterruptedException {
        Thread.sleep(2000L);
    }

    @AfterAll
    static void closeWebDriver() {
        theBrowser.close();
    }

}
