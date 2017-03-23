package tests;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Александр on 23.03.2017.
 */
public class LoggingTest extends BaseTest {


    @Test
    public void tryLogItTest() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginAdmin();
        adminCatalogHelper.openRubberDucksCatalog();

        List<WebElement> ducks = adminCatalogHelper.getDuckRows();
        List<String> urls = new ArrayList<>();
        for (WebElement duck : ducks){
            urls.add(duck.getAttribute("href"));
        }

        for (String url : urls){
            driver.get(url);
        }
    }
}
