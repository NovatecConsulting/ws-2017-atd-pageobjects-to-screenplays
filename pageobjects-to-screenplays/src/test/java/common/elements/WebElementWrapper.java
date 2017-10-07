package common.elements;

import org.openqa.selenium.WebElement;


public class WebElementWrapper {

    protected final WebElement webElement;

    protected WebElementWrapper(WebElement webElement) {
        this.webElement = webElement;
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

}
