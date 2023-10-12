package org.aia.testcases.ces;

import java.time.LocalDate;
import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorCESAPI;
import org.aia.pages.api.ces.FontevaCESTermDateChangeAPI;
import org.aia.pages.api.ces.RenewCESAPIValidation;
import org.aia.pages.api.ces.JoinCESAPIValidation;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
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
import org.aia.pages.fonteva.ces.CES_Memberships;
import org.aia.pages.fonteva.ces.CES_ReNewUser;
import org.aia.pages.fonteva.ces.CES_SalesOrder;
import org.aia.pages.membership.OrderSummaryPage;
import org.aia.pages.membership.PaymentInformation;
import org.aia.pages.membership.PrimaryInformationPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpSuccess;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.apache.poi.util.PackageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestCESMembershipStatus_CES extends BaseClass {
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
	RenewCESPage renew;
	CES_ReNewUser reNewUser;
	CES_ContactPage ces_ContactPage;
	CES_Memberships ces_membership;
	FontevaCESTermDateChangeAPI termDateChangeApi;
	JoinCESAPIValidation apiValidation;
	FontevaCES fontevaPage;
	FontevaCESTermDateChangeAPI cesTermDateChangeAPI;
	CES_SalesOrder salesorder;

	public ExtentReports extent;
	public ExtentTest extentTest;
	final static Logger logger = Logger.getLogger(TestCESMembershipStatus_CES.class);

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		sessionID = new FontevaConnectionSOAP();
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
		fontevaPage = PageFactory.initElements(driver, FontevaCES.class);
		renew = PageFactory.initElements(driver, RenewCESPage.class);
		reNewUser = PageFactory.initElements(driver, CES_ReNewUser.class);
		ces_ContactPage = PageFactory.initElements(driver, CES_ContactPage.class);
		ces_membership = PageFactory.initElements(driver, CES_Memberships.class);
		termDateChangeApi = PageFactory.initElements(driver, FontevaCESTermDateChangeAPI.class);
		apiValidation = PageFactory.initElements(driver, JoinCESAPIValidation.class);
		fontevaPage = PageFactory.initElements(driver, FontevaCES.class);
		cesTermDateChangeAPI = PageFactory.initElements(driver, FontevaCESTermDateChangeAPI.class);
		ces_ContactPage = PageFactory.initElements(driver, CES_ContactPage.class);
		salesorder = PageFactory.initElements(driver, CES_SalesOrder.class);
	
	}

	@Test(priority = 1, description = "(FC-331)validate Term creation for $0 CES Membership type", enabled = false)
	public void validateTermCreation$0CESMembershipType() throws Exception {
		String prefix = "Dr.";
		String suffix = "Sr.";
		signUpPage.clickSignUplink();
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		loginPageCes.loginToCes(dataList.get(5), dataList.get(6));
		loginPageCes.checkLoginSuccess();
		primarypocPage.enterPrimaryPocDetails(prefix, suffix, dataList.get(2));
		String text = organizationPage.enterOrganizationDetails(dataList, "Other", "No",
				"United States of America (+1)");
		subscribePage.SubscriptionType(text, "Yes", null, "Non-profit");
		secPoc.enterSecondaryPocDetails(dataList, prefix, suffix, "No", "United States of America (+1)");
		additionalUsers.doneWithCreatingUsers();
		providerStatement.providerStatementEnterNameDate2("FNProviderStatement");
		checkOutPageCes.SubscriptionType(text);
		Logging.logger.info("Total Amount is : " + paymntSuccesFullPageCes.amountPaid());
		Object amount = paymntSuccesFullPageCes.amountPaid();
		// Navigate to Fonteva app and make record renew eligible.
		driver.get(DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		ces_ContactPage.cesRapidOrderEntry(dataList.get(0) + " " + dataList.get(1), "CES AIA National", "National");
		ces_ContactPage.validateDeleteCESMembership();
		//ces_ContactPage.validateAvailableMemType();
		termDateChangeApi.changeTermDateAPI(dataList.get(3),(testData.testDataProvider().getProperty("changeTermDate")),0);
		// Validate Provider Application & CES Provider account details - Fonteva API
		// validations
		apiValidation.verifyProviderApplicationDetails("Approved", dataList, "Passport",
				dataList.get(0) + " " + dataList.get(1), true, util.todaysDate().toString(), "AutomationOrg", "Other",
				"No");

		// Validate CES Provider account details - Fonteva API validations
		apiValidation.verifyProviderApplicationAccountDetails("Active", "CES Passport", "2023-12-31", false);
		// Validate Primary POC
		apiValidation.verifyPointOfContact("CES Primary", dataList.get(5), dataList.get(0) + " " + dataList.get(1));
	}

	@Test(priority = 2, description = "(FC-172) Validate Membership Type when term date for CES Membership is updated", enabled = false)
	public void verifyTermDateCESMembershipType() throws Exception {
		String prefix = "Dr.";
		String suffix = "Sr.";
		signUpPage.clickSignUplink();
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		loginPageCes.loginToCes(dataList.get(5), dataList.get(6));
		loginPageCes.checkLoginSuccess();
		primarypocPage.enterPrimaryPocDetails(prefix, suffix, dataList.get(2));
		String text = organizationPage.enterOrganizationDetails(dataList, "Other", "No",
				"United States of America (+1)");
		subscribePage.SubscriptionType(text, "Yes", null, "Non-profit");
		secPoc.enterSecondaryPocDetails(dataList, prefix, suffix, "No", "United States of America (+1)");
		additionalUsers.doneWithCreatingUsers();
		providerStatement.providerStatementEnterNameDate2("FNProviderStatement");
		checkOutPageCes.SubscriptionType(text);
		Logging.logger.info("Total Amount is : " + paymntSuccesFullPageCes.amountPaid());
		Object amount = paymntSuccesFullPageCes.amountPaid();
		// Navigate to Fonteva app and make record renew eligible.
		driver.get(DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		ces_ContactPage.cesRapidOrderEntry(dataList.get(0) + " " + dataList.get(1), "CES AIA National", "National");
		termDateChangeApi.changeTermDateAPI(dataList.get(3), "2024-12-31",1);
		termDateChangeApi.getCESAccountDetails("Active","CES AIA National");
		//cesTermDateChangeAPI.validateCESMembershipCreated(testData.testDataProvider().getProperty("membershipType"),testData.testDataProvider().getProperty("memTypeEndPoint"));

	}

	@Test(priority = 3, description = "(FC-164) Verify Membership status when term date for CES Membership is updated", enabled = false, groups = {
			"Smoke" })
	public void validateCESMembershipStatus() throws Exception {
        //Here we create the user
		String prefix = "Dr.";
		String suffix = "Sr.";
		signUpPage.clickSignUplink();
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		loginPageCes.loginToCes(dataList.get(5), dataList.get(6));
		loginPageCes.checkLoginSuccess();
		primarypocPage.enterPrimaryPocDetails(prefix, suffix, dataList.get(2));
		String text = organizationPage.enterOrganizationDetails(dataList, "Other", "No",
				"United States of America (+1)");
		subscribePage.SubscriptionType(text, "Yes", null, "Non-profit");
		secPoc.enterSecondaryPocDetails(dataList, prefix, suffix, "No", "United States of America (+1)");
		additionalUsers.doneWithCreatingUsers();
		providerStatement.providerStatementEnterNameDate2("FNProviderStatement");
		checkOutPageCes.SubscriptionType(text);
		Logging.logger.info("Total Amount is : " + paymntSuccesFullPageCes.amountPaid());
		String reciptData = paymntSuccesFullPageCes.ClickonViewReceipt();
        //Navigate to Fonteva side
		driver.get(DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
        //fontevaPage.changeTermDates(dataList.get(0) + " " + dataList.get(1));
		driver.get(DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		ces_ContactPage.cesRapidOrderEntry(dataList.get(0) + " " + dataList.get(1), "CES AIA National", "National");
		termDateChangeApi.changeTermDateAPI(dataList.get(3), "2024-12-31",1);
		termDateChangeApi.getCESAccountDetails("Active","CES AIA National");
//		cesTermDateChangeAPI.changeTermDateAPI(dataList.get(3), util.todaysDate().toString(),0);
//		cesTermDateChangeAPI.validateCESMembershipCreated(testData.testDataProvider().getProperty("membershipStatus"),testData.testDataProvider().getProperty("memStatusEndPoint"));

	}
	@Test(priority = 4, description = "(FC-165) Verify update the NON-CES membership as the latest Term end date", enabled = true)
	public void verifyTermDateNonCESMembershipType() throws Exception {
		/*String prefix = "Dr.";
		String suffix = "Sr.";
		signUpPage.clickSignUplink();
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		loginPageCes.loginToCes(dataList.get(5), dataList.get(6));
		loginPageCes.checkLoginSuccess();
		primarypocPage.enterPrimaryPocDetails(prefix, suffix, dataList.get(2));
		String text = organizationPage.enterOrganizationDetails(dataList, "Other", "No",
				"United States of America (+1)");
		subscribePage.SubscriptionType(text, "Yes", null, "Non-profit");
		secPoc.enterSecondaryPocDetails(dataList, prefix, suffix, "No", "United States of America (+1)");
		additionalUsers.doneWithCreatingUsers();
		providerStatement.providerStatementEnterNameDate2("FNProviderStatement");
		checkOutPageCes.SubscriptionType(text);
		Logging.logger.info("Total Amount is : " + paymntSuccesFullPageCes.amountPaid());
		Object amount = paymntSuccesFullPageCes.amountPaid();
		// Navigate to Fonteva app and make record renew eligible.
		driver.get(DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		ces_ContactPage.cesRapidOrderEntry(dataList.get(0) + " " + dataList.get(1), "Allied - National", "National");*/
		termDateChangeApi.changeTermDateAPI("auto_tlvw10122023", "2024-12-31",1);
		termDateChangeApi.getNonCESAccountDetails("Active","Allied - National");
		//termDateChangeApi.changeTermDateAPI(dataList.get(3), util.todaysDate().toString(),1);
		//cesTermDateChangeAPI.validateCESMembershipCreated(testData.testDataProvider().getProperty("nonCESMemType"),testData.testDataProvider().getProperty("memTypeEndPoint"));

	}

}
