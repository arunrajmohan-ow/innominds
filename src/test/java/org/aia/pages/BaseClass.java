package org.aia.pages;

import java.io.IOException;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.GenerateReports;
import org.aia.utility.Utility;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BaseClass {

	public static WebDriver driver;
	
	ExtentReports report;
	ExtentTest logger;
	protected Utility util; 
	protected ConfigDataProvider testData;
	protected FontevaConnectionSOAP sessionID;
	public static ExtentReporter htmlReporter;
	public static GenerateReports reports;
	// Configure Log4j to perform error logging

	@BeforeSuite(alwaysRun = true)
	public void setup() {
		System.out.println("Extent report is getting started");
		System.out.println("Extent report is ready to use ");
	}

	// @Parameters({"browser","url"})
	@BeforeClass(alwaysRun = true)
	// public void setup(String browser, String url)
	public void setupBrowser() {
		Reporter.log("LOG: INFO : Creating browser instances", true);
		util = new Utility(driver, 30);
		Reporter.log("LOG: INFO : Browser instance is ready ", true);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		Reporter.log("LOG: INFO : Closing browser instances", true);
		BrowserSetup.closeBrowser(driver);
		Reporter.log("LOG: INFO : Browser instances closed", true);
	}

	@BeforeTest(alwaysRun = true)
	public void initialTestSetup() {
		System.out.println("inside @BeforeTest initialTestSetup method");
		reports = GenerateReports.getInstance();
	}

	@AfterTest(alwaysRun = true)
	public void finalTestTearDown() {
		System.out.println("@afterTest started");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
	
		System.out.println("Driver value in after method is " + driver);
		System.out.println("Running After method Test executed with below status");
		System.out.println("Status value " + result.getStatus());
		if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("LOG : PASS User is able to login");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("LOG : FAIL Test failed to executed");
			TakesScreenshot ts = (TakesScreenshot) driver;
			File screenshot = ts.getScreenshotAs(OutputType.FILE);
			try {
				// Define the destination path for the screenshot
				String screenshotPath = "./ScreenShots/" + result.getName() + ".png";
				Files.copy(screenshot.toPath(), new File(screenshotPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Screenshot saved at: " + screenshotPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (result.getStatus() == ITestResult.SKIP) {
			System.out.println("LOG : SKIP Test did not executed");
		}
//		reports.endReport();
	}

	public static WebDriver getDriverInstance() {
		return driver;
	}
}
