package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Created by Александр on 23.02.2017.
 */
public class ApplicationManager {
    private static ApplicationManager instance;
    private AdminNavigationHelper adminNavigationHelper;
    private AdminCatalogHelper adminCatalogHelper;
    private EventFiringWebDriver webDriver;

    public static ApplicationManager getInstance(){
        if(instance == null){
            instance = new ApplicationManager();
        }
        return instance;
    }

    private ApplicationManager(){
        initWebDriver();
        adminNavigationHelper = new AdminNavigationHelper(getWebDriver());
        adminCatalogHelper = new AdminCatalogHelper(getWebDriver());
    }

    private void initWebDriver(){
        webDriver = new EventFiringWebDriver(getChromeDriver());
    }

    public static EventFiringWebDriver getChromeDriver(){
        EventFiringWebDriver driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());
        return driver;
    }

    public static EventFiringWebDriver getIEDriver(){
        EventFiringWebDriver driver = new EventFiringWebDriver(new InternetExplorerDriver());
        driver.register(new MyListener());
        return driver;
    }

    public static EventFiringWebDriver getFirefoxDriver(){
        EventFiringWebDriver driver = new EventFiringWebDriver(new FirefoxDriver());
        driver.register(new MyListener());
        return driver;
    }

    public EventFiringWebDriver getWebDriver() {
        return webDriver;
    }

    public AdminNavigationHelper getAdminNavigationHelper() {
        return adminNavigationHelper;
    }

    public AdminCatalogHelper getAdminCatalogHelper() {
        return adminCatalogHelper;
    }

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by +  " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
        }
    }
}
