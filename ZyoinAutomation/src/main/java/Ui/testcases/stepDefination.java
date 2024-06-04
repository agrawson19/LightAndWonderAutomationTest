package Ui.testcases;

import Ui.driver.BrowserDriver;
import Ui.driver.DriverManager;
import Ui.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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
       System.out.println("testData");
       new LoginPage() .loginToApplication().verifyLoginSucessfully();
   }

   @And("^Search field type \\\"(.*)\\\"$")
    public void searchField(String  fieldName) throws Throwable {
       System.out.println("test");
}

}

