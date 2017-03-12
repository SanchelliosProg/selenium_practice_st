import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 12.03.2017.
 */
public class PickItemTest extends BaseTest {
    WebElement cart;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Test
    public void pickTest() {
        driver.get(MAIN_URL_EN);
        cart = getCart();
        int quantity = getIntValueFromCart();
        while (quantity < 3) {

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
            driver.get(MAIN_URL_EN);
        }
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        driver.findElement(By.cssSelector("button[type='submit'][name='remove_cart_item']")).click();
        List<WebElement> table = driver.findElements(By.cssSelector("table.dataTable.rounded-corners tbody tr"));
        int rowsQuantity = table.size();
        WebElement finalLabel;
        try {
            for (int i = 0; i <= quantity; i++) {

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
        } finally {
            finalLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#checkout-cart-wrapper p em")));
            assertThat(finalLabel.isDisplayed(), is(true));
        }


    }

    private int getIntValueFromCart() {
        return Integer.parseInt(cart.getText());
    }

    private WebElement getCart() {
        return driver.findElement(By.cssSelector("span.quantity"));
    }
}
