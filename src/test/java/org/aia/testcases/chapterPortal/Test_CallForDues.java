package org.aia.testcases.chapterPortal;

import java.io.IOException;

import org.aia.pages.BaseClass;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.fonteva.chapterPortal.ChapterInfo;
import org.aia.pages.fonteva.chapterPortal.MemberShipInChapterPortal;
import org.aia.pages.fonteva.chapterPortal.NavigateToChapterPortal;
import org.aia.pages.fonteva.chapterPortal.CallForDues;
import org.aia.utility.BrowserSetup;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.aia.utility.VideoRecorder;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Test_CallForDues extends BaseClass {
	boolean recording;
	NavigateToChapterPortal naToChapterPortal;
	MemberShipInChapterPortal memChapterPortal;
	CallForDues callForDues;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		testData = new ConfigDataProvider();
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				testData.getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		recording = Boolean.parseBoolean(testData.testDataProvider().getProperty("videoRecording"));
		naToChapterPortal = PageFactory.initElements(driver, NavigateToChapterPortal.class);
		memChapterPortal= PageFactory.initElements(driver, MemberShipInChapterPortal.class);
		callForDues = PageFactory.initElements(driver, CallForDues.class);
		Logging.configure();
	}
	
	@Test(description = "FM-412: \"Call For Dues\" Component Contact section", enabled = true, priority = 1)
	public void test_VerificationCallForDues(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_VerificationCallForDues");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
		memChapterPortal.clickChapterInfoTab();
		callForDues.clickCallForDuesInfoTab();
		callForDues.getParagraphTextInComponent();
		callForDues.getcontactDetailsTextInComponent();
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown(ITestResult result) throws IOException {
		if (recording) {
			VideoRecorder.stopRecording();
		}
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("LOG : FAIL Test failed to executed");
			Utility.takeScreenShotAfterFail(driver, result);
		}
		BrowserSetup.closeBrowser(driver);
	}


}
