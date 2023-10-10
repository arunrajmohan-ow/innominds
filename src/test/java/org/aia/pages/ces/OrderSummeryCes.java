package org.aia.pages.ces;

import org.aia.utility.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderSummeryCes {
WebDriver driver;
	
	Utility util = new Utility(driver, 30);
	
	public OrderSummeryCes(WebDriver Idriver) {
		this.driver = Idriver;
	}
	
	@FindBy(xpath = "//div[@data-name='totalAmount']//div//strong//span") 
	WebElement checkoutAmount;
	
	@FindBy(xpath = "//div[@data-label='Discount Code']//input")
	WebElement discountCodeBox;
	
	@FindBy(xpath = "//div[text()='Discount Applied']//parent::div//strong")
	WebElement discountCodeApplied;
	
	public void applyDiscountCode(String discountCode) {
		util.waitUntilElement(driver, checkoutAmount);
		String beforApplyCodeAmnt=checkoutAmount.getText();
		discountCodeBox.sendKeys(discountCode);
		
		
	}
}
