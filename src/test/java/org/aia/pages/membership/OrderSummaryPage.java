package org.aia.pages.membership;

import org.aia.utility.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderSummaryPage {

	WebDriver driver;
	Utility util = new Utility(driver, 10);
	
	public OrderSummaryPage(WebDriver Idriver) 
	{
		this.driver = Idriver;
	}

	@FindBy(xpath="//p[text()='Dues:']") WebElement duesLabel;
	
	@FindBy(xpath="//input[@id='confirmLicense']/parent::span") WebElement confirmLicense;
	
	@FindBy(xpath="//input[@id='confirmTerms']") WebElement confirmTerms;
	
	@FindBy(xpath="//p[text()='Pay in Full']") WebElement payInFull;
	
	@FindBy(xpath="//p[text()='Pay in installments']") WebElement payInInstallments;
	
	
	
	public void clickonPayInFull() throws InterruptedException
	{
		Thread.sleep(10000);
		util.waitUntilElement(driver, duesLabel);
		Thread.sleep(10000);
		util.waitUntilElement(driver, confirmLicense);
		Thread.sleep(10000);
		confirmLicense.click();
		confirmTerms.click();
		payInFull.click();
	}
	
	public void clickonPayInInstallments()
	{
		util.waitUntilElement(driver, duesLabel);
		confirmLicense.click();
		confirmTerms.click();
		payInInstallments.click();
	}
	
	
}
