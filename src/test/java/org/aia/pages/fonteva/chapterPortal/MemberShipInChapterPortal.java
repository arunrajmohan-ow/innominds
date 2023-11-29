package org.aia.pages.fonteva.chapterPortal;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MemberShipInChapterPortal {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	Actions act;
	static Logger log = Logger.getLogger(MemberShipInChapterPortal.class);
	public String salesOrder = "";
	public String aiaNumber = "";

	public MemberShipInChapterPortal(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
	}
	
	@FindBy(xpath = "//button[text()='Retention']") WebElement retentionTab;
	
	@FindBy(xpath = "//button[text()='Finance']") WebElement financeTab; 
	
	@FindBy(xpath = "//button[text()='Chapter Info']") WebElement chapterInfoTab; 
	
	@FindBy(xpath = "//button[text()='Call for Dues']") WebElement callForDuesInfoTab;
	
	public void clickRetentionTab() {
		Utility.waitForWebElement(driver, retentionTab, 0);
		Assert.assertTrue(retentionTab.isDisplayed());
		retentionTab.click();
		util.waitForJavascript(driver, 10000, 2000);
	}
	
	public void clickFinanceTab() {
		Utility.waitForWebElement(driver, financeTab, 0);
		Assert.assertTrue(financeTab.isDisplayed());
		financeTab.click();
		util.waitForJavascript(driver, 10000, 2000);
	}
	
	public void clickChapterInfoTab() {
		Utility.waitForWebElement(driver, chapterInfoTab, 0);
		Assert.assertTrue(chapterInfoTab.isDisplayed());
		chapterInfoTab.click();
		util.waitForJavascript(driver, 10000, 2000);
	}
	
	public void clickCallForDuesInfoTab() {
		Utility.waitForWebElement(driver, callForDuesInfoTab, 0);
		Assert.assertTrue(callForDuesInfoTab.isDisplayed());
		callForDuesInfoTab.click();
		util.waitForJavascript(driver, 10000, 2000);
	}
	

}
