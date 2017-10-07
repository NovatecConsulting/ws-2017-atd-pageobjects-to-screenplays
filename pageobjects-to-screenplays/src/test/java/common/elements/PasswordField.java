package common.elements;

import org.openqa.selenium.WebElement;


public class PasswordField extends WebElementWrapper {

    public PasswordField(WebElement webElement) {
        super(webElement);
    }

    public PasswordField setText(String value) {
        webElement.clear();
        webElement.sendKeys(value);
        return this;
    }

}
