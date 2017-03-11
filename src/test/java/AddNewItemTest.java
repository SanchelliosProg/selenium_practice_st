import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.security.Key;

/**
 * Created by Александр on 11.03.2017.
 */
public class AddNewItemTest extends BaseTest {
    private final String APPLE_DESC = "The Apple II series (trademarked with square brackets as \"Apple ][\" and rendered on later models as \"Apple //\") is a family of home computers, one of the first highly successful mass-produced text command microcomputer products,[1] designed primarily by Steve Wozniak, manufactured by Apple Computer (now Apple Inc.) and introduced in 1977 with the original Apple II. In terms of ease of use, features and expandability, the Apple II was a major technological advancement over its predecessor, the Apple I, a limited-production bare circuit board computer for electronics hobbyists that pioneered many features that made the Apple II a commercial success. Introduced at the West Coast Computer Faire on April 16, 1977, the Apple II was among the first successful personal computers; it launched the Apple company into a successful business (and allowed several related companies to start). Throughout the years, a number of models were sold, with the most popular model remaining relatively little changed into the 1990s. While primarily an 8-bit computer, by mid-run a 16-bit model was introduced.";
    @Before
    public void setUp() {
        loginAdmin();
    }

    @Test
    public void addTest() {
        driver.get(CATALOG_URL);
        driver.findElement(
                By
                .cssSelector("a.button[href='http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product']"))
                .click();
        WebElement input = driver.findElement(By.cssSelector("span.input-wrapper input[type='text'][name='name[en]'][data-type='text'][data-size='medium']"));
        input.click();
        input.sendKeys("Apple II" + Keys.TAB + "12345");
        driver.findElement(By.cssSelector("input[type='checkbox'][name='product_groups[]'][value='1-3']")).click();
        input = driver.findElement(By.cssSelector("input[type='number'][name='quantity']"));
        input.clear();
        input.sendKeys("5");
        input = driver.findElement(By.cssSelector("input[type='file'][name='new_images[]']"));
        try {
            input.sendKeys(getAbsolutePathToTheFile(".\\..\\resources\\appleii-system.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        input = driver.findElement(By.cssSelector("input[type='date'][name='date_valid_from']"));
        input.sendKeys("10122016" + Keys.TAB + "20042017");
        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
        Select select = new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
        select.selectByIndex(1);
        input = driver.findElement(By.cssSelector("input[type='text'][name='keywords']"));
        input.click();
        input.sendKeys("apple"+Keys.TAB+"the best computer ever"+Keys.TAB+APPLE_DESC+Keys.TAB+"Apple ][ computer"+ Keys.TAB+"apple_ii");
        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        input = driver.findElement(By.cssSelector("input[type='number'][name='purchase_price']"));
        input.click();
        input.clear();
        input.sendKeys("2000");
        select = new Select(driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")));
        select.selectByVisibleText("US Dollars");
        driver.findElement(By.cssSelector("button[type='submit'][name='save']")).click();
        pause();
    }

    private String getAbsolutePathToTheFile(String relativePath) throws IOException {
        File pic = new File(relativePath);
        return pic.getCanonicalPath();
    }

    private void pause(){
        try {
            Thread.sleep(32000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
