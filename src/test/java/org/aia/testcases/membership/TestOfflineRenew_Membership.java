package org.aia.testcases.membership;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.pages.fonteva.membership.ReNewUser;
import org.aia.utility.BrowserSetup;
import org.aia.utility.DataProviderFactory;
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
public class TestOfflineRenew_Membership extends BaseClass{
	ContactCreateUser fontevaJoin;
	ReNewUser reNew;
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeMethod
	public void setUp() throws Exception {
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("fonteva_endpoint"));
		util = new Utility(driver, 30);
		fontevaJoin = PageFactory.initElements(driver, ContactCreateUser.class);
		reNew = PageFactory.initElements(driver, ReNewUser.class);
	}

	/**
	 * @throws InterruptedException
	 */
	@Test(priority = 1, description = "verify the offline membership renew in fonteva application", enabled = true)
	public void offlineRenewProcess() throws InterruptedException {
		ArrayList<String> dataList = fontevaJoin.userData();
		fontevaJoin.signInFonteva();
		fontevaJoin.createUserInFonteva();
		fontevaJoin.joinCreatedUser("Architect", "Non profit");
		fontevaJoin.enterLicenseDetail();
		fontevaJoin.createSalesOrder();
		fontevaJoin.applyPayment();
		ArrayList<Object> data =fontevaJoin.getPaymentReceiptData();
		reNew.changeTermDate(dataList.get(5));
		reNew.renewMembership(dataList.get(5));
		
	}

	@AfterMethod
	public void teardown() {
		BrowserSetup.closeBrowser(driver);
	}
}
