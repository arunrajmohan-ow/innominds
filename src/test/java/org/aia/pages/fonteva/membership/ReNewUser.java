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

/**
 * @author IM-RT-LP-1483(Suhas)
 *
 */
public class ReNewUser {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	static Logger log = Logger.getLogger(ContactCreateUser.class);
	Actions action;
	JavascriptExecutor executor;

	/**
	 * @param Idriver
	 */
	public ReNewUser(WebDriver Idriver) {
		this.driver = Idriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
	}
	
	String contact ="//span[text()='%s']//ancestor::a";
	
	@FindBy(xpath="(//span[contains(text(),'Memberships')])[3]/ancestor::a")
	WebElement selectMembership;
	
	@FindBy(xpath="(//table[@aria-label='Memberships']//tr)[2]/th/span/a")
	WebElement subscriptionId;
	
	@FindBy(xpath="(//span[contains(text(),'Terms')])[2]/ancestor::a")
	WebElement termsLink;
	
	@FindBy(xpath="(//table[@aria-label='Terms']//tr)[2]/th/span/a")
	WebElement termId;
	
	@FindBy(xpath="//button[@title='Edit Term End Date']")
	WebElement termEditBtn;
	
	@FindBy(xpath="//input[@name='OrderApi__Term_End_Date__c']")
	WebElement termDate;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	String contactTerm="(//span[text()='%s']//ancestor::a)[2]";
	
	@FindBy(xpath="(//button[text()='Renew'])[2]")
	WebElement renewBtn;
	
	@FindBy(xpath="//button[contains(@aria-label,'Subscription ')]")
	WebElement subPlanDrp;
	
	@FindBy(xpath="//span[text()='Dues - Renew Payment in Full']")
	WebElement selectDeusPlan;
	
	@FindBy(xpath="//button[@name='executeRenew']")
	WebElement updateSalesOrderBtn;
	
	
	/**
	 * @param fullName
	 * @throws InterruptedException 
	 */
	public void changeTermDate(String fullName) throws InterruptedException {
		Thread.sleep(7000);
		WebElement selectContact =driver.findElement(By.xpath(String.format(contact, fullName)));
        executor.executeScript("arguments[0].click();", selectContact);
        util.waitUntilElement(driver, selectMembership);
        selectMembership.click();
        Thread.sleep(10000);
        driver.navigate().refresh();
        util.waitUntilElement(driver, subscriptionId);
        subscriptionId.click();
        util.waitUntilElement(driver, termsLink);
        termsLink.click();
        util.waitUntilElement(driver, termId);
        termId.click();
        util.waitUntilElement(driver, termEditBtn);
        action.scrollToElement(termEditBtn);
        termEditBtn.click();
        util.enterText(driver, termDate, data.testDataProvider().getProperty("termEndDate"));
        Thread.sleep(5000);
        saveBtn.click();
	}
	
	/**
	 * @param fullName
	 * @throws InterruptedException 
	 */
	public void renewMembership(String fullName) throws InterruptedException {
		Thread.sleep(10000);
		executor.executeScript("window.scrollBy(0,-500)", "" );
		WebElement contactInTermLink = driver.findElement(By.xpath(String.format(contactTerm, fullName)));
		executor.executeScript("arguments[0].click();", contactInTermLink);
		util.waitUntilElement(driver, renewBtn);
		renewBtn.click();
		util.waitUntilElement(driver, subPlanDrp);
		subPlanDrp.click();
		selectDeusPlan.click();
		util.waitUntilElement(driver, updateSalesOrderBtn);
		updateSalesOrderBtn.click();
	}
	
	
	
	
	
	
	
	
	
	
}
