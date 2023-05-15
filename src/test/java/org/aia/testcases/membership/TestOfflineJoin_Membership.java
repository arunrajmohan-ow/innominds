package org.aia.testcases.membership;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.utility.BrowserSetup;
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
 * @author IM-RT-LP-1483(Suhas)
 *
 */
public class TestOfflineJoin_Membership extends BaseClass {
	ContactCreateUser fontevaJoin;
	MailinatorAPI apiValidation;
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeMethod
	public void setUp() throws Exception {
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("fonteva_endpoint"));
		util = new Utility(driver, 30);
		fontevaJoin = PageFactory.initElements(driver, ContactCreateUser.class);
		apiValidation=PageFactory.initElements(driver,MailinatorAPI.class);
		// Configure Log4j to perform error logging
		Logging.configure();
	}

	/**
	 * @throws InterruptedException
	 */
	@Test(priority = 1, description = "verify the offline membership join in fonteva application", enabled = true)
	public void offlineJoinProcess() throws InterruptedException {

		// driver.get(DataProviderFactory.getConfig().getValue("fonteva_endpoint"));
		ArrayList<String> dataList = fontevaJoin.userData();
		fontevaJoin.signInFonteva();
		fontevaJoin.createUserInFonteva();
		fontevaJoin.joinCreatedUser("Architect", "Non profit");
		fontevaJoin.enterLicenseDetail();
		fontevaJoin.createSalesOrder();
		fontevaJoin.applyPayment();
		//Validation of Thank you massage in email inbox after register.
		apiValidation.thankYouEmailforOfflineJoin(dataList.get(2));
	}

	@AfterMethod
	public void teardown() {
		BrowserSetup.closeBrowser(driver);
	}

}
