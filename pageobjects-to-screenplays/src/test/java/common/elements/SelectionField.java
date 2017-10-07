package common.elements;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class SelectionField extends WebElementWrapper {

    private Select select;

    public SelectionField(WebElement webElement) {
        super(webElement);
        this.select = new Select(webElement);
    }

    public SelectionField selectIndex(int index) {
        select.selectByIndex(index);
        return this;
    }

    public SelectionField selectText(String text) {
        select.selectByValue(text);
        return this;
    }

    public List<String> getSelectionValues() {
        return select.getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
