package tests;

import appmanager.AdminCatalogHelper;
import appmanager.AdminNavigationHelper;
import appmanager.NavigationHelper;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import appmanager.ApplicationManager;
import pages.CartPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 04.03.2017.
 */
public class BaseTest {
    protected static EventFiringWebDriver driver = ApplicationManager.getInstance().getWebDriver();
    protected static WebDriverWait wait;
    protected AdminNavigationHelper adminNavigationHelper = ApplicationManager.getInstance().getAdminNavigationHelper();
    protected AdminCatalogHelper adminCatalogHelper = ApplicationManager.getInstance().getAdminCatalogHelper();
    protected NavigationHelper navigationHelper = ApplicationManager.getInstance().getNavigationHelper();
    protected CartPage cartPage = ApplicationManager.getInstance().getCartPage();


    protected String ADMIN_URL = "http://localhost/litecart/admin/";
    protected String MAIN_URL_EN = "http://localhost/litecart/en/";
    protected String COUNTRIES_URL = "http://localhost/litecart/admin/?app=countries&doc=countries";
    protected String GEOZONES_URL = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";
    protected String CATALOG_URL = "http://localhost/litecart/admin/?app=catalog&doc=catalog";


    protected void loginAdmin(){
        driver.get(ADMIN_URL);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        assertThat(driver.getTitle(), is("My Store"));
    }

    protected WebDriverWait getWait(int seconds){
        return new WebDriverWait(driver, seconds);
    }

    @AfterClass
    public static void tearDown(){
        printBrowserLogs();
        driver.quit();
    }

    protected void log(Object ... objects) {
        for (Object obj : objects) {
            System.out.print(String.valueOf(obj) + " ");
        }
        System.out.println();
    }

    protected static void printBrowserLogs(){
        for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
            System.out.println(l);
        }
    }
}
