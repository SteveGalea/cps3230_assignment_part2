package test.website;

import com.task1_screenscraper.facade.Facade;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.website.pageobjects.MarketAlertsPageObject;

import java.util.List;

public class WebsiteSteps {

    protected MarketAlertsPageObject marketAlertsPageObject;
    protected Facade screenScraperFacade;
    protected WebDriver driver;

    // private methods
    private void assertThatAllElementsOfLoginPageLoaded(){
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/Login", marketAlertsPageObject.getCurrentUrl());
        Assertions.assertEquals("- MarketAlertUM", marketAlertsPageObject.getTitle()); // This alone is not sufficient// Note that this the website is hard to test this way, as no title is provided for the login screen.
        Assertions.assertEquals("Log In", marketAlertsPageObject.getLogInAnchorText()); // Let's assert that there is a tab with text "Log In" in side it
        Assertions.assertEquals("User ID:", marketAlertsPageObject.getUserIDText()); // Let's assert that there is a tab with text "Log In" in side it
        Assertions.assertNotNull(marketAlertsPageObject.getUserIdInputFieldElement()); // Let's assert that a form with a User ID input box is present
        Assertions.assertNotNull(marketAlertsPageObject.getSubmitButton());
    }

    private void assertThatMyAlertsPageLoaded(){
        Assertions.assertEquals("https://www.marketalertum.com/Alerts/List", marketAlertsPageObject.getCurrentUrl());
        Assertions.assertEquals("Latest alerts for Steve Galea", marketAlertsPageObject.getLatestAlertsForUserText());
    }

    private void logIn(){
        String userValidUserId = "baf95487-17f6-40df-b758-3c938a0ec72a";
        marketAlertsPageObject.inputCredentials(userValidUserId);
        marketAlertsPageObject.getSubmitButton().submit();
        assertThatMyAlertsPageLoaded();
    }

    private void uploadXAlerts(int x) {
        uploadXAlerts(x, "Laptop");
    }

    private void uploadXAlerts(int x, String productTerm) {
        screenScraperFacade = new Facade();
        // reset alerts
        screenScraperFacade.clearAlerts();
        // assume x alerts being uploaded are Laptops
        screenScraperFacade.scrapeAndUploadXAlertsUsingKeyword(x, productTerm);
    }
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\steve\\OneDrive\\Desktop\\YR3\\SEM1\\CPS3230\\webtesting\\chromedriver.exe");
        driver = new ChromeDriver();
        marketAlertsPageObject = new MarketAlertsPageObject(driver);
    }
    @After
    public void teardown(){
        marketAlertsPageObject.logOut();
        driver.quit();
    }

    // public methods
    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        marketAlertsPageObject.goToLoginPage();
        assertThatAllElementsOfLoginPageLoaded();
    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        logIn();
    }
    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        assertThatMyAlertsPageLoaded();
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        // John Doe UUID - invalid for me
        String userInvalidUserId = "7ca5f131-0ff0-42cd-85e8-cae25a4ee41f";
        marketAlertsPageObject.inputCredentials(userInvalidUserId);
        marketAlertsPageObject.getSubmitButton().submit();
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        assertThatAllElementsOfLoginPageLoaded();
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int x) {
        uploadXAlerts(x);
    }
    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        logIn();
        int totalAlerts = marketAlertsPageObject.getMyAlerts().size();
        assertThatMyAlertsPageLoaded();
        Assertions.assertTrue( 1 <= totalAlerts && totalAlerts <= 5);
    }
    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        int totalAlerts = marketAlertsPageObject.getMyAlerts().size();
        List<String> allIconSources = marketAlertsPageObject.getAllIconSources();

        // icon
        Assertions.assertEquals(totalAlerts, marketAlertsPageObject.getAllIconSources().size());
        for (String element:
                allIconSources) {
            Assertions.assertNotNull(element);
        }
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        int totalAlerts = marketAlertsPageObject.getMyAlerts().size();
        List<String> allHeadings = marketAlertsPageObject.getAllHeadings();
        // heading
        Assertions.assertEquals(totalAlerts, marketAlertsPageObject.getAllHeadings().size());
        for (String element:
                allHeadings) {
            Assertions.assertNotNull(element);
        }
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        int totalAlerts = marketAlertsPageObject.getMyAlerts().size();
        List<String> allDescriptions = marketAlertsPageObject.getAllDescriptions();
        // description
        Assertions.assertEquals(totalAlerts, marketAlertsPageObject.getAllHeadings().size());
        for (String element:
                allDescriptions) {
            Assertions.assertNotNull(element);
        }
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        int totalAlerts = marketAlertsPageObject.getMyAlerts().size();
        List<String> allImageSources = marketAlertsPageObject.getAllImages();
        // image
        Assertions.assertEquals(totalAlerts, marketAlertsPageObject.getAllIconSources().size());
        for (String element:
                allImageSources) {
            Assertions.assertNotNull(element);
        }
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        int totalAlerts = marketAlertsPageObject.getMyAlerts().size();
        List<String> allPrices = marketAlertsPageObject.getAllPrices();
        // price
        Assertions.assertEquals(totalAlerts, marketAlertsPageObject.getAllPrices().size());
        for (String element:
                allPrices) {
            Assertions.assertNotNull(element);
        }
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        int totalAlerts = marketAlertsPageObject.getMyAlerts().size();
        List<String> allLinks = marketAlertsPageObject.getAllLinks();
        // link
        Assertions.assertEquals(totalAlerts, marketAlertsPageObject.getAllLinks().size());
        for (String element:
                allLinks) {
            Assertions.assertNotNull(element);
        }
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int x) {
        uploadXAlerts(x+1);
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int amountExpected) {
        Assertions.assertEquals(amountExpected, marketAlertsPageObject.getMyAlerts().size());
    }
    @Given("I am an administrator of the website and I upload an alert of type 1")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType1() {
        uploadXAlerts(1, "Cars");
    }

    @Given("I am an administrator of the website and I upload an alert of type 2")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType2() {
        uploadXAlerts(1, "Marine");
    }

    @Given("I am an administrator of the website and I upload an alert of type 3")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType3() {
        uploadXAlerts(1, "Long Lets");
    }

    @Given("I am an administrator of the website and I upload an alert of type 4")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType4() {
        uploadXAlerts(1, "Property For Sale");
    }

    @Given("I am an administrator of the website and I upload an alert of type 5")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType5() {
        uploadXAlerts(1, "Toys");
    }

    @Given("I am an administrator of the website and I upload an alert of type 6")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType6() {
        uploadXAlerts(1, "Computers & Office");
    }

    @Given("I am an administrator of the website and I upload an alert of type error")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfTypeError() {
        uploadXAlerts(1, "Food & Wine"); // Note that Food & Wine should return an alertType of -1
    }
    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBe(String icon_File) {
        Assertions.assertEquals(icon_File, marketAlertsPageObject.getIconFile());
    }

}
