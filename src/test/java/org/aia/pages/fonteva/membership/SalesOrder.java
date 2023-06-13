package org.aia.pages.fonteva.membership;

import static org.testng.Assert.*;


import java.util.ArrayList;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * @author IM-RT-LP-1483(Suhas)
 *
 */
public class SalesOrder {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	static Logger log = Logger.getLogger(ContactCreateUser.class);
	Actions action;
	JavascriptExecutor executor;
	
	public SalesOrder(WebDriver Idriver) {
		this.driver = Idriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
	}
	
	@FindBy(xpath="(//ul//span[contains(text(),'Sales Order')]/ancestor::a)[1]")
	WebElement salesOrderLink;
	
	@FindBy(xpath="//table[@aria-label='Sales Orders']")
	WebElement salesOrderTable;
	
	@FindBy(xpath="(//table[@aria-label='Sales Orders']//tr)[2]/th//a")
	WebElement orderId;
	
	@FindBy(xpath="//span[contains(text(),'Sales Order Lines')]//ancestor::a")
	WebElement salesOrderLine;
	
	@FindBy(xpath="//table[@aria-label='Sales Order Lines']")
	WebElement salesOrderLineTable;
	
	//@FindBy(xpath="(//table[@aria-label='Sales Order Lines']//tr[1])[2]//th/span/a")
	@FindBy(xpath="(//table[@aria-label='Sales Order Lines']//tr[1])[2]//th//a")
	WebElement salesOrderFirstLine;
	
	@FindBy(xpath="(//table[@aria-label='Sales Order Lines']//tr[2])//th//a")
	WebElement salesOrderSecondLine;
	
	@FindBy(xpath="(//button[text()='Set Discount'])[1]")
	WebElement setDiscountBtn;
	
	@FindBy(xpath="(//button[text()='Set Discount'])[2]")
	WebElement setDiscountBtnSecond;
	
	@FindBy(xpath="//h2[text()='Set Discount']")
	WebElement discountPopUp;
	
	@FindBy(xpath="//input[@name='Discount_Ammount_Input']")
	WebElement discountInput;
	
	@FindBy(xpath="//button[text()='Next']")
	WebElement nextBtn;
	
	@FindBy(xpath="//p[@title='Total']/parent::div/p[2]/slot/lightning-formatted-text")
    WebElement afterDiscountAmt;
	
	/**
	 * @throws InterruptedException 
	 * 
	 */
	public void setDiscount() throws InterruptedException {
		util.waitUntilElement(driver, salesOrderLink);
		executor.executeScript("arguments[0].click();", salesOrderLink);
		// Validate sales order line is there
		util.waitUntilElement(driver, salesOrderTable);
		assertTrue(salesOrderTable.isDisplayed());
		util.waitUntilElement(driver, orderId);
		executor.executeScript("arguments[0].click();", orderId);
		//orderId.click();
		util.waitUntilElement(driver, salesOrderLine);
		salesOrderLine.click();
		util.waitUntilElement(driver, salesOrderLineTable);
		assertTrue(salesOrderLineTable.isDisplayed());
		
		util.waitUntilElement(driver, salesOrderFirstLine);
		executor.executeScript("arguments[0].click();", salesOrderFirstLine);
		//salesOrderFirstLine.click();
		util.waitUntilElement(driver, setDiscountBtn);
		setDiscountBtn.click();
		util.waitUntilElement(driver, discountPopUp);
		discountPopUp.click();
		assertTrue(discountPopUp.isDisplayed());
		util.enterText(driver, discountInput, data.testDataProvider().getProperty("discountAmt"));
		util.waitUntilElement(driver, nextBtn);
		nextBtn.click();
		util.waitUntilElement(driver, afterDiscountAmt);
		//Validate discount is set as $0 for first line
		driver.navigate().refresh();
		util.waitUntilElement(driver, afterDiscountAmt);
		assertEquals(afterDiscountAmt.getText(),data.testDataProvider().getProperty("replacatedAmt"));
		driver.navigate().back();
		driver.navigate().back();
		util.waitUntilElement(driver, salesOrderSecondLine);
		executor.executeScript("arguments[0].click();", salesOrderSecondLine);
		//salesOrderSecondLine.click();
		util.waitUntilElement(driver, setDiscountBtnSecond);
		setDiscountBtnSecond.click();
		util.waitUntilElement(driver, discountPopUp);
		assertTrue(discountPopUp.isDisplayed());
		util.enterText(driver, discountInput, data.testDataProvider().getProperty("discountAmt"));
		util.waitUntilElement(driver, nextBtn);
		nextBtn.click();
		driver.navigate().refresh();
		util.waitUntilElement(driver, afterDiscountAmt);
		//Validate discount is set as $0 for second Line
		assertEquals(afterDiscountAmt.getText(),data.testDataProvider().getProperty("replacatedAmt"));
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(10000);
        driver.navigate().refresh();
	}
	
	public void switchToTab() {
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}
	
}
