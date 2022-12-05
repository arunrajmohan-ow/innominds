package org.aia.pages.membership;

import java.util.ArrayList;
import java.util.List;

import org.aia.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FinalPageThankYou {

WebDriver driver;
	
	public FinalPageThankYou(WebDriver Idriver) 
	{
		this.driver = Idriver;
	}
	Utility util = new Utility(driver, 10);
	
	@FindBy(xpath="//strong[text()='THANK YOU!']") WebElement thankYou;
	
	@FindBy(xpath="//iframe[@id='transactionFrame']") WebElement frame;
	
	@FindBy(xpath="//body[@id='document']/span/table/tbody/tr[3]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]") WebElement receiptNum;
	
	@FindBy(xpath="//body[@id='document']/span/table/tbody/tr[3]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td/table/tbody/tr[5]/td[2]") WebElement custAIANum;

	
	
	public void verifyThankYouMessage() throws InterruptedException {
		util.waitUntilElement(driver, frame);
		driver.switchTo().frame(frame);
		util.waitUntilElement(driver, thankYou);
		System.out.println("Thank you !  Message is Displayed");
	}
	
	public ArrayList<String> getFinalReceiptData() {
		
		ArrayList<String> receiptData = new ArrayList<String>();
		String receiptNumber = receiptNum.getText();
		receiptData.add(0, receiptNumber);
		String customerAIANumber = custAIANum.getText();
		receiptData.add(1, customerAIANumber);
		
		return receiptData;
	}
}
