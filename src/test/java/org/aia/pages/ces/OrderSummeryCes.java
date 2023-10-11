package org.aia.pages.ces;

import static org.testng.Assert.assertTrue;

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

	@FindBy(xpath = "//button[text()='Apply']")
	WebElement applyBtn;

	@FindBy(xpath = "//div[text()='Discount Applied']//parent::div//strong")
	WebElement discountCodeApplied;

	/**
	 * @param discountCode
	 * @return 
	 */
	public String applyDiscountCode(String discountCode) {
		util.waitUntilElement(driver, checkoutAmount);
		String beforApplyCodeAmnt = checkoutAmount.getText();
		discountCodeBox.sendKeys(discountCode);
		applyBtn.click();
		return beforApplyCodeAmnt;
		
	}
	
	public void validateAmountAfterApplyDiscountCode() {
		util.waitUntilElement(driver, checkoutAmount);
		String afterApplyCodeAmnt = checkoutAmount.getText();
		assertTrue(false);
	}
	
	
}
