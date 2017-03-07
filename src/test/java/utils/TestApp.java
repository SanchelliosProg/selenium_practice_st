package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        webDriver = getChromeDriver();
    }

    public static WebDriver getChromeDriver(){
        return new ChromeDriver();
    }

    public static WebDriver getIEDriver(){
        return new InternetExplorerDriver();
    }

    public static WebDriver getFirefoxDriver(){
        return new FirefoxDriver();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
