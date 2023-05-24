package org.aia.testcases.membership;

import java.util.ArrayList;

import org.aia.pages.BaseClass;
import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.pages.fonteva.membership.ProcessException;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Utility;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * @author IM-RT-LP-1483(Suhas)
 *
 */
public class TestProcessException_Membership extends BaseClass {
	ContactCreateUser fontevaJoin;
	ProcessException processException;
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeMethod
	public void setUp() throws Exception {
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("fonteva_endpoint"));
		util = new Utility(driver, 30);
		testData = new ConfigDataProvider();
		fontevaJoin = PageFactory.initElements(driver, ContactCreateUser.class);
		processException = PageFactory.initElements(driver, ProcessException.class);
	}

	/**
	 * @throws InterruptedException
	 */
	@Test(priority = 1, description = "Saving New Processing Exception", enabled = true)
	public void offlineRenewProcess() throws InterruptedException {
		ArrayList<String> dataList = fontevaJoin.userData();
		// First we create new user in Fonteva
		fontevaJoin.signInFonteva();
		fontevaJoin.createUserInFonteva();
		fontevaJoin.joinCreatedUser(testData.testDataProvider().getProperty("membershipType"),
				testData.testDataProvider().getProperty("selection"));
		fontevaJoin.enterLicenseDetail();
		fontevaJoin.createSalesOrder(testData.testDataProvider().getProperty("paymentMethod"));
		fontevaJoin.applyPayment();
		// We set the process exception
		processException.createNewProcessException(dataList.get(0) + " " + dataList.get(1),
				testData.testDataProvider().getProperty("activityOption"),
				testData.testDataProvider().getProperty("enterNote"),
				testData.testDataProvider().getProperty("reasonOption"),
				testData.testDataProvider().getProperty("intitialReachOutOption"),
				testData.testDataProvider().getProperty("statusOption"));
		//We Validate process exception is created
		processException.validateProcessException(testData.testDataProvider().getProperty("activityOption"),
				testData.testDataProvider().getProperty("reasonOption"),
				testData.testDataProvider().getProperty("intitialReachOutOption"),
				testData.testDataProvider().getProperty("enterNote"));
	}

}
