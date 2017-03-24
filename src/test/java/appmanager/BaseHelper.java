package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Александр on 23.03.2017.
 */
public class BaseHelper {
    protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void getTo(String url){
        driver.get(url);
    }

    public WebElement find(String css){
        return driver.findElement(By.cssSelector(css));
    }

    public List<WebElement> findAll(String css){
        return driver.findElements(By.cssSelector(css));
    }

    public void click(String css){
        find(css).click();
    }

}
