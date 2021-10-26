import org.testng.Assert;
import org.testng.annotations.Test;

/*
ZADANIE DOMOWE:

napisz metodę checkHomePageUrl() sprawdzającą czy znajdujemy się na dobrej stronie

driver.getCurrentUrl() - pobiera aktualny adres
 */
public class ShopTests  extends BaseTest {

    @Test
    public void checkTitle() {
        driver.get("http://sampleshop.inqa.pl/");
        Assert.assertEquals(driver.getTitle(), "Automation Sample Shop");
    }

    @Test
    public void checkHomePageUrl() {
        driver.get("http://sampleshop.inga.pl/");
        Assert.assertEquals(driver.getCurrentUrl(), "http://sampleshop.inga.pl/");
    }


}
