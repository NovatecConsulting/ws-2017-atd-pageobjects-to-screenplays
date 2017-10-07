package common.elements;

import org.openqa.selenium.WebElement;


public class Link extends WebElementWrapper {

    public Link(WebElement webElement) {
        super(webElement);
    }

    public Link click() {
        webElement.click();
        return this;
    }

}
