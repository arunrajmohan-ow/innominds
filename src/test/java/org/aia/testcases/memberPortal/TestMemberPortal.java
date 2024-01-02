package org.aia.testcases.memberPortal;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.MailinatorCESAPI;
import org.aia.pages.api.ces.FontevaCESTermDateChangeAPI;
import org.aia.pages.api.ces.RenewCESAPIValidation;
import org.aia.pages.api.membership.APIValidationContacts;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.api.membership.JoinAPIValidation;
import org.aia.pages.ces.AdditionalProviderUser;
import org.aia.pages.ces.AdditionalUsers;
import org.aia.pages.ces.CheckOutPageCes;
import org.aia.pages.ces.CloseBtnPageCes;
import org.aia.pages.ces.FontevaCES;
import org.aia.pages.ces.LoginPageCes;
import org.aia.pages.ces.Organization;
import org.aia.pages.ces.PaymentSuccessFullPageCes;
import org.aia.pages.ces.PrimaryPointOfContact;
import org.aia.pages.ces.ProviderStatement;
import org.aia.pages.ces.RenewCESPage;
import org.aia.pages.ces.SecondaryPointOfContact;
import org.aia.pages.ces.SignUpPageCes;
import org.aia.pages.ces.Subscription;
import org.aia.pages.fonteva.ces.CES_ContactPage;
import org.aia.pages.fonteva.memberPortal.MemberPortal;
import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.pages.membership.OrderSummaryPage;
import org.aia.pages.membership.PaymentInformation;
import org.aia.pages.membership.PrimaryInformationPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpSuccess;
import org.aia.testcases.ces.TestJoinPassport_CES;
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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class TestMemberPortal extends BaseClass {
	SignUpPageCes signUpPage;
	SignInPage signInpage;
	CloseBtnPageCes closeButtnPage;
	MailinatorCESAPI mailinator;
	SignUpSuccess successPage;
	PrimaryInformationPage primaryInfoPage;
	OrderSummaryPage orderSummaryPage;
	PaymentInformation paymentInfoPage;
	LoginPageCes loginPageCes;
	PrimaryPointOfContact primarypocPage;
	Organization organizationPage;
	Subscription subscribePage;
	SecondaryPointOfContact secPoc;
	AdditionalUsers additionalUsers;
	AdditionalProviderUser additionalProviderUser;
	ProviderStatement providerStatement;
	CheckOutPageCes checkOutPageCes;
	PaymentSuccessFullPageCes paymntSuccesFullPageCes;
	RenewCESAPIValidation apiValidation;
	FontevaCES fontevaPage;
	CES_ContactPage ces_ContactPage;
	RenewCESPage renew;
	FontevaCESTermDateChangeAPI termDateChangeAPICall;
	MemberPortal memberPotalPage;
	APIValidationContacts contactApiValidation;
	ContactCreateUser fontevaJoin;
	MailinatorAPI malinator;
	JoinAPIValidation offlinApiValidation;
	public ExtentReports extent;
	public ExtentTest extentTest;
	final static Logger logger = Logger.getLogger(TestMemberPortal.class);

	@BeforeMethod(alwaysRun=true)
	public void setUp() throws Exception {
		sessionID=new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl")+sessionID.getSessionID());
		util = new Utility(driver, 30);
		testData = new ConfigDataProvider();
		fontevaJoin = PageFactory.initElements(driver, ContactCreateUser.class);
		malinator = PageFactory.initElements(driver, MailinatorAPI.class);
		offlinApiValidation = PageFactory.initElements(driver, JoinAPIValidation.class);
		// Configure Log4j to perform error logging
		signUpPage = PageFactory.initElements(driver, SignUpPageCes.class);
		signInpage = PageFactory.initElements(driver, SignInPage.class);
		closeButtnPage = PageFactory.initElements(driver, CloseBtnPageCes.class);
		mailinator = PageFactory.initElements(driver, MailinatorCESAPI.class);
		successPage = PageFactory.initElements(driver, SignUpSuccess.class);
		loginPageCes = PageFactory.initElements(driver, LoginPageCes.class);
		primarypocPage = PageFactory.initElements(driver, PrimaryPointOfContact.class);
		organizationPage = PageFactory.initElements(driver, Organization.class);
		subscribePage = PageFactory.initElements(driver, Subscription.class);
		secPoc = PageFactory.initElements(driver, SecondaryPointOfContact.class);
		additionalUsers = PageFactory.initElements(driver, AdditionalUsers.class);
		additionalProviderUser = PageFactory.initElements(driver, AdditionalProviderUser.class);
		providerStatement = PageFactory.initElements(driver, ProviderStatement.class);
		checkOutPageCes = PageFactory.initElements(driver, CheckOutPageCes.class);
		paymntSuccesFullPageCes = PageFactory.initElements(driver, PaymentSuccessFullPageCes.class);
		apiValidation = PageFactory.initElements(driver, RenewCESAPIValidation.class);
		ces_ContactPage = PageFactory.initElements(driver, CES_ContactPage.class);
		fontevaPage = PageFactory.initElements(driver, FontevaCES.class);
		renew = PageFactory.initElements(driver, RenewCESPage.class);
		memberPotalPage = PageFactory.initElements(driver, MemberPortal.class);
		termDateChangeAPICall = PageFactory.initElements(driver, FontevaCESTermDateChangeAPI.class);
		contactApiValidation = PageFactory.initElements(driver, APIValidationContacts.class);
		Logging.configure();
	}

	/**
	 * @throws InterruptedException
	 */
	
	@Test(priority = 1, description = " FM- 319 Contact Information page verification", enabled = true)
	public void contactpageVerification() throws InterruptedException {

		// driver.get(DataProviderFactory.getConfig().getValue("fonteva_endpoint"));
		String prefix = "Dr.";
		String suffix = "Sr.";
		ArrayList<String> dataList = fontevaJoin.userData();
		//fontevaJoin.pointOffset(); 
		fontevaJoin.createUserInFonteva();
		fontevaJoin.joinCreatedUser(testData.testDataProvider().getProperty("membershipType"),
				testData.testDataProvider().getProperty("selection"));
		fontevaJoin.enterLicenseDetail();
		fontevaJoin.createSalesOrder(testData.testDataProvider().getProperty("paymentMethod"));
		fontevaJoin.applyPayment(dataList.get(5));
		ArrayList<Object> data =fontevaJoin.getPaymentReceiptData();
		//Validation of Thank you massage in email inbox after register.
		malinator.thankYouEmailforOfflineJoin(dataList.get(2));
		memberPotalPage.navigateToMemberPortal("AIA Customer");
		//fontevaPage.verifyFieldsMemberPortal("AIA Customer");
		ces_ContactPage.selectExpAsUserOpt2();
		memberPotalPage.validateContactInformationpage(dataList.get(0), dataList.get(1), dataList.get(2), "United States");
	}
	
	@Test(priority = 2, description = " FM- 324 Membership page verification", enabled = true)
	public void verifyMembershipPage() throws InterruptedException {

	;
		ArrayList<String> dataList = fontevaJoin.userData(); 
		fontevaJoin.createUserInFonteva();
		fontevaJoin.joinCreatedUser(testData.testDataProvider().getProperty("membershipType"),
				testData.testDataProvider().getProperty("selection"));
		fontevaJoin.enterLicenseDetail();
		fontevaJoin.createSalesOrder(testData.testDataProvider().getProperty("paymentMethod"));
		fontevaJoin.applyPayment(dataList.get(5));
		ArrayList<Object> data =fontevaJoin.getPaymentReceiptData();
		malinator.thankYouEmailforOfflineJoin(dataList.get(2));
		memberPotalPage.navigateToMemberPortal("AIA Customer");
		ces_ContactPage.selectExpAsUserOpt2();
		memberPotalPage.validateMembershipFields();
	}
		
	@Test(priority = 3, description = " FM- 325 Interest Areas page verification", enabled = true)
	public void interestAreaPageVerification() throws InterruptedException {

		
		ArrayList<String> dataList = fontevaJoin.userData();
		fontevaJoin.createUserInFonteva();
		fontevaJoin.joinCreatedUser(testData.testDataProvider().getProperty("membershipType"),
				testData.testDataProvider().getProperty("selection"));
		fontevaJoin.enterLicenseDetail();
		fontevaJoin.createSalesOrder(testData.testDataProvider().getProperty("paymentMethod"));
		fontevaJoin.applyPayment(dataList.get(5));
		ArrayList<Object> data =fontevaJoin.getPaymentReceiptData();
		malinator.thankYouEmailforOfflineJoin(dataList.get(2));
		memberPotalPage.navigateToMemberPortal("AIA Customer");
		ces_ContactPage.selectExpAsUserOpt2();
		memberPotalPage.validateInterestPageFields();
	}
	
	@Test(priority = 4, description = " FM- 326 Demographics page verification(My Account)", enabled = true)
	public void demographicsPageVerification() throws InterruptedException {

		
		ArrayList<String> dataList = fontevaJoin.userData();
		fontevaJoin.createUserInFonteva();
		fontevaJoin.joinCreatedUser(testData.testDataProvider().getProperty("membershipType"),
				testData.testDataProvider().getProperty("selection"));
		fontevaJoin.enterLicenseDetail();
		fontevaJoin.createSalesOrder(testData.testDataProvider().getProperty("paymentMethod"));
		fontevaJoin.applyPayment(dataList.get(5));
		ArrayList<Object> data =fontevaJoin.getPaymentReceiptData();
		malinator.thankYouEmailforOfflineJoin(dataList.get(2));
		memberPotalPage.navigateToMemberPortal("AIA Customer");
		ces_ContactPage.selectExpAsUserOpt2();
		memberPotalPage.verifyDemographicsFields();
	}
	
	
	

	@Test(priority = 5, description = " FM- 320 Communication Preferences page verification(My Account)", enabled = true)
	public void communicationPreferencesVerification() throws InterruptedException {

		
		ArrayList<String> dataList = fontevaJoin.userData();
		fontevaJoin.createUserInFonteva();
		fontevaJoin.joinCreatedUser(testData.testDataProvider().getProperty("membershipType"),
				testData.testDataProvider().getProperty("selection"));
		fontevaJoin.enterLicenseDetail();
		fontevaJoin.createSalesOrder(testData.testDataProvider().getProperty("paymentMethod"));
		fontevaJoin.applyPayment(dataList.get(5));
		ArrayList<Object> data =fontevaJoin.getPaymentReceiptData();
		malinator.thankYouEmailforOfflineJoin(dataList.get(2));
		memberPotalPage.navigateToMemberPortal("AIA Customer");
		ces_ContactPage.selectExpAsUserOpt2();
		memberPotalPage.verifycommunicationPreferences();
	}
	
	}
