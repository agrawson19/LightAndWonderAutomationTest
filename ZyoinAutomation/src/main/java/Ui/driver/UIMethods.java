package Ui.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UIMethods {

    private static final Logger logger = LogManager.getLogger(UIMethods.class);
    private WebDriver driver;
    public UIMethods() {
        {
            driver = DriverManager.getDriver();
        }
    }
    public void setText(By findBy, String text, String desc) {
        logger.debug("{}:: {}", desc, text);
//       this.driver.waitForElementToBeVisible(findBy, desc);
       this.driver.findElement(findBy).clear();
        this.driver.findElement(findBy).sendKeys(new CharSequence[]{text});
    }


}
