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

public class Memberships {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	static Logger log = Logger.getLogger(ContactCreateUser.class);
	Actions action;
	JavascriptExecutor executor;

	/**
	 * @param Idriver
	 */
	public Memberships(WebDriver Idriver) {
		this.driver = Idriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
	}

	@FindBy(xpath = "//a/slot/span[contains(text(),'Memberships')]")
	WebElement membership;

	@FindBy(xpath = "//table//tbody//tr//th//a")
	WebElement membershipSubId;

	@FindBy(xpath = "//h2//span[@title='Terms']")
	WebElement terms;
	@FindBy(xpath = "//table[@aria-label='Terms']/tbody/tr/th//span/a")
	WebElement termId;

	@FindBy(xpath = "//button[text()='Save']")
	WebElement saveBtn;

	@FindBy(xpath = "//table[@aria-label='Memberships']/tbody/tr/th")
	WebElement tableSubscriptionId;

	@FindBy(xpath = "//input[@name='OrderApi__Term_End_Date__c']")
	WebElement inputTermEndDate;

	@FindBy(xpath = "//input[@name='OrderApi__Grace_Period_End_Date__c']")
	WebElement inputTermGraceDate;

	@FindBy(xpath = "//button[@title='Edit Term End Date']/span")
	WebElement editBtn;

	String contactName = "//h2[text()='Tabs']//parent::div//span[text()='%s']//ancestor::a";

	@FindBy(xpath = "//a[contains(text(),'Show All')]")
	WebElement showAll;

	/**
	 * @param userFullname
	 * @throws InterruptedException
	 * 
	 */
	public void terminateUser(String userFullname) throws InterruptedException {
		Thread.sleep(7000);
		WebElement selectContact = driver.findElement(By.xpath(String.format(contactName, userFullname)));
		executor.executeScript("arguments[0].click();", selectContact);
		util.waitUntilElement(driver, showAll);
		showAll.click();
		util.waitUntilElement(driver, showAll);
		showAll.click();
		util.waitUntilElement(driver, membership);
		action.moveToElement(membership).build().perform();
		membership.click();
		util.waitUntilElement(driver, membershipSubId);
		membershipSubId.click();
		util.waitUntilElement(driver, terms);
		action.moveToElement(terms).build().perform();
		terms.click();
		util.waitUntilElement(driver, termId);
		termId.click();
		util.waitUntilElement(driver, editBtn);
		Thread.sleep(5000);
		action.scrollToElement(editBtn).build().perform();
		editBtn.click();
		util.waitUntilElement(driver, inputTermEndDate);
		inputTermEndDate.clear();
		inputTermEndDate.sendKeys(data.testDataProvider().getProperty("termEndDate"));
		util.waitUntilElement(driver, inputTermGraceDate);
		inputTermGraceDate.clear();
		inputTermGraceDate.sendKeys(data.testDataProvider().getProperty("termGraceDate"));
		saveBtn.click();
	}
}
