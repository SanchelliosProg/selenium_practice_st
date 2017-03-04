import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestApp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Александр on 03.03.2017.
 */
public class ClickAllOnTheLeftSideTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private String ADMIN_URL = "http://localhost/litecart/admin/";
    @BeforeClass
    public static void setUp(){
        driver = TestApp.getInstance().getWebDriver();
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);

    }
    private void login(){
        driver.get(ADMIN_URL);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        assertThat(driver.getTitle(), is("My Store"));
    }

    @Test
    public void clickAllTest(){
        login();
        String boxLocator = "div#box-apps-menu-wrapper > ul";
        WebElement box = driver.findElement(By.cssSelector(boxLocator));
        List<WebElement> apps = box.findElements(By.cssSelector("li#app-"));
        for(int i = 1; i <= apps.size(); i++){
            String locator = boxLocator+"> li#app-:nth-child("+i+")";
            WebElement currentElement = driver.findElement(By.cssSelector(locator));
            currentElement.click();
            try{
                WebElement webElement = driver.findElement(By.cssSelector("ul.docs"));
                List<WebElement> docs = webElement.findElements(By.cssSelector("li"));
                if (docs.size() > 1) {
                    for (int j = 2; j <= docs.size(); j++) {
                        String innerLocator = locator + " > ul.docs > li:nth-child("+j+")";
                        WebElement innerElement = driver.findElement(By.cssSelector(innerLocator));
                        innerElement.click();
                    }
                }
            }catch (NoSuchElementException ex){
                System.out.println("Have no list");
            }
        }
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
