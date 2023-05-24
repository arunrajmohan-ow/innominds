package org.aia.pages.fonteva.membership;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProcessException {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	static Logger log = Logger.getLogger(ContactCreateUser.class);
	Actions action;
	JavascriptExecutor executor;

	public ProcessException(WebDriver Idriver) {
		this.driver = Idriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
	}

	String contact = "//span[text()='%s']//ancestor::a";

	@FindBy(xpath = "//span[contains(text(),'Processing Exceptions')]/ancestor::a")
	WebElement processExceptionTab;

	@FindBy(xpath = "//a[@title=\"New\"]")
	WebElement newBtn;

	@FindBy(xpath = "//button[contains(@aria-label,'Activity')]")
	WebElement activityDrp;

	String activityOpt = "//span[contains(@title,'%s')]";

	@FindBy(xpath = "//textarea")
	WebElement noteInput;

	@FindBy(xpath = "//button[contains(@aria-label,'Reason')]")
	WebElement reasonDrp;

	String reasonOpt = "//span[text()='%s']";

	@FindBy(xpath = "//button[contains(@aria-label,'Initial ')]")
	WebElement initialReachOutDrp;

	String initialReachOutOpt = "//span[text()='%s']";

	@FindBy(xpath = "//button[contains(@aria-label,'Status')]")
	WebElement statusDrp;

	String statusOpt = "//span[text()='%s']";

	@FindBy(xpath = "//button[text()='Save']")
	WebElement saveBtn;

	@FindBy(xpath="//table[@aria-label]")
	WebElement exceptionTable;

	@FindBy(xpath="//td[2]/span/span")
	WebElement activityText;
	
	@FindBy(xpath="//td[3]/span/span")
	WebElement initialReachOutText;
	
	@FindBy(xpath="//td[4]/span/span")
	WebElement reasonText;
	
	@FindBy(xpath="//td[5]/span/span")
	WebElement noteText;
	
	@FindBy(xpath="//h2[text()='New Processing Exception']")
	WebElement heading;

	/**
	 * @param fullName
	 * @param reasonOption
	 * @param intitialReachOutOption
	 * @param statusOption
	 * @throws InterruptedException
	 */
	public void createNewProcessException(String fullName, String activityOption, String enterNote, String reasonOption,
			String intitialReachOutOption, String statusOption) throws InterruptedException {
		Thread.sleep(60000);
		WebElement selectContact = util.getCustomizedWebElement(driver, contact, fullName);
		executor.executeScript("arguments[0].click();", selectContact);
		util.waitUntilElement(driver, processExceptionTab);
		processExceptionTab.click();
		util.waitUntilElement(driver, newBtn);
		newBtn.click();
		util.waitUntilElement(driver, heading);
		assertTrue(heading.isDisplayed());
		util.waitUntilElement(driver, activityDrp);
		activityDrp.click();
		util.getCustomizedWebElement(driver, activityOpt, activityOption).click();
		util.enterText(driver, noteInput, enterNote);
		util.waitUntilElement(driver, reasonDrp);
		reasonDrp.click();
	    util.getCustomizedWebElement(driver, reasonOpt, reasonOption).click();
		util.waitUntilElement(driver, initialReachOutDrp);
		initialReachOutDrp.click();
		util.getCustomizedWebElement(driver, initialReachOutOpt, intitialReachOutOption).click();
		util.waitUntilElement(driver, statusDrp);
		statusDrp.click();
		util.getCustomizedWebElement(driver, statusOpt, statusOption).click();
		saveBtn.click();
	}

	/**
	 * @param activity
	 * @param reason
	 * @param initialReach
	 * @param status
	 */
	public void validateProcessException(String activity, String reason, String initialReach, String note) {
		assertTrue(exceptionTable.isDisplayed());
		util.waitUntilElement(driver, activityText);
		assertEquals(activityText.getText(), activity);
		assertEquals(reasonText.getText(), reason);
		assertEquals(initialReachOutText.getText(), initialReach);
		assertEquals(noteText.getText(), note);
	}

}
