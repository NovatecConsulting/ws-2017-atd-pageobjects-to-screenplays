package pageobjects.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.elements.WebElementWrapper;


/**
 * This models the Juice Shop's search result page and all interactions possible
 * there.
 */
public class SearchResultPage extends JuiceShopPage<SearchResultPage> {

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void assertThatCorrectPageIsDisplayed() {
        WebElement headline = findByCss("Search Result Headline", "span[translate=TITLE_SEARCH_RESULTS]");
        assertThat(headline.isDisplayed()).isTrue();
    }

    /**
     * Returns the number of currently displayed product rows.
     * <p>
     * Since the Juice Shop is a dynamic application, this might return 0 when
     * invoked in the split seconds between page navigation.
     */
    public long getNumberOfResults() {
        return findProductRows().count();
    }

    /**
     * Tries to find the product of the given name within the result set.
     * If not found an exception is thrown. If it was found it will be
     * added to the shopping basket.
     * <p>
     * Since the Juice Shop is a dynamic application, this might fail
     * unexpectedly if invoked in the split seconds between page navigation.
     * It should always be checked first if the table was loaded. This
     * can be done by using {@link #getNumberOfResults()} within a wait
     * operation in order to wait until at least 1 product is displayed.
     */
    public SearchResultPage addToBasket(String productName) {
        findRowByProductName(productName)//
            .orElseThrow(() -> new IllegalStateException("product <" + productName + "> not found!"))//
            .addToBasket();
        return create(SearchResultPage.class);
    }

    private Optional<ProductRow> findRowByProductName(String productName) {
        return findProductRows()//
            .filter(row -> productName.equals(row.getProductName()))//
            .findFirst();
    }

    private Stream<ProductRow> findProductRows() {
        return findManyByCss("tr[data-ng-repeat='product in products']").stream().map(ProductRow::new);
    }

    private static class ProductRow extends WebElementWrapper {

        ProductRow(WebElement webElement) {
            super(webElement);
        }

        String getProductName() {
            return webElement.findElement(By.xpath("td[2]")).getText();
        }

        void addToBasket() {
            webElement.findElement(By.cssSelector("a[ng-click='addToBasket(product.id)']")).click();
        }

    }

}
