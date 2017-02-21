import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by avvasi78 on 21.02.2017.
 */
public class SimpleTest {

    private final String SOFTWARE_TESTING_URL = "http://software-testing.ru/";

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void visitGitHub(){
        driver.get(SOFTWARE_TESTING_URL);
    }

    @After
    public void  tearDown(){
        driver.quit();
    }
}
