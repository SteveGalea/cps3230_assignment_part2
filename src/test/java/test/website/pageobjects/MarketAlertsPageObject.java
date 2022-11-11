package test.website.pageobjects;

import com.task1_screenscraper.pageobjects.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MarketAlertsPageObject extends PageObject {

    public MarketAlertsPageObject(WebDriver driver){
        super(driver);
    }
    public void goToLoginPage() {
        driver.get("https://www.marketalertum.com/Alerts/Login");
    }

    public String getLogInAnchorText() {
        return driver.findElement(By.xpath("//a[contains(@class,'nav-link') and contains(@class,'text-dark') and contains(.,'Log In')]")).getText();
    }

    public WebElement getUserIdInputFieldElement() {
        return driver.findElement(By.id("UserId"));
    }

    public String getUserIDText() {
        return driver.findElement(By.xpath("//form/b[contains(.,'User ID')]")).getText();
    }

    public WebElement getSubmitButton() {
        return driver.findElement(By.xpath("//input[@type='submit']")); // should there be more buttons- cannot account for them...
    }

    public void inputCredentials(String userValidDetails) {
        getUserIdInputFieldElement().sendKeys(userValidDetails);
    }

    public List<WebElement> getMyAlerts() {
        return driver.findElements(By.xpath("/html/body/div/main/table"));
    }

    public String getLatestAlertsForUserText() {
        return driver.findElement(By.xpath("//main/h1[contains(.,'Latest alerts for Steve Galea')]")).getText();
    }

    public List<String> getAllIconSources() {
        List<String> iconSources = new ArrayList<>(5);
        for (WebElement ignored:
             getMyAlerts()) {
            iconSources.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table[1]/tbody/tr[1]/td/h4/img")).getAttribute("src"));
            //finding paths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return iconSources;
    }

    public List<String> getAllHeadings() {
        List<String> headings = new ArrayList<>(5);
        for (WebElement ignored:
                getMyAlerts()) {
            headings.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table[1]/tbody/tr[1]/td/h4")).getText());
            //finding paths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return headings;
    }

    public List<String> getAllDescriptions() {
        List<String> descriptions = new ArrayList<>(5);
        for (WebElement ignored:
                getMyAlerts()) {
            descriptions.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table[1]/tbody/tr[3]/td")).getText());
            //finding paths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return descriptions;
    }

    public List<String> getAllImages() {
        List<String> images = new ArrayList<>(5);
        for (WebElement ignored:
                getMyAlerts()) {
            images.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table[1]/tbody/tr[2]/td/img")).getAttribute("src"));
            //finding paths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return images;
    }

    public List<String> getAllPrices() {
        List<String> prices = new ArrayList<>(5);
        for (WebElement ignored:
                getMyAlerts()) {
            prices.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table[1]/tbody/tr[4]/td")).getText());
            //finding paths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return prices;
    }
    public List<String> getAllLinks() {
        List<String> links = new ArrayList<>(5);
        for (WebElement ignored:
                getMyAlerts()) {
            links.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table[1]/tbody/tr[5]/td/a")).getAttribute("href"));
            //finding paths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return links;
    }

    public void logOut() {
        driver.get("https://www.marketalertum.com/Home/Logout");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getIconPng() {
        String fullIconURI = driver.findElement(By.xpath("/html/body/div/main/table[1]/tbody/tr[1]/td/h4/img")).getAttribute("src");
//        Path path = Paths.get(fullIconURI);
//        return path.getFileName().toString();
        String string_png;
        try{
            string_png = new URL(fullIconURI).getPath();
        }catch (Exception e){
            System.out.println(e.getMessage());
            string_png = "";//?
        }
        string_png = string_png.replace("/images/","");
        return string_png;
    }
}
