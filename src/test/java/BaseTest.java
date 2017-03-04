import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestApp;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 04.03.2017.
 */
public class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected String ADMIN_URL = "http://localhost/litecart/admin/";
    protected String MAIN_URL_EN = "http://localhost/litecart/en/";
    protected String COUNTRIES_URL = "http://localhost/litecart/admin/?app=countries&doc=countries";
    protected String GEOZONES_URL = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";
    @BeforeClass
    public static void setUp(){
        driver = TestApp.getInstance().getWebDriver();
        driver.manage().timeouts().implicitlyWait(250, TimeUnit.MILLISECONDS);
    }

    protected void loginAdmin(){
        driver.get(ADMIN_URL);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        assertThat(driver.getTitle(), is("My Store"));
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    protected void log(Object ... objects) {
        for (Object obj : objects) {
            System.out.print(String.valueOf(obj) + " ");
        }
        System.out.println();
    }
}
