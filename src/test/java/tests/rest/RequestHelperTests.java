package tests.rest;

import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.rest.RequestHelper;
import com.task1_screenscraper.rest.RequestMaker;
import com.task1_screenscraper.utils.MarketAlertServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class RequestHelperTests {
    RequestHelper requestHelper;
    RequestMaker requestMaker;
    MarketAlertServer marketAlertServer;

    @BeforeEach
    public void setup(){
        requestHelper = mock(RequestHelper.class);
        requestMaker = new RequestMaker();
        marketAlertServer = mock(MarketAlertServer.class);
    }

    @AfterEach
    public void teardown(){
        requestHelper = null;
    }

    @Test
    public void testMakeDeleteRequest(){
        // Setup (if any);

        // Exercise
        int statusCode = requestMaker.makeDeleteRequest() ;

        // Verify
        Assertions.assertEquals(marketAlertServer.OK,statusCode);

        // Teardown (if any)
    }

    @Test
    public void testPostRequestWithNoJsonBodyReturnsBadRequest(){
        // Setup

        // Exercise
        int statusCode = requestMaker.makePostRequest();

        // Verify
        Assertions.assertEquals(marketAlertServer.BAD_REQUEST, statusCode);
    }

    @Test
    public void testPostRequestReturnsCreated(){
        // Setup (if any)

        // create dummy object
        int alertType = 6;
        String heading = "Jumper Windows 11 Laptop";
        String description = "Jumper Windows 11 Laptop 1080P Display,12GB RAM 256GB SSD";
        String url = "https://www.amazon.co.uk/Windows-Display-Ultrabook-Processor-Bluetooth";
        String imageUrl = "https://m.media-amazon.com/images/I/712Xf2LtbJL._AC_SX679_.jpg";
        int priceInCents = 24999;
        Product product = new Product(alertType, heading, description, url, imageUrl, priceInCents);

        // setter injection
        requestMaker.setJSONObject(product);

        // Exercise
        int statusCode = requestMaker.makePostRequest();

        // Verify
        Assertions.assertEquals(marketAlertServer.CREATED, statusCode);

        // Teardown (if any)
        requestMaker.makeDeleteRequest();
    }
}
