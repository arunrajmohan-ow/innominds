package org.aia.testcases.events;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.events.EditCloneEvent;
import org.aia.pages.events.EventRegistration;
import org.aia.pages.events.NewCloneEvents;
import org.aia.pages.membership.CheckYourEmailPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpPage;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.junit.BeforeClass;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClone_Events extends BaseClass {
	
	NewCloneEvents cloneEventpage;
	ConfigDataProvider testData;
	EditCloneEvent editCloneEvent;
	SignUpPage signUpPage;
	MailinatorAPI  mailinator;
	SignInPage signInpage;
	CheckYourEmailPage closeButtnPage;
	EventRegistration eventRegistration;
	
	
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
		Logging.configure();
	}
	
	
	@Test(priority= 1, description="Create New CloneEvent enter event name, enter date, select event category and event search click clone button", enabled = true)

	public void test_CreateCloneEvent() throws Throwable {
		cloneEventpage.newCloneEvent(testData.testDataProvider().getProperty("eventCategory"));
		cloneEventpage.verifyCloneEventSegmentCheckBoxs();
	}
	
	@Test(priority= 2, description="Edit cloneEven info, tickets,", enabled = false)
	public void test_EditCloneEvent() throws InterruptedException, Throwable {
		String cardNumber = testData.testDataProvider().getProperty("CREDIT_CARD_NUMBER");
		String cardExpMonth = testData.testDataProvider().getProperty("CREDIT_CARD_EXP_MONTH");
		String cardExpYear = testData.testDataProvider().getProperty("CREDIT_CARD_EXP_YEAR");
		
		cloneEventpage.newCloneEvent(testData.testDataProvider().getProperty("eventCategory"));
		cloneEventpage.verifyCloneEventSegmentCheckBoxs();
		String eventName = cloneEventpage.newEvent;
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
		util.navigateToURl(driver, "https://account-dev.aia.org/signin");
		signInpage.login(dataList.get(5), dataList.get(6));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		editCloneEvent.clickEventUrl(3);
		eventRegistration.selectTicketQuantity();
		eventRegistration.clickRegisterButton();
		eventRegistration.rigisterRequiredInfo();
		eventRegistration.clickRegistrationButton();
		eventRegistration.clickRegistrationButton();
		eventRegistration.agendaModule();
		eventRegistration.checkoutModule(cardNumber, cardExpMonth, cardExpYear);
	}

}
