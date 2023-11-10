package org.aia.testcases.events;
 
import java.io.IOException;

import org.aia.pages.BaseClass;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.fonteva.events.EmailAllAttendees;
import org.aia.pages.fonteva.events.Events;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.aia.utility.VideoRecorder;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
 
@Listeners(org.aia.utility.GenerateReportsListener.class)
public class TestEmailAllAttendees extends BaseClass {
	Events events;
	EmailAllAttendees email;
	ConfigDataProvider testData;
	boolean recording;
 
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		testData = new ConfigDataProvider();
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				testData.getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		events = PageFactory.initElements(driver, Events.class);
		email = PageFactory.initElements(driver, EmailAllAttendees.class);
		
		recording = Boolean.parseBoolean(testData.testDataProvider().getProperty("videoRecording"));
		Logging.configure();
	}
 
	@Test(priority = 1, description = "validateEmailAttandees", enabled = true)
	public void validateEmailAttandees(ITestContext context) throws InterruptedException, Throwable {
		events.eventsTab();
		email.clickEmailAdress();
		System.out.println("testcase passed");
}
	
	@Test(priority = 2, description = "validateEmailAttandeesNo", enabled = true)
	public void validateEmailAttandeesNo(ITestContext context) throws InterruptedException, Throwable {
		events.eventsTab();
		System.out.println("eventsticket tab displayed");
		email.clickEmailAdressNo();
		System.out.println("testcase passed");
}
	@AfterMethod(alwaysRun = true)
	public void teardown(ITestResult result) throws IOException {
		if (recording) {
			VideoRecorder.stopRecording();
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("LOG : FAIL Test failed to executed");
			Utility.takeScreenShotAfterFail(driver, result);
		}
		BrowserSetup.closeBrowser(driver);
	}
}
 