import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Randomizer;

import java.security.Key;
import java.util.List;

/**
 * Created by Александр on 10.03.2017.
 */
public class RegisterTest extends BaseTest {
    private static String mail = Randomizer.getRandomEmailAddress("@mail.ru");
    private static String password = "admin666";
    private static int index = Randomizer.getUSAIndex();

    private void pause() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void disableCapture() {
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=settings&doc=security");
        WebElement row = driver.findElement(By.cssSelector("table.dataTable tr:last-child"));
        WebElement pencil = row.findElement(By.cssSelector("i.fa.fa-pencil"));
        pencil.click();
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("table.dataTable tr:last-child label input:last-child"));
        for (WebElement radio : radioButtons) {
            if (!radio.isSelected()){
                radio.click();
            }
        }
        driver.findElement(By.cssSelector("table.dataTable tr:last-child button[type='submit']")).click();
    }

    @Before
    public void setUp() {
        disableCapture();
    }

    @Test
    public void registerPersonTest() {
        driver.get(MAIN_URL_EN);
        driver.findElement(By.cssSelector("form a[href='http://localhost/litecart/en/create_account']")).click();
        WebElement input = driver.findElement(By.cssSelector("tbody tr td input"));
        input.click();
        input.sendKeys("" + Randomizer.getJustAnyNumber() + Keys.TAB +
                "Skynet" + Keys.TAB +
                "Sarah" + Keys.TAB +
                "Connor" + Keys.TAB +
                "Hell street 666" + Keys.TAB +
                "Heaven Avenue 777" + Keys.TAB + Randomizer.getUSAIndex() + Keys.TAB + "Los-Angels");
        Select countrySelect = new Select(driver.findElement(By.cssSelector("select.select2-hidden-accessible")));
        countrySelect.selectByVisibleText("United States");
        input = driver.findElement(By.cssSelector("table tbody tr td input[type='email']"));
        input.sendKeys(mail + Keys.TAB + "00000000000"+Keys.TAB+Keys.TAB+password+Keys.TAB+password);
        driver.findElement(By.cssSelector("button[type='submit'][name='create_account'][value='Create Account']")).click();
        WebElement emailInput = driver.findElement(By.cssSelector("input[type='text'][name='email']"));
        emailInput.clear();
        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        passwordInput.clear();
        driver.findElement(By.cssSelector("button[type='submit'][name='login']")).click();
        pause();
    }
}
