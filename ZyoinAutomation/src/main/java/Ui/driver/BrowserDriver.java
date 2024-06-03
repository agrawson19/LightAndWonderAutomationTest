package Ui.driver;

//import Ui.exceptions.TestSetupException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BrowserDriver {

    public BrowserDriver(String browserName){
        this.driver =createDriver(browserName);
    }
    private static final Logger logger = LogManager.getLogger(BrowserDriver.class);
    private final WebDriver driver;
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
            chromePrefs.put("profile.default_content_settings.popups", 0);
//            chromePrefs.put("download.prompt_for_download", false);
//            chromePrefs.put(DEFAULT_DOWNLOAD_DIRECTORY, downloadFilesPath);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("disable-infobars");
            chromeOptions.setAcceptInsecureCerts(true);
            chromeOptions.setExperimentalOption("prefs", chromePrefs);

                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(chromeOptions);


        }
        catch (Exception e) {
            logger.fatal(String.format("Exception occurred in createChromeBrowserDriver() - %s", e), e);
            try {
               // throw new TestSetupException(e);
            } catch (Exception ex) {
              //  throw new RuntimeException(ex);
            }

        }
        return webDriver;
    }

}
