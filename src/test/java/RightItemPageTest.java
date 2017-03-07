import com.gargoylesoftware.htmlunit.javascript.configuration.BrowserName;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.HRefHelper;
import utils.MultiplePricesHRefHelper;
import utils.TestApp;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 06.03.2017.
 */
public class RightItemPageTest extends BaseTest {
    private final static BrowserName CURRENT_BROWSER = BrowserName.CHROME;
    private final String CHECK_TITLES_LOCATOR = "div#box-product > div > h1.title";
    private final String MOST_POPULAR_ID = "#box-most-popular.box ";
    private final String BOX_CAMPAGIN_ID = "#box-campaigns ";
    private final String LATEST_PRODUCST_ID = "#box-latest-products ";
    private List<HRefHelper> duckProfiles;

    @BeforeClass
    public static void setUp() {
        switch (CURRENT_BROWSER) {
            case CHROME:
                break;
            case FF:
                driver = TestApp.getFirefoxDriver();
                break;
            case IE:
                driver = TestApp.getIEDriver();
                break;
            default:
                break;
        }
    }

    @Test
    public void correctMostPopularNamesTest() {
        duckProfiles = new ArrayList<>();
        driver.get(MAIN_URL_EN);
        List<WebElement> mostPopularCards = getCardsWithValues(MOST_POPULAR_ID);
        collectDuckProfiles(mostPopularCards, "a");
        for (HRefHelper duckProfile : duckProfiles) {
            check(duckProfile, CHECK_TITLES_LOCATOR);
        }
    }

    @Test
    public void correctBoxCampaignsNamesTest() {
        duckProfiles = new ArrayList<>();
        driver.get(MAIN_URL_EN);
        List<WebElement> boxCampaignsCards = getCardsWithValues(BOX_CAMPAGIN_ID);
        collectDuckProfiles(boxCampaignsCards, "a.link");
        for (HRefHelper duckProfile : duckProfiles) {
            check(duckProfile, CHECK_TITLES_LOCATOR);
        }
    }

    @Test
    public void correctLatestProductsNamesTest() {
        duckProfiles = new ArrayList<>();
        driver.get(MAIN_URL_EN);
        List<WebElement> latestCards = getCardsWithValues(LATEST_PRODUCST_ID);
        collectDuckProfiles(latestCards, "a.link");
        for (HRefHelper duckProfile : duckProfiles) {
            check(duckProfile, CHECK_TITLES_LOCATOR);
        }
    }

    @Test
    public void correctPopularPriceTest() {
        log("на главной странице и на странице товара совпадают цены (обычная и акционная)");
        duckProfiles = new ArrayList<>();
        driver.get(MAIN_URL_EN);
        List<WebElement> mostPopularCards = getCardsWithValues(MOST_POPULAR_ID);
        for (WebElement card : mostPopularCards) {
            try {
                MultiplePricesHRefHelper duckProfile = new MultiplePricesHRefHelper();
                duckProfile.setUrl(card.findElement(By.cssSelector("a.link")).getAttribute("href"));
                String regularPrice = card.findElement(By.cssSelector("div.price-wrapper s.regular-price")).getText();
                String campaignPrice = card.findElement(By.cssSelector("div.price-wrapper strong.campaign-price")).getText();
                duckProfile.setRegularPriceValue(regularPrice);
                duckProfile.setCampaignPriceValue(campaignPrice);
                duckProfiles.add(duckProfile);
            }catch (NoSuchElementException ex){
                HRefHelper duckProfile = new HRefHelper();
                duckProfile.setUrl(card.findElement(By.cssSelector("a.link")).getAttribute("href"));
                String price = card.findElement(By.cssSelector("div.price-wrapper span.price")).getText();
                duckProfile.setValue(price);
                duckProfiles.add(duckProfile);
            }
        }
    }

    private void collectDuckProfiles(List<WebElement> mostPopularCards, String linkLocator) {
        for (WebElement card : mostPopularCards) {
            if(!isOnRightPage(MAIN_URL_EN)){
                driver.get(MAIN_URL_EN);
            }
            HRefHelper duckProfile = new HRefHelper();
            duckProfile.setUrl(card.findElement(By.cssSelector(linkLocator)).getAttribute("href"));
            log(duckProfile.getUrl());
            duckProfile.setValue(card.findElement(By.cssSelector(linkLocator + " div.name")).getText());
            log(duckProfile.getValue());
            duckProfiles.add(duckProfile);
        }
    }

    private boolean isOnRightPage(String expectedUrl){
        return driver.getCurrentUrl().equals(expectedUrl);
    }

    private void check(HRefHelper duckProfile, String locator){
        driver.get(duckProfile.getUrl());
        String name = driver.findElement(By.cssSelector(locator)).getText();
        assertThat(duckProfile.getValue().equals(name), is(true));
    }

    private List<WebElement> getCardsWithValues(String id) {
        driver.get(MAIN_URL_EN);
        return driver.findElements(By.cssSelector("div"+id+"div.content ul.listing-wrapper.products li"));
    }

    @Test
    public void test3() {
        log("обычная цена серая и зачёркнутая, а акционная цена красная и жирная (это надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)");
    }

    @Test
    public void test4() {
        log("акционная цена крупнее, чем обычная (это надо проверить на каждой странице независимо)");
    }
}
