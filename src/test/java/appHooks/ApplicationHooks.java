package appHooks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.pages.CommonMethods;
import com.qa.factory.DriverFactory;
import com.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	private CommonMethods commonMethods;
	Properties prop;

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	public static String Timestamp = sdf.format(new Date());

	public static ExtentSparkReporter spark = new ExtentSparkReporter("test-output/SparkReport_" + Timestamp + ".html");
	public static ExtentReports extent = new ExtentReports();

	@Before(order = 0)
	public void getProperty() {

		configReader = new ConfigReader();
		prop = configReader.init_prop();
		ApplicationHooks.spark.config().setDocumentTitle("Automation Report");
		ApplicationHooks.spark.config().setReportName("Functional Test Results");
		ApplicationHooks.spark.config().setTheme(Theme.DARK);
		ApplicationHooks.extent.attachReporter(ApplicationHooks.spark);
		ApplicationHooks.extent.setSystemInfo("Tester", "Soumya");

	}

	@Before(order = 1)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		String url = prop.getProperty("url");
		driver = driverFactory.init_driver(browserName);
		commonMethods = new CommonMethods(driver);
		driver.get(url);

	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}
	
	

	@After
	public void reportGenerator() {
		extent.flush();
	}

}
