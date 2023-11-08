package org.aia.testcases.events;

import org.aia.pages.BaseClass;
import org.aia.pages.api.events.EventAPIValidations;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.fonteva.events.EditCloneEvent;
import org.aia.pages.fonteva.events.EventConfig;
import org.aia.pages.fonteva.events.Events;
import org.aia.pages.fonteva.events.NewCloneEvents;
import org.aia.pages.fonteva.events.TicketModule;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.aia.utility.VideoRecorder;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class Test_CloneTicketsInMediumTemplate extends BaseClass {
	Events events;
	NewCloneEvents cloneEventpage;
	ConfigDataProvider testData;
	EditCloneEvent editCloneEvent;
	TicketModule ticketModule;
	EventAPIValidations eventApivalidation;
	boolean recording;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		testData = new ConfigDataProvider();
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				testData.getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		events = PageFactory.initElements(driver, Events.class);
		cloneEventpage = PageFactory.initElements(driver, NewCloneEvents.class);
		editCloneEvent = PageFactory.initElements(driver, EditCloneEvent.class);
		ticketModule = PageFactory.initElements(driver, TicketModule.class);
		eventApivalidation = PageFactory.initElements(driver, EventAPIValidations.class);
		recording = Boolean.parseBoolean(testData.testDataProvider().getProperty("videoRecording"));
		if (recording) {
			VideoRecorder.startRecording("test_verifyFieldInTicketModule");
		}
		Logging.configure();
	}
	
	@Test(priority = 1, description = "verify field in TicketModule tickets info,", enabled = true)
	public void test_verifyFieldInTicketModuleMediumTEmplate(ITestContext context) throws InterruptedException, Throwable {
		Logging.logger.info("================================test_EditPriceInCloneEvent started==========================");
		// Fec-102
		try {
			events.eventsTab();
			events.clickCreatedEvent("RecentEvents");
			editCloneEvent.clickEditButton();
			util.waitForJavascript(driver, 20000, 5000);
			ticketModule.verifyAllFIeldsTicketModule();
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		} catch (Throwable e) {
			throw new AssertionError(e.getMessage());
		}
		
	}

	@Test(priority = 2, description = "Verify creation of New Ticket Type", enabled = true)
	public void validate_CreateNewTicketTypeMediumTEmplate(ITestContext context) throws InterruptedException, Throwable {
		Logging.logger.info("================================test_EditPriceInCloneEvent started==========================");
		// fec-103
		try {
			events.eventsTab();
			events.clickCreatedEvent("RecentEvents");
			editCloneEvent.clickEditButton();
			ticketModule.eventTicketsTab();
			ticketModule.clickNewTicketType();
			ticketModule.publishedCheckBox();
			ticketModule.activeCheckBox();
			ticketModule.enterTicketName();
			ticketModule.enterPriceInCreateTicketType();
			ticketModule.restrictQuantityCheckBox();
			ticketModule.enterDescriptionInCreateTicketType();
			ticketModule.buttonsInCreateTicketType("SaveContinue");
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		} catch (Throwable e) {
			throw new AssertionError(e.getMessage());
		}
		
	}

	@Test(priority = 3, description = "verify field in TicketModule info, tickets sales start date,", enabled = true)
	public void test_VeriftTickets_sales_startDateMediumTEmplate(ITestContext context) throws InterruptedException, Throwable {
		Logging.logger.info("================================test_EditPriceInCloneEvent started==========================");
		// Fec-104
		try {
			events.eventsTab();
			events.clickCreatedEvent("RecentEvents");
			editCloneEvent.clickEditButton();
			util.waitForJavascript(driver, 90000, 5000);
			ticketModule.verifyUserAbleToProvidedateIntoTicketSalesStartDate();
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		} catch (Throwable e) {
			throw new AssertionError(e.getMessage());
		}
	}

	@Test(priority = 4, description = "Verify CreateNewTicket And ValidateAllfields,", enabled = true)
	public void test_VerifycCreateNewTicketAndValidateAllfieldsMediumTEmplate(ITestContext context)
			throws InterruptedException, Throwable {
		Logging.logger.info("================================test_EditPriceInCloneEvent started==========================");
		// Fec-114
		try {
			events.eventsTab();
		    events.clickCreatedEvent("RecentEvents");
			editCloneEvent.clickEditButton();
			util.waitForJavascript(driver, 30000, 5000);
			ticketModule.eventTicketsTab();
			ticketModule.verifyAllFIeldsInCreateTicketpivotTabPopUp();
			ticketModule.publishedCheckBox();
			ticketModule.activeCheckBox();
			ticketModule.enterTicketName();
			ticketModule.enterPriceInCreateTicketType();
			ticketModule.restrictQuantityCheckBox();
			ticketModule.enterDescriptionInCreateTicketType();
			ticketModule.buttonsInCreateTicketType("SaveContinue");
			
		}catch (Exception e) {
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
