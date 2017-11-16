package pageobjects.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.elements.Button;
import common.elements.Link;
import common.elements.TextField;
import common.elements.WebElementWrapper;


/**
 * This models the Juice Shop's shopping basket page and all interactions
 * possible there.
 */
public class ShoppingBasketPage extends JuiceShopPage<ShoppingBasketPage> {

    public ShoppingBasketPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void assertThatCorrectPageIsDisplayed() {
        WebElement headline = findByCss("Basket Headline", "span[translate=TITLE_BASKET]");
        assertThat(headline.isDisplayed())//
            .describedAs("Shopping Basket Page Headline is displayed")//
            .isTrue();
    }

    public ShoppingBasketPage deleteFromBasket(String productName) {
        findRequiredProductByName(productName)//
            .delete();
        return create(ShoppingBasketPage.class);
    }

    public ShoppingBasketPage increaseQuantity(String productName) {
        findRequiredProductByName(productName)//
            .increaseQuantity();
        return create(ShoppingBasketPage.class);
    }

    public ShoppingBasketPage decreaseQuantity(String productName) {
        findRequiredProductByName(productName)//
            .decreaseQuantity();
        return create(ShoppingBasketPage.class);
    }

    public List<String> getNamesOfProductsInBasket() {
        return findBasketItemRows()//
            .map(ProductRow::getProductName)//
            .collect(Collectors.toList());
    }

    private ProductRow findRequiredProductByName(String productName) {
        return findRowByProductName(productName)//
            .orElseThrow(() -> new IllegalStateException("product <" + productName + "> not found!"));
    }

    public long getNumberOfItems() {
        return findBasketItemRows().count();
    }

    private Optional<ProductRow> findRowByProductName(String productName) {
        return findBasketItemRows()//
            .filter(row -> productName.equals(row.getProductName()))//
            .findFirst();
    }

    private Stream<ProductRow> findBasketItemRows() {
        return findManyByCss("tr[data-ng-repeat='product in products']").stream().map(ProductRow::new);
    }

    private Button findCheckoutButton() {
        return new Button(findByCss("Checkout Button", "#checkoutButton"));
    }

    private Link findShowCouponLink() {
        return new Link(findByCss("Collapse Coupon Button", "#collapseCouponButton"));
    }

    private Link findShowPaymentOptionsLink() {
        return new Link(findByCss("Collapse Payment Button", "#collapsePaymentButton"));
    }

    private TextField findCouponTextField() {
        return new TextField(findByCss("Apply Coupon Button", "#coupon"));
    }

    private Button findApplyCouponButton() {
        return new Button(findByCss("Apply Coupon Button", "#applyCouponButton"));
    }

    private static class ProductRow extends WebElementWrapper {

        public ProductRow(WebElement webElement) {
            super(webElement);
        }

        String getProductName() {
            return webElement.findElement(By.xpath("td[1]")).getText();
        }

        String getProductDescription() {
            return webElement.findElement(By.xpath("td[2]")).getText();
        }

        String getProductPrice() {
            return webElement.findElement(By.xpath("td[3]")).getText();
        }

        String getQuantity() {
            return webElement.findElement(By.xpath("td[4]/div/span")).getText();
        }

        void decreaseQuantity() {
            webElement.findElement(By.xpath("td[4]/div/a[1]")).click();
        }

        void increaseQuantity() {
            webElement.findElement(By.xpath("td[4]/div/a[2]")).click();
        }

        String getTotalPrice() {
            return webElement.findElement(By.xpath("td[5]")).getText();
        }

        void delete() {
            webElement.findElement(By.xpath("td[6]/div/a")).click();
        }

    }

}
