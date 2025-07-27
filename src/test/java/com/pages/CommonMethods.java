package com.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class CommonMethods {

	private WebDriver driver;
	private SoftAssert softAssert;

	public CommonMethods(WebDriver driver) {
		this.driver = driver;
		this.softAssert = new SoftAssert();
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

	// Common method for finding elements with highlighting
	private WebElement findElement(By locator) {
		try {
			WebElement element = driver.findElement(locator);
			highlightElement(element);
			return element;
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Element not found: " + locator, e);
		}
	}

	// Highlight element (extracted as separate reusable method)
	private void highlightElement(WebElement element) {
		//Your existing highlight implementation
		Example: ((JavascriptExecutor)
		driver).executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void click(By locator) {
		try {
			WebElement element = findElement(locator);
			element.click();
		} catch (Exception e) {
			throw new RuntimeException("Failed to click element: " + locator, e);
		}
	}

	// SendKeys with consistent pattern
	public void sendKeys(By locator, String text) {
		try {
			WebElement element = findElement(locator);
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			throw new RuntimeException("Failed to enter text in element: " + locator, e);
		}
	}

	// isDisplayed with consistent pattern
	public boolean isDisplayed(By locator) {
		try {
			return findElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// getText with consistent pattern
	public String getText(By locator) {
		try {
			return findElement(locator).getText();
		} catch (Exception e) {
			throw new RuntimeException("Failed to get text from element: " + locator, e);
		}
	}

	// getCurrentUrl with consistent pattern
	public String getCurrentUrl() {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			throw new RuntimeException("Failed to get current URL", e);
		}
	}

	/*
	 * Check if the Title matches
	 */
	public String getPageTitle() {
		return driver.getTitle();
	}

	// Soft Assert for Page Title
	public boolean softAssertPageTitle(String expectedTitle) {
		String actualTitle = getPageTitle();
		boolean isMatch = actualTitle.equals(expectedTitle);

		if (!isMatch) {
			String errorMessage = String.format(
					"\nPage Title Mismatch!\nExpected: '%s'\nActual: '%s'",
					expectedTitle, actualTitle);
			softAssert.fail(errorMessage);
		}

		return isMatch;
	}

	// Waits
	public void waitForElementToBeClickable(By locator, Duration timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void assertAll() {
		softAssert.assertAll();
	}

}
