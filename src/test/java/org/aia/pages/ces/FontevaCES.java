package org.aia.pages.ces;

import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.aia.utility.ConfigDataProvider;

import org.aia.utility.DateUtils;
import org.aia.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.AddHasCasting;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import io.cucumber.java.it.Data;

public class FontevaCES {

	WebDriver driver;
	Actions action;

	public FontevaCES(WebDriver Idriver) {
		this.driver = Idriver;
		action = new Actions(driver);
	}

	ConfigDataProvider data = new ConfigDataProvider();
	Utility util = new Utility(driver, 10);

	@FindBy(xpath = "//input[@id='username']")
	WebElement userName;

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//input[@id='Login']")
	WebElement loginBtn;

	@FindBy(xpath = "//*[@title='Contacts']/span")
	WebElement contacts;

	@FindBy(xpath = "//span[text()='App Launcher']/parent::div//parent::button")
	WebElement appLauncherIcn;

	@FindBy(xpath = "//label[text()='Search apps and items...']/parent::div/div/input")
	WebElement appSearchtxtbx;

	@FindBy(xpath = "//b[text()='Provider Application']")
	WebElement searchedAppPA;

	@FindBy(xpath = "//table[@aria-label='Recently Viewed']/tbody/tr/th")
	WebElement tableProviderApp;

	@FindBy(xpath = "//button[@title='Select a List View'] | //button[contains(@title,'Select a List View')]")
	WebElement selectList;

	@FindBy(xpath = "//span[@class=' virtualAutocompleteOptionText'and text()='All']")
	WebElement allBtn;

	@FindBy(xpath = "//table[@aria-label='All']/tbody/tr")
	WebElement tableAllProviders;

	@FindBy(xpath = "//span[text()='Application Status']/parent::div/parent::div//button")
	WebElement editAppStatusIon;

	@FindBy(xpath = "//button[contains(@aria-label, 'Application Status,')]")
	WebElement appStatusBtnDrpdwn;

	@FindBy(xpath = "//button[text()='Save']")
	WebElement saveBtn;

	////////////// Membership

	@FindBy(xpath = "//a[@title='Contacts']/parent::one-app-nav-bar-item-root")
	WebElement contactsDiv;

	@FindBy(xpath = "//div[@class='uiVirtualDataTable indicator']")
	WebElement tableDiv;

	// @FindBy(xpath="//a/slot/span[contains(text(),'Memberships')]") WebElement
	// memberShip;
	@FindBy(xpath = "//a/slot/span[contains(text(),'Memberships')]//ancestor::a")
	WebElement memberShip;

	@FindBy(xpath = "//a/span[@title='Name']")
	WebElement tableheaderName;

	@FindBy(xpath = "//h2//span[@title='Terms']")
	WebElement Terms;

	// @FindBy(xpath="//table[@aria-label='Terms']/tbody/tr/th//span/a |
	// //table[@aria-label='Terms']/tbody/tr/th//span//ancestor::a") WebElement
	// termId;
	@FindBy(xpath = "//table[@aria-label='Terms']/tbody/tr/th//span//ancestor::a")
	WebElement termId;

	@FindBy(xpath = "//table[@aria-label='Memberships']/tbody/tr/th//div")
	WebElement tableSubscriptionId;

	@FindBy(xpath = "//input[@name='OrderApi__Term_End_Date__c']")
	WebElement inputTermEndDate;

	@FindBy(xpath = "//input[@name='OrderApi__Grace_Period_End_Date__c']")
	WebElement inputTermGraceDate;

	@FindBy(xpath = "//div[@class='uiVirtualDataTable indicator']/following-sibling::table/tbody/tr/th")
	WebElement Name;

	// @FindBy(xpath="//span[text()='Term End
	// Date']/parent::div/following-sibling::div//button") WebElement editBtn;

	// @FindBy(xpath="//a[contains(text(),'Show All (1')]") WebElement showallBtn;

