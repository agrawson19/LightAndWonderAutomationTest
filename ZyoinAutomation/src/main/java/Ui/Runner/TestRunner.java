package Ui.Runner;


import Ui.driver.BrowserDriver;
import Ui.driver.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.testng.*;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;

import io.cucumber.testng.CucumberPropertiesProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

@CucumberOptions(tags = "@smoke",
        features = "src/main/resources/features",
        glue= {"Ui.testcases"},
        plugin = { "pretty", "html:target/cucumber-reports/index.html" },
        monochrome = true
)
public class TestRunner extends  AbstractTestNGCucumberTests{



}
