package appmanager;

import org.openqa.selenium.WebDriver;
import tests.BaseTest;

/**
 * Created by Александр on 23.03.2017.
 */
public class AdminNavigationHelper extends BaseHelper{
    protected String ADMIN_URL = "http://localhost/litecart/admin/";
    protected String COUNTRIES_URL = "http://localhost/litecart/admin/?app=countries&doc=countries";
    protected String GEOZONES_URL = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";
    protected String CATALOG_URL = "http://localhost/litecart/admin/?app=catalog&doc=catalog";

    public AdminNavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void getToAdminCatalog(){
        getTo(CATALOG_URL);
    }
}
