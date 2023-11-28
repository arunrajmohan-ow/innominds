package org.aia.testcases.membership;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.api.membership.JoinAPIValidation;
import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * @author sghodake
 *
 */
public class TestMembershipUpgradeRequest extends BaseClass {
	ContactCreateUser fontevaJoin;
	MailinatorAPI malinator;
	JoinAPIValidation offlinApiValidation;
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		util = new Utility(driver, 30);
		testData = new ConfigDataProvider();
		fontevaJoin = PageFactory.initElements(driver, ContactCreateUser.class);
		malinator = PageFactory.initElements(driver, MailinatorAPI.class);
		offlinApiValidation = PageFactory.initElements(driver, JoinAPIValidation.class);
		// Configure Log4j to perform error logging
		Logging.configure();
	}

	/**
	 * @throws InterruptedException
	 */
	@Test(priority = 1, description = "Membership Upgrade request (Fonteva)", enabled = true)
	public void membershipUpgradeRequest() throws InterruptedException {
		// driver.get(DataProviderFactory.getConfig().getValue("fonteva_endpoint"));
		ArrayList<String> dataList = fontevaJoin.userData();
		fontevaJoin.pointOffset();
		fontevaJoin.createUserInFonteva();
		fontevaJoin.joinCreatedUser(testData.testDataProvider().getProperty("membershipType"),
				testData.testDataProvider().getProperty("selection"));
		fontevaJoin.enterLicenseDetail();
		fontevaJoin.createSalesOrder(testData.testDataProvider().getProperty("paymentMethod"));
		fontevaJoin.applyPayment(dataList.get(5));

	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		BrowserSetup.closeBrowser(driver);
	}

}
