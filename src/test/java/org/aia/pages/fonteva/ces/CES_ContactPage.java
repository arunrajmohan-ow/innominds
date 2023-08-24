package org.aia.pages.fonteva.ces;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.commons.io.serialization.ValidatingObjectInputStream;
import org.apache.commons.lang3.RandomStringUtils;
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
public class CES_ContactPage {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	static Logger log = Logger.getLogger(ContactCreateUser.class);
	Actions action;
	JavascriptExecutor executor;

	public CES_ContactPage(WebDriver Idriver) {
		this.driver = Idriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
	}

	@FindBy(xpath = "//*[@title='Contacts']/span")
	WebElement contacts;

	@FindBy(xpath = "//a[@title='Contacts']/parent::one-app-nav-bar-item-root")
	WebElement contactsDiv;

	@FindBy(xpath = "//a/span[@title='Name']")
	WebElement tableheaderName;

	@FindBy(xpath = "//h1/span[text()='Contacts']/parent::h1/parent::div/parent::div//button")
	WebElement contactallBtn;

	@FindBy(xpath = "//li[contains(@class,'forceVirtualAutocompleteMenuOption')]//span[text()='All Contacts'][1]")
	WebElement contactallLink;

	@FindBy(xpath = "//span[text()='App Launcher']/parent::div//parent::button")
	WebElement appLauncherIcn;

	@FindBy(xpath = "//label[text()='Search apps and items...']/parent::div/div/input")
	WebElement appSearchtxtbx;

	@FindBy(xpath = "//b[text()='Provider Application']")
	WebElement searchedAppPA;

	String providerApp = "//b[text()='%s']";

	@FindBy(xpath = "//table[@aria-label='Recently Viewed']/tbody/tr/th")
	WebElement tableProviderApp;

	@FindBy(xpath = "//button[@title='Select a List View'] | //button[contains(@title,'Select a List View')]")
	WebElement selectList;

	@FindBy(xpath = "//span[@class=' virtualAutocompleteOptionText'and text()='All']")
	WebElement allBtn;

	@FindBy(xpath = "//table[@aria-label='All']/tbody/tr")
	WebElement tableAllProviders;
	
	@FindBy(xpath = "//div[text()='New']/parent::a")
	WebElement newBtn;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstName;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastName;

	@FindBy(xpath = "//input[@name='OrderApi__Personal_Email__c']")
	WebElement emailAddress;

	@FindBy(xpath = "//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(xpath = "//button[text()='Join']")
	WebElement joinBtn;
	
	String memType = "//span[@title='%s']";

	@FindBy(xpath = "//button[@name='AIA_Membership_Type__c']")
	WebElement selectMemTypeBtn;

	@FindBy(xpath = "//input[contains(@name,'Zip_Code')]")
	WebElement enterZipCode;

	@FindBy(xpath = "//button[contains(@name,'Career_Type')]")
	WebElement careerTypeDrp;

	String careerType = "//span[text()='%s']";

	@FindBy(xpath = "//button[text()='Next']")
	WebElement nextBtn;



	String fName;
	String lName;
	String fullname;
	String emailPrefix;
	String emailDomain;
	String emailaddressdata;
	ArrayList<String> userList = new ArrayList<String>();

	/**
	 * @return
	 */
	public ArrayList<String> userData() {
		fName = "autofn" + RandomStringUtils.randomAlphabetic(4);
		userList.add(0, fName);
		log.info("Users First Name:" + fName);
		lName = "autoln" + RandomStringUtils.randomAlphabetic(4);
		userList.add(1, lName);
		log.info("Users Last Name:" + lName);
		DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		emailPrefix = "auto_" + RandomStringUtils.randomAlphabetic(4).toLowerCase() + date1;
		userList.add(2, emailPrefix);
		emailDomain = "@architects-team.m8r.co";
		userList.add(3, emailDomain);
		emailaddressdata = emailPrefix + emailDomain;
		log.info("Email:" + emailaddressdata);
		userList.add(4, emailaddressdata);
		fullname = fName + " " + lName;
		userList.add(5, fullname);
		return userList;
	}

	/**
	 * @throws InterruptedException
	 * 
	 */
	public void selectContact() throws InterruptedException {
		util.waitUntilElement(driver, contacts);
		contactsDiv.click();
		util.waitUntilElement(driver, tableheaderName);
		Thread.sleep(5000);
		util.waitUntilElement(driver, contactallBtn);
		contactallBtn.click();
		util.waitUntilElement(driver, contactallLink);
		contactallLink.click();
	}

	/**
	 * 
	 */
	public void createNewContactInFonteva() {
		util.waitUntilElement(driver, contacts);
		contactsDiv.click();
		util.waitUntilElement(driver, newBtn);
		newBtn.click();
		util.waitUntilElement(driver, firstName);
		firstName.sendKeys(fName);
		util.waitUntilElement(driver, lastName);
		lastName.sendKeys(lName);
		executor.executeScript("arguments[0].scrollIntoView(true);", emailAddress);
		emailAddress.sendKeys(emailaddressdata);
		saveBtn.click();
	}
	
	public void joinCreatedUser(String membership, String career) throws InterruptedException {
		Thread.sleep(8000);
		util.waitUntilElement(driver, joinBtn);
		joinBtn.click();
		util.waitUntilElement(driver, selectMemTypeBtn);
		selectMemTypeBtn.click();
		WebElement membershipType = driver.findElement(By.xpath(String.format(memType, membership)));
		util.waitUntilElement(driver, membershipType);
		membershipType.click();
		action.moveToElement(enterZipCode);
		util.enterText(driver, enterZipCode, data.testDataProvider().getProperty("zipCode"));
		util.waitUntilElement(driver, careerTypeDrp);
		careerTypeDrp.click();
		WebElement selectCareerType = driver.findElement(By.xpath(String.format(careerType, career)));
		selectCareerType.click();
		// action.scrollToElement(nextBtn);
		executor.executeScript("arguments[0].scrollIntoView(true);", nextBtn);
		util.waitUntilElement(driver, nextBtn);
		nextBtn.click();
	}

}
