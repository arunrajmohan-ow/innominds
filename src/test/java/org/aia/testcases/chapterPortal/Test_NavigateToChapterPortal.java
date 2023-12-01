package org.aia.testcases.chapterPortal;

import java.io.IOException;

import org.aia.pages.BaseClass;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.fonteva.chapterPortal.ActiveMemberRoster;
import org.aia.pages.fonteva.chapterPortal.FullMemberRoster;
import org.aia.pages.fonteva.chapterPortal.LapsedMembers;
import org.aia.pages.fonteva.chapterPortal.MemberShipInChapterPortal;
import org.aia.pages.fonteva.chapterPortal.NavigateToChapterPortal;
import org.aia.pages.fonteva.chapterPortal.TerminatedMembers;
import org.aia.pages.fonteva.chapterPortal.UpgradeToArchitect;
import org.aia.pages.fonteva.chapterPortal.UpgradeToEmeritus;
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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class Test_NavigateToChapterPortal extends BaseClass {
	boolean recording;
	NavigateToChapterPortal naToChapterPortal;
	MemberShipInChapterPortal memChapterPortal;
	FullMemberRoster fullMemberRoster;
	ActiveMemberRoster activeMemberRoster;
	LapsedMembers lapsedMembers;
	TerminatedMembers terminatedMembers;
	UpgradeToArchitect upgradeToArchitect;
	UpgradeToEmeritus upgradeToEmeritus; 
	

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		testData = new ConfigDataProvider();
		sessionID = new FontevaConnectionSOAP();
		driver = BrowserSetup.startApplication(driver, DataProviderFactory.getConfig().getValue("browser"),
				testData.getValue("fontevaSessionIdUrl") + sessionID.getSessionID());
		recording = Boolean.parseBoolean(testData.testDataProvider().getProperty("videoRecording"));
		naToChapterPortal = PageFactory.initElements(driver, NavigateToChapterPortal.class);
		memChapterPortal= PageFactory.initElements(driver, MemberShipInChapterPortal.class);
		fullMemberRoster=PageFactory.initElements(driver, FullMemberRoster.class);
		activeMemberRoster = PageFactory.initElements(driver, ActiveMemberRoster.class);
		lapsedMembers=PageFactory.initElements(driver, LapsedMembers.class);
		upgradeToArchitect = PageFactory.initElements(driver, UpgradeToArchitect.class);
		upgradeToEmeritus= PageFactory.initElements(driver, UpgradeToEmeritus.class);
		terminatedMembers = PageFactory.initElements(driver, TerminatedMembers.class);
		Logging.configure();
	}

	@Test(description = "FM-421: My chapters page and community group name verification", enabled = true, priority = 1)
	public void test_NavigationToCP(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_navigationToCP");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
	}
	
	@Test(description = "FM-422: Tab's verification on My Chapters page", enabled = true, priority = 2)
	public void test_VerificationTabsInCP(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_navigationToCP");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
		memChapterPortal.clickRetentionTab();
		memChapterPortal.clickFinanceTab();
		memChapterPortal.clickChapterInfoTab();
		memChapterPortal.clickCallForDuesInfoTab();
	}
	
	@Test(description = "FM-425: Membership Tab page and its section's verification", enabled = true, priority = 3)
	public void test_ValidateMembersShipTabSections(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_ValidateMembersShipTabSections");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
		memChapterPortal.validateMemberShipTabSections();
		memChapterPortal.validateMemberShipPiecharts();
	}
	
	@Test(description = "FM-426: Full Member Roster report page ", enabled = true, priority = 4)
	public void test_ValidateFullMemberRoster(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_ValidateFullMemberRoster");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
		memChapterPortal.validateMemberShipTabSections();
		fullMemberRoster.clickFullMemberRosterTab();
		fullMemberRoster.getFullMemberRosterRecordsCount();
		fullMemberRoster.getRecordsData();
	}
	
	@Test(description = "FM-427: Active Member Roaster report page ", enabled = true, priority = 5)
	public void test_ValidateActiveMemberRoster(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_ValidateActiveMemberRoster");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
		memChapterPortal.validateMemberShipTabSections();
		activeMemberRoster.clickActiveMemberRosterTab();
		activeMemberRoster.getActiveMemberRosterRecordsCount();
		activeMemberRoster.getActiveMembersRecordsData();
	}
	
	@Test(description = "FM-428: Export functionality  Active Member Roaster report page ", enabled = true, priority = 6)
	public void test_ExportfuncActiveMemberRoster(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_ExportfuncActiveMemberRoster");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
		memChapterPortal.validateMemberShipTabSections();
		activeMemberRoster.clickActiveMemberRosterTab();
		String recordCount = activeMemberRoster.getActiveMemberRosterRecordsCount();
		activeMemberRoster.getActiveMembersRecordsData();
		activeMemberRoster.clickExportButton();
		activeMemberRoster.selectExportView("Formatted");
		activeMemberRoster.clickExportButtonInexportPopup("Export");
		activeMemberRoster.validateDownloafFileDataAndApplicationdata(recordCount);
	}
	
	@Test(description = "FM-437: Terminated Members report page", enabled = true, priority = 7)
	public void test_ValidateTerminatedMemberRoster(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_ValidateTerminatedMemberRoster");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
		memChapterPortal.validateMemberShipTabSections();
		terminatedMembers.clickTerminatedMemberRosterTab();
		terminatedMembers.getTerminatedMemberRosterRecordsCount();
		terminatedMembers.getTerminatedMembersRecordsData();
	}
	
	@Test(description = "FM-438: Lapsed Members report page", enabled = true, priority = 8)
	public void test_ValidateLapsedMemberRoster(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_ValidateLapsedMemberRoster");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
		memChapterPortal.validateMemberShipTabSections();
		lapsedMembers.clickLapsedMemberRosterTab();
		lapsedMembers.getLapsedMemberRosterRecordsCount();
		lapsedMembers.getLapsedMembersRecordsData();
	}
	
	@Test(description = "FM-439: Upgrade to Architect report page", enabled = true, priority = 9)
	public void test_ValidateUpgradeToArchitect(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_ValidateUpgradeToArchitect");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
		memChapterPortal.validateMemberShipTabSections();
		upgradeToArchitect.clicupgradeToArchitectTab();
		upgradeToArchitect.getUpgradeToArchitectTabRecordsCount();
	}
	
	@Test(description = "FM-440: Upgrade to Emeritus report page", enabled = true, priority = 10)
	public void test_ValidateUpgradeToEmeritus(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_ValidateUpgradeToEmeritus");
		}
		naToChapterPortal.clickContactsModule();
		naToChapterPortal.clickContactsCPAccess();
		naToChapterPortal.showAllInRealtedQuickLinks();
		naToChapterPortal.getPortalAccessCount();
		naToChapterPortal.clickDropDownInActionContainer();
		naToChapterPortal.optionsInactionContainer();
		naToChapterPortal.clickMyChapterTab();
		naToChapterPortal.getComunityGroup();
		memChapterPortal.validateMemberShipTabSections();
		upgradeToEmeritus.clicUpgradeToEmeritusTab();
		upgradeToEmeritus.getUpgradeToEmeritusTabRecordsCount();
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
