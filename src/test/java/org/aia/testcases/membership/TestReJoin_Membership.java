package org.aia.testcases.membership;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.api.membership.JoinAPIValidation;
import org.aia.pages.membership.CheckYourEmailPage;
import org.aia.pages.membership.FinalPageThankYou;
import org.aia.pages.membership.OrderSummaryPage;
import org.aia.pages.membership.PaymentInformation;
import org.aia.pages.membership.PrimaryInformationPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpPage;
import org.aia.pages.membership.SignUpSuccess;
import org.aia.pages.membership.TellusAboutYourselfPage;
import org.aia.utility.BrowserSetup;
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
	PrimaryInformationPage primaryInfoPage;
	OrderSummaryPage orderSummaryPage;
	PaymentInformation paymentInfoPage;
	FinalPageThankYou finalPage;
	JoinAPIValidation apiValidation;
	TellusAboutYourselfPage tellAbtPage;
	ReJoinSandBoxFonteva reJoinfontevaPage;
	RejoinPage reJoinUIPage;
	ReJoinAPIValidation reJoinAPIValidation;
	public String inbox;
	static Logger log = Logger.getLogger(TestReJoin_Membership.class);

	@BeforeMethod
	public void setUp() throws Exception {
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("devstagingurl_membership"));
		util = new Utility(driver, 30);
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
		reJoinfontevaPage = PageFactory.initElements(driver, ReJoinSandBoxFonteva.class);
		reJoinUIPage = PageFactory.initElements(driver, RejoinPage.class);
		reJoinAPIValidation = PageFactory.initElements(driver, ReJoinAPIValidation.class);
		Logging.configure();
	}

	/**
	 * Bug found in this script bug id is FM-336 FM-337
	 */
	@Test
	public void validateReJoin() throws Exception {
		// User creating is starting
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.gotoMembershipSignUpPage(dataList.get(5));
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		signInpage.login(dataList.get(5), dataList.get(6));
		primaryInfoPage.enterPrimaryInfo_pac("activeUSLicense", "None Selected", "Welcome to AIA");
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
		driver.get(DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl")+ sessionID.getSessionID());
		reJoinfontevaPage.setStatusAsTerminate(dataList.get(0) + " " + dataList.get(1));
		Logging.logger.info("Set status as Terminated");

		// Navigate membership portal
		driver.get(DataProviderFactory.getConfig().getValue("membership_app_endpoint"));

		// Enter Email in membership page
		reJoinUIPage.reJoinMembership(dataList.get(5));
		// Enter detail in primary info page
		primaryInfoPage.enterPrimaryInfo("activeUSLicense", "Non profit", "Restart your membership");
		// Confirm terms and proceed for payment.
		orderSummaryPage.confirmTerms("activeUSLicense");
		orderSummaryPage.clickonPayNow();
		paymentInfoPage.clickOnCreditCard();
		paymentInfoPage.paymentDetails("activeUSLicense");
		//tellAbtPage.tellUsForRejoin();
		// Fetch the details on receipt & add details in receiptData array list.
		finalPage.verifyThankYouMessage();
		finalPage.getFinalReceiptData();
		ArrayList<Object> receiptData2 = finalPage.getFinalReceiptData();
		// Validate Membership Rejoin - Fonteva API validations
		reJoinAPIValidation.verifyReJoinMemebership(dataList.get(3),
				DataProviderFactory.getConfig().getValue("termEndDate"), receiptData.get(2),
				DataProviderFactory.getConfig().getValue("type_aia_national"), "Architect", "Non profit");
		// Validate sales order
		reJoinAPIValidation.verifySalesOrder(DataProviderFactory.getConfig().getValue("salesOrderStatus"),
				DataProviderFactory.getConfig().getValue("orderStatus"), receiptData2.get(2),
				DataProviderFactory.getConfig().getValue("postingStatus"));
		// Validate Receipt Details
		reJoinAPIValidation.verifyReciptDetails(receiptData.get(0), receiptData.get(2));

	}

	@AfterMethod
	public void teardown() {
		BrowserSetup.closeBrowser(driver);
	}
}
