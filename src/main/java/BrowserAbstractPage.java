import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sazed on 9/22/2015.
 */
public class BrowserAbstractPage {

    protected WebDriver driver;


    public BrowserAbstractPage(WebDriver driver){

        this.driver = driver;
    }

    public static enum Browser {
        FIREFOX,
        CHROME,
        IE,
    };

    //Get WebDriver for Firefox/Chrome/IE
    public WebDriver getWebDriver(Browser b){

        switch (b) {

            case FIREFOX: driver = new FirefoxDriver();
                driver.manage().window().maximize(); // Maximize window
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                break;

            case CHROME: driver = new ChromeDriver();
                driver.manage().window().maximize(); // Maximize window
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                break;

            case IE: driver = new InternetExplorerDriver();
                driver.manage().window().maximize(); // Maximize window
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                break;

        }

        return driver;
    }
}
