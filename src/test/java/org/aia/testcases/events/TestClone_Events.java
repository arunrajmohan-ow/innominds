package org.aia.testcases.events;

import java.util.ArrayList;
import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.events.EventAPIValidations;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.events.EditCloneEvent;
import org.aia.pages.events.EventRegistration;
import org.aia.pages.events.NewCloneEvents;
import org.aia.pages.events.ViewRecipts;
import org.aia.pages.membership.CheckYourEmailPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpPage;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.FailedTestRun;
import org.aia.utility.Logging;
import org.aia.utility.RetryListenerClass;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.aia.utility.GenerateReportsListener.class)
public class TestClone_Events extends BaseClass {

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

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		testData = new ConfigDataProvider();
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				testData.getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		cloneEventpage = PageFactory.initElements(driver, NewCloneEvents.class);
		editCloneEvent = PageFactory.initElements(driver, EditCloneEvent.class);
		signInpage = PageFactory.initElements(driver, SignInPage.class);
		signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
		closeButtnPage = PageFactory.initElements(driver, CheckYourEmailPage.class);
		eventRegistration = PageFactory.initElements(driver, EventRegistration.class);
		viewReceipts = PageFactory.initElements(driver, ViewRecipts.class);
		eventApivalidation = PageFactory.initElements(driver, EventAPIValidations.class);
		Logging.configure();
	}

	@Test(priority = 1, description = "Create New CloneEvent enter event name, enter date, select event category and event search click clone button", enabled = false)

	public void test_CreateCloneEvent(ITestContext context) throws Throwable {
		cloneEventpage.newCloneEvent(testData.testDataProvider().getProperty("eventCategory"));
		cloneEventpage.verifyCloneEventSegmentCheckBoxs();
		context.setAttribute("eventId", cloneEventpage.eventId);
		context.setAttribute("eventName", cloneEventpage.eName);
		context.setAttribute("startDate", cloneEventpage.startDate);
		context.setAttribute("eventCategory", testData.testDataProvider().getProperty("eventCategory"));
		eventApivalidation.verifyEvent(context);
	}

	@Test(priority = 2, description = "Edit cloneEven info, tickets,", enabled = true)
	public void test_EditCloneEvent(ITestContext context) throws InterruptedException, Throwable {

		test_CreateCloneEvent(context);

		String eventName = cloneEventpage.newEvent;
		editCloneEvent.eventsTab();
		editCloneEvent.eventsSearch(eventName);
		editCloneEvent.clickEditButton();
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
		editCloneEvent.clickEventUrl(1);
		signInpage.signUp();
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3), 1);
		util.navigateToURl(driver, DataProviderFactory.getConfig().getValue("fonteva_sign_in"));
		signInpage.login(dataList.get(5), dataList.get(6));
		util.switchToTabs(driver, 0);
		editCloneEvent.globalSearch(signUpPage.emailaddressdata);
		editCloneEvent.getAIAData();
		editCloneEvent.eventsSearch(eventName);
		editCloneEvent.clickEventUrl(3);
		eventRegistration.selectTicketQuantity();
		eventRegistration.clickRegisterButton();
		eventRegistration.rigisterRequiredInfo();
		eventRegistration.clickRegistrationButton();
		eventRegistration.validateRegisterReq();
		eventRegistration.agendaModule();
		
		//Here we getting receipt data from UI and storing in ArrayList
		ArrayList<Object> receiptData= eventRegistration.checkoutModule();
		
		//Here we validate pdf data to fonteva date
		viewReceipts.getReceiptBody(receiptData.get(1), receiptData.get(0));
		
		//Here we validate the receipt using api call
		eventApivalidation.verifyReciptDetails(dataList.get(3), receiptData.get(1), receiptData.get(0));
		
		//Salse order using api call
		eventApivalidation.verifySalesOrder(dataList.get(3), DataProviderFactory.getConfig().getValue("salesOrderStatus"), 
				DataProviderFactory.getConfig().getValue("orderStatus"), 
				DataProviderFactory.getConfig().getValue("postingStatus"));
		
		//Email validations
		mailinator.welcomeAIAEmailLink(dataList, receiptData);
		
	}

}
