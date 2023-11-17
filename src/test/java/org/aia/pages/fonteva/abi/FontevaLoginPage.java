package org.aia.pages.fonteva.abi;

import org.aia.pages.api.ces.SubscriptionPlanPrice;
import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class FontevaLoginPage {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	static Logger log = Logger.getLogger(ContactCreateUser.class);
	Actions action;
	JavascriptExecutor executor;

	public FontevaLoginPage(WebDriver Idriver) {
		this.driver = Idriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
	}

	@FindBy(xpath = "//button[@aria-label='Search' and @type='button']")
	WebElement search;
	
	@FindBy(xpath = "//div[@class='forceSearchAssistantDialog']//input[@type='search']")
	WebElement searchInput;
	
	@FindBy(xpath = "//ul[starts-with(@class,'scopesListSection slds-has-block-links--space')]//a[@title='Sales Orders']")
	WebElement salesOrders;
	
	@FindBy(xpath = "//span[@class='slds-grid slds-grid--align-spread']/a")
	WebElement salesOrderID;
	
	@FindBy(xpath = "//ul[starts-with(@class,'scopesListSection slds-has-block-links--space')]//a[@title='Memberships']")
	WebElement memberships;
	
	@FindBy(xpath = "//tbody//th//a")
	WebElement subscriptionID;
	
	@FindBy(xpath = "//button[text()='Renew']")
	WebElement renewBtn;
	
	@FindBy(xpath = "//button[text()='Ready For Payment']")
	WebElement ready4Pymt;
	
	@FindBy(xpath = "//ul[@class='slds-button-group-list']//li[9]")
	WebElement dropDown;
	
	@FindBy(xpath = "//ul[@class='slds-button-group-list']//li[9]//span[text()='Send Public Proforma Invoice']")
	WebElement sendPPInvoice;
	
	@FindBy(xpath = "//label[text()='Subject']//following::input[@class='slds-input'][1]")
	WebElement subject;
	
	@FindBy(xpath = "//button[@aria-label='Select a site, Select a Community Site...']")
	WebElement selectACommunitySite;
	
	@FindBy(xpath = "//button[text()='Send Email']")
	WebElement sendEmailBtn;
	
	public void renewABI(String user) throws InterruptedException {
		util.waitUntilElement(driver, renewBtn);
		util.clickUsingJS(driver, renewBtn);
		util.waitUntilElement(driver, ready4Pymt);
		ready4Pymt.click();
		util.waitUntilElement(driver, dropDown);
		dropDown.click();
		util.waitForJavascript(driver, 6000, 1000);
		util.waitUntilElement(driver, sendPPInvoice);
		sendPPInvoice.click();
		util.waitForJavascript(driver, 6000, 1000);
		util.waitUntilElement(driver, subject);
		subject.click();
		subject.sendKeys("Payment_For_Renewal");
		util.waitUntilElement(driver, selectACommunitySite);
		selectACommunitySite.click();
		selectACommunitySite.sendKeys(Keys.ENTER);
		util.waitUntilElement(driver, sendEmailBtn);
		util.clickUsingJS(driver, sendEmailBtn);
	}
	
	public void viewSalesOrder(String user, String Id) {
		util.waitForJavascript(driver, 6000, 1000);
		util.waitUntilElement(driver, search);
		util.clickUsingJS(driver, search);
		util.waitUntilElement(driver, searchInput);
		util.clickUsingJS(driver, searchInput);
		searchInput.sendKeys(user+Keys.ENTER);
		util.waitUntilElement(driver, salesOrders);
		salesOrders.click();
		util.waitUntilElement(driver, salesOrderID);
		salesOrderID.click();				
	}
	
	public void viewSubscriptionOrder(String user) {
		util.waitForJavascript(driver, 6000, 1000);
		util.waitUntilElement(driver, search);
		search.click();
		util.waitUntilElement(driver, searchInput);
		searchInput.click();
		searchInput.sendKeys(user+Keys.ENTER);
		util.waitUntilElement(driver, memberships);
		memberships.click();
		util.waitUntilElement(driver, subscriptionID);
		subscriptionID.click();				
	}
}
