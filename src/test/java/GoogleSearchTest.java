import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sazed on 9/27/2015.
 */
public class GoogleSearchTest {
    public static WebDriver driver;

    @Before
    public void SetUp(){
        driver = new BrowserAbstractPage(driver).getWebDriver(BrowserAbstractPage.Browser.FIREFOX);
    }

    @Test
    //Assert that version number that has the most numbers of downloading is 1.2.19
    public void GoogleSearch(){
        GoogleSearchPage gooSearch = new GoogleSearchPage(driver);
        Assert.assertEquals(gooSearch.GoogleSearchQueries().toString(),"[1.2.19]");
    }

    @After
    public void TearDown(){
        driver.quit();
    }
}
