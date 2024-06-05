package Ui.pages;

import Ui.commonComponents.UIMethods;
import Ui.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import  org.assertj.core.api.Assertions.*;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(){

    }
    UIMethods uiMethods = new UIMethods();

  public By amazonLogo = By.xpath("//div[@id='nav-logo']");
  public By txtBoxSearchItem = By.xpath( "//input[@id='twotabsearchtextbox']");
    public By labelItemList = By.xpath( "//span[contains(text(),'Check each product page for other buying options.')]");
  public By lstSearchItems = By.xpath(   "//div[@data-component-type='s-search-result']");


    public  String amazonLoginPage = "amazon login screen";
    public LoginPage loginToApplication(){
        logger.info("login to amazon");
        String url = "https://www.amazon.in/";
        uiMethods.getUrl(url);
        uiMethods.waitForPageLoadedSucessfully();
        return this;
    }

    public boolean isLoginPageDisplayed(By findBy){
       return (uiMethods.isElementDisplayed(findBy,amazonLoginPage));
    }
    public LoginPage verifyLoginSucessfully(){

        Assertions.assertThat(isLoginPageDisplayed(amazonLogo))
                .as("")
                .isTrue();
        logger.info("amazon home screen displayed");
        return  this;
    }
    public LoginPage verifyElementDispalyed(By findBy,String desc){

        Assertions.assertThat(uiMethods.isElementDisplayed(findBy,desc))
                .as("")
                .isTrue();
        logger.info("Element dispalyed on page :"+desc);
        return  this;
    }

    public LoginPage verifyListOfMonitorDisplayed(){
        verifyElementDispalyed(labelItemList,"list of monitor display on page");
        logger.info("List of monitor displayed on page sucessfully :");
        return  this;
    }
    public LoginPage searchItem(String fieldName){
        uiMethods.waitForElementToBeVisible(txtBoxSearchItem,"search item : "+fieldName);
        uiMethods.setTextAndEnter(txtBoxSearchItem,fieldName,"search item :"+fieldName);
        return this;
    }
    public ProductPage selectItemInList(){
        List<WebElement> eleList = uiMethods.findElements(lstSearchItems);
        logger.info("Select item from product  list");
        uiMethods.click(eleList.get(0),"click on  first item in list");
        uiMethods.delay(5000);
        return new ProductPage();
    }



}
