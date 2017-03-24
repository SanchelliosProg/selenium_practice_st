package tests;

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

    @Test
    public void pickTest() {
        navigationHelper.getToMainPage();
        int capacity = 3;
        cartPage.getRandomGoodsInNumberOf(capacity);
        cartPage.goToCart();
        cartPage.deleteAllChosenGoods(cartPage.getRowsQuantity(), capacity);
        cartPage.checkIfAllItemsWhereRemoved();
    }
}
