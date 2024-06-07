package Ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BrowserDriver {

    public BrowserDriver(){
  }
    private static final Logger logger = LogManager.getLogger(BrowserDriver.class);
    public WebDriver createDriver(String browserName) {
        logger.debug("Browser for Execution - {}", browserName);
        WebDriver driver;
        driver= createChromeBrowserDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15000, TimeUnit.MILLISECONDS);
        return driver;

    }

    private WebDriver createChromeBrowserDriver()  {
        WebDriver webDriver =null;
        try {
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("safebrowsing.enabled", "true");
            chromePrefs.put("credentials_enable_service", false);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setAcceptInsecureCerts(true);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);

                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(chromeOptions);


        }
        catch (Exception e) {
            logger.info(String.format("Exception occurred in createChromeBrowserDriver() - %s", e), e);
        }
        return webDriver;
    }

}
