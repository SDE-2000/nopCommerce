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
import io.cucumber.java.After;
import com.aventstack.extentreports.*;
import io.cucumber.java.en.*;

public class LoginSteps {

	private WebDriver driver = DriverFactory.getDriver();
	private CommonMethods commonMethods = new CommonMethods(driver);
	private HomePage homePage = new HomePage();
	private LoginPage loginPage = new LoginPage();
	private ExtentTest test;
	private String pageTitle;

	@Given("user is on Home page")
    public void user_is_on_home_page() throws IOException {
        test = ApplicationHooks.extent.createTest("Home Page Verification");
        try {
            String expectedTitle = "nopCommerce demo store. Home page title";
            Assert.assertTrue(commonMethods.getPageTitle().contains(expectedTitle));
            test.log(Status.PASS, "User is on Home page");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Home page verification failed: " + e.getMessage());
            throw e;
        } finally {
            String screenShot = CommonMethods.getScreenshot(driver, "HomePage");
            test.addScreenCaptureFromPath(screenShot);
        }
    }

    @Given("user click login")
    public void user_click_login() throws IOException {
        test = ApplicationHooks.extent.createTest("Click Login Link");
        try {
            commonMethods.click(homePage.loginLink);
            test.log(Status.PASS, "Clicked on Login link successfully");
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to click Login link: " + e.getMessage());
            throw e;
        } finally {
            String screenShot = CommonMethods.getScreenshot(driver, "ClickLogin");
            test.addScreenCaptureFromPath(screenShot);
        }
    }

    @When("user gets the title of the page")
    public void user_gets_the_title_of_the_page() throws IOException {
        test = ApplicationHooks.extent.createTest("Get Page Title");
        try {
            pageTitle = commonMethods.getPageTitle();
            System.out.println(pageTitle);
            test.log(Status.PASS, "Page title retrieved successfully: " + pageTitle);
        } catch (Exception e) {
            test.log(Status.FAIL, "Failed to get page title: " + e.getMessage());
            throw e;
        } finally {
            String screenShot = CommonMethods.getScreenshot(driver, "PageTitle");
            test.addScreenCaptureFromPath(screenShot);
        }
    }

    @Then("page title should be {string}")
    public void page_title_should_be(String expectedTitle) throws IOException {
        test = ApplicationHooks.extent.createTest("Verify Page Title");
        try {
            commonMethods.softAssertPageTitle(expectedTitle);
            test.log(Status.PASS, "Page title matches expected: " + expectedTitle);
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Page title verification failed: " + e.getMessage());
            throw e;
        } finally {
            String screenShot = CommonMethods.getScreenshot(driver, "TitleVerification");
            test.addScreenCaptureFromPath(screenShot);
        }
    }

    @Then("forgot your password link should be displayed")
    public void forgot_your_password_link_should_be_displayed() throws IOException {
        test = ApplicationHooks.extent.createTest("Verify Forgot Password Link");
        try {
            Assert.assertTrue(commonMethods.isDisplayed(loginPage.forgotPasswordLink));
            test.log(Status.PASS, "Forgot password link is displayed");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Forgot password link verification failed: " + e.getMessage());
            throw e;
        } finally {
            String screenShot = CommonMethods.getScreenshot(driver, "ForgotPasswordLink");
            test.addScreenCaptureFromPath(screenShot);
        }
    }

    @After
    public void verifyAllAssertions() {
        commonMethods.assertAll();
    }
}