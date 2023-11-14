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

	@Test(priority = 1, description = "Create New CloneEvent enter event name, enter date, select event category and event search click clone button", enabled = true)

	public void test_CreateCloneEvent(ITestContext context) throws Throwable {
		Logging.logger.info("================================ test_CreateCloneEvent wls template started==========================");
		try {
			if (recording) {
				VideoRecorder.startRecording("test_CreateCloneEvent");
			}
			String exitEvent = testData.testDataProvider().getProperty("cloneEvent");
			events.eventsTab();
			util.waitForJavascript(driver, 90000, 5000);
			events.newButtonInEvents();
			events.validateHeaderCloneEvent();
			String eventName  = cloneEventpage.enterEventName("Wls");
			cloneEventpage.enterStartDate();
			cloneEventpage.selectEventCategory(testData.testDataProvider().getProperty("eventCategory"));
			cloneEventpage.CloneEventSearchTemplate(exitEvent);
			cloneEventpage.eventCloneButton();
			cloneEventpage.verifyCloneEventSegmentCheckBoxs();
			cloneEventpage.eventFinishCloneButton();
			cloneEventpage.validateEventHeader();
			cloneEventpage.getEventId();
			util.waitForJavascript(driver, 90000, 5000);
			cloneEventpage.validateEventHeader();
			cloneEventpage.getEventId();

			context.setAttribute("eventId", cloneEventpage.eventId);
			context.setAttribute("eventName", eventName);
			context.setAttribute("startDate", cloneEventpage.startDate);
			context.setAttribute("eventCategory", testData.testDataProvider().getProperty("eventCategory"));
			// Create Clone event validation
			eventApivalidation.verifyEvent(context);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		} catch (Throwable e) {
			throw new AssertionError(e.getMessage());
		}
		
	}

	@Test(priority = 2, description = "Verify Price modify for an existing Event", enabled = true)
	public void test_EditPriceInCloneEvent(ITestContext context) throws InterruptedException, Throwable {
		Logging.logger.info("================================test_EditPriceInCloneEvent wls template started==========================");
		try {
			if (recording) {
				VideoRecorder.startRecording("test_EditPriceInCloneEvent");
			}
			events.eventsTab();
			String eventName = events.clickCreatedEvent("RecentEvents");
			editCloneEvent.clickEditButton();
			util.waitForJavascript(driver, 90000, 5000);
			String eventTimeZone = testData.testDataProvider().getProperty("eventTimeZone");
			String registrationTime = testData.testDataProvider().getProperty("registrationTimer");
			String startTime = testData.testDataProvider().getProperty("eventStartTime");
			String endTime = testData.testDataProvider().getProperty("eventEndTime");
			editCloneEvent.editEventInfo(eventName, startTime, endTime, registrationTime, eventTimeZone);
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
			// String scheduleName = editCloneEvent.getSceduleItemsInAgenda();
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
			util.waitForJavascript(driver, 20000, 2000);
			String aiaNumber = eventRegistration.getAIAData();
			util.switchToTabs(driver, 0);
			cloneEventpage.getEventId();
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
			eventRegistration.paymentDataIncheckoutModule();

			eventRegistration.biilingaddressInCheckoutModule();

			eventRegistration.paymentProcessButton();

			ArrayList<Object> receiptData = eventRegistration.clickReceiptInChecout();

			util.waitForJavascript(driver, 90000, 5000);

			// Here we validate PDF data
			String paymentType = testData.testDataProvider().getProperty("PaymentType");
			String paymentMethodDescr = testData.testDataProvider().getProperty("PaymentMethodDescription");
			viewReceipts.viewReceiptValidationsForEvents(receiptData.get(1), receiptData.get(0), paymentType,
					paymentMethodDescr, aiaNumber);

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
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		} catch (Throwable e) {
			throw new AssertionError(e.getMessage());
		}
			}

	@Test(priority = 3, description = "Verify 'Attendees' info after registering for the event", enabled = true)
	public void validate_Attendees(ITestContext context) throws InterruptedException, Throwable {
		Logging.logger.info("================================validate_Attendees wls template started==========================");
		try {
			if (recording) {
				VideoRecorder.startRecording("validate_Attendees");
			}
			util.waitForJavascript(driver, 10000, 5000);
			if (recording) {
				VideoRecorder.startRecording("validate_Attendees");
			}
			events.eventsTab();
			util.waitForJavascript(driver, 90000, 5000);
			String eventName = events.clickCreatedEvent("RecentEvents");
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
			util.waitForJavascript(driver, 20000, 2000);
			String aiaNumber = eventRegistration.getAIAData();
			util.switchToTabs(driver, 0);
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
			eventRegistration.paymentDataIncheckoutModule();

			eventRegistration.biilingaddressInCheckoutModule();

			eventRegistration.paymentProcessButton();

			ArrayList<Object> receiptData = eventRegistration.clickReceiptInChecout();

			util.waitForJavascript(driver, 90000, 5000);

			// Here we validate PDF data
			String paymentType = testData.testDataProvider().getProperty("PaymentType");
			String paymentMethodDescr = testData.testDataProvider().getProperty("PaymentMethodDescription");
			viewReceipts.viewReceiptValidationsForEvents(receiptData.get(1), receiptData.get(0), paymentType,
					paymentMethodDescr, aiaNumber);

			util.switchToTabs(driver, 0);
			events.eventsTab();
			events.eventsSearch(eventName);
			String eventID = cloneEventpage.getEventId();
			ArrayList<String> afterRegistrationsalesandTotal = events.validateAfterRegistrationData();
			linksInEvents.clickAttendees();

			// Registered attendees count
			linksInEvents.getAttendeesSize();
			context.setAttribute("eventName", eventName);
			context.setAttribute("attendees", afterRegistrationsalesandTotal.get(0));
			context.setAttribute("eventId", eventID);

			// Here we validate Attendees totals using api call
			eventApivalidation.verifyAttendees(context);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		} catch (Throwable e) {
			throw new AssertionError(e.getMessage());
		}
			}

	@Test(priority = 4, description = "Verify 'Attendees' info after registering for the event", enabled = true)
	public void validate_SalesAndRegistration(ITestContext context) throws InterruptedException, Throwable {
		Logging.logger.info("================================validate_SalesAndRegistration wls template started==========================");
		try {
			if (recording) {
				VideoRecorder.startRecording("validate_Attendees");
			}
			util.waitForJavascript(driver, 10000, 5000);
			if (recording) {
				VideoRecorder.startRecording("validate_SalesAndRegistration");
			}
			events.eventsTab();
			util.waitForJavascript(driver, 90000, 5000);
			String eventName = events.clickCreatedEvent("RecentEvents");
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
			util.waitForJavascript(driver, 20000, 2000);
			String aiaNumber = eventRegistration.getAIAData();
			util.switchToTabs(driver, 0);
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
			eventRegistration.paymentDataIncheckoutModule();

			eventRegistration.biilingaddressInCheckoutModule();

			eventRegistration.paymentProcessButton();

			ArrayList<Object> receiptData = eventRegistration.clickReceiptInChecout();

			util.waitForJavascript(driver, 90000, 5000);

			// Here we validate PDF data
			String paymentType = testData.testDataProvider().getProperty("PaymentType");
			String paymentMethodDescr = testData.testDataProvider().getProperty("PaymentMethodDescription");
			viewReceipts.viewReceiptValidationsForEvents(receiptData.get(1), receiptData.get(0), paymentType,
					paymentMethodDescr, aiaNumber);

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
