package org.aia.testcases.chapterPortal;

import java.io.IOException;

import org.aia.pages.BaseClass;
import org.aia.pages.api.membership.FontevaConnectionSOAP;
import org.aia.pages.fonteva.chapterPortal.ActiveMemberRoster;
import org.aia.pages.fonteva.chapterPortal.ArchitectsLicenced;
import org.aia.pages.fonteva.chapterPortal.AssociatePathToLicence;
import org.aia.pages.fonteva.chapterPortal.ChapterInfo;
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

@Listeners(org.aia.utility.GenerateReportsListener.class)
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
	ChapterInfo chapterInfo;
	AssociatePathToLicence associatePathToLicence;
	ArchitectsLicenced architectsLicenced;
	

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
		chapterInfo = PageFactory.initElements(driver, ChapterInfo.class);
		associatePathToLicence =PageFactory.initElements(driver, AssociatePathToLicence.class);
		architectsLicenced = PageFactory.initElements(driver, ArchitectsLicenced.class);
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
	
	@Test(description = "FM-422: Tab's verification on My Chapters page", enabled = false, priority = 2)
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
	
	@Test(description = "FM-425: Membership Tab page and its section's verification", enabled = false, priority = 3)
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
	
	@Test(description = "FM-426: Full Member Roster report page ", enabled = false, priority = 4)
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
	
	@Test(description = "FM-427: Active Member Roaster report page ", enabled = false, priority = 5)
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
	
	@Test(description = "FM-428: Export functionality  Active Member Roaster report page ", enabled = false, priority = 6)
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
	
	@Test(description = "FM-429: Chapter Info Tab page verification", enabled = false, priority = 7)
	public void test_VerificationChapterInfo(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_VerificationChapterInfo");
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
		chapterInfo.getdetailsStaffInChapterInfo();
		chapterInfo.getLeaderShipTabledataInChapterInfo();
		chapterInfo.getServiceAreaContactsInChapterINfo();
		
	}
	
	@Test(description = "FM-437: Terminated Members report page", enabled = false, priority = 8)
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
	
	@Test(description = "FM-438: Lapsed Members report page", enabled = false, priority = 9)
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
	
	@Test(description = "FM-439: Upgrade to Architect report page", enabled = false, priority = 10)
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
		upgradeToArchitect.clickUpgradeToArchitectTab();
		upgradeToArchitect.getUpgradeToArchitectTabRecordsCount();
	}
	
	@Test(description = "FM-440: Upgrade to Emeritus report page", enabled = false, priority = 11)
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
	
	@Test(description = "FM-441: \"Associate/Path to Licensure\" report page", enabled = false, priority = 12)
	public void test_ValidateAssociatePathToLicensure(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_ValidateAssociatePathToLicensure");
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
	    associatePathToLicence.clickAssociatesPathtoLicensureTab();
	    associatePathToLicence.getAssociatesPathtoLicensureRecordsCount();
	    associatePathToLicence.getAssociatesPathtoLicensureRecordsData();
	}
	
	@Test(description = "FM-442: \"Active Architect Members Licensed <10Yrs\" report page", enabled = false, priority = 13)
	public void test_ValidateActiveArchitectMembersLicensed10Yrs(ITestContext context) throws InterruptedException, Throwable {
		if (recording) {
			VideoRecorder.startRecording("test_ValidateActiveArchitectMembersLicensed10Yrs");
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
		architectsLicenced.clickArchitectsLicensedTenyearsTab();
		architectsLicenced.getArchitectsLicensedTenyearsRecordsCount();
		architectsLicenced.getArchitectsLicensedTenyearsRecordsData();
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
