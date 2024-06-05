package Ui.commonComponents;

import Ui.driver.DriverManager;
import Ui.exceptions.WebElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import org.testng.asserts.*;
import org.testng.Reporter.*;

import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class UIMethods {

    private static final Logger logger = LogManager.getLogger(UIMethods.class);
    private WebDriver driver;
    public UIMethods() {
        {
            driver = DriverManager.getDriver();
        }
    }
    public void setText(By findBy, String text, String desc) {
        logger.info("{}:: {}", desc, text);
        waitForElementToBeVisible(findBy, desc);
        findElement(findBy).clear();
        findElement(findBy).sendKeys(text);
    }
    public void setTextAndEnter(By findBy, String text, String desc) {
        logger.info("{}:: {}", desc, text);
        waitForElementToBeVisible(findBy, desc);
        findElement(findBy).clear();
        findElement(findBy).sendKeys(text);
        delay(2000);
        findElement(findBy).sendKeys(Keys.ENTER);
    }
    public WebElement findElement(By byLocator) {
        return this.driver.findElement(byLocator);
    }

    public List<WebElement> findElements(By byLocator) {
        return this.driver.findElements(byLocator);
    }

    public boolean waitForElementToBeVisible(By findBy, String desc) {
        long waitSeconds=10;
        boolean flag = false;
        logger.info("waitForElementToBeVisible - '{}'", desc);
        try {
            Wait<WebDriver> wait = new FluentWait<>(this.driver)
                    .withTimeout(Duration.ofSeconds(waitSeconds))
                    .pollingEvery(Duration.ofSeconds(1))
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
            wait.until(ExpectedConditions.visibilityOf(this.findElement(findBy)));
            flag = true;
        } catch (Exception e) {
            throw new WebElementException(String.format("Exception occurred in waitForElementToBeVisible. Exception: [%s]", e), e);

        }
        return flag;
    }

    public void waitForPageLoadedSucessfully() {
        long waitMilliSeconds=10;

        logger.info("waitForLoginPageLoadedSucessfully");
        try {
            this.driver.manage().timeouts().pageLoadTimeout(waitMilliSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Exception occurred in waitForPageLoadedSucessfully. Exception: [%s]", e), e);

        }

    }

    public void click(By findBy,String desc){
        try {
            waitForElementToBeVisible(findBy, desc);
            findElement(findBy).click();
        }
        catch (Exception e) {
            throw new WebElementException(e);
        }
    }

    public void click(WebElement eleToClick,String desc){
        try {
            eleToClick.click();
            logger.info(desc);
        }
        catch (Exception e) {
            throw new WebElementException(e);
        }
    }
    public void switchToWindowAndCloseCurrentWindow(){
        ArrayList<String>windows = new ArrayList<>(this.driver.getWindowHandles());
        if(windows.size()==1){
            logger.info("there is no new window open");
            throw new WebDriverException("No, new window or tab opened.");
        }
        else {
            this.driver.close();
            this.driver.switchTo().window((String) windows.get(1));
            logger.info("Successfully switched to newly opened Tab or Window - " + this.driver.getTitle());
        }
    }

    public  boolean isElementDisplayed(By findBy,String desc){
        boolean flag =false;
        waitForElementToBeVisible(findBy,desc);
       flag= this.findElement(findBy).isDisplayed();
       return flag;

    }
    public String getText(By findBy, String desc) {
        waitForElementToBeVisible(findBy,desc);
        String text;
        logger.info(desc);
        try {
            text = findElement(findBy).getText().trim();
        } catch (Exception e) {
            throw new WebElementException("Issue in Get Text : " + desc
                    +  e.getMessage() , e);
        }
        return text;
    }

    public void getUrl(String url){
        logger.info("access url: " + url);
        boolean sucessful =false;
        int count =0;
            while (!sucessful){
                if(count>=2){
                    break;}
                else {
                    try{
                        this.driver.get(url);
                        sucessful=true;

                    }
                    catch (Exception e){
                        logger.info("Retrying to hit the url:"+ url);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }

            }
            if(!sucessful){
                throw new WebDriverException("failed to get url: "+url);
            }

    }

    public static void delay(int numberInMilliSeconds){
        try {
            Thread.sleep(numberInMilliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }






}
