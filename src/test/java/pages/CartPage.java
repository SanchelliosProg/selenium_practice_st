package pages;

import appmanager.BaseHelper;
import appmanager.NavigationHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 24.03.2017.
 */
public class CartPage extends BaseHelper {

    private WebElement cart;
    private WebDriverWait wait = new WebDriverWait(driver, 10);

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void checkIfAllItemsWhereRemoved(){
        WebElement finalLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#checkout-cart-wrapper p em")));
        assertThat(finalLabel.isDisplayed(), is(true));
    }

    public void deleteAllChosenGoods(int rowsQuantity, int capacity){
        try {
            for (int i = 0; i < capacity; i++) {

                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[type='submit'][name='remove_cart_item']"))).click();
                int expectedQuantity = rowsQuantity - i;
                if (expectedQuantity == 5) {
                    expectedQuantity = 0;
                }
                wait.until(
                        ExpectedConditions
                                .numberOfElementsToBe(By
                                        .cssSelector("table.dataTable.rounded-corners tbody tr"), expectedQuantity));
            }
        } catch (NoSuchElementException e) {
        }
    }

    public int getRowsQuantity(){
        List<WebElement> table = driver.findElements(By.cssSelector("table.dataTable.rounded-corners tbody tr"));
        return table.size();
    }

    public void goToCart(){
        driver.findElement(By.cssSelector("div#cart a.link")).click();
    }

    public void getRandomGoodsInNumberOf(int capacity){
        NavigationHelper navigationHelper = new NavigationHelper(driver);
        cart = getCart();
        int quantity = getIntValueFromCart();
        while (quantity < capacity) {
            driver.findElement(By.cssSelector("div#box-most-popular div.content a.link")).click();
            try {
                Select select = new Select(driver.findElement(By.cssSelector("select[name='options[Size]']")));
                select.selectByIndex(1);
            } catch (NoSuchElementException ignored) {
            }
            driver.findElement(By.cssSelector("td.quantity button[type='submit']")).click();
            cart = getCart();
            wait.until(ExpectedConditions.textToBePresentInElement(cart, String.valueOf(quantity + 1)));
            quantity = getIntValueFromCart();
            navigationHelper.getToMainPage();
        }
    }

    public int getIntValueFromCart() {
        return Integer.parseInt(cart.getText());
    }

    public WebElement getCart() {
        return driver.findElement(By.cssSelector("span.quantity"));
    }


}