	// @FindBy(xpath="//lst-related-list-quick-links-grid//div//div[@class='slds-card__body
	// slds-card__body--inner']//div[@class='rlql-toggle
	// slds-text-align_center']//a[contains(text(),'Show All')]") WebElement
	// showallBtn;

	@FindBy(xpath = "//h1/span[text()='Contacts']/parent::h1/parent::div/parent::div//button")
	WebElement contactallBtn;

	@FindBy(xpath = "//li[contains(@class,'forceVirtualAutocompleteMenuOption')]//span[text()='All Contacts'][1]")
	WebElement contactallLink;

	@FindBy(xpath = "//div[text()='Contact']")
	WebElement contactTitle;

	@FindBy(xpath = "//input[@placeholder='Search this list...']")
	WebElement searchBox;

	@FindBy(xpath = "//span[text()='No items to display.']")
	WebElement noItemHeading;

	@FindBy(xpath = "//p[text()='Account Name']//parent::div//div//a")
	WebElement accountName;

	@FindBy(xpath = "(//*[@role = 'grid']//tbody//tr//td)[2]//a")
	WebElement selectPId;

	@FindBy(xpath = "//span[text()='Provider Application Number']/parent::div/parent::div//div//span//slot/*[@slot='outputField']")
	WebElement pAppNumber;

	@FindBy(xpath = "//span[text()='Owner']/parent::div/parent::div//slot/span")
	WebElement ownerName;

	@FindBy(xpath = "//span[text()='Original Application Type']/parent::div/parent::div//div//span//slot/*[@slot='outputField']")
	WebElement originalAppType;

	@FindBy(xpath = "//span[text()='Application Status']/parent::div/parent::div//div//span//slot/*[@slot='outputField']")
	WebElement appStatus;

	@FindBy(xpath = "//span[text()='Attestation Date']/parent::div/parent::div//div//span//slot/*[@slot='outputField']")
	WebElement attestationDate;

	// @FindBy(xpath="//span[text()='Account']/parent::div/parent::div//div//span//slot/*[@slot='outputField']")WebElement
	// accounttxt;
	@FindBy(xpath = "//span[text()= 'Account']/parent::div/parent::div//a//span")
	WebElement accounttxt;

	@FindBy(xpath = "//span[text()='Organization Name']/parent::div/parent::div//div//span//slot/*[@slot='outputField']")
	WebElement organizationName;

	@FindBy(xpath = "//span[text()= 'Telephone']/parent::div/parent::div//a")
	WebElement telephonetxt;

	@FindBy(xpath = "//span[text()='CES Provider Number']/parent::div/parent::div//div//span//slot/*[@slot='outputField']")
	WebElement cesProviderNum;

	@FindBy(xpath = "//span[text()='CES Provider Status']/parent::div/parent::div//div//span//slot/*[@slot='outputField']")
	WebElement cesProviderStatus;

	@FindBy(xpath = "//span[text()='CES Membership Type']/parent::div/parent::div//div//span//slot/*[@slot='outputField']")
	WebElement cesMembershipType;

	@FindBy(xpath = "//*[contains(text(),'Open Memberships')]")
	WebElement membershipslnk;

	@FindBy(xpath = "(//*[@role = 'table']//tbody//tr//td)[4]//div//div")
	WebElement availableMemType;

	@FindBy(xpath = "//div//a[@title='AutomationOrg']")
	WebElement accountPage;

	@FindBy(xpath = "//*[contains(text(),'Open Sales Orders')]")
	WebElement salesOrderlnk;

	@FindBy(xpath = "//*[@data-label='Sales Order #']//a//span")
	WebElement salesOrderNumber;

	@FindBy(xpath = "//*[contains(text(),'Open Receipts')]")
	WebElement receiptslnk;

	@FindBy(xpath = "//*[@data-label='Receipt Number']//a//span")
	WebElement receiptNumber;

