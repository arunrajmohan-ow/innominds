package org.aia.pages.fonteva.ces;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.aia.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CES_PointsOfContact {
    
	WebDriver driver;
	Actions action;
	JavascriptExecutor executor;
	public CES_PointsOfContact(WebDriver Idriver) 
	{
		this.driver = Idriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
	}
	Utility util = new Utility(driver, 10);
	
	@FindBy(xpath="//input[@id='username']") WebElement userName;

	@FindBy(xpath="//input[@id='password']") WebElement password;
	
	@FindBy(xpath="//input[@id='Login']") WebElement loginBtn;
	
	@FindBy(xpath="//*[@title='Contact']/span") WebElement contacts;
	
	@FindBy(xpath="//span[text()='App Launcher']/parent::div//parent::button") WebElement appLauncherIcn;
	
	@FindBy(xpath="//label[text()='Search apps and items...']/parent::div/div/input") WebElement appSearchtxtbx;
	
	@FindBy(xpath="//b[text()='Provider Application']") WebElement searchedAppPA;
	
	@FindBy(xpath="//table[@aria-label='Recently Viewed']/tbody/tr/th") WebElement tableProviderApp;
	
	@FindBy(xpath="//button[@title='Select a List View'] | //button[contains(@title,'Select a List View')]") WebElement selectList;
	
	@FindBy(xpath="//span[@class=' virtualAutocompleteOptionText'and text()='All']") WebElement allBtn;
	
	@FindBy(xpath="//table[@aria-label='All']/tbody/tr") WebElement tableAllProviders;
	
	@FindBy(xpath="//span[text()='Application Status']/parent::div/parent::div//button") WebElement editAppStatusIon;
	
	@FindBy(xpath="//button[contains(@aria-label, 'Application Status,')]") WebElement appStatusBtnDrpdwn;
	
	@FindBy(xpath="//button[text()='Save']") WebElement saveBtn;
	
	////////////// Membership
	
	@FindBy(xpath="//a[@title='Contacts']/parent::one-app-nav-bar-item-root") WebElement contactsDiv;

	@FindBy(xpath="//div[@class='uiVirtualDataTable indicator']") WebElement tableDiv;

	//@FindBy(xpath="//a/slot/span[contains(text(),'Memberships')]") WebElement memberShip;
	@FindBy(xpath="//a/slot/span[contains(text(),'Memberships')]//ancestor::a") WebElement memberShip;

	@FindBy(xpath="//a/span[@title='Name']") WebElement tableheaderName;

	@FindBy(xpath="//h2//span[@title='Terms']") WebElement Terms;

	//@FindBy(xpath="//table[@aria-label='Terms']/tbody/tr/th//span/a | //table[@aria-label='Terms']/tbody/tr/th//span//ancestor::a") WebElement termId;
	@FindBy(xpath="//table[@aria-label='Terms']/tbody/tr/th//span//ancestor::a") WebElement termId;
	
	@FindBy(xpath="//table[@aria-label='Memberships']/tbody/tr/th//div") WebElement tableSubscriptionId;

	@FindBy(xpath="//input[@name='OrderApi__Term_End_Date__c']") WebElement inputTermEndDate;

	@FindBy(xpath="//input[@name='OrderApi__Grace_Period_End_Date__c']") WebElement inputTermGraceDate;

	@FindBy(xpath="//div[@class='uiVirtualDataTable indicator']/following-sibling::table/tbody/tr/th") WebElement Name;

	//@FindBy(xpath="//span[text()='Term End Date']/parent::div/following-sibling::div//button") WebElement editBtn;
	
	@FindBy(xpath="//button[@title='Edit Term End Date']/span") WebElement editBtn;
	
	@FindBy(xpath="//a[contains(text(),'Show')]") WebElement showallBtn;
	
	//@FindBy(xpath="//a[contains(text(),'Show All (1')]") WebElement showallBtn;
	
	//@FindBy(xpath="//lst-related-list-quick-links-grid//div//div[@class='slds-card__body slds-card__body--inner']//div[@class='rlql-toggle slds-text-align_center']//a[contains(text(),'Show All')]") WebElement showallBtn;
	
	@FindBy(xpath="//h1/span[text()='Contacts']/parent::h1/parent::div/parent::div//button") WebElement contactallBtn;
	
	@FindBy(xpath="//li[contains(@class,'forceVirtualAutocompleteMenuOption')]//span[text()='All Contacts'][1]") WebElement contactallLink;
	
	@FindBy(xpath="//div[text()='Contact']") WebElement contactTitle;
	
	@FindBy(xpath = "//input[@placeholder='Search this list...']") WebElement searchBox;
	
	@FindBy(xpath = "//span[text()='No items to display.']") WebElement noItemHeading;
	
	@FindBy(xpath = "//p[text()='Account Name']//parent::div//div//a") WebElement accountName;
	
//	@FindBy(xpath = "//span[contains(@data-proxy-id, 'aura-pos-lib-14')]/ancestor::a")
//	WebElement accountNameLink;
	@FindBy(xpath = "//span[@class='error']")
	WebElement messageError;
	
	@FindBy(xpath = "//span[text()='AutomationOrg']")
	WebElement accountNameLink;
	
	@FindBy(xpath = "//slot/span[contains(text(),'Points of')]") WebElement pointsOfContactBtn;
	
	@FindBy(xpath = "//lightning-base-combobox//div/descendant::span[text()='CES Primary']") WebElement accountRole;
	
	@FindBy(xpath = "//slot/lightning-button/button[@class='slds-button slds-button_brand']") WebElement pointsOfContactSaveBtn;
	
	@FindBy(xpath = "//slot/lightning-button/button[contains(text(),'Save & New')]") WebElement pointsOfContactSaveNewBtn;
	
	@FindBy(xpath = "//div[@class='slds-media slds-media_center slds-has-flexi-truncate']/div/h2") WebElement newPointsOfContactError;
	
	@FindBy(xpath = "//slot//lightning-button//button[text()='New']") WebElement pointsOfContactNewBtn;
	
	//@FindBy(xpath = "//a[@title='Show 2 more actions']") WebElement chevronBtn;
	
	@FindBy(xpath = "//tr[@data-row-number='1']//button") WebElement chevronBtn;
	
	@FindBy(xpath = "//div[contains(@class,'actionMenu') and contains(@class, 'visible')]//a[@title='Edit']") WebElement chevronEditBtn;
	
	@FindBy(xpath = "//div[contains(@class,'actionMenu') and contains(@class, 'visible')]//a[@title='Delete']") WebElement chevronDeleteBtn;

	@FindBy(xpath = "//records-record-layout-item//lightning-combobox//button[@role='combobox']") WebElement accountRoleDropDown;
	
	@FindBy(xpath = "//records-record-layout-item//lightning-combobox//span[@title='CES Secondary']") WebElement accountRoleDropDownValue;
	
	@FindBy(xpath = "//input[@placeholder='Search Contacts...']") WebElement searchBtn;
	
	@FindBy(xpath = "//span[text()='Delete']") WebElement deletePointsOfContact;
	
	@FindBy(xpath = "//th[@data-label='Contact']//lightning-formatted-rich-text/span/a") WebElement contactLinkPOC;
	//th[@data-label="Contact"]//lightning-formatted-rich-text/span/a
	
	
	//records-record-layout-item//lightning-combobox//button[@role='combobox']
	//records-record-layout-item//lightning-base-combobox-item//span[@title='CES Users']
	
	
	String  startLocator = "//div[@class='uiVirtualDataTable indicator']/following-sibling::table/tbody//a[text()='";
	String  endLocator = "']";
	String  appName = "Provider Application";
	
	public void clickPointsOfContact(String fullName, String providerID, String providerStatus) throws InterruptedException 
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
		WebElement provAppElement = Utility.waitForWebElement(driver, "//b[text()='"+appName+"']", 10);
		provAppElement.click();
		Thread.sleep(2000);
		util.waitUntilElement(driver, tableProviderApp);
		util.waitUntilElement(driver, selectList);
		selectList.click();
		util.waitUntilElement(driver, allBtn);
		allBtn.click();
		WebElement provAppIDElement = Utility.waitForWebElement(driver, "//table[@aria-label='All']/tbody//a[@title='"+providerID+"']", 10);
		provAppIDElement.click();
		
		util.waitUntilElement(driver, accountNameLink);
		accountNameLink.click();	
		util.waitUntilElement(driver, showallBtn);
		showallBtn.click();	
		util.waitUntilElement(driver, pointsOfContactBtn);
		pointsOfContactBtn.click();
		//assertTrue(chevronBtn.isDisplayed());
		Thread.sleep(40000);
		chevronBtn.click();
	}
	
	public void deletePointsOfContact() throws InterruptedException 
	{
		//292
		
//		assertTrue(chevronBtn.isDisplayed());
//		Thread.sleep(20000);
//		chevronBtn.click();
		assertTrue(chevronEditBtn.isDisplayed());
		assertTrue(chevronDeleteBtn.isDisplayed());
		util.waitUntilElement(driver, chevronDeleteBtn);
		chevronDeleteBtn.click(); 
		deletePointsOfContact.click();
		assertTrue(messageError.isDisplayed());
	}
	
	public void roleChangePointsOfContact() throws InterruptedException 
	{
		//294
//		util.waitUntilElement(driver, chevronBtn);
//		chevronBtn.click();
		
		//click on new
//		assertTrue(chevronEditBtn.isDisplayed());
//		assertTrue(chevronDeleteBtn.isDisplayed());
		Thread.sleep(40000);
		executor.executeScript("arguments[0].click();", chevronEditBtn);
//		Thread.sleep(50000);
//		chevronEditBtn.click(); 
		List<WebElement> options = driver.findElements(By.xpath("//records-record-layout-item//lightning-combobox//span[@title='CES Secondary']"));
		for (WebElement accountRoleOption : options) {
			System.out.println(accountRoleOption.getText());
			accountRoleOption.click();
		}
//		accountRoleDropDown.click();
//		accountRoleDropDownValue.click();
		pointsOfContactSaveBtn.click();
//		executor.executeScript("arguments[0].click();",
//				util.getCustomizedWebElement(driver, accountRoleDropDownValue));
	}
	
	
	public void editPointsOfContact() throws InterruptedException 
	{
		//295
		assertTrue(chevronEditBtn.isDisplayed());
		util.waitUntilElement(driver, chevronEditBtn);
		chevronEditBtn.click(); 
		List<WebElement> options = driver.findElements(By.xpath("//records-record-layout-item//lightning-combobox//span[@title='CES Secondary']"));
		for (WebElement accountRoleOption : options) {
			System.out.println(accountRoleOption.getText());
			accountRoleOption.click();
		}
		
		pointsOfContactSaveBtn.click();
		contactLinkPOC.click();
		assertTrue(accountNameLink.isDisplayed());
	}
	
	public void newPointsOfContact() throws InterruptedException 
	{
		//297
		
		util.waitUntilElement(driver, pointsOfContactNewBtn);
		pointsOfContactNewBtn.click(); 
		List<WebElement> options = driver.findElements(By.xpath("//records-record-layout-item//lightning-combobox//span[@title='LMS admin']"));
		for (WebElement accountRoleOption : options) {
			System.out.println(accountRoleOption.getText());
			accountRoleOption.click();
		}
		
		pointsOfContactSaveBtn.click();
		contactLinkPOC.click();
		assertTrue(accountNameLink.isDisplayed());
	}
	
	
}
