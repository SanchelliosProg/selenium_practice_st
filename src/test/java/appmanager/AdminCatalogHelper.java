package appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Александр on 23.03.2017.
 */
public class AdminCatalogHelper extends BaseHelper {
    private final String RUBBER_DUCKS_CATALOG_URL = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";

    private final String RUBBER_DUCKS_FOLDER_CSS ="a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1']";
    private final String SUBCATEGORY_FOLDER_CSS = "a[href='http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=2']";

    public AdminCatalogHelper(WebDriver driver) {
        super(driver);
    }

    public void openRubberDucksCatalog(){
        getTo(RUBBER_DUCKS_CATALOG_URL);
    }

    public List<WebElement> getDuckRows(){
        return findAll("tr.row td:nth-child(3) img ~a");
    }
}
