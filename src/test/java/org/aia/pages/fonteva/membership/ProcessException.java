package org.aia.pages.fonteva.membership;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

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

	@FindBy(xpath = "//span[contains(@title,'Reinstating Member')]")
	WebElement activityOpt;

	@FindBy(xpath = "//textarea")
	WebElement noteInput;

	@FindBy(xpath = "//button[contains(@aria-label,'Reason')]")
	WebElement reasonDrp;

	@FindBy(xpath = "//span[text()='Declined Card']")
	WebElement reasonOpt;

	@FindBy(xpath = "//button[contains(@aria-label,'Initial ')]")
	WebElement initialReachOutDrp;

	@FindBy(xpath = "//span[text()='By Phone']")
	WebElement initialReachOutOpt;

	@FindBy(xpath = "//button[contains(@aria-label,'Status')]")
	WebElement statusDrp;

	@FindBy(xpath = "//span[text()='Pending']")
	WebElement statusOpt;

	@FindBy(xpath = "//button[text()='Save']")
	WebElement saveBtn;

	@FindBy(xpath="//span[text()='Reinstating Member']")
	WebElement activityText;
	/**
	 * @param fullName
	 * @throws InterruptedException
	 */
	public void createNewProcessException(String fullName) throws InterruptedException {
		Thread.sleep(60000);
		util.waitUntilElement(driver, driver.findElement(By.xpath(String.format(contact, fullName))));
		;
		WebElement selectContact = driver.findElement(By.xpath(String.format(contact, fullName)));
		executor.executeScript("arguments[0].click();", selectContact);
		util.waitUntilElement(driver, processExceptionTab);
		processExceptionTab.click();
		util.waitUntilElement(driver, newBtn);
		newBtn.click();
		util.waitUntilElement(driver, activityDrp);
		activityDrp.click();
		util.waitUntilElement(driver, activityOpt);
		String activityPrimary=activityOpt.getText();
		activityOpt.click();
		util.enterText(driver, noteInput, "testing");
		util.waitUntilElement(driver, reasonDrp);
		reasonDrp.click();
		util.waitUntilElement(driver, reasonOpt);
		reasonOpt.click();
		util.waitUntilElement(driver, initialReachOutDrp);
		initialReachOutDrp.click();
		util.waitUntilElement(driver, initialReachOutOpt);
		initialReachOutOpt.click();
		util.waitUntilElement(driver, statusDrp);
		statusDrp.click();
		util.waitUntilElement(driver, statusOpt);
		statusOpt.click();
		saveBtn.click();
		util.waitUntilElement(driver, activityText);
		String activityName=activityText.getText();
		//Here we validate process exception is created or not 
		Assert.assertEquals(activityName, activityPrimary);
	}

}
