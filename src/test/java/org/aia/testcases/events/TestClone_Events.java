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
import org.aia.pages.membership.CheckYourEmailPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpPage;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.VideoRecorder;
import org.checkerframework.common.reflection.qual.GetMethod;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
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
		Logging.configure();
	}

	@Test(priority = 1, description = "Create New CloneEvent enter event name, enter date, select event category and event search click clone button", enabled = true)

	public void test_CreateCloneEvent(ITestContext context) throws Throwable {
		
	    VideoRecorder.startRecording("test_CreateCloneEvent");
		
		events.clickEventsModule();
		util.waitForJavascript(driver, 90000, 5000);
		events.newButtonInEvents();
		events.validateHeaderCloneEvent();
		cloneEventpage.newCloneOnExistingEvent(testData.testDataProvider().getProperty("eventCategory"));
		util.waitForJavascript(driver, 90000, 5000);
		cloneEventpage.verifyCloneEventSegmentCheckBoxs();
		context.setAttribute("eventId", cloneEventpage.eventId);
		context.setAttribute("eventName", cloneEventpage.eName);
		context.setAttribute("startDate", cloneEventpage.startDate);
		context.setAttribute("eventCategory", testData.testDataProvider().getProperty("eventCategory"));
		//Create Clone event validation
		eventApivalidation.verifyEvent(context);
	}

	@Test(priority = 2, description = "Verify Price modify for an existing Event", enabled = true)
	public void test_EditPriceInCloneEvent(ITestContext context) throws InterruptedException, Throwable {

		test_CreateCloneEvent(context);
		VideoRecorder.startRecording("test_EditPriceInCloneEvent");
		String eventName = cloneEventpage.newEvent;
		events.clickEventsModule();
		events.eventsSearch(eventName);
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 90000, 5000);
		editCloneEvent.editEventInfo(eventName);
		editCloneEvent.editEventTicket(true);
		editCloneEvent.editEventInvitation();
		editCloneEvent.editEventVenues();
		editCloneEvent.editEventAccessPermissions();
		editCloneEvent.editEventSpeakers();
		editCloneEvent.editEventAgenda();
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
		//Note:- Sometimes API body returning as null
		mailinator.registrationConfirmationEmailforEvents(dataList, eventName);
	}

	@Test(priority = 3, description = "Verify 'Attendees' info after registering for the event", enabled = true)
	public void validate_Attendees(ITestContext context) throws InterruptedException, Throwable {

		test_CreateCloneEvent(context);
		VideoRecorder.startRecording("validate_Attendees");
		String eventName = cloneEventpage.newEvent;
		events.clickEventsModule();
		util.waitForJavascript(driver, 90000, 5000);
		events.eventsSearch(eventName);
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 90000, 5000);
		editCloneEvent.editEventInfo(eventName);
		editCloneEvent.editEventTicket(true);
		editCloneEvent.editEventInvitation();
		editCloneEvent.editEventVenues();
		editCloneEvent.editEventAccessPermissions();
		editCloneEvent.editEventSpeakers();
		editCloneEvent.editEventAgenda();
		editCloneEvent.editEventSponsorPackages();
		editCloneEvent.editEventStatuses();
		editCloneEvent.editEventPages();
		editCloneEvent.saveExitButton();
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
		events.clickEventsModule();
		events.eventsSearch(eventName);
		ArrayList<String> afterRegistrationsalesandTotal = events.validateAfterRegistrationData();
		linksInEvents.clickAttendees();

		// Registered attendees count
		linksInEvents.getAttendeesSize();
		context.setAttribute("attendees", afterRegistrationsalesandTotal.get(0));

		// Here we validate Attendees totals using api call
		eventApivalidation.verifyAttendees(context);
		
		VideoRecorder.stopRecording();
	}

	@Test(priority = 4, description = "Verify 'Attendees' info after registering for the event", enabled = true)
	public void validate_SalesAndRegistration(ITestContext context) throws InterruptedException, Throwable {

		test_CreateCloneEvent(context);
		
        VideoRecorder.startRecording("validate_SalesAndRegistration");
		String eventName = cloneEventpage.newEvent;
		events.clickEventsModule();
		util.waitForJavascript(driver, 90000, 5000);
		events.eventsSearch(eventName);
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 90000, 5000);
		editCloneEvent.editEventInfo(eventName);
		editCloneEvent.editEventTicket(true);
		editCloneEvent.editEventInvitation();
		editCloneEvent.editEventVenues();
		editCloneEvent.editEventAccessPermissions();
		editCloneEvent.editEventSpeakers();
		editCloneEvent.editEventAgenda();
		editCloneEvent.editEventSponsorPackages();
		editCloneEvent.editEventStatuses();
		editCloneEvent.editEventPages();
		editCloneEvent.saveExitButton();
		util.waitForJavascript(driver, 90000, 5000);
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
		events.clickEventsModule();
		events.eventsSearch(eventName);
		ArrayList<String> afterRegistrationsalesandTotal = events.validateAfterRegistrationData();
		context.setAttribute("attendees", afterRegistrationsalesandTotal.get(0));
		context.setAttribute("soldtickets", afterRegistrationsalesandTotal.get(1));
		context.setAttribute("remainEvents", afterRegistrationsalesandTotal.get(2));
		context.setAttribute("remainTickets", afterRegistrationsalesandTotal.get(3));

		// Here we validate sales & Registration totals using api call
		eventApivalidation.verifySalesOrderRegistration(context);
		VideoRecorder.stopRecording();
	}

}
