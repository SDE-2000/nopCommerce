package com.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

private WebDriver driver;
    
    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }
    
    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        // Create screenshots directory if it doesn't exist
        String screenshotsDir = System.getProperty("user.dir") + "/screenshots/";
        File directory = new File(screenshotsDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate timestamp for unique filename
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Take screenshot
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Create full path for the screenshot
        String screenshotPath = screenshotsDir + screenshotName + "_" + timeStamp + ".png";

        // Save the screenshot
        FileUtils.copyFile(source, new File(screenshotPath));

        // Return the full path
        return screenshotPath;
    }
    
    public void highlight_Element(WebElement element, WebDriver driver) {
    	 JavascriptExecutor js = (JavascriptExecutor) driver;
    	 js.executeScript("arguments[0].style.border='2px solid red'", element);
    }
    // Basic Interactions
    public void click(By locator) {
    	highlight_Element( driver.findElement(locator), driver);
        driver.findElement(locator).click();
        
    }
    
    public void sendKeys(By locator, String text) {
    	WebElement element = driver.findElement(locator);
    	highlight_Element(element, driver);
        element.sendKeys(text);
    }
    
    public boolean isDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
    
    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }
    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    /*
     * Check if the Title matches
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public void assertPageTitle(String expectedTitle) {
        String actualTitle = getPageTitle();
        if (!actualTitle.equals(expectedTitle)) {
            throw new AssertionError(
                String.format("\n\nPage Title Mismatch!\nExpected: '%s'\nActual: '%s'\n", 
                             expectedTitle, actualTitle)
            );
        }
    }
    // Waits
    public void waitForElementToBeClickable(By locator, Duration timeout) {
        new WebDriverWait(driver, timeout)
            .until(ExpectedConditions.elementToBeClickable(locator));
    }
    
}
