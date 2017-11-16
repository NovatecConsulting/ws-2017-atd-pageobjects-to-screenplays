package pageobjects.pages;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.waitAtMost;
import static org.awaitility.Duration.TWO_SECONDS;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


/**
 * This is the base class for all page objects. It defines some framework logic
 * for managing the WebDriver, identifying elements on a page and creating
 * instances of other page objects.
 */
public abstract class BasePage<T extends BasePage> {

    private final WebDriver webDriver;

    protected BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        assertThatCorrectPageIsDisplayed();
    }

    /**
     * Should implement some logic to verify that the correct page is currently displayed.
     * Is invoked right after the page object was initialized.
     */
    protected abstract void assertThatCorrectPageIsDisplayed();

    /**
     * Returns the first found WebElement matching the given CSS Selector. If
     * no WebElement was found, the lookup will be retried up to the configured
     * timeout. Upon reaching the timeout an empty Optional is returned!
     */
    protected Optional<WebElement> findOptionallyByCss(String name, String selector) {
        try {
            return Optional.of(findByCss(name, selector));
        } catch (ConditionTimeoutException e) {
            return Optional.empty();
        }
    }

    /**
     * Returns the first found WebElement matching the given CSS Selector. If
     * no WebElement was found, the lookup will be retried up to the configured
     * timeout. Upon reaching the timeout a ConditionTimeoutException is thrown.
     * This method will never return null!
     */
    protected WebElement findByCss(String name, String selector) {
        return withWait(name, () -> executeFindByCss(selector));
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
        }
        return Optional.ofNullable(element);
    }

    /**
     * Returns all WebElements matching the given CSS Selector present in the
     * current page's DOM. Might return an empty list of none were found at
     * the moment. In dynamic single page applications this might lead to
     * false results if not compensated by the caller.
     */
    protected List<WebElement> findManyByCss(String selector) {
        return webDriver.findElements(By.cssSelector(selector));
    }

    /**
     * Creates a new instance of the given page type. The created instance
     * will use the same WebDriver as this instance does.
     */
    protected <P extends BasePage<P>> P create(Class<P> pageType) {
        return PageFactory.initElements(webDriver, pageType);
    }

    /**
     * Waits up to 2 seconds until the given function returns {@code true}.
     * The function's parameter is the current page instance.
     */
    public T waitUntil(Predicate<T> body) {
        waitAtMost(TWO_SECONDS).until(() -> body.test(( T ) this));
        return ( T ) this;
    }

}