	@FindBy(xpath = "//*[contains(text(),'Open Transaction Lines')]")
	WebElement transcationsLines;

	@FindBy(xpath = "//*[contains(text(),'Open Points of contact')]")
	WebElement pointsofContactlnk;

	@FindBy(xpath = "//*[@aria-label='Points of contact']//th//div//span//a")
	WebElement pointsofContactName;

	@FindBy(xpath = "//button[@title='Edit Application Status']")
	WebElement editApplicationStatus;

	// @FindBy(xpath="//span[text()='Term End
	// Date']/parent::div/following-sibling::div//button") WebElement editBtn;

	@FindBy(xpath = "//button[@title='Edit Term End Date']/span")
	WebElement editBtn;

	// @FindBy(xpath="//a[contains(text(),'Show')]") WebElement showallBtn;

	@FindBy(xpath = "//a[contains(text(),'Show All (1')]")
	WebElement showallBtn;

	// @FindBy(xpath="//lst-related-list-quick-links-grid//div//div[@class='slds-card__body
	// slds-card__body--inner']//div[@class='rlql-toggle
	// slds-text-align_center']//a[contains(text(),'Show All')]") WebElement
	// showallBtn;
	String startLocator = "//div[@class='uiVirtualDataTable indicator']/following-sibling::table/tbody//a[text()='";
	String endLocator = "']";
	String appName = "Provider Application";

	public void changeProviderApplicationStatus(String fullName, String providerID, String providerStatus) throws InterruptedException 
	{
		/*
		 * util.waitUntilElement(driver, userName);
		 * userName.sendKeys("sgopisetty@innominds.com.aia.testing");
		 * password.sendKeys("Harshi@437"); loginBtn.click();
		 */
		util.waitUntilElement(driver, appLauncherIcn);
		Thread.sleep(10000);
		appLauncherIcn.click();
		util.waitUntilElement(driver, appSearchtxtbx);
		util.enterText(driver, appSearchtxtbx, appName);
		util.waitUntilElement(driver, searchedAppPA);
		WebElement provAppElement = Utility.waitForWebElement(driver, "//b[text()='" + appName + "']", 10);
		provAppElement.click();
		Thread.sleep(2000);
		util.waitUntilElement(driver, tableProviderApp);
		util.waitUntilElement(driver, selectList);
		selectList.click();
		util.waitUntilElement(driver, allBtn);
		allBtn.click();
		WebElement provAppIDElement = Utility.waitForWebElement(driver,
				"//table[@aria-label='All']/tbody//a[@title='" + providerID + "']", 10);
		provAppIDElement.click();
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.scrollToElement(editAppStatusIon);
		util.waitUntilElement(driver, editAppStatusIon);
		editAppStatusIon.click();
		util.waitUntilElement(driver, appStatusBtnDrpdwn);
		appStatusBtnDrpdwn.click();
		WebElement provStatusElement = Utility.waitForWebElement(driver, "//span[@title='" + providerStatus + "']", 10);
		provStatusElement.click();
		saveBtn.click();
		Thread.sleep(1000);
		act.sendKeys(Keys.F5);
		Thread.sleep(5000);
	}

