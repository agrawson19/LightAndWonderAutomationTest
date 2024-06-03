package Ui.pages;

import Ui.driver.DriverManager;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver uiDriver;
    public LoginPage() {
        synchronized (this) {

            uiDriver = DriverManager.getDriver();
        }
    }
}
