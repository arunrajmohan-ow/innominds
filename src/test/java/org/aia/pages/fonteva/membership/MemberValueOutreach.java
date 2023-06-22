package org.aia.pages.fonteva.membership;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * @author IM-RT-LP-1483 (Suhas)
 *
 */
public class MemberValueOutreach {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	static Logger log = Logger.getLogger(ContactCreateUser.class);
	Actions action;
	JavascriptExecutor executor;

	/**
	 * @param Idriver
	 */
	public MemberValueOutreach(WebDriver Idriver) {
		driver = Idriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
	}

	@FindBy(xpath = "//div[contains(@class,'modal__header')]//h2")
	WebElement mvoPopupHeading;

	String getMVOFields = "//lightning-combobox//label";

	String moveDrpSelection = "//span[contains(@class,'media__body')]//span[text()='%s']";

	@FindBy(xpath = "//label[text()='Contact']")
	WebElement contactDrpLable;

	@FindBy(xpath = "//button[contains(@aria-label,'Membership Year')]")
	WebElement membershipYearDrp;

	@FindBy(xpath = "//button[contains(@aria-label,'Round ')]")
	WebElement roundDrp;

	@FindBy(xpath = "//button[contains(@aria-label,'Call Category,')]")
	WebElement contactCatogaryDrp;

	@FindBy(xpath = "//button[contains(@aria-label,'Call Outcome,')]")
	WebElement callOutComeDrp;
	
	@FindBy(xpath="//textarea")
	WebElement generalNotes;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;

	/**
	 * @param round
	 * @param contactCatogary
	 * @param callOutCome
	 * @param generalNote 
	 * 
	 */
	public void createNewMVO(String membershipYear, String round, String contactCatogary, String callOutCome, String generalNote) {
		util.waitUntilElement(driver, mvoPopupHeading);
		assertTrue(mvoPopupHeading.isDisplayed());
		assertEquals(mvoPopupHeading.getText(), data.testDataProvider().getProperty("movPopUp"));
		ArrayList<String> mvoRightTextList = new ArrayList<String>();
		List<WebElement> mvoRightList = driver.findElements(By.xpath(getMVOFields));
		for (WebElement fieldList : mvoRightList) {
			mvoRightTextList.add(fieldList.getText());
		}
		assertTrue(contactDrpLable.isDisplayed());
		assertEquals(mvoRightTextList.toString(), data.testDataProvider().getProperty("movPopUpFields"));
		membershipYearDrp.click();
		util.getCustomizedWebElement(driver, moveDrpSelection, membershipYear).click();
		util.waitUntilElement(driver, roundDrp);
		roundDrp.click();
		util.getCustomizedWebElement(driver, moveDrpSelection, round).click();
		util.waitUntilElement(driver, contactCatogaryDrp);
		contactCatogaryDrp.click();
		util.getCustomizedWebElement(driver, moveDrpSelection, contactCatogary).click();
		util.waitUntilElement(driver, callOutComeDrp);
		callOutComeDrp.click();
		util.getCustomizedWebElement(driver, moveDrpSelection, callOutCome).click();
		util.enterText(driver, generalNotes, generalNote);
		saveBtn.click();
	}

}
