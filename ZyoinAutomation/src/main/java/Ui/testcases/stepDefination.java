package Ui.testcases;

import Ui.commonComponents.Property;
import Ui.driver.BrowserDriver;
import Ui.driver.DriverManager;
import Ui.pages.LoginPage;
import Ui.pages.ProductPage;
import Ui.pages.ShoppingCartPage;
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
        String browserName = Property.getProperty("browserName");
        BrowserDriver uiDriver = new BrowserDriver();
        driver= uiDriver.createDriver(browserName);
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
    @When("Add the item to cart")
    public void addTheItemToCart() {
            new ProductPage().addItemToCart().verifItemAddedToCart();
    }

    @And("add More Item To Cart")
    public void addMoreTheItemToCart() {
        new ProductPage().addMoreItemsToCart().verifItemAddedToCart();
    }
    @And("Open Cart from the top panel")
    public void openCartFromTheTopPanel() {
    new ProductPage().openCartFromTopPanel().verifIseletedItemInShoppingCart();
    }

    @Then("Verify that the price is identical to the product page")
    public void verifyThatThePriceIsIdenticalToTheProductPage() {
    new ShoppingCartPage().verifTotalPaymentPriceIsIdentical();
    }

    @And("Verify that the sub total is identical to the product page")
    public void verifyThatTheSubTotalIsIdenticalToTheProductPage() {
        new ShoppingCartPage().verifSubTotalPaymentPriceIsIdentical();
    }

    @And("Select {int} item in the list")
    public void selectItemInTheList(Integer itemNumber) {
        new LoginPage().selectItemInList(itemNumber)
                .verifyProductPageLoadedSucessfully();
    }


}

