package org.aia.pages.membership;

import org.aia.utility.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PaymentInformation {

	WebDriver driver;
	Utility util = new Utility(driver, 10);
	
	public PaymentInformation(WebDriver Idriver) 
	{
		this.driver = Idriver;
	}
	
	String creditCardNum = "4111111111111111";
	String cardExpMonth = "02";
	String cardExpYr = "2027";
	
	@FindBy(xpath="//*[@id='checkout-form-wrapper']/div[1]/h5/text()") WebElement paymentInfo;
	
	@FindBy(xpath="//a[text()='Credit card']") WebElement creditCard;
	
	@FindBy(xpath="//a[text()='ECheck']") WebElement eCheck;
	
	@FindBy(xpath="//div[@data-name='full_name']/input") WebElement fullName;
	
	@FindBy(xpath="//iframe[@title='Credit Card Input Frame']") WebElement cardNumFrame1;
	
	@FindBy(xpath="//iframe[@title='Card number']") WebElement cardNumFrame2;
	
	
	@FindBy(xpath="//input[@name='card_number']") WebElement cardNum;
	
	@FindBy(xpath="//select[@name='Exp month']") WebElement expMonth;
	
	@FindBy(xpath="//select[@name='Exp year']") WebElement expYr;
	
	@FindBy(xpath="//label[@data-name='savePaymentMethod']") WebElement chckBox;
	
	@FindBy(xpath="//button[@data-name='processBtn']") WebElement procssPaymntBtn;
	
	
	
	public void enterCrditCardDetails() throws InterruptedException 
	{
		util.waitUntilElement(driver, creditCard);
		util.waitUntilElement(driver, cardNumFrame1);
		driver.switchTo().frame(cardNumFrame1);
		driver.switchTo().frame(cardNumFrame2);
		util.enterText(driver, cardNum, creditCardNum);
		driver.switchTo().defaultContent();
		Select s1 = new Select(expMonth);
		s1.selectByValue(cardExpMonth);
		
		Select s2 = new Select(expYr);
		s2.selectByValue(cardExpYr);
		
		chckBox.click();
		procssPaymntBtn.click();
	}
	
}
