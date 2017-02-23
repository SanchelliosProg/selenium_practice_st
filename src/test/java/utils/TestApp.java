package utils;

import org.openqa.selenium.WebDriver;
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

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
