package tests;

import com.google.common.collect.Ordering;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.HRefHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.*;

/**
 * Created by Александр on 04.03.2017.
 */
public class CountrySortTest extends BaseTest {
    private static Set<HRefHelper> countriesWithStates = new HashSet<>();

    @Test
    public void test1() {
        loginAdmin();
        driver.get(COUNTRIES_URL);
        List<String> countryNames = new ArrayList<>();
        String rowsLocator = "table.dataTable > tbody > tr.row";
        List<WebElement> countryRows = driver.findElements(By.cssSelector(rowsLocator));
        for (WebElement row : countryRows) {
            String countryName = row.findElement(By.cssSelector("td:nth-child(5) a")).getText();
            countryNames.add(countryName);
            int numberOfZones = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(6)")).getText());
            if (numberOfZones > 0) {
                String url = row.findElement(By.cssSelector("td:nth-child(5) a")).getAttribute("href");
                HRefHelper country = new HRefHelper();
                country.setValue(countryName);
                country.setUrl(url);
                countriesWithStates.add(country);
            }
        }
        assertThat((Ordering.natural().isOrdered(countryNames)), is(true));
    }

    @Test
    public void test2() {
        for (HRefHelper country : countriesWithStates) {
            List<String> states = new ArrayList<>();
            driver.get(country.getUrl());
            log(country.getValue(), country.getUrl());
            List<WebElement> statesRows = driver.findElements(By.cssSelector("table#table-zones.dataTable > tbody > tr:not(.header)"));
            for (WebElement row : statesRows) {
                String stateName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
                states.add(stateName);
            }
            assertThat("States list of " + country.getValue() + " is not ordered", (Ordering.natural().isOrdered(states)), is(true));
        }
    }

    @Test
    public void test3() {
        List<String> zoneNames = new ArrayList<>();
        driver.get(GEOZONES_URL);
        countriesWithStates = new HashSet<>();
        List<WebElement> rows = driver.findElements(By.cssSelector("table.dataTable tbody tr.row"));
        for (WebElement row : rows) {
            HRefHelper country = new HRefHelper();
            String locator = "td:nth-child(3) a";
            country.setValue(row.findElement(By.cssSelector(locator)).getText());
            country.setUrl(row.findElement(By.cssSelector(locator)).getAttribute("href"));
            countriesWithStates.add(country);
            log(country.toString());
        }
        for (HRefHelper country : countriesWithStates) {
            driver.get(country.getUrl());

            List<WebElement> geoZoneRows = driver.findElements(By.cssSelector("table#table-zones.dataTable > tbody > tr:not(.header)"));
            for (WebElement row : geoZoneRows) {
                try{
                    String zoneName = row.findElement(By.cssSelector("td:nth-child(3) > select > option[selected=selected]")).getText();
                    zoneNames.add(zoneName);
                }catch (org.openqa.selenium.NoSuchElementException ex){
                    assertThat(row.findElement(By.cssSelector("td a#add_zone")).isDisplayed(), is(true));
                }
            }
            assertThat("Zones of "+country.getValue()+" are not sorted", Ordering.natural().isOrdered(zoneNames), is(true));
        }

    }
}
