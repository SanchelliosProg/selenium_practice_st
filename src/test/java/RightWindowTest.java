import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * Created by Александр on 14.03.2017.
 */
public class RightWindowTest  extends BaseTest {
    private String baseWindow;
    @Test
    public void isRightWindowOpened(){
        wait = new WebDriverWait(driver, 10);
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("td#content div a.button")).click();
        baseWindow = driver.getWindowHandle();
        log(baseWindow);
        List<WebElement> iconButtons = driver.findElements(By.cssSelector("i.fa.fa-external-link"));
        for (WebElement button : iconButtons){
            button.click();
            checkLink();
        }
    }

    private void checkLink() {
        Set<String> currentWindows = driver.getWindowHandles();
        log(currentWindows);
        currentWindows.remove(baseWindow);
        log(currentWindows);
        for (String pageId : currentWindows){
            driver.switchTo().window(pageId);
        }
        boolean isRightPage = driver.getCurrentUrl().contains("wiki") || driver.getCurrentUrl().contains("info");
        assertThat(isRightPage, is(true));
        driver.close();
        driver.switchTo().window(baseWindow);
    }
}
