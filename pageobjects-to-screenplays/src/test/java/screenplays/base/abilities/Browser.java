package screenplays.base.abilities;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;

import common.elements.WebElementWrapper;


public class Browser {

    public Browser(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private WebDriver webDriver;

    public void close() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    public void open(String url) {
        this.webDriver.get(url);
    }

    public <T extends WebElementWrapper> T find(Function<Locator, Optional<T>> findFunction) {
        return findOptional(findFunction).get();
    }

    public <T extends WebElementWrapper> Optional<T> findOptional(Function<Locator, Optional<T>> findFunction) {
        return findFunction.apply(getLocator());
    }

    public <T extends WebElementWrapper> Stream<T> findMany(Function<Locator, Stream<T>> findFunction) {
        return findFunction.apply(getLocator());
    }

    public Locator getLocator() {
        return new Locator(webDriver);
    }

}
