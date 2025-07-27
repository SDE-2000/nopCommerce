package stepDefinations;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.pages.CommonMethods;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.qa.factory.DriverFactory;

import appHooks.ApplicationHooks;
import io.cucumber.java.en.*;

public class LoginSteps {
    
    private WebDriver driver = DriverFactory.getDriver();
    private CommonMethods commonMethods = new CommonMethods(driver);
    private HomePage homePage = new HomePage();
    private LoginPage loginPage = new LoginPage();
    private String pageTitle;
    
    @Given("user is on Home page")
    public void user_is_on_home_page() throws IOException {
        String expectedTitle = "nopCommerce demo store. Home page title";
        Assert.assertTrue(commonMethods.getPageTitle().contains(expectedTitle));
        
        
       
        
        ExtentTest test = ApplicationHooks.extent.createTest("user is on Home page");
        test.fail("Login successful");
        String screenShot=CommonMethods.getScreenshot(driver, "HomePage");
        test.addScreenCaptureFromPath(screenShot);
        
        //test.fail("Password incorrect");

    }

    @Given("user click login")
    public void user_click_login() {
        commonMethods.click(homePage.loginLink);
    }
    
    @When("user gets the title of the page")
    public void user_gets_the_title_of_the_page() {
        pageTitle = commonMethods.getPageTitle();
        System.out.println(pageTitle);
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String expectedTitle) {
    	 commonMethods.assertPageTitle(expectedTitle);
    }

    @Then("forgot your password link should be displayed")
    public void forgot_your_password_link_should_be_displayed() {
        Assert.assertTrue(commonMethods.isDisplayed(loginPage.forgotPasswordLink));
    }
}