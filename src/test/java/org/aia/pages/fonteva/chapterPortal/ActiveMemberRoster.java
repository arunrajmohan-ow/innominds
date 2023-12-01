package org.aia.pages.fonteva.chapterPortal;

import java.util.ArrayList;
import java.util.List;

import org.aia.utility.ExcelDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ActiveMemberRoster {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	Actions act;
	ExcelDataProvider excelDataProvider;
	
	static Logger log = Logger.getLogger(ActiveMemberRoster.class);

	public ActiveMemberRoster(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
	}
	
	@FindBy(xpath = "//button[text()='Active Member Roster']") WebElement activeMemberRoasterTab;
	
	@FindBy(xpath = "//iframe[@title='Report Viewer']") WebElement activeMemberRoasterFrame;
	
	@FindBy(xpath = "//div[@title='Total Records']/parent::li") WebElement activeMemberRoasterRecords;
	
	@FindBy(xpath = "//button[text()='Export']") WebElement exportButtonInActiveMem;
	
	@FindBy(css = "select[class='slds-select']") WebElement formatDropDown;
	 
	@FindAll(value = {@FindBy(xpath = "//table[@class='data-grid-table data-grid-full-table']//tr[contains(@class,'data-grid-table-row')]")}) List<WebElement> activeMemberRecordsData;
	
	
	public void clickActiveMemberRosterTab() throws Throwable {
		activeMemberRoasterTab.click();
		Thread.sleep(10000);
		util.switchToTabs(driver, 1);
	}
	
	public void clickExportButton() {
		Utility.waitForWebElement(driver, exportButtonInActiveMem, 0);
		exportButtonInActiveMem.click();
		driver.switchTo().defaultContent();
	}
	
	public void selectExportView(String option) throws Throwable {
		switch (option) {
		case "Formatted":
			Thread.sleep(5000);
			System.out.println("khd");
			WebElement formatElem = driver.findElement(By.cssSelector("label[for='formatted-export']"));
			Assert.assertTrue(formatElem.isDisplayed());
			formatElem.click();
			Assert.assertFalse(formatDropDown.isEnabled());
			log.info("format drop down is disabled");
			break;
		case "DetailsOnly":
			WebElement detilsElem = driver.findElement(By.cssSelector("label[for='data-export']"));	
			detilsElem.click();
			Assert.assertTrue(formatDropDown.isEnabled());
		}
	}
	
	public void clickExportButtonInexportPopup(String option) {
		switch (option) {
		case "Export":
		WebElement exportEle =	driver.findElement(By.xpath("//div[@class='modal-footer slds-modal__footer']//button[@title='"+option+"']"));
		exportEle.click();
			break;
		case "Cancel":
			//TODO:
		}
	}
	
	public String getActiveMemberRosterRecordsCount() {
		util.waitForJavascript(driver, 10000, 2000);
		util.switchToFrameUsingWebElement(driver, activeMemberRoasterFrame);
		Utility.waitForWebElement(driver, activeMemberRoasterRecords, 0);
		String recordsCount = activeMemberRoasterRecords.getText();
		String count = recordsCount.replace("Total Records\n", "");
		System.out.println(recordsCount);
		return count;
	}
	
	public void validateDownloafFileDataAndApplicationdata(String uiActiveMemRecordCount) {
		excelDataProvider = new ExcelDataProvider("Portal Active Member Roster");
		ArrayList<String> excelData = new ArrayList<String>();
		for (int i = 16; i < 46; i++) {
			 excelData .add(excelDataProvider.getCellData("Portal Active Member Roster", i, 1));
		}
		Assert.assertEquals(excelData.size(),Integer.parseInt(uiActiveMemRecordCount));
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
	}

}

