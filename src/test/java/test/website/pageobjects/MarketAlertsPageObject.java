package test.website.pageobjects;

import com.task1_screenscraper.pageobjects.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MarketAlertsPageObject extends PageObject {

    public MarketAlertsPageObject(WebDriver driver){
        super(driver);
    }
    public void goToLoginPage() {
        driver.manage().window().maximize();
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
        return driver.findElement(By.xpath("//input[@type='submit']")); //retrieving only button
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
        for (int i = 1; i <= getMyAlerts().size(); i++) {
            iconSources.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table["+i+"]/tbody/tr[1]/td/h4/img")).getAttribute("src"));
            //finding paths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return iconSources;
    }

    public List<String> getAllHeadings() {
        List<String> headings = new ArrayList<>(5);
        for (int i = 1; i <= getMyAlerts().size(); i++) {
            headings.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table["+i+"]/tbody/tr[1]/td/h4")).getText());
            //finding by xpaths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return headings;
    }

    public List<String> getAllDescriptions() {
        List<String> descriptions = new ArrayList<>(5);
        for (int i = 1; i <= getMyAlerts().size(); i++) {
            descriptions.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table["+i+"]/tbody/tr[3]/td")).getText());
            //finding by xpaths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return descriptions;
    }

    public List<String> getAllImages() {
        List<String> images = new ArrayList<>(5);
        for (int i = 1; i <= getMyAlerts().size(); i++) {
            images.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table["+i+"]/tbody/tr[2]/td/img")).getAttribute("src"));
            //finding by xpaths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return images;
    }

    public List<String> getAllPrices() {
        List<String> prices = new ArrayList<>(5);
        for (int i = 1; i <= getMyAlerts().size(); i++) {
            prices.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table["+i+"]/tbody/tr[4]/td")).getText());
            //finding by xaths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
        }
        return prices;
    }
    public List<String> getAllLinks() {
        List<String> links = new ArrayList<>(5);
        for (int i = 1; i <= getMyAlerts().size(); i++) {
            links.add(driver.findElement(By.xpath("//body/div[contains(@class,'container')]/main/table["+i+"]/tbody/tr[5]/td/a")).getAttribute("href"));
            //finding by xaths is a bit difficult due to the lack of ids and names - had to resort to xpath. Long xpath strings make it complicated to maintain
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

    public String getIconFile() {
        String fullIconURI = driver.findElement(By.xpath("/html/body/div/main/table[1]/tbody/tr[1]/td/h4/img")).getAttribute("src");
        String string_png;
        try{
            string_png = new URL(fullIconURI).getPath();
        }catch (Exception e){
            System.out.println(e.getMessage());
            string_png = ""; // initialise string
        }
        string_png = string_png.replace("/images/","");
        return string_png;
    }
}
