package org.aia.testcases.membership;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
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

	public ExtentReports extent;
	public ExtentTest extentTest;
	public String inbox;

	@BeforeMethod
	public void setUp() throws Exception
	{
		driver=BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),DataProviderFactory.getConfig().getValue("stagingurl_membership"));
		inbox = DataProviderFactory.getConfig().getValue("inbox");
		util=new Utility(driver, 30);
		mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
		signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		signInpage = PageFactory.initElements(driver, SignInPage.class);
		closeButtnPage = PageFactory.initElements(driver, CheckYourEmailPage.class);
		mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
		successPage = PageFactory.initElements(driver, SignUpSuccess.class);
		primaryInfoPage = PageFactory.initElements(driver,
		PrimaryInformationPage.class); orderSummaryPage =
		PageFactory.initElements(driver, OrderSummaryPage.class); paymentInfoPage =
		PageFactory.initElements(driver, PaymentInformation.class); finalPage =
		PageFactory.initElements(driver, FinalPageThankYou.class);
	}


	@Test(priority=1, description="Validate Membership Signup", enabled=true)
	public void TestArchitectMembershipJoin() throws Exception
	{
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.gotoMembershipSignUpPage(dataList.get(5));
		/*
		 * signUpPage.signUpUser();
		 * mailinator.verifyEmailForAccountSetup(dataList.get(3));
		 * closeButtnPage.clickCloseAfterVerification();
		 * signInpage.login(dataList.get(5), dataList.get(6));
		 * primaryInfoPage.enterPrimaryInfo("activeUSLicense", "Non profit");
		 * orderSummaryPage.clickonPayInFull(); paymentInfoPage.enterCrditCardDetails();
		 * finalPage.verifyThankYouMessage(); ArrayList<String> data =
		 * finalPage.getFinalReceiptrData();
		 */
		/*
		 * Reporter.log("LOG : INFO -Receipt Number is"+data.get(0));
		 * Reporter.log("LOG : INFO -Customer AIA Number is : "+data.get(1));
		 */
	}


	public void teardown() 
	{
		BrowserSetup.closeBrowser(driver);
	}
	
}