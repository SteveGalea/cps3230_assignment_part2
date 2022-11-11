package test.website.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.website.pageobjects.MarketAlertsPageObject;

public class MarketAlertTests {
    WebDriver driver;
    MarketAlertsPageObject marketAlertsPageObject;
    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\steve\\OneDrive\\Desktop\\YR3\\SEM1\\CPS3230\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        marketAlertsPageObject = new MarketAlertsPageObject(driver);
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }

    @Test
    public void testGoToLoginPage(){
        // Setup (if any)
        // Exercise
        marketAlertsPageObject.goToLoginPage();

        // Verify that all the elements of login page loaded
        Assertions.assertEquals("- MarketAlertUM", driver.getTitle()); // This alone is not sufficient// Note that this the website is hard to test this way, as no title is provided for the login screen.
        Assertions.assertEquals("Log In", marketAlertsPageObject.getLogInAnchorText()); // Let's assert that there is a tab with text "Log In" in side it
        Assertions.assertEquals("User ID:", marketAlertsPageObject.getUserIDText()); // Let's assert that there is a tab with text "Log In" in side it
        Assertions.assertNotNull(marketAlertsPageObject.getUserIdInputFieldElement()); // Let's assert that a form with a User ID input box is present
        Assertions.assertNotNull(marketAlertsPageObject.getSubmitButton());

        // Teardown (if any)
    }

    @Test
    public void testInputValidCredentials(){
        // Setup (if any)
        String validUserId = "baf95487-17f6-40df-b758-3c938a0ec72a";
        marketAlertsPageObject.goToLoginPage();

        // Exercise
        marketAlertsPageObject.inputCredentials(validUserId);
        marketAlertsPageObject.getSubmitButton().submit();

        // Verify that all the elements of login page loaded
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/List", driver.getCurrentUrl());
        Assertions.assertEquals("Latest alerts for Steve Galea", marketAlertsPageObject.getLatestAlertsForUserText());

        // Teardown (if any)
    }

    @Test
    public void testInputVXalidCredentials(){
        // Setup (if any)
        String validUserId = "baf95487-17f6-40df-b758-3c938a0ec72a";
        marketAlertsPageObject.goToLoginPage();
        marketAlertsPageObject.inputCredentials(validUserId);
        marketAlertsPageObject.getSubmitButton().submit();

        // Exercise


        // Verify that all the elements of login page loaded
//        Assertions.assertEquals(, driver.getCurrentUrl());

        // Teardown (if any)
    }
}
