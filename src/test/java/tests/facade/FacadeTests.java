package tests.facade;

import com.task1_screenscraper.converters.PriceConverter;
import com.task1_screenscraper.facade.Facade;
import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import com.task1_screenscraper.rest.RequestHelper;
import com.task1_screenscraper.rest.RequestMaker;
import com.task1_screenscraper.screenscraper.MaltaParkScreenScraper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FacadeTests {
    Facade facade;
    WebDriver driver;
    MaltaParkScreenScraper maltaParkScreenScraper;
    WebDriverWait wait;
    PriceConverter priceConverter;
    List<Product> productList;
    RequestHelper requestHelper;
    RequestMaker requestMaker;
    MaltaParkPageObject maltaParkPageObject;

    @BeforeEach
    public void setup(){
        //initiate SUT
        facade = new Facade();

        //mocking DOCs
        driver = mock(WebDriver.class);
        wait = mock(WebDriverWait.class);
        priceConverter = mock(PriceConverter.class);
        productList = mock(List.class);
        requestHelper = mock(RequestHelper.class);
        requestMaker = mock(RequestMaker.class);
        maltaParkPageObject = mock(MaltaParkPageObject.class);
        maltaParkScreenScraper = mock(MaltaParkScreenScraper.class);

        //inject mocks of DOCs
        facade.setWebDriver(driver);
        facade.setWebDriverWait(wait);
        facade.setPriceConverter(priceConverter);
        facade.setProductList(productList);
        facade.setRequestHelper(requestHelper);
        facade.setRequestMaker(requestMaker);
        facade.setMaltaParkPageObject(maltaParkPageObject);
        facade.setMaltaParkScreenScraper(maltaParkScreenScraper);
    }

    @AfterEach
    public void teardown(){
        facade = null;
    }

    @Test
    public void testScrapeAndUploadAny5LaptopSearchResults(){
        // Setup
        doNothing().when(maltaParkScreenScraper).goToUrl("https://www.maltapark.com/");
        doNothing().when(maltaParkScreenScraper).closeMessageModal();
        doNothing().when(maltaParkScreenScraper).searchProductByTerm(anyString());
        doNothing().when(maltaParkScreenScraper).scrapeFirstXResults(5);
        doNothing().when(maltaParkScreenScraper).uploadProductListToMarketAlert();
        doNothing().when(maltaParkScreenScraper).stopScraping();
        when(productList.size()).thenReturn(5);

        // Exercise
        facade.scrapeAndUploadXAlertsUsingKeyword(5, "Laptop");

        // Verify
        //get num of calls of each method
        verify(maltaParkScreenScraper, times(1)).goToUrl(anyString());
        verify(maltaParkScreenScraper, times(1)).closeMessageModal();
        verify(maltaParkScreenScraper, times(1)).searchProductByTerm(anyString());
        verify(maltaParkScreenScraper, times(1)).scrapeFirstXResults(5);
        verify(maltaParkScreenScraper, times(1)).uploadProductListToMarketAlert();
        verify(maltaParkScreenScraper, times(1)).stopScraping();
        //other assertions
        Assertions.assertEquals(5, productList.size());

        // Teardown (if any)
    }
    @Test
    public void testClearAllAlerts(){
        // Setup
        doReturn(200).when(requestHelper).delete();

        // Exercise
        facade.clearAlerts();

        // Verify
        //get num of calls of each method
        verify(requestHelper, times(1)).delete();

        // Teardown (if any)
    }
}
