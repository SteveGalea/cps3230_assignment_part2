package com.task1_screenscraper.screenscraper;

import com.task1_screenscraper.converters.PriceConverter;
import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import com.task1_screenscraper.rest.RequestHelper;
import com.task1_screenscraper.rest.RequestMaker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MaltaParkScreenScraper {
    WebDriver driver;
    WebDriverWait wait;
    PriceConverter priceConverter;
    MaltaParkPageObject maltaParkPageObject;
    List<Product> productList;
    RequestHelper requestHelper;
    RequestMaker requestMaker;

    public MaltaParkScreenScraper(WebDriver driver, WebDriverWait wait, PriceConverter priceConverter, MaltaParkPageObject maltaParkPageObject, List<Product> productList, RequestHelper requestHelper, RequestMaker requestMaker) {
        this.driver = driver;
        this.wait = wait; //?
        this.maltaParkPageObject = maltaParkPageObject;
        this.productList = productList;
        this.priceConverter = priceConverter;// ?
        this.requestHelper = requestHelper;
        this.requestMaker = requestMaker;
    }

    public void goToUrl(String websiteUrl) {
        driver.manage().window().maximize();
        driver.get(websiteUrl);
    }

    public void closeMessageModal(){
        WebElement closeButton = maltaParkPageObject.getCloseButton();
        closeButton.click();
    }

    public void searchProductByTerm(String term){
        WebElement searchBar = maltaParkPageObject.getSearchBar();
        WebElement searchButton = maltaParkPageObject.getSearchButton();
        searchBar.sendKeys(term);
        searchButton.submit();
    }
    public void stopScraping() {
        driver.quit();
    }

    public void scrapeFirstXResults(int x) {
        List<String> firstXElementsLinks = maltaParkPageObject.getFirstXItemsUrls(x);

        for (String productLink:
                firstXElementsLinks) {
            goToUrl(productLink);

            Product product = new Product();
            //get data
            int alertType = maltaParkPageObject.getProductAlertType();
            String heading = maltaParkPageObject.getProductHeading();
            String description = maltaParkPageObject.getProductDescription();
            String url = maltaParkPageObject.getProductUrl();
            String imageUrl = maltaParkPageObject.getProductImageUrl();
            int priceInCents = maltaParkPageObject.getProductPriceInCents();

            // setter injection
            product.setAlertType(alertType);
            product.setHeading(heading);
            product.setDescription(description);
            product.setUrl(url);
            product.setImageUrl(imageUrl);
            product.setPriceInCents(priceInCents);

            productList.add(product);
        }
    }

    public void uploadProductListToMarketAlert(){
        for (Product product:
             productList) {
            requestMaker.setJSONObject(product);
            requestHelper.setRequestMaker(requestMaker);
            requestHelper.post();
        }
    }

    public void setProductList(List<Product> productList){
        this.productList = productList;
    }

}
