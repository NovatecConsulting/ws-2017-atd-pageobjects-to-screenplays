package common.elements;

import org.openqa.selenium.WebElement;


public class Text extends WebElementWrapper {

    public Text(WebElement webElement) {
        super(webElement);
    }

    public String getText() {
        return webElement.getText();
    }

}
