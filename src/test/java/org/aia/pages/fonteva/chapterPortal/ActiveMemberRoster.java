package org.aia.pages.fonteva.chapterPortal;

import java.util.List;

import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class ActiveMemberRoster {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	Actions act;
	static Logger log = Logger.getLogger(ActiveMemberRoster.class);

	public ActiveMemberRoster(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
	}
	
	@FindBy(xpath = "//button[text()='Active Member Roster']") WebElement activeMemberRoasterTab;
	
	@FindBy(xpath = "//iframe[@title='Report Viewer']") WebElement activeMemberRoasterFrame;
	
	@FindBy(xpath = "//div[@title='Total Records']/parent::li") WebElement activeMemberRoasterRecords;
	
	@FindAll(value = {@FindBy(xpath = "//table[@class='data-grid-table data-grid-full-table']//tr[contains(@class,'data-grid-table-row')]")}) List<WebElement> activeMemberRecordsData;
	
	
	public void clickActiveMemberRosterTab() throws Throwable {
		activeMemberRoasterTab.click();
		Thread.sleep(10000);
		util.switchToTabs(driver, 1);
	}
	
	public void getActiveMemberRosterRecordsCount() {
		util.waitForJavascript(driver, 10000, 2000);
		util.switchToFrameUsingWebElement(driver, activeMemberRoasterFrame);
		Utility.waitForWebElement(driver, activeMemberRoasterRecords, 0);
		String recordsCount = activeMemberRoasterRecords.getText();
		System.out.println(recordsCount);
	}
	
	public void getActiveMembersRecordsData() throws Throwable {
		Thread.sleep(8000);
		System.out.println(activeMemberRecordsData.size());
		for (int i = 0; i < activeMemberRecordsData.size(); i++) {
		String recordsData = activeMemberRecordsData.get(i).getText();
		System.out.println(recordsData.replace("\n", ""));
		log.info(recordsData.replace("\n", ""));
		System.out.println("records count"+i);
		}
		util.switchToTabs(driver, 0);
		driver.switchTo().defaultContent();
	}

}

