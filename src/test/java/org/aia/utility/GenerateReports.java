package org.aia.utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
//Singleton class
public class GenerateReports {
	
	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	private static GenerateReports gr;
	
	private GenerateReports() {
		
	}
	
	//Returns GenerateReports object when called
	public static GenerateReports getInstance() {
		if(gr==null) {
			gr=new GenerateReports();
		}
		return gr;
	}
	
	public void startExtentReport() {

		htmlReporter = new ExtentHtmlReporter(Constants.GENERATE_REPORT_PATH);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "AIA");
		extent.setSystemInfo("Environment", "Testing-Sandbox");
		extent.setSystemInfo("User Name", "Pallavi");

		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("AIA autoation Test");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}
	
	public void startTestReport(String testName) {
		logger = extent.createTest(testName);
	}
	
	public void logTestInfo(String message) {
		logger.log(Status.INFO, message);
	}
	
	public void logTestpassed(String testcaseName) {
		logger.log(Status.PASS, MarkupHelper.createLabel(testcaseName + "is passTest", ExtentColor.GREEN));
	}

	public void logTestFailed(String testcaseName) {
		logger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName + "is not passTest", ExtentColor.RED));
	}
	
	public void logTestFailedWithException(Exception e) {
		logger.log(Status.ERROR,e);
	}
	
	public void logTestSkipped(String testcaseName) {
		logger.log(Status.SKIP,
				MarkupHelper.createLabel(testcaseName + " skipped the Test", ExtentColor.YELLOW));
	}

	public void endReport() {
		extent.flush();
	}
	
	public void attachScreeshot(String path) throws IOException {
		
		logger.addScreenCaptureFromPath(path);
	}

}
