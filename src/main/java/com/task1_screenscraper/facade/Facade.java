package com.task1_screenscraper.facade;

import com.task1_screenscraper.converters.PriceConverter;
import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import com.task1_screenscraper.rest.RequestHelper;
import com.task1_screenscraper.rest.RequestMaker;
import com.task1_screenscraper.screenscraper.MaltaParkScreenScraper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Facade {
    WebDriver driver;
    MaltaParkScreenScraper maltaParkScreenScraper;
    WebDriverWait wait;
    PriceConverter priceConverter;
    List<Product> productList;
    RequestHelper requestHelper;
    RequestMaker requestMaker;
    MaltaParkPageObject maltaParkPageObject;
    final String eCommerceWebsiteUrl = "https://www.maltapark.com/";

    public Facade(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\steve\\OneDrive\\Desktop\\YR3\\SEM1\\CPS3230\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        priceConverter = new PriceConverter();
        productList = new ArrayList<>(5);
        requestHelper = new RequestHelper();
        requestMaker = new RequestMaker();
        maltaParkPageObject = new MaltaParkPageObject(driver, wait, priceConverter);
        maltaParkScreenScraper = new MaltaParkScreenScraper(driver, wait, priceConverter, maltaParkPageObject, productList,requestHelper, requestMaker);
    }

    //Setters to set depended upon components
    public void setWebDriver(WebDriver driver) {
        this.driver.quit(); // quit previously set webdriver
        this.driver = driver;
    }
    public void setMaltaParkScreenScraper(MaltaParkScreenScraper maltaParkScreenScraper) {
        this.maltaParkScreenScraper = maltaParkScreenScraper;
    }

    public void setPriceConverter(PriceConverter priceConverter) {
        this.priceConverter = priceConverter;
    }

    public void setWebDriverWait(WebDriverWait wait) {
        this.wait = wait;
    }
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setRequestHelper(RequestHelper requestHelper) {
        this.requestHelper = requestHelper;
    }
    public void setRequestMaker(RequestMaker requestMaker) {
        this.requestMaker = requestMaker;
    }

    public void setMaltaParkPageObject(MaltaParkPageObject maltaParkPageObject) {
        this.maltaParkPageObject = maltaParkPageObject;
    }

    // method/s
    public void scrapeAndUploadXAlertsUsingKeyword(int x, String keyword){
        maltaParkScreenScraper.goToUrl(eCommerceWebsiteUrl);
        maltaParkScreenScraper.closeMessageModal();
        maltaParkScreenScraper.searchProductByTerm(keyword);
        maltaParkScreenScraper.scrapeFirstXResults(x);
        maltaParkScreenScraper.uploadProductListToMarketAlert();
        maltaParkScreenScraper.stopScraping();
    }

}
