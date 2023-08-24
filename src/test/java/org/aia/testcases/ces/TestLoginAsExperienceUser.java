package org.aia.testcases.ces;

import org.aia.pages.BaseClass;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.ces.FontevaCES;
import org.aia.pages.fonteva.ces.CES_ContactPage;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestLoginAsExperienceUser extends BaseClass {
	FontevaCES fontevaPage;
	CES_ContactPage ces_ContactPage;
	public ExtentReports extent;
	public ExtentTest extentTest;
	final static Logger logger = Logger.getLogger(TestRenewPassport_CES.class);

	@BeforeMethod
	public void setUp() throws Exception {
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				DataProviderFactory.getConfig().getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		util = new Utility(driver, 30);
		testData = new ConfigDataProvider();
		fontevaPage = PageFactory.initElements(driver, FontevaCES.class);
		ces_ContactPage = PageFactory.initElements(driver, CES_ContactPage.class);
	}

	@Test(priority = 1, description = "Validate Login experience user in multiple module.", enabled = true)
	public void verifyLoginAsExpUser() throws InterruptedException {
		ces_ContactPage.userData();
		ces_ContactPage.createNewContactInFonteva();
		ces_ContactPage.joinCreatedUser(testData.testDataProvider().getProperty("membershipType"),
				testData.testDataProvider().getProperty("selection"));
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		BrowserSetup.closeBrowser(driver);

	}

}
