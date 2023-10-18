package org.aia.testcases.abi;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Map;

import org.aia.pages.BaseClass;
import org.aia.pages.abi.ABISignUpPage;
import org.aia.pages.abi.PaymentSuccessFullPageABI;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.abi.ArchitectureBillingIndexAPIValidation;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.api.membership.JoinAPIValidation;
import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.pages.fonteva.membership.SalesOrder;
import org.aia.pages.membership.CheckYourEmailPage;
import org.aia.pages.membership.FinalPageThankYou;
import org.aia.pages.membership.OrderSummaryPage;
import org.aia.pages.membership.PaymentInformation;
import org.aia.pages.membership.PrimaryInformationPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpPage;
//import org.aia.pages.membership.SignUpPage;
import org.aia.pages.membership.SignUpSuccess;
import org.aia.pages.membership.TellusAboutYourselfPage;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.netty.util.internal.SystemPropertyUtil;
import io.restassured.http.ContentType;

public class TestArchitectureBillingIndex extends BaseClass {
			
	SignUpPage signUpPage;
	ABISignUpPage abisignUpPage;
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
	ContactCreateUser fontevaJoin;
	SalesOrder salesOrder;
	PaymentSuccessFullPageABI paymentSuccessfullPage;

	public ExtentReports extent;
	public ExtentTest extentTest;
	public String inbox;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("devstagingurl_abi"));
		inbox = DataProviderFactory.getConfig().getValue("inbox");
		util = new Utility(driver, 30);
		testData = new ConfigDataProvider();
		mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
		signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		abisignUpPage = PageFactory.initElements(driver, ABISignUpPage.class);
		signInpage = PageFactory.initElements(driver, SignInPage.class);
		closeButtnPage = PageFactory.initElements(driver, CheckYourEmailPage.class);
		paymentSuccessfullPage = PageFactory.initElements(driver, PaymentSuccessFullPageABI.class);
		finalPage = PageFactory.initElements(driver, FinalPageThankYou.class);

		Logging.configure();
	}
	
	@Test(groups={"Smoke"}, priority=1, description=" To subscribe ABI", enabled=true)
	public void SubscribeToABIWithUSTaxableAddress() throws Exception {
		ArrayList<String> dataList = signUpPage.signUpData();
		abisignUpPage.goToSignUpLink();
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		signInpage.login(dataList.get(5),dataList.get(6));
		abisignUpPage.subscribeToABI(dataList.get(0)+" "+dataList.get(1));	
		String receiptData=paymentSuccessfullPage.ClickonViewReceipt();
		String receiptNumber = util.getSubString(receiptData, "");
		Reporter.log("LOG : INFO -Receipt Number is" + receiptNumber);
		Reporter.log("LOG : INFO -Customer AIA Number is : " + dataList.get(1));
		mailinator.welcomeAIAEmailLink(dataList, finalPage.getFinalReceiptData());
	    }
	
	@Test(groups={"Smoke"}, priority=2, description=" To verify ABI subscription", enabled=true)
	public void VerifyABISubscription(ITestContext context) throws Exception {
		ArchitectureBillingIndexAPIValidation apiValidation = new ArchitectureBillingIndexAPIValidation(driver);
		JSONObject jo = new JSONObject(apiValidation.verifyABISubscription().getBody().asString());
		System.out.println("*******************************************************************");
		JSONArray ja=jo.getJSONArray("recentItems");
		
		for(int i=0;i<=ja.length()-1;i++) {
			String name=ja.getJSONObject(i).getString("Name");
			System.out.println("Name: "+name);
		}
		
	}
	
}

