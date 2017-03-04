import com.google.common.collect.Ordering;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import java.text.Collator;
import java.util.*;

/**
 * Created by Александр on 04.03.2017.
 */
public class CountrySortTest extends BaseTest {
    @Before
    public void getIn(){
        loginAdmin();
        driver.get(COUNTRIES_URL);
    }

    @Test
    public void alphabetOrder() {
        List<String> countryNames = new ArrayList<>();
        String rowsLocator = "table.dataTable > tbody > tr.row";
        List<WebElement> rows = driver.findElements(By.cssSelector(rowsLocator));
        for(WebElement row : rows){
            String countryName = row.findElement(By.cssSelector("td:nth-child(5) a")).getText();
            countryNames.add(countryName);
            int numberOfZones = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(6)")).getText());
            if (numberOfZones > 0){
                //TODO: get href
                String url = row.findElement(By.cssSelector("td:nth-child(5) a")).getAttribute("href");
                log(countryName);
                log(url);
                //TODO: store urls and visit them in other test
                //TODO: set order of tests

            }
        }
        assertThat((Ordering.natural().isOrdered(countryNames)), is(true));
    }
}
