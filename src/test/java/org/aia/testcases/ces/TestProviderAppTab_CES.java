package org.aia.testcases.ces;


import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorCESAPI;
import org.aia.pages.api.ces.FontevaConnectionSOAP;
import org.aia.pages.api.ces.JoinCESAPIValidation;
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
import org.aia.pages.ces.SecondaryPointOfContact;
import org.aia.pages.ces.SignUpPageCes;
import org.aia.pages.ces.Subscription;
import org.aia.pages.membership.OrderSummaryPage;
import org.aia.pages.membership.PaymentInformation;
import org.aia.pages.membership.PrimaryInformationPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpSuccess;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestProviderAppTab_CES extends BaseClass {
	
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
	JoinCESAPIValidation apiValidation;
	FontevaCES fontevaPage;
	public ExtentReports extent;
	public ExtentTest extentTest;
	final static Logger logger = Logger.getLogger(TestJoinPassport_CES.class);
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("ces_signin"));
		util = new Utility(driver, 30);
		testData = new ConfigDataProvider();
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
		apiValidation = PageFactory.initElements(driver, JoinCESAPIValidation.class);
		fontevaPage = PageFactory.initElements(driver, FontevaCES.class);
	}

	/**
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Validate Primary point of contact tab", enabled = false)
	public void validatePrimaryPOCTab() throws Exception {
		String prefix = "Dr.";
		String suffix = "Sr.";
		signUpPage.clickSignUplink();
		ArrayList<String> dataList = signUpPage.signUpData();
		String mobileCountry = signUpPage.signUpUserDetail();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		loginPageCes.loginToCes(dataList.get(5), dataList.get(6));
		loginPageCes.checkLoginSuccess();
		primarypocPage.verifyPOCTab();
		primarypocPage.validateErrorOnPOCTab();
		primarypocPage.enterPOCdetail(prefix, suffix, dataList.get(2), dataList, mobileCountry);
	}
	
	@Test(priority = 2, description = "Validate refresh functionality Organization tab", enabled = false)
	public void validateRefreshFuntionInOrg() throws Exception {
		String prefix = "Dr.";
		String suffix = "Sr.";
		signUpPage.clickSignUplink();
		ArrayList<String> dataList = signUpPage.signUpData();
		String mobileCountry = signUpPage.signUpUserDetail();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		loginPageCes.loginToCes(dataList.get(5), dataList.get(6));
		loginPageCes.checkLoginSuccess();
		primarypocPage.verifyPOCTab();
		primarypocPage.validateErrorOnPOCTab();
		primarypocPage.enterPOCdetail(prefix, suffix, dataList.get(2), dataList, mobileCountry);
		organizationPage.verifyOrganizationTab();
		organizationPage.refreshFunction();
			
	}
	
	@Test(priority = 3, description = "Validate the Provider application number being created", enabled = true)
	public void validateProviderAppNoCreated() throws Exception {
		String prefix = "Dr.";
		String suffix = "Sr.";
		signUpPage.clickSignUplink(); 
		ArrayList<String> dataList = signUpPage.signUpData(); 
		ArrayList<String> userAccount = dataList;
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		loginPageCes.loginToCes(dataList.get(5), dataList.get(6));
		loginPageCes.checkLoginSuccess();
		primarypocPage.enterPrimaryPocDetails(prefix, suffix, dataList.get(2));
		String subType = organizationPage.enterOrganizationDetails(dataList, 
				  "Architecture Firm", "No", "United States of America (+1)");
		subscribePage.SubscriptionType(subType, "Yes", null, "Non-profit");
		//subscribePage.proratedSubscriptionNext();
		secPoc.enterSecondaryPocDetails(dataList, prefix, suffix, "No", "United States of America (+1)"); 
		additionalUsers.doneWithCreatingUsers();
		providerStatement.providerStatementEnterNameDate2("FNProviderStatement");
		checkOutPageCes.SubscriptionType(subType);
		mailinator.ProviderApplicationReviewEmailLink(userAccount);

		for(int i=0;i<userAccount.size();i++)
			System.out.println("useraccount value is ***:" +userAccount.get(i));
		// Get Provider application ID
		String paId = apiValidation.getProviderApplicationID(userAccount.get(0)+" "+userAccount.get(1)); 
		
		// Navigate to Fonteva app and make record renew eligible.
		FontevaConnectionSOAP sessionID = new FontevaConnectionSOAP(); 
		System.out.println("sessionID is :" +sessionID);
		final String sID = sessionID.getSessionID();
		System.out.println("sessionID 2 is :" +sID);
		driver.get("https://aia--testing.sandbox.my.salesforce.com/secur/frontdoor.jsp?sid=" + sID);
		fontevaPage.changeProviderApplicationStatus(userAccount.get(0)+" "+userAccount.get(1), paId, "Approved");
		
	}
	
	}
	
	


