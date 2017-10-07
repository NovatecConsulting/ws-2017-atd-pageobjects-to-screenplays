package common.elements;

import org.openqa.selenium.WebElement;


public class Button extends WebElementWrapper {

    public Button(WebElement webElement) {
        super(webElement);
    }

    public Button click() {
        webElement.click();
        return this;
    }

}
