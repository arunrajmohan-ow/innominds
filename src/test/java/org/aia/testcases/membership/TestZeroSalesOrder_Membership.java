package org.aia.testcases.membership;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.pages.fonteva.membership.SalesOrder;
import org.aia.pages.membership.CheckYourEmailPage;
import org.aia.pages.membership.OrderSummaryPage;
import org.aia.pages.membership.PrimaryInformationPage;
import org.aia.pages.membership.SignInPage;
import org.aia.pages.membership.SignUpPage;
import org.aia.pages.membership.SignUpSuccess;
import org.aia.utility.BrowserSetup;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author IM-RT-LP-1483(Suhas)
 *
 */
public class TestZeroSalesOrder_Membership extends BaseClass {
	SignUpPage signUpPage;
	SignInPage signInpage;
	CheckYourEmailPage closeButtnPage;
	MailinatorAPI mailinator;
	SignUpSuccess successPage;
	PrimaryInformationPage primaryInfoPage;
	OrderSummaryPage orderSummaryPage;
	ContactCreateUser joinFonteva;
	SalesOrder salesOrder;

	@BeforeMethod
	public void setUp() throws Exception {
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("devstagingurl_membership"));
		util = new Utility(driver, 30);
		mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
		signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		signInpage = PageFactory.initElements(driver, SignInPage.class);
		closeButtnPage = PageFactory.initElements(driver, CheckYourEmailPage.class);
		mailinator = PageFactory.initElements(driver, MailinatorAPI.class);
		successPage = PageFactory.initElements(driver, SignUpSuccess.class);
		primaryInfoPage = PageFactory.initElements(driver, PrimaryInformationPage.class);
		orderSummaryPage = PageFactory.initElements(driver, OrderSummaryPage.class);
		joinFonteva=PageFactory.initElements(driver, ContactCreateUser.class);
		salesOrder=PageFactory.initElements(driver, SalesOrder.class);
		// Configure Log4j to perform error logging
		Logging.configure();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void validateZerosalesOrder() throws Exception {
		ArrayList<String> dataList = signUpPage.signUpData();
		signUpPage.gotoMembershipSignUpPage(dataList.get(5));
		signUpPage.signUpUser();
		mailinator.verifyEmailForAccountSetup(dataList.get(3));
		closeButtnPage.clickCloseAfterVerification();
		signInpage.login(dataList.get(5), dataList.get(6));
		primaryInfoPage.enterPrimaryInfo("activeUSLicense", "Non profit");
		orderSummaryPage.confirmTerms("activeUSLicense");
		orderSummaryPage.clickonPayNow();
		// Navigate to Fonteva app and make record renew eligible.
		salesOrder.switchToTab();
		driver.get(DataProviderFactory.getConfig().getValue("fonteva_endpoint"));
		joinFonteva.signInFonteva();
		joinFonteva.selectContact(dataList.get(0)+" "+dataList.get(1));
		salesOrder.setDiscount();
	}
}
