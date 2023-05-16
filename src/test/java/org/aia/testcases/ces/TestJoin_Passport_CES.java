package org.aia.testcases.ces;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.MailinatorCESAPI;
import org.aia.pages.api.ces.JoinCESAPIValidation;
import org.aia.pages.api.membership.JoinAPIValidation;
import org.aia.pages.ces.*;
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
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.inject.Key;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class TestJoin_Passport_CES extends BaseClass {

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
	
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	@BeforeMethod
	public void setUp() throws Exception
	{
		driver=BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),DataProviderFactory.getConfig().getValue("ces_signin"));
		util=new Utility(driver, 30);
		signUpPage = PageFactory.initElements(driver, SignUpPageCes.class);
		signInpage = PageFactory.initElements(driver, SignInPage.class);
		closeButtnPage = PageFactory.initElements(driver, CloseBtnPageCes.class);
		mailinator = PageFactory.initElements(driver, MailinatorCESAPI.class);
		successPage = PageFactory.initElements(driver, SignUpSuccess.class);
		loginPageCes =PageFactory.initElements(driver, LoginPageCes.class);
		primarypocPage =PageFactory.initElements(driver, PrimaryPointOfContact.class);
		organizationPage =PageFactory.initElements(driver, Organization.class);
		subscribePage =PageFactory.initElements(driver, Subscription.class);	
		secPoc =PageFactory.initElements(driver, SecondaryPointOfContact.class);
		additionalUsers =PageFactory.initElements(driver, AdditionalUsers.class);
		additionalProviderUser =PageFactory.initElements(driver, AdditionalProviderUser.class);	
		providerStatement =PageFactory.initElements(driver, ProviderStatement.class);	
		checkOutPageCes =PageFactory.initElements(driver, CheckOutPageCes.class);			
		paymntSuccesFullPageCes =PageFactory.initElements(driver, PaymentSuccessFullPageCes.class);
		apiValidation = PageFactory.initElements(driver, JoinCESAPIValidation.class);
	}
	
	@Test(priority=1, description="Validate CES JOIN with subscription type : Passport", enabled=true)
	public void ValidateCESPassportJoin() throws Exception
	{
		String prefix = "Dr.";
		String suffix = "Sr.";
		
		  //signUpPage.clickSignUplink(); 
		  ArrayList<String> dataList = signUpPage.signUpData(); 
			/*
			 * //signUpPage.signUpUser();
			 * //mailinator.verifyEmailForAccountSetup(dataList.get(3));
			 * //closeButtnPage.clickCloseAfterVerification();
			 * //loginPageCes.loginToCes(dataList.get(5), dataList.get(6));
			 * loginPageCes.loginToCes("auto_uuus04172023@architects-team.m8r.co",
			 * "Login_123"); loginPageCes.checkLoginSuccess();
			 * primarypocPage.enterPrimaryPocDetails(prefix, suffix, dataList.get(2));
			 * String text = organizationPage.enterOrganizationDetails(dataList, "Other",
			 * "No", "United States of America (+1)");
			 * //subscribePage.SubscriptionType(text, "Yes", null, "Government Agency");
			 * subscribePage.SubscriptionType(text, "Yes", null, "Non-profit");
			 * secPoc.enterSecondaryPocDetails(dataList, prefix, suffix, "No",
			 * "United States of America (+1)"); additionalUsers.doneWithCreatingUsers();
			 * providerStatement.providerStatementEnterNameDate2("FNProviderStatement");
			 * checkOutPageCes.SubscriptionType(text);
			 * //checkOutPageCes.enterCardDetailsCes();
			 * Logging.logger.info("Total Amount is : " +
			 * paymntSuccesFullPageCes.amountPaid()); String reciptData =
			 * paymntSuccesFullPageCes.ClickonViewReceipt(); String reciptNumber =
			 * util.getSubString(reciptData, "" ); Logging.logger.info("Receipt Number is :"
			 * + reciptNumber); Logging.logger.info("First Name is : " + dataList.get(0));
			 * 
			 * mailinator.welcomeAIAEmailLink(dataList, reciptNumber);
			 */
		
		// Validate Provider Application & CES Provider account details - Fonteva API validations
		  apiValidation.verifyProviderApplicationDetails("Approved for Passport", dataList, "Passport", "autofnHdBE autolnNdIY", 
				  true, "2023-04-21", "AutomationOrg", "Other", "No"); 
		  
		// Validate CES Provider account details - Fonteva API validations
		  apiValidation.verifyProviderApplicationAccountDetails("Active", "CES Passport", "2023-12-31",
				  false);
		 
		// Validate sales order
		  apiValidation.verifySalesOrder(DataProviderFactory.getConfig().getValue("salesOrderStatus"), 
					DataProviderFactory.getConfig().getValue("orderStatus"), 
					4560.0, DataProviderFactory.getConfig().getValue("postingStatus"));
		  
		//Validate Receipt Details 
			apiValidation.verifyReciptDetails("0000218861", 4560.0, "CES Passport");
			
		//Validate Primary POC 
		apiValidation.verifyPointOfContact("CES Primary", "auto_mgiz04212023@architects-team.m8r.co", "autofnHdBE autolnNdIY");
		
	}



	@AfterMethod
	public void teardown() 
	{
		BrowserSetup.closeBrowser(driver);
		
	}
	
}