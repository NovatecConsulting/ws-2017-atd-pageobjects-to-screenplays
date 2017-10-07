package common.screenplay.abilities;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Locator {

    private WebDriver webDriver;

    Locator(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Returns the first found WebElement matching the given CSS Selector. If
     * no WebElement was found, the lookup will be retried up to the configured
     * timeout. Upon reaching the timeout an empty Optional is returned!
     */
    public Optional<WebElement> findByCss(String name, String selector) {
        WebElement webElement = null;
        try {
            webElement = withWait(name, () -> executeFindByCss(selector));
        } catch (ConditionTimeoutException e) {
            System.out.println("Not found because of timeout: " + selector);
        }
        return Optional.ofNullable(webElement);
    }

    private WebElement withWait(String name, Supplier<Optional<WebElement>> webElementSupplier)
        throws ConditionTimeoutException {
        await("waiting for <" + name + "> to be found")//
            .atMost(TWO_SECONDS)//
            .pollDelay(50L, TimeUnit.MILLISECONDS)//
            .until(() -> webElementSupplier.get().isPresent());
        return webElementSupplier.get().orElseThrow(IllegalStateException::new);
    }

    private Optional<WebElement> executeFindByCss(String selector) {
        WebElement element = null;
        try {
            element = webDriver.findElement(By.cssSelector(selector));
        } catch (NoSuchElementException e) {
            System.out.println("No such element at the moment: " + selector);
        }
        return Optional.ofNullable(element);
    }

    /**
     * Returns all WebElements matching the given CSS Selector present in the
     * current page's DOM. Might return an empty list of none were found at
     * the moment. In dynamic single page applications this might lead to
     * false results if not compensated by the caller.
     */
    public Stream<WebElement> findManyByCss(String selector) {
        return webDriver.findElements(By.cssSelector(selector)).stream();
    }

}
