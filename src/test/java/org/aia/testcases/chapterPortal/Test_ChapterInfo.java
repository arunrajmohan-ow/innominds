package org.aia.testcases.chapterPortal;

import java.io.IOException;
import org.aia.pages.BaseClass;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.fonteva.chapterPortal.ChapterInfo;
import org.aia.pages.fonteva.chapterPortal.GlobalSearch;
import org.aia.pages.fonteva.chapterPortal.MemberShipInChapterPortal;
import org.aia.pages.fonteva.chapterPortal.NavigateToChapterPortal;
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

public class Test_ChapterInfo extends BaseClass {
	boolean recording;
	NavigateToChapterPortal naToChapterPortal;
	MemberShipInChapterPortal memChapterPortal;
	CommonMehodsInCP commonMehodsInCP;
	ChapterInfo chapterInfo;
	GlobalSearch globalSearch;
	
	
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		testData = new ConfigDataProvider();
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				testData.getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		recording = Boolean.parseBoolean(testData.testDataProvider().getProperty("videoRecording"));
		naToChapterPortal = PageFactory.initElements(driver, NavigateToChapterPortal.class);
		memChapterPortal= PageFactory.initElements(driver, MemberShipInChapterPortal.class);
		chapterInfo = PageFactory.initElements(driver, ChapterInfo.class);
		globalSearch = PageFactory.initElements(driver, GlobalSearch.class);
		commonMehodsInCP = new CommonMehodsInCP(driver);
		Logging.configure();
	}
	
	@Test(description = "FM-429: Chapter Info Tab page verification", enabled = true, priority = 1)
	public void test_VerificationChapterInfo(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_VerificationChapterInfo");
		}
		commonMehodsInCP.navigationChapterPortal("Allison Garwood Freedland");
		naToChapterPortal.getComunityGroup(1);
		memChapterPortal.clickChapterInfoTab();
		chapterInfo.getdetailsStaffInChapterInfo();
		chapterInfo.getLeaderShipTabledataInChapterInfo();
		chapterInfo.getServiceAreaContactsInChapterINfo();
		chapterInfo.fileUploadInChapterInfo();
		//pending It is not working in headless mode..
		//chapterInfo.validateUploadFilePopup();
		//chapterInfo.deleteFileInNotesAndAttachments("Delete");
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
