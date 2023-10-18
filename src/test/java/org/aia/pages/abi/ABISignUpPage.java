package org.aia.pages.abi;

import java.util.ArrayList;

import org.aia.pages.membership.SignUpPage;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ABISignUpPage {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();

	public ABISignUpPage(WebDriver Idriver) {
		this.driver = Idriver;
	}

	@FindBy(xpath = "//a[@title='Log In']")
	WebElement loginBtn;

	@FindBy(xpath = "//a[@routerlink='/signup']")
	WebElement signUpLink;

	@FindBy(xpath = "//input[@formcontrolname='username']")
	WebElement emailTxt;

	@FindBy(xpath = "//input[@formcontrolname='password']")
	WebElement passwordTxt;

	@FindBy(xpath = "//span[text()='Sign In']")
	WebElement signInBtn;

	@FindBy(xpath = "//div[@class='pfm-tile-img']")
	WebElement abiImage;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement autoRenewChkBx;

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement continueBtn;

	@FindBy(xpath = "//button[@class='slds-button slds-button_brand pfm-button']")
	WebElement addToCart;

	@FindBy(xpath = "//div[@data-aura-class='LTEShoppingCartIcon']")
	WebElement cartIcon;

	@FindBy(xpath = "//button[text()='View Cart']")
	WebElement viewCart;

	@FindBy(xpath = "//button[@data-name='checkoutButton']")
	WebElement checkOut;

	@FindBy(xpath = "//div[@data-name='mainPopupKnownAddressDiv']//button[@data-name='addressCreateButton']")
	WebElement createAddress;

	@FindBy(css = "input[id^='86']")
	WebElement name;

	@FindBy(xpath = "//select[@name='Type']")
	WebElement type;

	@FindBy(xpath = "//input[@aria-label='Enter your address']")
	WebElement address;

	@FindBy(xpath = "//h2[text()='New Address']//parent::div//following-sibling::div[contains(@class,'l__footer')]//button[text()='Save']")
	WebElement saveBtn;

	@FindBy(xpath = "//button[text()='Proceed to Checkout']")
	WebElement proceedToCheckOut;
	
	@FindBy(xpath = "//iframe[@title='Credit Card Input Frame']")
	WebElement iFrame1;
	
	@FindBy(xpath = "//iframe[@title='Card number']")
	WebElement iFrame2;
	

	@FindBy(xpath = "//input[@id='card_number']")
	WebElement ccNumberTxt;
	
	@FindBy(xpath = "//select[@name='Exp year']")
	WebElement expYear;

	@FindBy(xpath = "//button[@aria-label='Process payment']")
	WebElement processPymtBtn;

	@FindBy(xpath = "//span[text()='View Receipt']")
	WebElement viewReceipt;

	public void goToSignUpLink() throws Exception {
		util.waitUntilElement(driver, loginBtn);
		loginBtn.click();
		util.waitUntilElement(driver, signUpLink); 
		signUpLink.click();
		 

	}

	public void signInUser(String email, String password) throws Exception {
		util.waitUntilElement(driver, emailTxt);
		emailTxt.sendKeys(email);
		util.waitUntilElement(driver, passwordTxt);
		passwordTxt.sendKeys(password);
		util.waitUntilElement(driver, signInBtn);
		signInBtn.click();
	}

	@SuppressWarnings("static-access")
	public void subscribeToABI(String user) throws InterruptedException {
		util.waitForWebElement(driver, abiImage, 5000);
		abiImage.click();
		util.waitForWebElement(driver, autoRenewChkBx, 5000);
		util.waitForWebElement(driver, continueBtn, 5000);
		continueBtn.click();
		util.waitForWebElement(driver, addToCart, 5000);
		addToCart.click();
		Thread.sleep(7000);
		util.waitForWebElement(driver, cartIcon, 5000);
		cartIcon.click();

		util.waitForWebElement(driver, viewCart, 5000);
		viewCart.click();
		Thread.sleep(7000);

		util.waitForWebElement(driver, checkOut, 5000);
		checkOut.click();
		util.waitForWebElement(driver, createAddress, 5000);
		createAddress.click();
		util.waitUntilElement(driver, name);
		name.sendKeys(user);
		util.waitUntilElement(driver, type);
		Select selType = new Select(type);
		selType.selectByValue("Home");
		util.waitUntilElement(driver, address);
		// 1735 York Avenue, NewYork, NY, USA
		address.sendKeys("1735");
		Thread.sleep(3000);
		address.sendKeys(Keys.ENTER);

		util.waitUntilElement(driver, saveBtn);
		saveBtn.click();
		util.waitForWebElement(driver, proceedToCheckOut, 5000);
		proceedToCheckOut.click();
		
		Thread.sleep(3000);
		util.waitForWebElement(driver, iFrame1, 5000);
		driver.switchTo().frame(iFrame1);
		
		util.waitForWebElement(driver, iFrame2, 5000);
		driver.switchTo().frame(iFrame2);
		
		util.waitForWebElement(driver, ccNumberTxt, 5000);
		ccNumberTxt.sendKeys("4111 1111 1111 1111");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		util.waitForWebElement(driver, expYear, 5000);
		Select expYr = new Select(expYear);
		expYr.selectByValue("2024");
		Thread.sleep(3000);

		util.waitForWebElement(driver, processPymtBtn, 5000);
		processPymtBtn.click();
		Thread.sleep(3000);
		util.waitForWebElement(driver, viewReceipt, 5000);
		//viewReceipt.click();
	}
}
