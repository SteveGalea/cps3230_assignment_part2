package tests.website;

import com.task1_screenscraper.converters.PriceConverter;
import com.task1_screenscraper.pageobjects.MaltaParkPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MaltaParkTests {
    WebDriver driver;
    WebDriverWait wait;
    WebElement mockWebElement;
    PriceConverter priceConverter;
    MaltaParkPageObject maltaParkPageObject;

    @BeforeEach
    public void setup(){
        //Setup before each test case
        driver = mock(ChromeDriver.class);
        wait = mock(WebDriverWait.class);
        priceConverter = mock(PriceConverter.class);
        maltaParkPageObject = new MaltaParkPageObject(driver, wait, priceConverter);
        mockWebElement = mock(WebElement.class);

        Mockito.when(wait.until(any())).thenReturn(true);
        Mockito.when(driver.findElement(Mockito.any())).thenReturn(mockWebElement);

    }

    @AfterEach
    public void teardown(){
        //Teardown before each test case
        maltaParkPageObject = null;
    }

    @Test
    public void testGetCloseButton(){
        //Setup (if any)
        Mockito.when(mockWebElement.getText()).thenReturn(Mockito.anyString());

        //Exercise
        WebElement closeButtonElement = maltaParkPageObject.getCloseButton();

        //Verify
        Assertions.assertNotNull(closeButtonElement);

        //Teardown (if any)
    }

    @Test
    public void testGetSearchBar(){
        //Setup (if any)
        Mockito.when(mockWebElement.getText()).thenReturn(Mockito.anyString());

        //Exercise
        WebElement searchBarElement = maltaParkPageObject.getSearchBar();

        //Verify
        Assertions.assertNotNull(searchBarElement);

        //Teardown (if any)
    }

    @Test
    public void testSearchButton(){
        //Setup (if any)
        Mockito.when(mockWebElement.getText()).thenReturn(Mockito.anyString());

        //Exercise
        WebElement searchButtonElement = maltaParkPageObject.getSearchButton();

        //Verify
        Assertions.assertNotNull(searchButtonElement);

        //Teardown (if any)
    }

    @Test
    public void testGetProductHeading(){
        //Setup (if any)
        Mockito.when(mockWebElement.getText()).thenReturn(Mockito.anyString());

        //Exercise
        String productHeadingString = maltaParkPageObject.getProductHeading();

        //Verify
        Assertions.assertNotNull(productHeadingString);

        //Teardown (if any)
    }
    @Test
    public void testGetProductDescription(){
        //Setup (if any)
        Mockito.when(mockWebElement.getText()).thenReturn(Mockito.anyString());

        //Exercise
        String productDescriptionString = maltaParkPageObject.getProductDescription();

        //Verify
        Assertions.assertNotNull(productDescriptionString);

        //Teardown (if any)
    }

    @Test
    public void testGetProductUrl(){
        //Setup (if any)

        //Exercise
        maltaParkPageObject.getProductUrl();

        //Verify
        Mockito.verify(driver, Mockito.times(1)).getCurrentUrl();

        //Teardown (if any)
    }


    @Test
    public void testGetProductImageUrl(){
        //Setup (if any)
        Mockito.when(mockWebElement.getAttribute(anyString())).thenReturn(Mockito.anyString());

        //Exercise
        String productImageUrlString = maltaParkPageObject.getProductImageUrl();

        //Verify
        Assertions.assertNotNull(productImageUrlString);

        //Teardown (if any)
    }
    @Test
    public void testGetProductPriceInCents(){
        //Setup (if any)
        //dummy data passed, proper testing of conversion in price converter component
        int cents = 1234;
        doReturn(String.valueOf(cents)).when(mockWebElement).getText();
        doReturn(cents).when(priceConverter).textToCents(anyString());

        //Exercise
        int productPriceInCents = maltaParkPageObject.getProductPriceInCents();

        //Verify
        Assertions.assertEquals(cents, productPriceInCents);

        //Teardown (if any)
    }

    @Test
    public void testGetCategoryOfItem(){
        //Setup
        //Dummy data passed
        Mockito.when(mockWebElement.getText()).thenReturn("Category:RandomCategory");

        //Exercise
        String category = maltaParkPageObject.getCategoryOfItem();

        //Verify
        Assertions.assertEquals("RandomCategory",category);

        //Teardown (if any)
    }

    @Test
    public void testTextMapsToCorrectAlertType(){
        //Setup
        Mockito.when(mockWebElement.getText()).thenReturn(
                "Category:Cars","Category:Other",
                "Category:Marine",
                "Category:Long Lets","Category:Short / Holiday Lets",
                "Category:Property For Sale",
                "Category:Dolls & Bears", "Category:Toys",
                "Category:Cameras & Photo", "Category:Video Games",
                "Category:Unlisted Category");
        // 11 alerts are expected
        int[] expectedOutcomes = new int[]{
          1,1,2,3,3,4,5,5,6,6,-1
        };
        int[] actualOutcome = new int[11];

        //Exercise
        //iterate multiple times to check if the correct alert type was assigned at the partition ends
        for(int i = 0; i < 11; i++){
            actualOutcome[i] = maltaParkPageObject.getProductAlertType();
        }

        //Verify
        for(int i = 0; i < 11; i++){
            Assertions.assertEquals(expectedOutcomes[i],actualOutcome[i]);
        }

        //Teardown
    }

    @Test
    public void testGetFirst5ItemUrls(){
        //Setup
        List<WebElement> mockListElements = mock(List.class);

        Mockito.when(driver.findElements(any())).thenReturn(mockListElements);
        Mockito.when(mockWebElement.getAttribute(anyString())).thenReturn(Mockito.anyString());

        //Exercise
        List<String> elements = maltaParkPageObject.getFirstXItemsUrls(5);

        //Verify
        Assertions.assertNotNull(elements);

        //Teardown (if any)
    }

}
