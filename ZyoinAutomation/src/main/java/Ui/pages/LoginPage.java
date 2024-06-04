package Ui.pages;

import Ui.commonComponents.UIMethods;
import Ui.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import  org.assertj.core.api.Assertions.*;
public class LoginPage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(){

    }
    UIMethods uiMethods = new UIMethods();

  public By amazonLogo = By.xpath("//div[@id='nav-logo']");
   public  String amazonLoginPage = "amazon login screen";
    public LoginPage loginToApplication(){
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
}
