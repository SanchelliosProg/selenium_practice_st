import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by Александр on 12.03.2017.
 */
public class PickItemTest extends BaseTest {
    @Test
    public void pickTest(){
        driver.get(MAIN_URL_EN);
        driver.findElement(By.cssSelector("div#box-most-popular div.content a.link")).click();
    }
}
