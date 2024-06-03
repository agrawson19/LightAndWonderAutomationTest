package Ui.driver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

public class BaseTestSupportingMethods {
    WebDriver driver;
    @BeforeTest
    public  void beforeTestMethod(){
        BrowserDriver uiDriver = new BrowserDriver("chrome");
        driver= uiDriver.createDriver("chrome");
        driver.get("https://www.amazon.com/");
        DriverManager.setDriver(driver);
    }
}
