package org.aia.testcases.events;

import org.aia.pages.BaseClass;
import org.aia.pages.api.events.EventAPIValidations;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.fonteva.events.EventInfoModule;
import org.aia.pages.fonteva.events.Events;
import org.aia.pages.fonteva.events.NewCloneEvents;
import org.aia.pages.fonteva.events.TicketModule;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.VideoRecorder;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.aia.utility.GenerateReportsListener.class)
public class TicketsTestClone_Execution extends BaseClass {
	Events events;
	NewCloneEvents cloneEventpage;
	ConfigDataProvider testData;
	EventInfoModule editCloneEvent;
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
		editCloneEvent = PageFactory.initElements(driver, EventInfoModule.class);
		eventApivalidation = PageFactory.initElements(driver, EventAPIValidations.class);
		ticketModule = PageFactory.initElements(driver, TicketModule.class);
		recording = Boolean.parseBoolean(testData.testDataProvider().getProperty("videoRecording"));
		Logging.configure();
	}

	@Test(priority = 1, description = "verify field in TicketModule tickets info,", enabled = false)
	public void test_verifyFieldInTicketModule(ITestContext context) throws InterruptedException, Throwable {
		// Fec-102
		if (recording) {
			VideoRecorder.startRecording("verifyCreateVenuePopUpInputField");
		}
		events.eventsTab();
		events.clickCreatedEvent("RecentEvents");
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 90000, 5000);
		ticketModule.verifyAllFIeldsTicketModule();
	}

	@Test(priority = 2, description = "Verify creation of New Ticket Type", enabled = false)
	public void validate_CreateNewTicketType(ITestContext context) throws InterruptedException, Throwable {
		// fec-103
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
	}

	@Test(priority = 3, description = "verify field in TicketModule info, tickets sales start date,", enabled = false)
	public void test_VeriftTickets_sales_startDate(ITestContext context) throws InterruptedException, Throwable {
		// Fec-104 duplicate as fec-116
		events.eventsTab();
		events.clickCreatedEvent("RecentEvents");
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 90000, 5000);
		ticketModule.verifyUserAbleToProvidedateIntoTicketSalesStartDate();
	}

	@Test(priority = 4, description = "Verify CreateNewTicket And ValidateAllfields,", enabled = false)
	public void test_VerifycCreateNewTicketAndValidateAllfields(ITestContext context)
			throws InterruptedException, Throwable {
		// Fec-114
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
		if (recording) {
			VideoRecorder.stopRecording();
		}
	}
	
	@Test(priority = 5, description = "validate_TicketDisplayOrderValues", enabled = true)
	public void validate_TicketDisplayOrderValues(ITestContext context) throws InterruptedException, Throwable {
		//fec-115
		events.eventsTab();
		System.out.println("eventsticket tab displayed");
		events.clickCreatedEvent("RecentEvents");
		editCloneEvent.clickEditButton();
		ticketModule.eventTicketsTab();
		util.waitForJavascript(driver, 30000, 5000);
		System.out.println("tickets tab is displayed");
		ticketModule.VerifyTicketOrderValues();
		System.out.println("testcase executed SUccessfully");
    }
	
	@Test(priority = 6, description = "test_VerifyManageInventoryInTickets", enabled = true)
	public void test_VerifyManageInventoryInTickets(ITestContext context)
			throws InterruptedException, Throwable {
		// Fec-118
		events.eventsTab();
		String eventName =events.clickCreatedEvent("RecentEvents");
		 String eventId = cloneEventpage.getEventId();
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 30000, 5000);
		ticketModule.eventTicketsTab();
		ticketModule.clickManageInventory();
		ticketModule.EnterAiaMemberDetails();
		String uiEventCapacity =ticketModule.VerifyEventTicketCapacity();
		context.setAttribute("eventId", eventId);
		context.setAttribute("eventName", eventName);
		context.setAttribute("uiEventCapacityAfterModify", uiEventCapacity);
		
		//Edit Event capacity API validation
		eventApivalidation.verifyEventTicketCapacity(context);
		if (recording) {
			VideoRecorder.stopRecording();
		}
	}
}

