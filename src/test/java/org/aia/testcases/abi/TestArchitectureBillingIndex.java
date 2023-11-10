package org.aia.testcases.abi;

import static io.restassured.RestAssured.given;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.aia.pages.BaseClass;
import org.aia.pages.abi.ABISignUpPage;
import org.aia.pages.abi.PaymentSuccessFullPageABI;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.abi.ABIAPIValidation;
import org.aia.pages.api.abi.ABIAPIValidation;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.api.membership.JoinAPIValidation;
import org.aia.pages.fonteva.abi.FontevaLoginPage;
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
import org.aia.testcases.events.FontevaConnection;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.FailedTestRun;
import org.aia.utility.GenerateReportsListener;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.netty.util.internal.SystemPropertyUtil;
import io.restassured.http.ContentType;

@Listeners(org.aia.utility.GenerateReportsListener.class)

public class TestArchitectureBillingIndex extends GenerateReportsListener {
			
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
	//JoinAPIValidation apiValidation;
	TellusAboutYourselfPage tellAbtPage;
	ContactCreateUser fontevaJoin;
	SalesOrder salesOrder;
	PaymentSuccessFullPageABI paymentSuccessfullPage;
	ABIAPIValidation apiValidation;
	FontevaLoginPage fontevaLoginPage;

	public ExtentReports extent;
	public ExtentTest extentTest;
	public String inbox;
	public static HashMap<String, String> users=new HashMap<String, String>();
	public static ArrayList<String> pdfDetails=new ArrayList<String>();
	protected FontevaConnectionSOAP sessionID;

	
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
		fontevaLoginPage = PageFactory.initElements(driver, FontevaLoginPage.class);

		//apiValidation = PageFactory.initElements(driver, ArchitectureBillingIndexAPIValidation.class);
		Logging.configure();
		util.waitForPageLoad(driver);
	}
	
	@Test(groups={"Smoke"}, description=" To subscribe for ABI", enabled=true, dataProvider="Address")//, retryAnalyzer = FailedTestRun.class
	public void SubscribeToABI(ITestContext context, String address) throws Exception {
		System.out.println("Subscription");
		ArrayList<String> dataList = signUpPage.signUpData();
		abisignUpPage.goToSignUpLink();
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		signInpage.login(dataList.get(5), dataList.get(6));
		pdfDetails=abisignUpPage.subscribeToABI(dataList.get(0)+" "+dataList.get(1), String.valueOf(address));	
		//context.setAttribute("contactName", dataList.get(0)+" "+dataList.get(1));
		users.put(dataList.get(0)+" "+dataList.get(1), dataList.get(5));
	    }
	
	@Test(groups={"Smoke"}, description=" To renew ABI subscription", enabled=true, dependsOnMethods={"SubscribeToABI"})
	public void RenewABISubscription(ITestContext context) throws Exception {
		  	for(Map.Entry<String,String> e:users.entrySet()) {	
			driver.get(DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
			fontevaLoginPage.renewABI(e.getKey());
			abisignUpPage.processPayment(e.getValue());
			pdfDetails=abisignUpPage.proceedToCheckOut();
		  	}
	}
	
	@DataProvider(name="Address")
	public Object[][] getAddress(ITestContext context) {
		return new Object[][] {
			{"1735 york avenue, New york"}
			//, {"115 E 3rd Ave, Anchorage, AK 99501, United States"}
		};
	}
	
	@Test(groups={"Smoke"}, description=" To renew ABI subscription", enabled=true, dataProvider="Address")
	public void SubscribeToABIAsGuest(ITestContext context, String address) throws Exception {
		ArrayList<String> dataList = signUpPage.signUpData();
		pdfDetails=abisignUpPage.subscribeToABI(dataList.get(0)+" "+dataList.get(1), String.valueOf(address));	

	}
	
	@AfterMethod 
	public void parseResponse(ITestContext context) throws URISyntaxException, InterruptedException, IOException {
		driver.get(DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		fontevaLoginPage.viewSalesOrder((String)context.getAttribute("contactName")); 
		abisignUpPage.validateSubscriptionDetails(pdfDetails);

	}
    
	}

