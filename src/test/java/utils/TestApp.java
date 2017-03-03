package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by Александр on 23.02.2017.
 */
public class TestApp {
    private static TestApp instance;
    private WebDriver webDriver;

    public static TestApp getInstance(){
        if(instance == null){
            instance = new TestApp();
        }
        return instance;
    }

    private TestApp(){
        initWebDriver();
    }

    private void initWebDriver(){
        webDriver = new RemoteWebDriver(DesiredCapabilities.chrome());
    }

    public static WebDriver getIEDriver(){
        WebDriver driver = new InternetExplorerDriver();
        return driver;
    }

    public static WebDriver getFirefoxDriver(){
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
