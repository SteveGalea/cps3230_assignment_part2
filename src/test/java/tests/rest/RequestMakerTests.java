package tests.rest;

import com.task1_screenscraper.models.Product;
import com.task1_screenscraper.rest.RequestHelper;
import com.task1_screenscraper.rest.RequestMaker;
import com.task1_screenscraper.utils.MarketAlertServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class RequestMakerTests {
    RequestMaker requestMaker;
    RequestHelper requestHelper;
    MarketAlertServer marketAlertServer;

    @BeforeEach
    public void setup(){
        // Setup
        requestHelper = new RequestHelper();
        requestMaker = mock(RequestMaker.class);
        requestHelper.setRequestMaker(requestMaker);
        marketAlertServer = mock(MarketAlertServer.class);
    }

    @AfterEach
    public void teardown(){
        requestHelper = null;
    }

    @Test
    public void testDeleteRequestReturnsOK(){
        // Setup (if any)
        Mockito.when(requestMaker.makeDeleteRequest()).thenReturn(MarketAlertServer.OK);

        // Exercise
        int statusCode = requestMaker.makeDeleteRequest() ;

        // Verify
        Assertions.assertEquals(MarketAlertServer.OK,statusCode);
        verify(requestMaker, times(1)).makeDeleteRequest();

        // Teardown (if any)
    }

    @Test
    public void testPostRequestReturnsCreated(){
        // Setup (if any)
        Mockito.when(requestMaker.makePostRequest()).thenReturn(marketAlertServer.CREATED);

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
        verify(requestMaker, times(1)).makePostRequest();

        // Teardown (if any)
    }

    @Test
    public void testPostRequestReturnsBadRequestAsNoBodyIsSupplied(){
        // Setup (if any)
        doReturn(marketAlertServer.BAD_REQUEST).when(requestMaker).makePostRequest();

        // Exercise
        int response = requestMaker.makePostRequest();

        // Verify
        Assertions.assertEquals(marketAlertServer.BAD_REQUEST, response);
        verify(requestMaker, times(1)).makePostRequest();

        // Teardown (if any)
    }

    @Test
    public void testPostRequestReturnsUnsupportedMediaTypeWhenNotPassingJsonBody(){
        // Setup (if any)
        Mockito.when(requestMaker.makePostRequest()).thenReturn(marketAlertServer.UNSUPPORTED_MEDIA_TYPE);

        // Exercise
        int response = requestMaker.makePostRequest();

        // Verify
        Assertions.assertEquals(marketAlertServer.UNSUPPORTED_MEDIA_TYPE, response);
        verify(requestMaker, times(1)).makePostRequest();

        // Teardown (if any)
    }
    @Test
    public void testNoServiceAvailableForPostRequest(){
        // Setup (if any)
        requestHelper.setRequestMaker(null);

        // Exercise
        int statusCode = requestHelper.post();

        // Verify
        Assertions.assertEquals(MarketAlertServer.SERVICE_UNAVAILABLE, statusCode);

        // Teardown (if any)
    }
    @Test
    public void testSuccessfulPostRequestAfter1Try(){
        // Setup (if any)
        when(requestMaker.makePostRequest()).thenReturn(marketAlertServer.CREATED);

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
        int statusCode = requestHelper.post();

        // Verify
        Assertions.assertEquals(marketAlertServer.CREATED, statusCode);
        verify(requestMaker, times(1)).makePostRequest();

        // Teardown (if any)
    }
    @Test
    public void testSuccessfulPostRequestAfter3Tries(){
        // Setup (if any)
        when(requestMaker.makePostRequest()).thenReturn(marketAlertServer.BAD_REQUEST,marketAlertServer.BAD_REQUEST,marketAlertServer.CREATED);

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
        int statusCode = requestHelper.post();

        // Verify
        Assertions.assertEquals(marketAlertServer.CREATED, statusCode);
        verify(requestMaker, times(3)).makePostRequest();

        // Teardown (if any)
    }
    @Test
    public void testUnsuccessfulPostRequestAfter3Tries(){
        // Setup (if any)
        when(requestMaker.makePostRequest()).thenReturn(marketAlertServer.BAD_REQUEST);

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
        int statusCode = requestHelper.post();

        // Verify
        Assertions.assertEquals(marketAlertServer.BAD_REQUEST, statusCode);
        verify(requestMaker, times(3)).makePostRequest();

        // Teardown (if any)
    }

    @Test
    public void testMaximumNumberOfReAttemptingPostRequestsIsThree(){
        // Setup (if any)
        when(requestMaker.makePostRequest()).thenReturn(marketAlertServer.BAD_REQUEST,marketAlertServer.BAD_REQUEST,marketAlertServer.BAD_REQUEST, marketAlertServer.CREATED);

        // Exercise
        int statusCode = requestHelper.post();

        // Verify
        verify(requestMaker, times(3)).makePostRequest();
        Assertions.assertEquals(marketAlertServer.BAD_REQUEST, statusCode);

        // Teardown (if any)
    }

    @Test
    public void testNoServiceAvailableForDeleteRequest(){
        // Setup (if any)
        requestHelper.setRequestMaker(null);

        // Exercise
        int statusCode = requestHelper.delete();

        // Verify
        Assertions.assertEquals(marketAlertServer.SERVICE_UNAVAILABLE, statusCode);

        // Teardown (if any)
    }
    @Test
    public void testSuccessfulDeleteRequestAfter1Try(){
        // Setup (if any)
        when(requestMaker.makeDeleteRequest()).thenReturn(marketAlertServer.OK);

        // Exercise
        int statusCode = requestHelper.delete();

        // Verify
        Assertions.assertEquals(marketAlertServer.OK, statusCode);
        verify(requestMaker, times(1)).makeDeleteRequest();

        // Teardown (if any)
    }
    @Test
    public void testSuccessfulDeleteRequestAfter3Tries(){
        // Setup (if any)
        when(requestMaker.makeDeleteRequest()).thenReturn(marketAlertServer.BAD_REQUEST,marketAlertServer.BAD_REQUEST,marketAlertServer.OK);

        // Exercise
        int statusCode = requestHelper.delete();

        // Verify
        Assertions.assertEquals(marketAlertServer.OK, statusCode);
        verify(requestMaker, times(3)).makeDeleteRequest();

        // Teardown (if any)
    }
    @Test
    public void testUnsuccessfulDeleteRequestAfter3Tries(){
        // Setup (if any)
        when(requestMaker.makeDeleteRequest()).thenReturn(marketAlertServer.BAD_REQUEST,marketAlertServer.BAD_REQUEST,marketAlertServer.BAD_REQUEST);

        // Exercise
        int statusCode = requestHelper.delete();

        // Verify
        Assertions.assertEquals(marketAlertServer.BAD_REQUEST, statusCode);
        verify(requestMaker, times(3)).makeDeleteRequest();

        // Teardown (if any)
    }
    @Test
    public void testMaximumNumberOfReAttemptingDeleteRequestsIsThree(){
        // Setup (if any)
        when(requestMaker.makeDeleteRequest()).thenReturn(marketAlertServer.BAD_REQUEST,marketAlertServer.BAD_REQUEST,marketAlertServer.BAD_REQUEST, marketAlertServer.OK);

        // Exercise
        int statusCode = requestHelper.delete();

        // Verify
        Assertions.assertEquals(marketAlertServer.BAD_REQUEST, statusCode);
        verify(requestMaker, times(3)).makeDeleteRequest();

        // Teardown (if any)
    }

}
