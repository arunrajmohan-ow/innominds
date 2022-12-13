package org.aia.testcases.membership;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.membership.JoinAPIValidation;
import org.aia.pages.membership.*;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.inject.Key;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class TestJoin_MembershipTypes extends BaseClass {

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

	public ExtentReports extent;
	public ExtentTest extentTest;
	public String inbox;

	@BeforeMethod
	public void setUp() throws Exception {
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("stagingurl_membership"));
		inbox = DataProviderFactory.getConfig().getValue("inbox");
		util = new Utility(driver, 30);
		mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
		signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		signInpage = PageFactory.initElements(driver, SignInPage.class);
		closeButtnPage = PageFactory.initElements(driver, CheckYourEmailPage.class);
		mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
		successPage = PageFactory.initElements(driver, SignUpSuccess.class);
		apiValidation = PageFactory.initElements(driver, JoinAPIValidation.class);
		primaryInfoPage = PageFactory.initElements(driver, PrimaryInformationPage.class);
		orderSummaryPage = PageFactory.initElements(driver, OrderSummaryPage.class);
		paymentInfoPage = PageFactory.initElements(driver, PaymentInformation.class);
		finalPage = PageFactory.initElements(driver, FinalPageThankYou.class);
		tellAbtPage = PageFactory.initElements(driver, TellusAboutYourselfPage.class);
	}

	
	  @Test(priority=1, description="Validate Membership Join - ActiveUSLicense",
	  enabled=true) public void TestArchitectMembershipJoin() throws Exception {
	  ArrayList<String> dataList = signUpPage.signUpData();
	  
	  signUpPage.gotoMembershipSignUpPage(dataList.get(5));
	  signUpPage.signUpUser();
	  mailinator.verifyEmailForAccountSetup(dataList.get(3));
	  closeButtnPage.clickCloseAfterVerification();
	  signInpage.login(dataList.get(5), dataList.get(6));
	  primaryInfoPage.enterPrimaryInfo("activeUSLicense", "Non profit");
	  orderSummaryPage.confirmTerms("activeUSLicense");
	  orderSummaryPage.clickonPayInFull(); String aiaNational =
	  paymentInfoPage.enterCrditCardDetails(); finalPage.verifyThankYouMessage();
	  ArrayList<Object> data = finalPage.getFinalReceiptData();
	  Reporter.log("LOG : INFO -Receipt Number is"+data.get(0));
	  Reporter.log("LOG : INFO -Customer AIA Number is : "+data.get(1));
	  System.out.println("Total Amount is "+data.get(2));
	  System.out.println("AIA National is "+aiaNational);
	  //mailinator.welcomeAIAEmailLink(dataList, data);
	  
	  // Validate Membership creation - Fonteva API validations
	  apiValidation.verifyMemebershipCreation(dataList.get(3), 
			  DataProviderFactory.getConfig().getValue("termEndDate"), 
			  data.get(2), 
			  DataProviderFactory.getConfig().getValue("type_aia_national"), 
			  "Architect"); 
	  // Validate sales order
	  apiValidation.verifySalesOrder(DataProviderFactory.getConfig().getValue("salesOrderStatus"), 
			  DataProviderFactory.getConfig().getValue("orderStatus"),
			  data.get(2), 
			  DataProviderFactory.getConfig().getValue("postingStatus")); 
	  //Validate Receipt Details 
	  apiValidation.verifyReciptDetails(data.get(0), data.get(2));
	  
		/*
		 * apiValidation.verifyMemebershipCreation("automation_xjtg11282022",
		 * DataProviderFactory.getConfig().getValue("termEndDate"), 638.0
		 * ,DataProviderFactory.getConfig().getValue("type_aia_national"));
		 * apiValidation.verifySalesOrder("Paid", "Closed", 638.0, "Posted");
		 * apiValidation.verifyReciptDetails("0000105204", 638.0);
		 */
	  
	  }
	 

	@Test(priority = 2, description = "Validate Membership Join - Allied", enabled = true)
	public void TestAlliedMembershipJoin() throws Exception {
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.gotoMembershipSignUpPage(dataList.get(5));
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		signInpage.login(dataList.get(5), dataList.get(6));
		primaryInfoPage.enterPrimaryInfo("allied", "Non profit");
		orderSummaryPage.confirmTerms("allied");
		orderSummaryPage.clickonPayInFull();
		paymentInfoPage.enterCrditCardDetails();
		finalPage.verifyThankYouMessage();
		ArrayList<Object> data = finalPage.getFinalReceiptData();

		// mailinator.welcomeAIAEmailLink(dataList.get(3));

		// Validate Membership creation - Fonteva API validations
		apiValidation.verifyMemebershipCreation(dataList.get(3),
				DataProviderFactory.getConfig().getValue("termEndDate"), data.get(2),
				DataProviderFactory.getConfig().getValue("type_aia_national"), "Allied");
		// Validate sales order
		apiValidation.verifySalesOrder(DataProviderFactory.getConfig().getValue("salesOrderStatus"),
				DataProviderFactory.getConfig().getValue("orderStatus"), data.get(2),
				DataProviderFactory.getConfig().getValue("postingStatus"));
		// Validate Receipt Details
		apiValidation.verifyReciptDetails(data.get(0), data.get(2));
	}
	
	@AfterTest
	public void teardown() {
		BrowserSetup.closeBrowser(driver);
	}
}