package appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by Александр on 24.03.2017.
 */
public class NavigationHelper extends BaseHelper {
    private String MAIN_URL_EN = "http://localhost/litecart/en/";

    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void getToMainPage(){
        getTo(MAIN_URL_EN);
    }

}
