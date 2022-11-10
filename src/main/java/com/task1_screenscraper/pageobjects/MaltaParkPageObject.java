package com.task1_screenscraper.pageobjects;

import com.task1_screenscraper.converters.PriceConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaltaParkPageObject extends PageObject{
    // helper method price converter called to convert text into a savable price
    PriceConverter priceConverter;
    WebDriverWait wait;

    public MaltaParkPageObject(WebDriver driver, WebDriverWait wait, PriceConverter priceConverter){
        super(driver);
        this.wait = wait;
        this.priceConverter = priceConverter;
    }

    public WebElement getCloseButton() {
        By byXpathButtonDisabled = By.xpath("//*[@id=\"okbutton\" and contains(., 'Close (1)')]");
        By byXpathButtonEnabled = By.xpath("//*[@id=\"okbutton\" and contains(., 'Close')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpathButtonDisabled));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpathButtonEnabled));
        wait.until(ExpectedConditions.elementToBeClickable(byXpathButtonEnabled));
        return driver.findElement(byXpathButtonEnabled);
    }

    public WebElement getSearchBar() {
        By bySearchBarId = By.id("search");
        wait.until(ExpectedConditions.visibilityOfElementLocated(bySearchBarId));
        return driver.findElement(bySearchBarId);
    }

    public WebElement getSearchButton() {
        By bySearchButtonXpath = By.xpath("//button[contains(@class, 'btn-search')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(bySearchButtonXpath));
        return driver.findElement(bySearchButtonXpath);
    }

//    public WebElement getFirstItem() {
//        By bySearchButtonXpath = By.xpath("//div[contains(@class, 'item') and contains(@class, 'featured') and contains(@class, 'e4') and contains(@class, 'e3') and contains(@class, 'e2') and contains(@class, 'i0')]");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(bySearchButtonXpath));
//        return driver.findElement(bySearchButtonXpath);
//    }

    public String getProductHeading() {
        By byHeadingXpath = By.xpath("//h1[@class='top-title']/span");
        WebElement webElement = driver.findElement(byHeadingXpath);
        return webElement.getText();
    }

    public String getProductDescription() {
        By byHeadingXpath = By.xpath("//div[@class='readmore-wrapper']");
        WebElement webElement = driver.findElement(byHeadingXpath);
        return webElement.getText();
    }

    public String getProductUrl() {
        return driver.getCurrentUrl();
    }

    public String getProductImageUrl() {
        By byImageXpath = By.xpath("//a[@class='fancybox']");
        WebElement webElement = driver.findElement(byImageXpath);
        return webElement.getAttribute("href");
    }

    public int getProductPriceInCents() {
        By byPriceXpath = By.xpath("//h1[@class='top-price']");
        WebElement webElement = driver.findElement(byPriceXpath);
        String rawPriceText = webElement.getText();
        return priceConverter.textToCents(rawPriceText);
    }

    public int getProductAlertType() {
        int alertType;
        String categoryText = getCategoryOfItem();

        // determine and assign Alert Type
        alertType = switch (categoryText) {
            // Car
            case "Cars", "Motorcycles", "Quad Bikes", "Scooters", "Vans & Trucks", "Vehicle Parts", "Other" -> 1;

            // Boat
            case "Marine" -> 2;

            // PropertyForRent
            case "Long Lets", "Short / Holiday Lets" -> 3;

            // PropertyForSale
            case "Property For Sale" -> 4;

            // Toys
            case "Dolls & Bears", "Toys" -> 5;

            // Electronics
            case "Cameras & Photo", "Computers & Office", "Consumer Electronics", "Home Appliances", "Networking & Telecom", "PDAs", "TV", "Video Games" ->
                    6;

            // Anything else not considered above will be invalid
            default -> -1;
        };
        return alertType;
    }

    public String getCategoryOfItem() {
        By byCategoryXpath = By.xpath("//div[contains(@class,'ui') and contains(@class,'list') and contains(@class,'fixed-label') and contains(@class,'item-details')]/div[3]");
        WebElement webElement = driver.findElement(byCategoryXpath);
        return webElement.getText().split("Category:")[1];
    }

    public List<String> getFirstXItemsUrls(int x) {
        By byCommonClassXpath =  By.xpath("//div[contains(@class,'ui') and contains(@class,'items') and contains(@class,'listings')]/div[contains(@class,'item')]/*/a[@class='header']");
        List<WebElement> allElements = driver.findElements(byCommonClassXpath);
        List<String> allLinks = new ArrayList<>(x);
        allElements.forEach(element -> allLinks.add(element.getAttribute("href")));
        return allLinks.stream().limit(x).collect(Collectors.toList());
    }

}
