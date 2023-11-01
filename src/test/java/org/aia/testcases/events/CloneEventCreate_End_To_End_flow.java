package org.aia.testcases.events;
import java.util.ArrayList;
import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.events.EventAPIValidations;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.events.EventRegistration;
import org.aia.pages.events.ViewRecipts;
import org.aia.pages.fonteva.events.AgendaModule;
import org.aia.pages.fonteva.events.EventInfoModule;
import org.aia.pages.fonteva.events.Events;
import org.aia.pages.fonteva.events.NewCloneEvents;
import org.aia.pages.fonteva.events.QuickLinksInEvents;
import org.aia.pages.fonteva.events.SpeakersModule;
import org.aia.pages.fonteva.events.StatusesModule;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.aia.utility.GenerateReportsListener.class)
public class CloneEventCreate_End_To_End_flow extends BaseClass{
	Events events;
	NewCloneEvents cloneEventpage;
	ConfigDataProvider testData;
	EventInfoModule editCloneEvent;
	SignUpPage signUpPage;
	MailinatorAPI mailinator;
	SignInPage signInpage;
	CheckYourEmailPage closeButtnPage;
	EventRegistration eventRegistration;
	ViewRecipts viewReceipts;
	EventAPIValidations eventApivalidation;
	QuickLinksInEvents linksInEvents;
	TicketModule ticketModule;
	SpeakersModule speakersModule;
	AgendaModule agendaModule;
	StatusesModule statusModule;
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
		signInpage = PageFactory.initElements(driver, SignInPage.class);
		signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		linksInEvents = PageFactory.initElements(driver, QuickLinksInEvents.class);
		mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
		closeButtnPage = PageFactory.initElements(driver, CheckYourEmailPage.class);
		eventRegistration = PageFactory.initElements(driver, EventRegistration.class);
		viewReceipts = PageFactory.initElements(driver, ViewRecipts.class);
		eventApivalidation = PageFactory.initElements(driver, EventAPIValidations.class);
		ticketModule = PageFactory.initElements(driver, TicketModule.class);
		speakersModule= PageFactory.initElements(driver, SpeakersModule.class);
		agendaModule= PageFactory.initElements(driver, AgendaModule.class);
		statusModule= PageFactory.initElements(driver, StatusesModule.class);
		recording = Boolean.parseBoolean(testData.testDataProvider().getProperty("videoRecording"));
		Logging.configure();
	}
	
	@Test(description = "Create a Valid Clone event (End to End Process)", enabled = true, priority = 4)
	public void CreateCloneEvent_End_To_End_Flow(ITestContext context) throws InterruptedException, Throwable {
		
		if (recording) {
			VideoRecorder.startRecording("CreateCloneEvent_End_To_End_Flow");
		}
		String exitEvent = testData.testDataProvider().getProperty("cloneEventTemplate");
		events.eventsTab();
		events.eventsTab();
		util.waitForJavascript(driver, 30000, 5000);
		events.newButtonInEvents();
		events.validateHeaderCloneEvent();
		cloneEventpage.validateCloneOnExistingRadioButton();
		cloneEventpage.enterEventName("Medium");
		cloneEventpage.enterStartDate();
		cloneEventpage.selectEventCategory(testData.testDataProvider().getProperty("eventCategory"));
		cloneEventpage.CloneEventSearchTemplate(exitEvent);
		cloneEventpage.eventCloneButton();
		cloneEventpage.verifyCloneEventSegmentCheckBoxs();
		cloneEventpage.eventFinishCloneButton();
		util.waitForJavascript(driver, 20000, 5000);
		cloneEventpage.validateEventHeader();
		cloneEventpage.getEventId();
		util.waitForJavascript(driver, 90000, 5000);
		context.setAttribute("eventId", cloneEventpage.eventId);
		context.setAttribute("eventName", cloneEventpage.eName);
		context.setAttribute("startDate", cloneEventpage.startDate);
		context.setAttribute("eventCategory", testData.testDataProvider().getProperty("eventCategory"));
		// Create Clone event validation
		eventApivalidation.verifyEvent(context);
		events.eventsTab();
		util.waitForJavascript(driver, 20000, 5000);
		String eventName = events.clickCreatedEvent();
		editCloneEvent.clickEditButton();
		util.waitForJavascript(driver, 30000, 5000);
		String eventTimeZone = testData.testDataProvider().getProperty("eventTimeZone");
		String registrationTime = testData.testDataProvider().getProperty("registrationTimer");
		String startTime = testData.testDataProvider().getProperty("eventStartTimeInMediumTemplate");
		String endTime = testData.testDataProvider().getProperty("eventEndTimeInMediumTemplate");
		//Event info
		editCloneEvent.editEventInfo(eventName, startTime, endTime, registrationTime, eventTimeZone);
		// tickets tab
		ticketModule.eventTicketsTab();
		ticketModule.validateEventTicketSalesStartDate();
		ticketModule.editEventTicket(true);
		ticketModule.validateEditTicketTypeHeader();
		ticketModule.enterPriceInCreateTicketType();
		ticketModule.saveAndContinueButtonInTicketType();
		// speakers tab
		speakersModule.eventSpeakersTab();
		speakersModule.clickNewSpeaker();
		speakersModule.enterSpeakerName();
		speakersModule.contactRecordsInNewSpeakerPopup();
		speakersModule.featuredRadioButton();
		speakersModule.enterTitleInSpeaker();
		speakersModule.enterCompanyInSpeaker();
		speakersModule.selectStatusInSpeakers();
		speakersModule.speakerPhotoUrlBrowser();
		speakersModule.cropImageButtonsInSpeaker();
		speakersModule.speakerButtonsInnewSpeakerPopup();
		
		//agenda tab
		agendaModule.clickEventAgenda();
		
		
		
		
		
		
		util.waitForJavascript(driver, 30000, 5000);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		editCloneEvent.clickStatusDropDown();
		editCloneEvent.selectActiveStatus();
		editCloneEvent.saveExitButton();
		util.waitForJavascript(driver, 30000, 5000);
		editCloneEvent.clickEventUrl();
		util.waitForJavascript(driver, 30000, 5000);
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
		util.waitForJavascript(driver, 30000, 5000);
		eventRegistration.validateFirstNameInRegistartion();
		eventRegistration.validateLastNameInRegistartion();
		eventRegistration.ValidateEmailInRegistartion();
		eventRegistration.singleticketRegistratioButton();
		eventRegistration.agendaModule();

		// Here we getting receipt data from UI and storing in ArrayList
		eventRegistration.paymentDataIncheckoutModule();

		eventRegistration.biilingaddressInCheckoutModule();

		eventRegistration.paymentProcessButton();

		ArrayList<Object> receiptData = eventRegistration.clickReceiptInChecout();

		util.waitForJavascript(driver, 90000, 5000);

		// Here we validate PDF data
		String paymentType = testData.testDataProvider().getProperty("payMentTypeInMediumTemplate");
		String paymentMethodDescr = testData.testDataProvider().getProperty("PaymentMethodDescriptionInMediumTemplate");
		viewReceipts.viewReceiptValidationsForEvents(receiptData.get(1), receiptData.get(0), paymentType,
				paymentMethodDescr);

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
}