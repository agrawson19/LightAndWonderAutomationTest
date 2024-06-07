package Ui.pages;

import Ui.commonComponents.Property;
import Ui.commonComponents.UIMethods;
import Ui.driver.DriverManager;
import io.cucumber.java.an.E;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
  String ItemToSelect ="//div[@data-index=%s]//img";

    public  String amazonLoginPage = "amazon login screen";
    public LoginPage loginToApplication(){
        logger.info("login to amazon");
        String url = Property.getProperty("url");
        uiMethods.getUrl(url);
        uiMethods.waitForPageLoadedSucessfully();
        return this;
    }

    public boolean isLoginPageDisplayed(By findBy){
       return (uiMethods.isElementDisplayed(findBy,amazonLoginPage));
    }
    public LoginPage verifyLoginSucessfully(){

        Assertions.assertThat(isLoginPageDisplayed(amazonLogo))
                .as(" verify login")
                .isTrue();
        logger.info("amazon home screen displayed");
        return  this;
    }
    public LoginPage verifyElementDispalyed(By findBy,String... desc){

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

    public ProductPage selectItemFromGivenList(Integer index){
        uiMethods.delay(1000);
        logger.info("Select item from product  list");
        List<WebElement> eleList = uiMethods.findElements(lstSearchItems);
        logger.info("Select item from product  list");
        uiMethods.delay(2000);
        // Integer val = Integer.parseInt(index);
        uiMethods.click(eleList.get(index),"click on  first item in list");
        uiMethods.delay(2000);
        return new ProductPage();
    }


    public ProductPage selectItemInList(Integer index){
        uiMethods.delay(1000);
        logger.info("Select item from product  list");
        try {
            uiMethods.scrollIntoView(DriverManager.getDriver().findElement(By.xpath(String.format(ItemToSelect, index + 3))));
            JavascriptExecutor js = (JavascriptExecutor) (DriverManager.getDriver());
            WebElement ele = DriverManager.getDriver().findElement(By.xpath(String.format(ItemToSelect, index + 3)));
            js.executeScript("arguments[0].click()", ele);
            uiMethods.delay(5000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ProductPage();
    }


}
