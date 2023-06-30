package org.aia.testcases.membership;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.api.membership.JoinAPIValidation;
import org.aia.pages.api.membership.ReJoinAPIValidation;
import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.pages.fonteva.membership.Memberships;
import org.aia.pages.membership.CheckYourEmailPage;
import org.aia.pages.membership.FinalPageThankYou;
import org.aia.pages.membership.OrderSummaryPage;
import org.aia.pages.membership.PaymentInformation;
import org.aia.pages.membership.PrimaryInformationPage;
import org.aia.pages.membership.RejoinPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpPage;
import org.aia.pages.membership.SignUpSuccess;
import org.aia.pages.membership.TellusAboutYourselfPage;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestReJoin_Membership extends BaseClass {
	SignUpPage signUpPage;
	SignInPage signInpage;
	CheckYourEmailPage closeButtnPage;
	MailinatorAPI mailinator;
	SignUpSuccess successPage;
	Memberships fontevaPage;
	PrimaryInformationPage primaryInfoPage;
	OrderSummaryPage orderSummaryPage;
	PaymentInformation paymentInfoPage;
	FinalPageThankYou finalPage;
	JoinAPIValidation apiValidation;
	TellusAboutYourselfPage tellAbtPage;
	ReJoinAPIValidation reJoinValidate;
	ContactCreateUser fontevaJoin;
	ReJoinAPIValidation reJoinAPIValidation;
	JoinAPIValidation offlinApiValidation;
	RejoinPage rejoinPage;
	public String inbox;
	static Logger log = Logger.getLogger(TestReJoin_Membership.class);

	@BeforeMethod
	public void setUp() throws Exception {
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("devstagingurl_membership"));
		util = new Utility(driver, 30);
		testData = new ConfigDataProvider();
		fontevaJoin = PageFactory.initElements(driver, ContactCreateUser.class);
		mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
		signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		signInpage = PageFactory.initElements(driver, SignInPage.class);
		closeButtnPage = PageFactory.initElements(driver, CheckYourEmailPage.class);
		successPage = PageFactory.initElements(driver, SignUpSuccess.class);
		apiValidation = PageFactory.initElements(driver, JoinAPIValidation.class);
		primaryInfoPage = PageFactory.initElements(driver, PrimaryInformationPage.class);
		orderSummaryPage = PageFactory.initElements(driver, OrderSummaryPage.class);
		paymentInfoPage = PageFactory.initElements(driver, PaymentInformation.class);
		finalPage = PageFactory.initElements(driver, FinalPageThankYou.class);
		tellAbtPage = PageFactory.initElements(driver, TellusAboutYourselfPage.class);
		reJoinAPIValidation = PageFactory.initElements(driver, ReJoinAPIValidation.class);
		reJoinValidate = PageFactory.initElements(driver, ReJoinAPIValidation.class);
		offlinApiValidation = PageFactory.initElements(driver, JoinAPIValidation.class);
		fontevaPage = PageFactory.initElements(driver, Memberships.class);
		rejoinPage = PageFactory.initElements(driver, RejoinPage.class);
		Logging.configure();
	}

	/**
	 * Bug found in this script bug id is FM-336 FM-337
	 */
	@Test(priority = 1, description = "verify the offline membership rejoin in UI Application", enabled = true)
	public void validateReJoin() throws Exception {
		// User creating is starting
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.gotoMembershipSignUpPage(dataList.get(5));
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		signInpage.login(dataList.get(5), dataList.get(6));
		primaryInfoPage.enterPrimaryInfo("activeUSLicense", "None Selected");
		orderSummaryPage.confirmTerms("activeUSLicense");
		orderSummaryPage.clickonPayNow();
		String aiaNational = paymentInfoPage.paymentDetails("activeUSLicense");
		tellAbtPage.enterTellUsAboutYourSelfdetails("activeUSLicense", "None Selected");
		finalPage.verifyThankYouMessage();
		ArrayList<Object> receiptData = finalPage.getFinalReceiptData();
		receiptData.add(3, aiaNational);
		mailinator.welcomeAIAEmailLink(dataList, receiptData);
		Logging.logger.info("User get created successfully");

		// Navigate to Fonteva app and make record rejoin eligible.
		driver.get(DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		fontevaJoin.selectContact(dataList.get(0) + " " + dataList.get(1));
		fontevaPage.setMembershipStatus(dataList.get(0) + " " + dataList.get(1),
				testData.testDataProvider().getProperty("membershipStatus"));
		fontevaJoin.selectContact(dataList.get(0) + " " + dataList.get(1));
		fontevaPage.expireMembership();
		Logging.logger.info("Set status as Canclled");

		// Navigate membership portal
		driver.get(DataProviderFactory.getConfig().getValue("membership_app_endpoint"));
		// Enter Email in membership page
		rejoinPage.reJoinMembership(dataList.get(5));
		// Enter detail in primary info page
		primaryInfoPage.enterPrimaryInfo("activeUSLicense", "None Selected");
		// Confirm terms and proceed for payment.
		orderSummaryPage.confirmTerms("activeUSLicense");
		orderSummaryPage.clickonPayNow();
		paymentInfoPage.clickOnCreditCard();
		paymentInfoPage.paymentDetails("activeUSLicense");
		tellAbtPage.enterTellUsAboutYourSelfdetails("activeUSLicense", "None Selected");
		// Fetch the details on receipt & add details in receiptData array list.
		finalPage.verifyThankYouMessage();
		ArrayList<Object> receiptData2 = finalPage.getFinalReceiptData();
		// Validate Membership Rejoin - Fonteva API validations
		reJoinValidate.validateReJoinMemebership(dataList.get(3),
				DataProviderFactory.getConfig().getValue("termEndDate"), receiptData.get(2),
				DataProviderFactory.getConfig().getValue("type_aia_national"), "Architect", "Non profit");
		// Validate sales order
		offlinApiValidation.verifySalesOrder(DataProviderFactory.getConfig().getValue("salesOrderStatus"),
				DataProviderFactory.getConfig().getValue("orderStatus"), receiptData2.get(2),
				DataProviderFactory.getConfig().getValue("postingStatus"));
		// Validate Receipt Details
		offlinApiValidation.verifyReciptDetails(receiptData.get(0), receiptData.get(2));

	}

	@AfterMethod
	public void teardown() {
		BrowserSetup.closeBrowser(driver);
	}
}
