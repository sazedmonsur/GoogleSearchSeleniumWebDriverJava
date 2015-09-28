import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

/**
 * Created by Sazed on 9/22/2015.
 */

public class GoogleSearchPage extends BrowserAbstractPage {

    public GoogleSearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //Get the Mantis version number(s) that has the most numbers of downloading
    public Collection<String> GoogleSearchQueries() {

        List<WebElement> linkElements = new ArrayList<WebElement>();
        ListIterator<WebElement> itr = null;
        driver.get("http://google.com");
        WebElement gelement = driver.findElement(By.name("q"));
        WebElement toClick = null;
        gelement.sendKeys("mantis");
        gelement.submit();
        int pageNumber = 1;
        WebDriverWait wait = new WebDriverWait(driver, 15);
        boolean flag = false;
        Collection<String> finalOutput = null;

        while (!flag) {
            linkElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h3[@class='r']/a")));
            itr = linkElements.listIterator(); // re-initializing iterator
            while (itr.hasNext()) {
                toClick = itr.next();
                if (toClick.getText().equals("MantisBT | SourceForge.net")) {
                    toClick.click();
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("files")));
                    driver.findElement(By.className("files")).click();
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("name")));
                    driver.findElement(By.linkText("mantis-stable")).click();
                    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("label")));
                    flag = true;

                    //Create a instance of Mantis Page object
                    MantisPageObject mpo = new MantisPageObject(driver);
                    int maximumDownload = mpo.maximumDownloads();
                    List<String> version = mpo.versionInfo();
                    List<Integer> download = mpo.downloads();

                    //A collection that maps keys to values, each key may be associated with multiple values.
                    Multimap<Integer, String> map = ArrayListMultimap.create();

                    //Insert version number and corresponding downloads into Multimap
                    for (int i = 0; i < download.size(); i++) {
                        map.put(download.get(i), version.get(i));
                    }
                    finalOutput = map.get(mpo.maximumDownloads());
                    System.out.println("versions and download statistics log:\n"+ map);
                    System.out.println("version number that has the most numbers of downloading:\n"+ finalOutput);
                    break;
                }
            }
            if (!flag) {
                driver.findElement(By.xpath("//a[@id='pnnext']/span[1]")).click();
                pageNumber++;
                linkElements.clear(); // clean list
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//td[@class='cur']"), pageNumber + ""));
            }
        }
        return finalOutput;
    }




}