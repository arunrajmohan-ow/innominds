package org.aia.pages.fonteva.ces;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.util.List;

import org.aia.pages.api.ces.SubscriptionPlanPrice;
import org.aia.pages.ces.Organization;
import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CES_RapidOrderEntry {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	CES_ContactPage contactPage;
	SubscriptionPlanPrice subscriptionAPI = new SubscriptionPlanPrice(driver);
	static Logger log = Logger.getLogger(ContactCreateUser.class);
	Actions action;
	JavascriptExecutor executor;

	public CES_RapidOrderEntry(WebDriver ldriver) {
		this.driver = ldriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
		contactPage = new CES_ContactPage(driver);
	}

	@FindBy(xpath = "//p[text()='Account Name']//parent::div//div//a//span")
	WebElement accountName;

	@FindBy(xpath = "//button[text()='Rapid Order Entry']")
	WebElement rapidOrderEnteryBtn;

	@FindBy(xpath = "//button[text()='Advanced Settings']")
	WebElement advanceSetting;

	@FindBy(xpath = "//h2[text()='Advanced Settings']")
	WebElement advancSettingPopUp;

	@FindBy(xpath = "//select[@name='Business Group']")
	WebElement businessGroupDrp;

	@FindBy(xpath = "//button[@title='Save']")
	WebElement advanceSettingsaveBtn;

	// @FindBy(xpath = "//strong[text()='Item Quick
	// Add']//parent::span//following-sibling::span//div//input")
	// (//strong[text()='Item Quick
	// Add']/ancestor::div[@data-name='itemQuickAdd']//div[@data-name='quickAddItem']/div/div/div/input)
	@FindBy(xpath = "//div[@data-name='itemQuickAdd']//div[@data-name='quickAddItem']/div/div/div/input")
	WebElement quickItemSelect;

	@FindBy(xpath = "//button[text()='Add to Order']")
	WebElement addOrderBtn;

	String quickItemNatinal = "(//span[text()='%s'])";

	String discountCodeInput = "//span[text()='%s']";

	@FindBy(xpath = "//button[text()='Go']") // (//button[normalize-space()='Go'])")
	WebElement goBtn;

	@FindBy(xpath = "//*[contains(text(),'Open Memberships')]")
	WebElement Membershipslnk;

//	@FindBy(xpath = "//span[text()= 'Account']/../..//span//a")
//	WebElement SelectAccount;
	
	@FindBy(xpath = "//span[text()='Account']/parent::div/parent::div//slot/span")
	WebElement SelectAccount;

	@FindBy(xpath = "//*[@role = 'table']//tbody//tr")
	WebElement RecordsCount;

	@FindBy(xpath = "((//*[@role = 'table']//tbody//tr)[2]//td)[10]")
	WebElement Chevronbtn;

	@FindBy(xpath = "//*[contains(text(), \"Are you sure you want to delete this Membership?\")]")
	WebElement DeleteMsg;

	@FindBy(xpath = "//span[text()='Delete']")
	WebElement Delete_membership;

	@FindBy(xpath = "(//*[@role = 'table']//tbody//tr//td)[4]")
	WebElement AvailableMemType;

	@FindBy(xpath = "//span[text()='Refresh']")
	WebElement RefreshBtn;

	@FindBy(xpath = "//div[text()='Delete']")
	WebElement DeleteBtn_chevrontype;

	@FindBy(xpath = "//div[@data-label='Discount Code']//input")
	WebElement discountCodeInputInROE;

	@FindBy(xpath = "//p[@class='slideIn']")
	WebElement discountAplliedPopUp;

	@FindBy(xpath = "//button[@class='iziToast-close']")
	WebElement popUpCloseButton;

	@FindBy(xpath = "//button[text()='Apply']")
	WebElement discontApplyBtn;

	@FindBy(xpath = "//input[@name='referenceNumber']")
	WebElement referenceNumber;

	@FindBy(xpath = "//button//span[text()= 'Apply Payment']")
	WebElement applyPaymentBtn;

	@FindBy(xpath = "//*[@data-name='applyPaymentPage']")
	WebElement applyPaymentPage;

	@FindBy(xpath = "//div/strong[text()='Items']/span")
	WebElement itemsFees;

	@FindBy(xpath = "//div[text()='Receipt']/parent::h1")
	WebElement receiptElement;

	// @FindBy(xpath = "//a/slot/span[contains(text(),'Points of contact')]")
	// WebElement pointofContact;

	// @FindBy(xpath = "//*[contains(text(),'Open Points of contact')]")

	@FindBy(xpath = "//a[normalize-space()='Show All (10)']")
	WebElement showallBtn;

	/**
	 * @param userFullname
	 * @param itemQuick
	 * @throws InterruptedException
	 * @throws AWTException
	 */
	public void selectRapidOrderEntry(String userFullname, String itemQuick, String quickElement)
			throws InterruptedException, AWTException {
		contactPage.selectCreatedContact(userFullname);
		util.waitUntilElement(driver, accountName);
		executor.executeScript("arguments[0].click();", accountName);
		util.waitUntilElement(driver, rapidOrderEnteryBtn);
		rapidOrderEnteryBtn.click();
		Thread.sleep(20000);
		util.waitUntilElement(driver, quickItemSelect);
		executor.executeScript("arguments[0].click();", quickItemSelect);
		// executor.executeScript("arguments[0].value='"+itemQuick+"';",
		// quickItemSelect);
		quickItemSelect.sendKeys(itemQuick);
		Thread.sleep(20000);
		util.waitUntilElement(driver, util.getCustomizedWebElement(driver, quickItemNatinal, quickElement));
		util.getCustomizedWebElement(driver, quickItemNatinal, quickElement).click();
		Thread.sleep(20000);
		util.waitUntilElement(driver, addOrderBtn);
		addOrderBtn.click();
		util.waitUntilElement(driver, goBtn);
		Thread.sleep(20000);
		goBtn.click();

	}

	public void cesRapidOrderEntry(String userFullname, String itemQuick, String quickElement)
			throws InterruptedException, AWTException {
		Thread.sleep(30000);
		util.waitUntilElement(driver, rapidOrderEnteryBtn);
		rapidOrderEnteryBtn.click();
		util.waitUntilElement(driver, quickItemSelect);
		executor.executeScript("arguments[0].click();", quickItemSelect);
		Thread.sleep(20000);
		// executor.executeScript("arguments[0].value='"+itemQuick+"';",
		// quickItemSelect);
		quickItemSelect.sendKeys(itemQuick);
		Thread.sleep(10000);
		util.waitUntilElement(driver, util.getCustomizedWebElement(driver, quickItemNatinal, quickElement));
		util.getCustomizedWebElement(driver, quickItemNatinal, quickElement).click();
		Thread.sleep(10000);
		util.waitUntilElement(driver, addOrderBtn);
		addOrderBtn.click();
		Thread.sleep(20000);
		util.waitUntilElement(driver, itemsFees);
		String fee = itemsFees.getText();
		System.out.println("Item fee: " + fee);
		if (fee.equalsIgnoreCase("Free")) {
			Thread.sleep(10000);
			util.waitUntilElement(driver, goBtn);
			action.moveToElement(goBtn).click().perform();
			System.out.println("GO button clicked");
			Thread.sleep(10000);
			util.waitUntilElement(driver, SelectAccount);
			action.moveToElement(SelectAccount).click().perform();
			System.out.println("Account selected");
			Thread.sleep(10000);
			util.waitUntilElement(driver, Membershipslnk);
			action.moveToElement(Membershipslnk).click().perform();
			System.out.println("Memberships clicked");
			driver.navigate().refresh();
		} else {
			// Thread.sleep(10000);
			util.waitUntilElement(driver, goBtn);
			action.moveToElement(goBtn).click().perform();
			System.out.println("GO button clicked");
			Thread.sleep(10000);
			util.waitUntilElement(driver, referenceNumber);
			referenceNumber.sendKeys(data.testDataProvider().getProperty("referenceNum"));
			util.waitUntilElement(driver, applyPaymentBtn);
			action.moveToElement(applyPaymentBtn).click().perform();
			// applyPaymentBtn.click();
			System.out.println("applyPaymentButton clicked");
			Thread.sleep(20000);
		}

	}

	public void validateDeleteCESMembership() throws InterruptedException {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@role ='table']//tbody//tr"));
		System.out.println("Number of records:" + rows.size());
		Thread.sleep(30000);
		util.waitUntilElement(driver, Chevronbtn);
		action.moveToElement(Chevronbtn).click().perform();
		// Chevronbtn.click();
		System.out.println("Chevron button clicked");
		util.waitUntilElement(driver, DeleteBtn_chevrontype);
		action.moveToElement(DeleteBtn_chevrontype).click().perform();
		System.out.println("Delete Option clicked");
		util.waitUntilElement(driver, DeleteMsg);
		System.out.println("MyError:" + DeleteMsg.getText());
		assertTrue(DeleteMsg.getText().equalsIgnoreCase(data.testDataProvider().getProperty("DeleteMsg")));
		util.waitUntilElement(driver, Delete_membership);
		action.moveToElement(Delete_membership).click().perform();
		Thread.sleep(20000);
//		util.waitUntilElement(driver, DeleteBtn_chevrontype);
//		DeleteBtn_chevrontype.click();
//		System.out.println("Delete button clicked");
//		util.waitUntilElement(driver, DeleteMsg);
//		System.out.println("MyError:" + DeleteMsg.getText());
//		assertTrue(DeleteMsg.getText().equalsIgnoreCase(data.testDataProvider().getProperty("DeleteMsg")));
//		util.waitUntilElement(driver, Delete_membership);
//		action.moveToElement(Delete_membership).click().perform();
	}

	public void validateAvailableMemType() {
		util.waitUntilElement(driver, AvailableMemType);
		assertTrue(
				AvailableMemType.getText().equalsIgnoreCase(data.testDataProvider().getProperty("availableMemType")));
	}

	/**
	 * @throws InterruptedException
	 */
	public void selectAccountName() throws InterruptedException {
		util.waitUntilElement(driver, accountName);
		executor.executeScript("arguments[0].click();", accountName);
	}

	public void selectAccount() throws InterruptedException {
		util.waitUntilElement(driver, SelectAccount);
		action.moveToElement(SelectAccount).click().perform();
	}

	/**
	 * Selecting discount code in rapid order entry
	 * 
	 * @param discountCode
	 * @param membershipType
	 * @param membershipPriceId
	 * @param selectMembership
	 * @throws InterruptedException
	 */
	public void selectDiscountCode(String discountCode, String membershipType, String membershipPriceId)
			throws InterruptedException {
		util.waitUntilElement(driver, rapidOrderEnteryBtn);
		rapidOrderEnteryBtn.click();
		Thread.sleep(20000);
		util.waitUntilElement(driver, discountCodeInputInROE);
		discountCodeInputInROE.sendKeys(discountCode);
		Thread.sleep(10000);
		util.waitUntilElement(driver, util.getCustomizedWebElement(driver, discountCodeInput, discountCode));
		util.getCustomizedWebElement(driver, discountCodeInput, discountCode).click();
		util.waitUntilElement(driver, discontApplyBtn);
		discontApplyBtn.click();
		Thread.sleep(10000);
		util.waitUntilElement(driver, discountAplliedPopUp);
		assertEquals(data.testDataProvider().getProperty("cesDiscountPop"), discountAplliedPopUp.getText());
		System.out.println("Discount applied:" + discountAplliedPopUp.getText());
		popUpCloseButton.click();
		driver.navigate().refresh();
		Thread.sleep(20000);
		util.waitUntilElement(driver, quickItemSelect);
		executor.executeScript("arguments[0].click();", quickItemSelect);
		// executor.executeScript("arguments[0].value='"+itemQuick+"';",
		// quickItemSelect);
		quickItemSelect.sendKeys(membershipType);
		Thread.sleep(20000);
		util.waitUntilElement(driver, util.getCustomizedWebElement(driver, quickItemNatinal, membershipType));
		util.getCustomizedWebElement(driver, quickItemNatinal, membershipType).click();
		Thread.sleep(20000);
		util.waitUntilElement(driver, addOrderBtn);
		addOrderBtn.click();
		Thread.sleep(20000);
		if (membershipType.contains("Basic") || membershipType.contains("Passport")) {
			System.out.println("My code:" + Integer.parseInt(discountCode.substring(0, 2)));
			validateDiscountingAmmount(membershipPriceId, Integer.parseInt(discountCode.substring(0, 2)));
		}

		applyPaymentInROE();
		// Validate Receipt is visible
		util.waitUntilElement(driver, receiptElement);
		assertTrue(receiptElement.isDisplayed());
	}

	/**
	 * This method use for make payment after selecting membership in rapid order
	 * entry
	 */
	public void applyPaymentInROE() {
		util.waitUntilElement(driver, itemsFees);
		String fee = itemsFees.getText();
		System.out.println("Item fee: " + fee);

		if (fee.equalsIgnoreCase("Free")) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			util.waitUntilElement(driver, goBtn);
			action.moveToElement(goBtn).click().perform();
			System.out.println("GO button clicked");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			util.waitUntilElement(driver, SelectAccount);
			action.moveToElement(SelectAccount).click().perform();
			System.out.println("Account selected");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			util.waitUntilElement(driver, Membershipslnk);
			action.moveToElement(Membershipslnk).click().perform();
			System.out.println("Memberships clicked");
			driver.navigate().refresh();
		} else {
			// Thread.sleep(10000);
			util.waitUntilElement(driver, goBtn);
			action.moveToElement(goBtn).click().perform();
			System.out.println("GO button clicked");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			util.waitUntilElement(driver, referenceNumber);
			referenceNumber.sendKeys(data.testDataProvider().getProperty("referenceNum"));
			util.waitUntilElement(driver, applyPaymentBtn);
			action.moveToElement(applyPaymentBtn).click().perform();
			// applyPaymentBtn.click();
			System.out.println("applyPaymentButton clicked");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param membershipPriceId Here we calculate the deducted amount after applied
	 *                          discount code
	 */
	public void validateDiscountingAmmount(String membershipPriceId, int discount) {
		Double totalAmount = subscriptionAPI.getCesMembershipPrice(membershipPriceId);
		util.waitUntilElement(driver, itemsFees);
		String fee = itemsFees.getText();
		StringBuilder build = new StringBuilder(fee);
		if (fee != "Free") {
			if (fee.length() == 7) {
				build.deleteCharAt(0);
			} else if (fee.length() == 9) {
				build.deleteCharAt(0);
				build.deleteCharAt(1);
			} else {
				build.deleteCharAt(0);
				build.deleteCharAt(2);
			}
			Double amountAfterDiscount = Double.parseDouble(build.toString());
			// Validation of discounted amount.
			if (amountAfterDiscount < totalAmount) {
				int expectedAmount = (int) ((double) discount / 100 * totalAmount);
				assertTrue((totalAmount - expectedAmount) == amountAfterDiscount);
			} else {
				assertTrue(Double.parseDouble(build.toString()) == totalAmount);
				System.out.println("Discount is not aplied on this membership");
			}
		} else {
			System.out.println("Membership dues are zero");
		}

	}

	public void verifyRecieptAfterROE() throws InterruptedException {
		util.waitUntilElement(driver, receiptElement);
		assertTrue(receiptElement.isDisplayed());
	}
}
