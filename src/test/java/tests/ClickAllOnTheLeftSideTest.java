package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 03.03.2017.
 */
public class ClickAllOnTheLeftSideTest extends BaseTest{



    @Test
    public void clickAllTest(){
        loginAdmin();
        String boxLocator = "div#box-apps-menu-wrapper > ul";
        WebElement box = driver.findElement(By.cssSelector(boxLocator));
        List<WebElement> apps = box.findElements(By.cssSelector("li#app-"));
        for(int i = 1; i <= apps.size(); i++){
            String locator = boxLocator+"> li#app-:nth-child("+i+")";
            WebElement currentElement = driver.findElement(By.cssSelector(locator));
            currentElement.click();
            try{
                WebElement webElement = driver.findElement(By.cssSelector("ul.docs"));
                List<WebElement> docs = webElement.findElements(By.cssSelector("li"));
                if (docs.size() > 1) {
                    for (int j = 2; j <= docs.size(); j++) {
                        String innerLocator = locator + " > ul.docs > li:nth-child("+j+")";
                        WebElement innerElement = driver.findElement(By.cssSelector(innerLocator));
                        innerElement.click();
                    }
                }
            }catch (NoSuchElementException ex){
                System.out.println("Have no list");
            }
        }
    }
}
