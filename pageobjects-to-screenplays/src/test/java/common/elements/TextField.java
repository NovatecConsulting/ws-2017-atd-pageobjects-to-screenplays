package common.elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class TextField extends WebElementWrapper {

    public TextField(WebElement webElement) {
        super(webElement);
    }

    public TextField setText(String value) {
        webElement.clear();
        webElement.sendKeys(value);
        return this;
    }

    public TextField pressEnter() {
        webElement.sendKeys(Keys.ENTER);
        return this;
    }

}
