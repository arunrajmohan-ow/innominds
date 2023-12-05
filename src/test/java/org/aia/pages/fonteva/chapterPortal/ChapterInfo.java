package org.aia.pages.fonteva.chapterPortal;
import java.util.ArrayList;
import java.util.List;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ChapterInfo {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	Actions act;
	static Logger log = Logger.getLogger(ChapterInfo.class);

	public ChapterInfo(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
	}
	
	@FindBy(xpath = "//button[text()='Chapter Info']") WebElement chapterInfoTab;
	// 
	@FindAll(value = {@FindBy(xpath  = "//table[@data-aura-class='uiVirtualDataTable' and @aria-label='Leadership']/tbody//tr")}) List<WebElement> leaderhipTableDataInChatpterInfo;
	
	@FindAll(value = {@FindBy(xpath  = "//table[@data-aura-class='uiVirtualDataTable' and @aria-label='Service Area Contacts']/tbody//tr")}) List<WebElement> serviceAreaContactsTableDataInChatpterInfo;
	
	@FindAll(value = {@FindBy(xpath  = "//article[@class='slds-card']")}) List<WebElement> detailsStaffInfoInChatpterInfo;

	public void clickChapterInfoTab() {
		Utility.waitForWebElement(driver, chapterInfoTab, 0);
		Assert.assertTrue(chapterInfoTab.isDisplayed());
		chapterInfoTab.click();
		util.waitForJavascript(driver, 10000, 2000);
	}
	
	public void getdetailsStaffInChapterInfo() throws Throwable {
		Thread.sleep(3000);
		ArrayList<String> detailsStaffInfo = new ArrayList<String>();
		for (int i = 0; i < detailsStaffInfoInChatpterInfo.size(); i++) {
			 detailsStaffInfo.add(detailsStaffInfoInChatpterInfo.get(i).getText());
			System.out.println(detailsStaffInfo);
		}
	}
	
	public void getLeaderShipTabledataInChapterInfo() throws Throwable {
		Thread.sleep(3000);
		ArrayList<String> leaderShipDataInfo = new ArrayList<String>();
		for (int i = 0; i < leaderhipTableDataInChatpterInfo.size(); i++) {
			leaderShipDataInfo.add(leaderhipTableDataInChatpterInfo.get(i).getText());
		}
		System.out.println(leaderShipDataInfo.size());
	}
	
	public void getServiceAreaContactsInChapterINfo() throws Throwable {
		Thread.sleep(3000);
		ArrayList<String> serviceAreaContacsInfo = new ArrayList<String>();
		for (int i = 0; i < serviceAreaContactsTableDataInChatpterInfo.size(); i++) {
			serviceAreaContacsInfo.add(serviceAreaContactsTableDataInChatpterInfo.get(i).getText());
			System.out.println(serviceAreaContacsInfo);
		}
	}
	
	
}
