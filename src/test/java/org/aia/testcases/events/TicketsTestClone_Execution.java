package org.aia.testcases.events;

import org.aia.pages.BaseClass;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.fonteva.events.EditCloneEvent;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TicketsTestClone_Execution extends BaseClass {
	Events events;
	NewCloneEvents cloneEventpage;
	ConfigDataProvider testData;
	EditCloneEvent editCloneEvent;
	TicketModule ticketModule;
	boolean recording;
	
	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		testData = new ConfigDataProvider();
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				testData.getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		events = PageFactory.initElements(driver, Events.class);
		cloneEventpage = PageFactory.initElements(driver, NewCloneEvents.class);
		editCloneEvent = PageFactory.initElements(driver, EditCloneEvent.class);
		ticketModule = PageFactory.initElements(driver, TicketModule.class);
		recording = Boolean.parseBoolean(testData.testDataProvider().getProperty("videoRecording"));
		Logging.configure();
	}
	
	@Test(priority = 1, description = "verify field in TicketModule info, tickets,", enabled = true)
	public void test_verifyFieldInTicketModule(ITestContext context) throws InterruptedException, Throwable {
		//Fec-102
		if(recording) {
			VideoRecorder.startRecording("verifyCreateVenuePopUpInputField");
		}
		events.eventsTab();
		events.clickCreatedEvent();
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 90000, 5000);
		ticketModule.verifyAllFIeldsTicketModule();
	}
	
	@Test(priority = 2, description = "verify field in TicketModule info, tickets,", enabled = true)
	public void test_VeriftTickets_sales_startDate(ITestContext context) throws InterruptedException, Throwable {
		//Fec-104
		editCloneEvent.verifyUserAbleToProvidedateIntoTicketSalesStartDate();
	}
	
	@Test(priority = 3, description = "verify field in TicketModule info, tickets,", enabled = true)
	public void test_VerifycCreateNewTicketAndValidateAllfields(ITestContext context) throws InterruptedException, Throwable {
		//Fec-114
		ticketModule.verifyAllFIeldsInCreateTicketpivotTabPopUp();
		if(recording) {
			VideoRecorder.stopRecording();
		}
	}
}
