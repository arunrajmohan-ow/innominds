package org.aia.testcases.events;

import java.util.ArrayList;
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

public class TestClone_Events extends BaseClass {

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
	}

	@Test(priority = 1, description = "Create New CloneEvent enter event name, enter date, select event category and event search click clone button", enabled = false)

	public void test_CreateCloneEvent(ITestContext context) throws Throwable {
		if (recording) {
			VideoRecorder.startRecording("verifyCreateVenuePopUpInputField");
		}
		events.eventsTab();
		util.waitForJavascript(driver, 90000, 5000);
		events.newButtonInEvents();
		events.validateHeaderCloneEvent();
		cloneEventpage.newCloneOnExistingEvent(testData.testDataProvider().getProperty("eventCategory"));
		cloneEventpage.verifyCloneEventSegmentCheckBoxs();
		cloneEventpage.validateEventHeader();
		cloneEventpage.getEventId();
		util.waitForJavascript(driver, 90000, 5000);

		context.setAttribute("eventId", cloneEventpage.eventId);
		context.setAttribute("eventName", cloneEventpage.eName);
		context.setAttribute("startDate", cloneEventpage.startDate);
		context.setAttribute("eventCategory", testData.testDataProvider().getProperty("eventCategory"));
		// Create Clone event validation
		eventApivalidation.verifyEvent(context);
		if (recording) {
			VideoRecorder.stopRecording();
		}
	}

	@Test(description = "Verify Price modify for an existing Event", enabled = true, priority = 2)
	public void test_EditPriceInCloneEvent(ITestContext context) throws InterruptedException, Throwable {
		test_CreateCloneEvent(context);
		if (recording) {
			VideoRecorder.startRecording("test_EditPriceInCloneEvent");
		}
		events.eventsTab();
		String eventName = events.clickCreatedEvent();
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 90000, 5000);
		editCloneEvent.editEventInfo(eventName);
		// tickets tab
		ticketModule.eventTicketsTab();
		ticketModule.validateEventTicketSalesStartDate();
		ticketModule.editEventTicket(true);
		ticketModule.validateEditTicketTypeHeader();
		ticketModule.enterPriceInCreateTicketType();
		ticketModule.saveAndContinueButtonInTicketType();
		editCloneEvent.editEventInvitation();
		editCloneEvent.editEventVenues();
		editCloneEvent.editEventAccessPermissions();
		editCloneEvent.editEventSpeakers();
		editCloneEvent.editEventAgenda();
		//String scheduleName = editCloneEvent.getSceduleItemsInAgenda();
		editCloneEvent.editEventSponsorPackages();
		editCloneEvent.editEventStatuses();
		editCloneEvent.editEventPages();
		editCloneEvent.saveExitButton();
		util.waitForJavascript(driver, 90000, 5000);
		editCloneEvent.clickEventUrl();
		util.waitForJavascript(driver, 90000, 5000);
		// sometimes Register link is not clicked in AIA application
		eventRegistration.RegisterLink(1);
		util.waitForJavascript(driver, 90000, 5000);
		signInpage.signUp();
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		util.switchToTabs(driver, 1);
		util.navigateToURl(driver, DataProviderFactory.getConfig().getValue("fonteva_sign_in"));
		signInpage.login(dataList.get(5), dataList.get(6));
		util.switchToTabs(driver, 0);
		events.globalSearch(signUpPage.emailaddressdata);
		editCloneEvent.getAIAData();
		events.eventsSearch(eventName);
		editCloneEvent.clickEventUrl();
		// sometimes Register link is not clicked in AIA application
		eventRegistration.RegisterLink(3);
		util.waitForJavascript(driver, 90000, 5000);
		eventRegistration.selectTicketQuantity();
		eventRegistration.clickRegisterButton();
		eventRegistration.rigisterRequiredInfo();
		eventRegistration.clickRegistrationButton();
		eventRegistration.validateRegisterReq();
		eventRegistration.agendaModule();
	
		// Here we getting receipt data from UI and storing in ArrayList
		ArrayList<Object> receiptData = eventRegistration.checkoutModule();

		util.waitForJavascript(driver, 90000, 5000);

		// Here we validate PDF data
		viewReceipts.viewReceiptValidationsForEvents(receiptData.get(1), receiptData.get(0));

		// Here we validate the receipt using API call
		eventApivalidation.verifyReciptDetails(dataList.get(3), receiptData.get(1), receiptData.get(0));

		// Here we validate the Sales order using API call
		eventApivalidation.verifySalesOrder(dataList.get(3),
				DataProviderFactory.getConfig().getValue("salesOrderStatus"),
				DataProviderFactory.getConfig().getValue("orderStatus"), receiptData.get(0),
				DataProviderFactory.getConfig().getValue("postingStatus"));

		// Email validations session confirm message
		mailinator.sessionConfirmationEmailforEvents(dataList, eventName);

		// Email validations registration confirm message
		// Note:- Sometimes API body returning as null
		mailinator.registrationConfirmationEmailforEvents(dataList, eventName);

		if (recording) {
			VideoRecorder.stopRecording();
		}
	}

	@Test(description = "Verify 'Attendees' info after registering for the event", enabled = false, priority = 3)
	public void validate_Attendees(ITestContext context) throws InterruptedException, Throwable {
		util.waitForJavascript(driver, 10000, 5000);
		if (recording) {
			VideoRecorder.startRecording("validate_Attendees");
		}
		events.eventsTab();
		util.waitForJavascript(driver, 90000, 5000);
		String eventName = events.clickCreatedEvent();
		editCloneEvent.clickEventUrl();
		// sometimes Register link is not clicked in AIA application
		eventRegistration.RegisterLink(1);
		util.waitForJavascript(driver, 90000, 5000);
		signInpage.signUp();
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		util.switchToTabs(driver, 1);
		util.navigateToURl(driver, DataProviderFactory.getConfig().getValue("fonteva_sign_in"));
		signInpage.login(dataList.get(5), dataList.get(6));
		util.switchToTabs(driver, 0);
		events.globalSearch(signUpPage.emailaddressdata);
		editCloneEvent.getAIAData();
		events.eventsSearch(eventName);
		editCloneEvent.clickEventUrl();
		// sometimes Register link is not clicked in AIA application
		eventRegistration.RegisterLink(3);
		util.waitForJavascript(driver, 90000, 5000);
		eventRegistration.selectTicketQuantity();
		eventRegistration.clickRegisterButton();
		eventRegistration.rigisterRequiredInfo();
		eventRegistration.clickRegistrationButton();
		eventRegistration.validateRegisterReq();
		eventRegistration.agendaModule();

		// Here we getting receipt data from UI and storing in ArrayList
		ArrayList<Object> receiptData = eventRegistration.checkoutModule();

		util.waitForJavascript(driver, 90000, 5000);

		// Here we validate PDF data
		viewReceipts.viewReceiptValidationsForEvents(receiptData.get(1), receiptData.get(0));

		util.switchToTabs(driver, 0);
		events.eventsTab();
		events.eventsSearch(eventName);
		String eventID = cloneEventpage.getEventId();
		ArrayList<String> afterRegistrationsalesandTotal = events.validateAfterRegistrationData();
		linksInEvents.clickAttendees();

		// Registered attendees count
		linksInEvents.getAttendeesSize();
		context.setAttribute("eventId", eventID);
		context.setAttribute("eventName", eventName);
		context.setAttribute("attendees", afterRegistrationsalesandTotal.get(0));

		// Here we validate Attendees totals using api call
		eventApivalidation.verifyAttendees(context);

		if (recording) {
			VideoRecorder.stopRecording();
		}
	}

	@Test(description = "Verify 'Attendees' info after registering for the event", enabled = false, priority = 4)
	public void validate_SalesAndRegistration(ITestContext context) throws InterruptedException, Throwable {
		util.waitForJavascript(driver, 10000, 5000);
		if (recording) {
			VideoRecorder.startRecording("validate_SalesAndRegistration");
		}
		events.eventsTab();
		util.waitForJavascript(driver, 90000, 5000);
		String eventName = events.clickCreatedEvent();
		events.validateBeforeRegistrationData();
		editCloneEvent.clickEventUrl();
		// sometimes Register link is not clicked in AIA application
		eventRegistration.RegisterLink(1);
		util.waitForJavascript(driver, 90000, 5000);
		signInpage.signUp();
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		util.switchToTabs(driver, 1);
		util.navigateToURl(driver, DataProviderFactory.getConfig().getValue("fonteva_sign_in"));
		signInpage.login(dataList.get(5), dataList.get(6));
		util.switchToTabs(driver, 0);
		events.globalSearch(signUpPage.emailaddressdata);
		editCloneEvent.getAIAData();
		events.eventsSearch(eventName);
		editCloneEvent.clickEventUrl();
		// sometimes Register link is not clicked in AIA application
		eventRegistration.RegisterLink(3);
		util.waitForJavascript(driver, 90000, 5000);
		eventRegistration.selectTicketQuantity();
		eventRegistration.clickRegisterButton();
		eventRegistration.rigisterRequiredInfo();
		eventRegistration.clickRegistrationButton();
		eventRegistration.validateRegisterReq();
		eventRegistration.agendaModule();

		// Here we getting receipt data from UI and storing in ArrayList
		ArrayList<Object> receiptData = eventRegistration.checkoutModule();

		util.waitForJavascript(driver, 90000, 5000);

		// Here we validate PDF data
		viewReceipts.viewReceiptValidationsForEvents(receiptData.get(1), receiptData.get(0));

		util.switchToTabs(driver, 0);
		events.eventsTab();
		events.eventsSearch(eventName);
		String eventID = cloneEventpage.getEventId();
		ArrayList<String> afterRegistrationsalesandTotal = events.validateAfterRegistrationData();
		context.setAttribute("eventId", eventID);
		context.setAttribute("eventName", eventName);
		context.setAttribute("attendees", afterRegistrationsalesandTotal.get(0));
		context.setAttribute("soldtickets", afterRegistrationsalesandTotal.get(1));
		context.setAttribute("remainEvents", afterRegistrationsalesandTotal.get(2));
		context.setAttribute("remainTickets", afterRegistrationsalesandTotal.get(3));

		// Here we validate sales & Registration totals using api call
		eventApivalidation.verifySalesOrderRegistration(context);
		if (recording) {
			VideoRecorder.stopRecording();
		}
	}

	@Test(priority = 5, description = "Edit cloneEvent date and time,", enabled = false)
	public void test_EditSelectDateInCloneEventInfo(ITestContext context) throws InterruptedException, Throwable {
		events.eventsTab();
		events.clickCreatedEvent();
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 90000, 5000);
		editCloneEvent.editCloneEventDateAndTime();
		editCloneEvent.verifyTimeDurationOfEditCloneEventDateAndTime();
		if (recording) {
			VideoRecorder.stopRecording();
		}
	}

	@Test(priority = 6, description = "Validate register timer in Edit cloneEvent info,", enabled = false)
	public void validate_RegistrationTimerCloneEventInfo(ITestContext context) throws InterruptedException, Throwable {
		events.eventsTab();
		events.clickCreatedEvent();
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 90000, 5000);
		editCloneEvent.verifyUserAbleToProvidedataInRegistrationTimerInputBox();
		if (recording) {
			VideoRecorder.stopRecording();
		}
	}
	
	@Test(priority = 7, description = "Verify Delation Existing Event", enabled = false)
	public void validate_DeleteExistingEvent(ITestContext context) throws InterruptedException, Throwable {
		events.eventsTab();
		events.clickCreatedEvent();
		events.clickCreatedEvent();
		events.clickActionsInEvents("Delete");
		events.validateDeletePopup();
		events.clickActionsInDeletePopup("Delete");
		events.validateToasteMessage();
	}

//	@AfterMethod(alwaysRun = true)
//	public void teardown() {
//		BrowserSetup.closeBrowser(driver);
//
//	}

}
