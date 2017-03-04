import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

/**
 * Created by Александр on 04.03.2017.
 */
public class StickerTest extends BaseTest{

    @Before
    public void navigateToMainPage(){
        driver.get(MAIN_URL_EN);
    }

    @Test
    public void checkPopularStickersTest(){
        String mostPopularLocator = "div#box-most-popular div.content > ul > li";
        List<WebElement> webElements = driver.findElements(By.cssSelector(mostPopularLocator));
        log("There are " + webElements.size() + " elements found by locator: " + mostPopularLocator);
        assertStickersArePresented(webElements);
    }

    @Test
    public void checkCampaignsStickersTest(){
        String campaignsLocator = "div#box-campaigns div.content > ul > li";
        List<WebElement> webElements = driver.findElements(By.cssSelector(campaignsLocator));
        log("There are " + webElements.size() + " elements found by locator: " + campaignsLocator);
        assertStickersArePresented(webElements);
    }

    @Test
    public void checkLatestStickersTest() {
        String latestLocator = "div#box-latest-products div.content > ul > li";
        List<WebElement> webElements = driver.findElements(By.cssSelector(latestLocator));
        log("There are " + webElements.size() + " elements found by locator: " + latestLocator);
        assertStickersArePresented(webElements);
    }

    private void assertStickersArePresented(List<WebElement> webElements) {
        for (WebElement webElement : webElements){
            assertThat(webElement.findElement(By.cssSelector("div.image-wrapper div.sticker")).isDisplayed(), is(true));
        }
    }
}
