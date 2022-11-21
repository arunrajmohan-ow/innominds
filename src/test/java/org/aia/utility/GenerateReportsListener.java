package org.aia.utility;

import java.io.IOException;

import org.aia.pages.BaseClass;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class GenerateReportsListener implements ITestListener{
	
	ExtentHtmlReporter htmlreport;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public GenerateReports report = GenerateReports.getInstance();
	static int count_passedTCs;
	static int count_skippedTCs;
	static int count_failedTCs;
	static int count_totalTCs;

	public void onTestStart(ITestResult result) {
		count_totalTCs = count_totalTCs + 1;
		System.out.println("Inside GenerateReportsListener onTestStart() method creating test report");
		report.startTestReport(result.getMethod().getMethodName());
		System.out.println("onTestStart completed");
	}

	public void onTestSuccess(ITestResult result) {
		count_passedTCs = count_passedTCs + 1;
		System.out.println(" Inside onTestSuccess Listener method");
		report.logTestpassed(result.getMethod().getMethodName() );
	}

	public void onTestFailure(ITestResult result) {
		count_failedTCs = count_failedTCs + 1;
		WebDriver driver = BaseClass.getDriverInstance();
		String screenshotPath = Utility.captureScreenshot(driver);
		report.logTestFailed(result.getMethod().getMethodName());
		try {
			report.attachScreeshot(screenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		count_skippedTCs = count_skippedTCs + 1;
		report.logTestSkipped(result.getMethod().getMethodName());
	}

	public void onStart(ITestContext context) {
		System.out.println("Inside GenerateReportsListener onStart() method creating report");
		report.startExtentReport();
	}

	public void onFinish(ITestContext context) {
		report.endReport();
		EmailSendUtils.sendEmail(count_totalTCs, count_passedTCs, count_failedTCs, count_skippedTCs);
	}

}
