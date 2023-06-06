package org.aia.pages.fonteva.membership;

import static org.testng.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
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

	String exceptionContact = "(//span[text()='%s']//ancestor::a)[3]";

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

	@FindBy(xpath = "//table[@aria-label]")
	WebElement exceptionTable;

	@FindBy(xpath = "(//table[@aria-label]//tr)[2]")
	WebElement existingExceptionTable;

	@FindBy(xpath = "//button[@title='Close this window']")
	WebElement closeTheWindow;

	@FindBy(xpath = "//table[@aria-label]//tr[2]")
	WebElement cloneExceptionTable;

	@FindBy(xpath = "//td[2]/span/span")
	WebElement activityText;

	@FindBy(xpath = "//td[3]/span/span")
	WebElement initialReachOutText;

	@FindBy(xpath = "//td[4]/span/span")
	WebElement reasonText;

	@FindBy(xpath = "//td[5]/span/span")
	WebElement noteText;

	@FindBy(xpath = "//h2[text()='New Processing Exception']")
	WebElement heading;

	@FindBy(xpath = "(//table[@aria-label='Processing Exceptions']//tr)[2]/th/span/a")
	WebElement processExceptionId;

	@FindBy(xpath = "(//button[text()='Edit'])[2]")
	WebElement editBtn;

	@FindBy(xpath = "(//slot//lightning-formatted-text[@slot='primaryField'])[2]")
	WebElement exceptionId;

	@FindBy(xpath = "//div[@class='actionBody']//h2")
	WebElement PopUpheading;

	@FindBy(xpath = "//button[text()='Clone']")
	WebElement cloneBtn;

	@FindBy(xpath = "(//a[text()='Related'])[2]")
	WebElement relatedTab;

	@FindBy(xpath = "//a[@title='Upload Files']")
	WebElement fileUpload;

	@FindBy(xpath = "//h2[text()='Upload Files']")
	WebElement uploadPopUp;

	@FindBy(xpath = "(//span[@role='status'])[2]")
	WebElement isfileUploaded;
	
	@FindBy(xpath="//span[text()='Done']//parent::button")
	WebElement doneBtn;
	
	@FindBy(xpath="//ul[@class='uiAbstractList']/li")
	WebElement fileVisible;
	
	@FindBy(xpath="//lightning-icon[@icon-name='utility:success']")
	WebElement successIcon;

	static ArrayList<String> valueList = new ArrayList<String>();

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
		valueList.add(0, activityText.getText());
		valueList.add(1, reasonText.getText());
		valueList.add(2, initialReachOutText.getText());
		valueList.add(3, noteText.getText());

	}

	/**
	 * @param activityOption
	 * @param enterNote
	 * @param reasonOption
	 * @param intitialReachOutOption
	 * @param statusOption
	 */
	public void editProcessException(String activityOption, String enterNote, String reasonOption,
			String intitialReachOutOption, String statusOption) {
		util.waitUntilElement(driver, processExceptionId);
		processExceptionId.click();
		util.waitUntilElement(driver, editBtn);
		editBtn.click();
		util.waitUntilElement(driver, PopUpheading);
		assertTrue(PopUpheading.getText().contains(exceptionId.getText()));
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

	public void validateEditedProcessException(String editactivity, String editreason, String editinitialReach,
			String editnote) {
		assertNotEquals(valueList.get(0), editactivity);
		assertNotEquals(valueList.get(1), editreason);
		assertNotEquals(valueList.get(2), editinitialReach);
		assertNotEquals(valueList.get(3), editnote);
	}

	/**
	 * @param fullName
	 * @throws InterruptedException
	 */
	public void cloneExistingProcessException(String fullName) throws InterruptedException {
		util.waitUntilElement(driver, processExceptionId);
		processExceptionId.click();
		util.waitUntilElement(driver, cloneBtn);
		cloneBtn.click();
		util.waitUntilElement(driver, heading);
		assertTrue(heading.isDisplayed());
		saveBtn.click();
		Thread.sleep(60000);
		executor.executeScript("arguments[0].click();",
				util.getCustomizedWebElement(driver, exceptionContact, fullName));
		util.waitUntilElement(driver, processExceptionTab);
		processExceptionTab.click();
		util.waitUntilElement(driver, existingExceptionTable);
		assertTrue(existingExceptionTable.isDisplayed());
		util.waitUntilElement(driver, cloneExceptionTable);
		assertTrue(cloneExceptionTable.isDisplayed());
	}

	/**
	 * We upload pdf file in exception
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void attachFile() throws InterruptedException, IOException {
		processExceptionId.click();
		util.waitUntilElement(driver, relatedTab);
		relatedTab.click();
		util.waitUntilElement(driver, fileUpload);
		fileUpload.click();
		try {
			Robot robot = new Robot();
			String filePath = System.getProperty("user.dir") + data.testDataProvider().getProperty("uploadFile");
			System.out.println("My path name is:" + filePath);
			StringSelection strSelection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelection, null);
			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(3000);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("File Path is incorrect or file is not in directory");
		}
	}

	/**
	 * Validate File is uploaded 
	 */
	public void validateFileUpload() {
		util.waitUntilElement(driver, uploadPopUp);
		assertTrue(uploadPopUp.isDisplayed());
		util.waitUntilElement(driver, successIcon);
		assertEquals(isfileUploaded.getText(), data.testDataProvider().getProperty("fileUpload"));
		doneBtn.click();
	}

}
