import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sazed on 9/22/2015.
 */
public class MantisPageObject extends BrowserAbstractPage {

    public MantisPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    //Get all the version numbers and insert into a list
    public List<String> versionInfo() {
        List<String> versionList = new ArrayList<String>();
        List<WebElement> ElementVersionList = driver.findElements(By.xpath("//*[@id=\"files_list\"]/tbody/tr[*]/th/a"));

        for (WebElement elem : ElementVersionList) {
            versionList.add(elem.getText());
        }
        return versionList;
    }
    //Get number of downloads and insert into a list
    public List<Integer> downloads() {
        List<String> DownloadList = new ArrayList<String>();
        List<WebElement> ElementdownloadsList = driver.findElements(By.xpath("//*[@id=\"files_list\"]/tbody/tr[*]/td[3]"));

        for (WebElement elem : ElementdownloadsList) {
            DownloadList.add(elem.getText().replaceAll(",", ""));

        }

        List<Integer> newDownloadList = new ArrayList<Integer>(DownloadList.size());

        for (String myInt : DownloadList) {
            newDownloadList.add(Integer.valueOf(myInt));
        }
        return newDownloadList;


    }
    //Find the maximum downloads
    public int maximumDownloads(){
        int max;
        max = Collections.max(downloads());
        return max;
    }


}
