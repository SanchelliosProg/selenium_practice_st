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
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Александр on 06.03.2017.
 */
public class RightItemPageTest extends BaseTest {
    private final static BrowserName CURRENT_BROWSER = BrowserName.CHROME;
    private final String CHECK_TITLES_LOCATOR = "div#box-product > div > h1.title";
    private final String CHECK_PRICES = "CHECK_PRICES";
    private final String CHECK_PRICES_STYLES = "CHECK_PRICES_STYLES";
    private final String MOST_POPULAR_ID = "#box-most-popular.box ";
    private final String BOX_CAMPAIGN_ID = "#box-campaigns ";
    private final String LATEST_PRODUCST_ID = "#box-latest-products ";
    private final String REGULAR_PRICE_CSS_STYLE = "regular-price";
    private final String CAMPAIGN_PRICE_CSS_STYLE = "campaign-price";
    private final String CHECK_PRICES_SIZES = "CHECK_PRICES_SIZES";
    private final String ALL = "ALL";
    private List<HRefHelper> duckProfiles;

    final String regularPriceLocator = "div.price-wrapper s";
    final String campaignPriceLocator = "div.price-wrapper strong";

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
        List<WebElement> boxCampaignsCards = getCardsWithValues(BOX_CAMPAIGN_ID);
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
        duckProfiles = new ArrayList<>();
        driver.get(MAIN_URL_EN);
        List<WebElement> mostPopularCards = getCardsWithValues(ALL);
        for (WebElement card : mostPopularCards) {
            try {
                getAsCampaignCard(card);
            } catch (NoSuchElementException ex) {
                getAsRegularCard(card);
            }
        }

        for (HRefHelper duckProfile : duckProfiles) {
            check(duckProfile, CHECK_PRICES);
        }
    }

    @Test
    public void checkColorsTest() {
        // В данном тесте, как рекомендовано в видео, чтобы тесты были более стабильны при запусках браузеров,
        // выполняется проверка верности CSS стилей, а не CSS значений с помощью метода getCssValue()
        duckProfiles = new ArrayList<>();
        driver.get(MAIN_URL_EN);
        List<WebElement> cards = getCardsWithValues(ALL);
        for (WebElement card : cards) {
            try {
                getAsCampaignCard(card);
                checkPricesStyle(card);
            } catch (NoSuchElementException ignored) {
            }
        }

        for (HRefHelper duckProfile : duckProfiles) {
            check(duckProfile, CHECK_PRICES_STYLES);
        }
    }

    @Test
    public void checkPricesSizes() {
        duckProfiles = new ArrayList<>();
        driver.get(MAIN_URL_EN);
        List<WebElement> cards = getCardsWithValues(ALL);
        for (WebElement card : cards) {
            try {
                getAsCampaignCard(card);
                checkPriceTextSizes(card);
            } catch (NoSuchElementException ignored) {
            }
        }

        for (HRefHelper duckProfile : duckProfiles) {
            check(duckProfile, CHECK_PRICES_SIZES);
        }
    }

    private void checkPriceTextSizes(WebElement card) {
        double regTextSize = Double.parseDouble(card.findElement(By.cssSelector(regularPriceLocator)).getCssValue("font-size").replaceAll("[^\\d.]", ""));
        double campTextSize = Double.parseDouble(card.findElement(By.cssSelector(campaignPriceLocator)).getCssValue("font-size").replaceAll("[^\\d.]", ""));
        assertThat((regTextSize < campTextSize), is(true));
    }

    private void checkPricesStyle(WebElement card) {
        assertThat(card.findElement(By.cssSelector(regularPriceLocator)).getAttribute("class"), is(REGULAR_PRICE_CSS_STYLE));
        assertThat(card.findElement(By.cssSelector(campaignPriceLocator)).getAttribute("class"), is(CAMPAIGN_PRICE_CSS_STYLE));
    }

    private void getAsRegularCard(WebElement card) {
        HRefHelper duckProfile = new HRefHelper();
        duckProfile.setUrl(card.findElement(By.cssSelector("a.link")).getAttribute("href"));
        String price = card.findElement(By.cssSelector("div.price-wrapper span.price")).getText();
        duckProfile.setValue(price);
        duckProfiles.add(duckProfile);
    }

    private void getAsCampaignCard(WebElement card) {
        MultiplePricesHRefHelper duckProfile = new MultiplePricesHRefHelper();
        duckProfile.setUrl(card.findElement(By.cssSelector("a.link")).getAttribute("href"));
        String regularPrice = card.findElement(By.cssSelector("div.price-wrapper s.regular-price")).getText();
        String campaignPrice = card.findElement(By.cssSelector("div.price-wrapper strong.campaign-price")).getText();
        duckProfile.setRegularPriceValue(regularPrice);
        duckProfile.setCampaignPriceValue(campaignPrice);
        duckProfiles.add(duckProfile);
    }

    private void collectDuckProfiles(List<WebElement> mostPopularCards, String linkLocator) {
        for (WebElement card : mostPopularCards) {
            if (!isOnRightPage(MAIN_URL_EN)) {
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

    private boolean isOnRightPage(String expectedUrl) {
        return driver.getCurrentUrl().equals(expectedUrl);
    }

    private void check(HRefHelper duckProfile, String locator) {
        if (Objects.equals(locator, CHECK_PRICES)) {
            if (duckProfile instanceof MultiplePricesHRefHelper) {
                MultiplePricesHRefHelper extendedProfile = (MultiplePricesHRefHelper) duckProfile;
                driver.get(extendedProfile.getUrl());
                String regularPriceFromCard = driver.findElement(By.cssSelector("div.information div.price-wrapper s.regular-price")).getText();
                assertThat(regularPriceFromCard, is(extendedProfile.getRegularPriceValue()));
                String campaignPriceFromCard = driver.findElement(By.cssSelector("div.information div.price-wrapper strong.campaign-price")).getText();
                assertThat(campaignPriceFromCard, is(extendedProfile.getCampaignPriceValue()));
            } else {
                driver.get(duckProfile.getUrl());
                String priceLocator = "div.information div.price-wrapper span.price";
                String priceFromCard = driver.findElement(By.cssSelector(priceLocator)).getText();
                String priceFromProfile = duckProfile.getValue();
                assertThat(priceFromCard, is(priceFromProfile));
            }
        } else if (Objects.equals(locator, CHECK_TITLES_LOCATOR)) {
            driver.get(duckProfile.getUrl());
            String name = driver.findElement(By.cssSelector(locator)).getText();
            assertThat(duckProfile.getValue().equals(name), is(true));
        } else if (Objects.equals(locator, CHECK_PRICES_STYLES)) {
            driver.get(duckProfile.getUrl());
            WebElement priceElement = driver.findElement(By.cssSelector("div.information div.price-wrapper"));
            checkPricesStyle(priceElement);
        } else if (Objects.equals(locator, CHECK_PRICES_SIZES)) {
            driver.get(duckProfile.getUrl());
            WebElement priceElement = driver.findElement(By.cssSelector("div.information div.price-wrapper"));
            checkPriceTextSizes(priceElement);
        } else {
            throw new IllegalArgumentException();
        }

    }

    private List<WebElement> getCardsWithValues(String id) {
        driver.get(MAIN_URL_EN);
        if (id.equals(ALL)) {
            return driver.findElements(By.cssSelector("div.content ul.listing-wrapper.products li"));
        } else {
            return driver.findElements(By.cssSelector("div" + id + "div.content ul.listing-wrapper.products li"));
        }
    }
}
