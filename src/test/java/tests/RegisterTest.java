package tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Randomizer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

/**
 * Created by Александр on 10.03.2017.
 */
public class RegisterTest extends BaseTest {
    private static String mail = Randomizer.getRandomEmailAddress("@mail.ru");
    private static String password = "admin666";
    private static int index = Randomizer.getUSAIndex();

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
        WebDriverWait wait = new WebDriverWait(driver, 10);
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
        driver.findElement(By.cssSelector("div.content ul.list-vertical li a[href='http://localhost/litecart/en/logout']")).click();
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text'][name='email']")));
        emailInput.clear();
        emailInput.sendKeys(mail);
        WebElement passwordInput = driver.findElement(By.cssSelector("input[type='password']"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit'][name='login']")).click();
        assertThat(driver.getTitle(), is("Online Store | My Store"));
    }
}