	public void changeTermDates(String fullName) throws InterruptedException {
		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		util.waitUntilElement(driver, contacts);
		contactsDiv.click();
		// driver.navigate().refresh();
		util.waitUntilElement(driver, tableheaderName);
		Thread.sleep(5000);
		util.waitUntilElement(driver, contactallBtn);
		contactallBtn.click();
		util.waitUntilElement(driver, contactallLink);
		contactallLink.click();
		Thread.sleep(15000);
		driver.findElement(By.xpath(startLocator + fullName + endLocator)).click();
		util.waitUntilElement(driver, accountName);
		js.executeScript("arguments[0].click();", accountName);
		// accountName.click();
		util.waitUntilElement(driver, showallBtn);
		Thread.sleep(5000);
		actions.sendKeys(Keys.ARROW_DOWN).build().perform();
		actions.sendKeys(Keys.ARROW_DOWN).build().perform();

		actions.moveToElement(showallBtn).build().perform();
		showallBtn.click();
		Thread.sleep(2000);
		util.waitUntilElement(driver, memberShip);
		// Instantiating Actions class
		// Actions actions = new Actions(driver);
		// Hovering on main menu
		// actions.moveToElement(contactTitle);
		actions.sendKeys(Keys.ARROW_DOWN).build().perform();
		actions.sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(5000);
		util.waitUntilElement(driver, memberShip);
		memberShip.click();
		util.waitUntilElement(driver, tableSubscriptionId);
		Thread.sleep(1000);
		tableSubscriptionId.click();
		util.waitUntilElement(driver, Terms);
		Terms.click();
		util.waitUntilElement(driver, termId);
		js.executeScript("arguments[0].click();", termId);
		// termId.click();
		Thread.sleep(5000);
		util.waitUntilElement(driver, editBtn);
		Thread.sleep(5000);
		Actions act = new Actions(driver);
		act.scrollToElement(editBtn);
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)", editBtn);
		editBtn.click();
		util.waitUntilElement(driver, inputTermEndDate);
		inputTermEndDate.clear();
		inputTermEndDate.sendKeys("12/31/2023");
		util.waitUntilElement(driver, inputTermGraceDate);
		inputTermGraceDate.clear();
		inputTermGraceDate.sendKeys("4/4/2024");
		saveBtn.click();
		Thread.sleep(1000);
		act.sendKeys(Keys.F5);
		Thread.sleep(2000);
	}

	/**
	 * @throws InterruptedException
	 */
	public void checkUserInProviderApplication(String user) throws InterruptedException {
		Actions actions = new Actions(driver);
		util.waitUntilElement(driver, appLauncherIcn);
		Thread.sleep(10000);
		appLauncherIcn.click();
		util.waitUntilElement(driver, appSearchtxtbx);
		util.enterText(driver, appSearchtxtbx, appName);
		util.waitUntilElement(driver, searchedAppPA);
		WebElement provAppElement = Utility.waitForWebElement(driver, "//b[text()='" + appName + "']", 10);
		provAppElement.click();
		Thread.sleep(2000);
		util.waitUntilElement(driver, tableProviderApp);
		util.waitUntilElement(driver, selectList);
		selectList.click();
		util.waitUntilElement(driver, allBtn);
		allBtn.click();
		util.waitUntilElement(driver, searchBox);
		searchBox.click();
		searchBox.sendKeys(user);
		actions.sendKeys(Keys.ENTER).build().perform();
		util.waitUntilElement(driver, noItemHeading);
		assertTrue(noItemHeading.isDisplayed());
	}

	public void selectProviderApplication(String user) throws InterruptedException {
		Actions actions = new Actions(driver);
		util.waitUntilElement(driver, appLauncherIcn);
		Thread.sleep(10000);
		appLauncherIcn.click();
		util.waitUntilElement(driver, appSearchtxtbx);
		util.enterText(driver, appSearchtxtbx, appName);
		util.waitUntilElement(driver, searchedAppPA);
		WebElement provAppElement = Utility.waitForWebElement(driver, "//b[text()='" + appName + "']", 10);
		provAppElement.click();
		Thread.sleep(2000);
		util.waitUntilElement(driver, tableProviderApp);
		util.waitUntilElement(driver, selectList);
		selectList.click();
		util.waitUntilElement(driver, allBtn);
		allBtn.click();
		util.waitUntilElement(driver, searchBox);
		searchBox.click();
		searchBox.sendKeys(user);
		actions.sendKeys(Keys.ENTER).build().perform();
		util.waitUntilElement(driver, selectPId);
		selectPId.click();
	}
}
