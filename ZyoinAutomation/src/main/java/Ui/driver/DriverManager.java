package Ui.driver;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class DriverManager {

    public  static  final HashMap<String, WebDriver> map = new HashMap<>();
    public static void setDriver(WebDriver driver){
        map.put("drivrID",driver);
    }

    public static WebDriver getDriver(){
        return map.get("drivrID");
    }
}
