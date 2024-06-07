package Ui.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(tags = "@smoke",
        features = "src/main/resources/features",
        glue= {"Ui.testcases"},
        plugin = { "pretty", "html:target/cucumber-reports/index.html" },
        monochrome = true
)
public class TestRunner extends  AbstractTestNGCucumberTests{



}
