package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "./Features/Login.feature",
    glue = {"stepDefinations", "appHooks"}
//    plugin = {
//            "pretty",
//            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
//            "json:target/cucumber-reports/cucumber.json"
//        },
//        monochrome = true
)
public class MyRunner {
}