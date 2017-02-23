import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestApp;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 23.02.2017.
 */
public class AdminLoginTest {
    private WebDriver driver;
    @Before
    public void setUp(){
        driver = TestApp.getInstance().getWebDriver();
    }

    @Test
    public void loginToAdminConsoleTest(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        assertThat(driver.getTitle(), is("My Store"));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
