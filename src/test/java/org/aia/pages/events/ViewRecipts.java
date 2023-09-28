package org.aia.pages.events;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewRecipts {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	ConfigDataProvider testData;
	static Logger log = Logger.getLogger(EventRegistration.class);
	
	public ViewRecipts(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		testData = new ConfigDataProvider();
	}

	@FindBy(xpath = "//body//embed") WebElement getReceiptText;
	
	
	
	public void getReceiptBody(String receiptNo) throws Throwable {
		Thread.sleep(15000);
		util.waitUntilElement(driver, getReceiptText);
	  String receiptText =	getReceiptText.getText();
	  System.out.println(receiptText);  
	  receiptText.contains(receiptNo);
	}
}
