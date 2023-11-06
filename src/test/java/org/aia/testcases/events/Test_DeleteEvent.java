package org.aia.testcases.events;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.events.EventAPIValidations;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.events.EventRegistration;
import org.aia.pages.events.ViewRecipts;
import org.aia.pages.fonteva.events.EditCloneEvent;
import org.aia.pages.fonteva.events.Events;
import org.aia.pages.fonteva.events.NewCloneEvents;
import org.aia.pages.fonteva.events.QuickLinksInEvents;
import org.aia.pages.fonteva.events.TicketModule;
import org.aia.pages.membership.CheckYourEmailPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpPage;
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
import org.testng.annotations.Test;

public class Test_DeleteEvent extends BaseClass {
	
	Events events;
	NewCloneEvents cloneEventpage;
	ConfigDataProvider testData;
	EditCloneEvent editCloneEvent;
	SignUpPage signUpPage;
	MailinatorAPI mailinator;
	SignInPage signInpage;
	CheckYourEmailPage closeButtnPage;
	EventRegistration eventRegistration;
	ViewRecipts viewReceipts;
	EventAPIValidations eventApivalidation;
	QuickLinksInEvents linksInEvents;
	TicketModule ticketModule;
	boolean recording;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		try {
			testData = new ConfigDataProvider();
			sessionID = new FontevaConnectionSOAP();
			driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
					testData.getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
			events = PageFactory.initElements(driver, Events.class);
			cloneEventpage = PageFactory.initElements(driver, NewCloneEvents.class);
			editCloneEvent = PageFactory.initElements(driver, EditCloneEvent.class);
			signInpage = PageFactory.initElements(driver, SignInPage.class);
			signUpPage = PageFactory.initElements(driver, SignUpPage.class);
			linksInEvents = PageFactory.initElements(driver, QuickLinksInEvents.class);
			mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
			closeButtnPage = PageFactory.initElements(driver, CheckYourEmailPage.class);
			eventRegistration = PageFactory.initElements(driver, EventRegistration.class);
			viewReceipts = PageFactory.initElements(driver, ViewRecipts.class);
			eventApivalidation = PageFactory.initElements(driver, EventAPIValidations.class);
			ticketModule = PageFactory.initElements(driver, TicketModule.class);
			recording = Boolean.parseBoolean(testData.testDataProvider().getProperty("videoRecording"));
			Logging.configure();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Test(priority = 1, description = "Verify Delation Existing Event", enabled = true)
	public void validate_DeleteExistingEventMediumTEmplate(ITestContext context) throws InterruptedException, Throwable {
		 Logging.logger.info("================================validate_DeleteExistingEvent started==========================");
		try {
			events.eventsTab();
			events.clickActionsInEvents("Delete");
			events.validateDeletePopup();
			events.clickActionsInDeletePopup("Delete");
			events.validateToasteMessage();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} catch (Throwable e) {
			throw new AssertionError(e.getMessage());
		}
	}

	@AfterMethod(alwaysRun = true)
	public void teardown(ITestResult result) {
		if (recording) {
			VideoRecorder.stopRecording();
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("LOG : FAIL Test failed to executed");
			Utility.takeScreenShotAfterFail(driver, result);
		}
		if(driver != null){
			BrowserSetup.closeBrowser(driver);
			}
	}
}
