package org.aia.testcases.membership;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.membership.*;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
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

public class MembershipSignUpTest extends BaseClass {

	SignUpPage signUpPage;
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeMethod
	public void setUp() throws Exception
	{
		driver=BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),DataProviderFactory.getConfig().getValue("stagingurl_membership_signin"));
		util=new Utility(driver, 30);
		signUpPage = PageFactory.initElements(driver, SignUpPage.class);
	}
	
	
	@Test(priority=1, description="Verifying Signuppage is opened", enabled=true)
	public void ValidateSignUpPageISOpened() throws Exception
	{
		signUpPage.clickSignUplink();		
	}
	
	@Test(priority=2, description="Validate mebership Sign up.", enabled=true)
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Case Description: Verify Singup test on signup Page")
	public void ValidateSignup() throws Exception
	{
		ArrayList<String> dataList = signUpPage.signUpData();
		String email = dataList.get(5);
		signUpPage.clickSignUplink();
		signUpPage.signUpUser();
	}

	
	@AfterMethod
	public void teardown() 
	{
		BrowserSetup.closeBrowser(driver);
		//driver.quit();
	}
	
}