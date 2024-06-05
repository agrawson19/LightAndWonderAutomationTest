package Ui.testcases;

import Ui.driver.BrowserDriver;
import Ui.driver.DriverManager;
import Ui.pages.LoginPage;
import Ui.pages.ProductPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class stepDefination {

    public LoginPage page ;
    WebDriver driver;
    @Before
    public  void beforeTestMethod(){
        BrowserDriver uiDriver = new BrowserDriver("chrome");
        driver= uiDriver.createDriver("chrome");
        DriverManager.setDriver(driver);
    }
    @After
    public void tearDown(){
        DriverManager.getDriver().quit();
    }
   @Given("^login to amazon$")
   public void loginToAmazon() throws Throwable {
       new LoginPage() .loginToApplication().verifyLoginSucessfully();
   }

   @And("^Search field type \\\"(.*)\\\"$")
    public void searchItemOnPage(String  fieldName) throws Throwable {
       new LoginPage().searchItem( fieldName ).verifyListOfMonitorDisplayed();

}
    @And("Select the first item in the list")
    public void selectTheFirstItemInTheList() {
        new LoginPage().selectItemInList().verifyProductPageLoadedSucessfully();
    }

    @When("Add the item to cart")
    public void addTheItemToCart() {
            new ProductPage().addItemToCard().verifItemAddedToCart();
    }

    @And("Open Cart from the top panel")
    public void openCartFromTheTopPanel() {

    }

    @Then("Verify that the price is identical to the product page")
    public void verifyThatThePriceIsIdenticalToTheProductPage() {

    }

    @And("Verify that the sub total is identical to the product page")
    public void verifyThatTheSubTotalIsIdenticalToTheProductPage() {
    }
}

