import org.junit.*;
import org.openqa.selenium.WebDriver;
import utils.TestApp;


/**
 * Created by avvasi78 on 21.02.2017.
 */
public class SimpleTest {

    private WebDriver driver;

    @Before
    public void setUp(){
        driver = TestApp.getInstance().getWebDriver();
    }

    @Test
    public void visitSoftwareTesting(){
        String SOFTWARE_TESTING_URL = "http://software-testing.ru/";
        driver.get(SOFTWARE_TESTING_URL);
    }

    @After
    public void  tearDown(){
        driver.quit();
    }
}
