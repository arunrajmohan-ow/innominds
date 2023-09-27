package org.aia.pages.events;

import java.util.ArrayList;
import java.util.Random;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import groovy.transform.Final;

public class EventRegistration {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	ConfigDataProvider testData;
	public String newEvent = "";
	static Logger log = Logger.getLogger(NewCloneEvents.class);

	public EventRegistration(WebDriver Idriver) {
		this.driver = Idriver;
		executor = (JavascriptExecutor) driver;
		testData = new ConfigDataProvider();
	}

	@FindBy(xpath = "(//select[@name='Quantity'])[1]")WebElement ticketTypeQuantity;
	
	@FindBy(css = "div[id='ticketRegButton'] button") WebElement registerButton;
	
	@FindBy(css = "div[data-name='firstName'] div[data-name='matchFields'] input") WebElement firstName;
	
	@FindBy(css = "div[data-name='lastName'] div[data-name='matchFields'] input") WebElement lastName;
	
	@FindBy(css = "div[data-name='email'] div[data-name='matchFields'] input") WebElement email;
	
	@FindBy(css = "div[data-name='Badge name (first/nickname)-input'] input") WebElement badgeName;
	
	@FindBy(css = "div[data-name='Company-input'] input") WebElement companyName;
	
	@FindBy(css = "div[data-name='Title-input'] input") WebElement titleName;
	
	@FindBy(css = "div[data-name='Phone-input'] input") WebElement phoneNumber;
	
	@FindBy(css = "div[data-name='City-input'] input") WebElement cityName;
	
	@FindBy(css = "select[name='State']") WebElement selectState;
	
	@FindBy(css = "div[data-name='Degree Information-input'] input") WebElement degreeInformation;
	
	@FindBy(css = "select[name='Accessibility']") WebElement selectAccessibility; //Mobility
	
	@FindBy(css = "div[data-name='Emergency contact name-input'] input") WebElement emgContactName;
	
	@FindBy(css = "div[data-name='Emergency contact phone number-input'] input") WebElement emgphoneNumber;
	
	@FindBy(css =" select[name='How many times have you attended this conference?']") WebElement selectAttendedTimes;//First time
	
	@FindBy(css = "select[name='Ethnicity']") WebElement selectEthnicity;
	
	@FindBy(css = "select[name='Gender identity']") WebElement selectGender;
	
	@FindBy(css = "select[name='Professional Level']") WebElement selectProfessionalLevel;
	
	@FindBy(css = "select[name='Years in Profession']") WebElement selectYearsInProfession;
	
	@FindBy(xpath  = "(//span[@class='slds-checkbox--faux'])[2]") WebElement attestCheckobox;
	
	@FindBy(css = "select[name='I attest to the above']") WebElement selectAttest; //Yes No
	
	@FindBy(css = "div[data-name='Printed Name and Signature:-input'] input") WebElement printedNameAndSignature;
	
	@FindBy(css = "button[name='continueAttendeeSetup']") WebElement continueButtonInRegistration;
	
	//Agenda
	
	@FindBy(css = "a[data-name='agenda']") WebElement agenda;
	
	@FindBy(xpath  = "//button[text()='Continue']") WebElement continueButtonInAgenda;
	
	
	//checkout 
	
	@FindBy(xpath = "//iframe[@title='Credit Card Input Frame']") WebElement creditCardPaymentFrame;
	
	@FindBy(xpath="//iframe[@title='Card number']") WebElement cardNumFrame2;
	
	@FindBy(xpath = "//div[@data-name='payNow']//div/following::div/following::div//div[@class='slds-card slds-theme_default']/div[1]")
	WebElement choosePaymentText;
	
	@FindBy(xpath = "//div[@data-name='full_name']/input") WebElement cardHolderName;
	
//	@FindBy(xpath = "//input[@id='card_number']") WebElement creditCardNumber;
	@FindBy(xpath="//input[@name='card_number']") WebElement creditCardNumber;
	
	@FindBy(css = "select[name='Exp month']") WebElement expMonth;
	
	@FindBy(css = "select[name='Exp year']") WebElement expYear;
	
	@FindBy(css  = "data-name='processBtn'" ) WebElement processPayment;
	
	// New Billing Address
	@FindBy(css = "button[data-name='addressCreateButton']") WebElement addressCreateButton;
	
	@FindBy(css = "div[data-name='name'] input") WebElement billingAddressName;
	
	@FindBy(css = "select[name='Type']") WebElement billingType;//Home
	
	@FindBy(css = "input[placeholder='Enter your address']") WebElement addressSearch;//India
	
	
	//view recipt
	@FindBy(css = "button[data-name='view-receipt']") WebElement viewRecieptInCheckout;
	
	
	ArrayList<String> list = new ArrayList<String>();

	public void selectTicketQuantity() {
		util.waitUntilElement(driver, ticketTypeQuantity);
		util.scrollingElementUsingJS(driver, ticketTypeQuantity);
		Utility.highLightElement(driver, ticketTypeQuantity);
		util.selectDropDownByText(ticketTypeQuantity, "1");
		log.info("mticketTypeQuantity dropdown selected as 1");
	}
	
	public void clickRegisterButton() throws Throwable {
		util.waitUntilElement(driver, registerButton);
		Utility.highLightElement(driver, registerButton);
		registerButton.click();
		log.info("Register button is cliked sucessfully");
		Thread.sleep(14000);
	}
	
	/**
	 * Here we entered badgename, company, title, mobileNumber, emergencyMobileNUmber.
	 * Select state, degreeInformation, Accessbility, AttendenceTimes, ethnicity, professional level, years in professional, attest.
	 */
	public void rigisterRequiredInfo() {
		String mobNumb = "012345" + String.format("%05d", new Random().nextInt(10000));
		String emgMobNumb = "112345" + String.format("%05d", new Random().nextInt(10000));
		String nickName = "autoln" + RandomStringUtils.randomAlphabetic(4);
		util.waitUntilElement(driver, firstName);
		System.out.println(firstName.getAttribute("value"));
		System.out.println(lastName.getAttribute("value"));
		System.out.println(email.getAttribute("value"));
		util.enterText(driver, badgeName, nickName);
		String company = "poiu" + RandomStringUtils.randomAlphabetic(4);
		util.enterText(driver, companyName, company);
		String title = "jhgf" + RandomStringUtils.randomAlphabetic(4);
		util.enterText(driver, titleName, title);
		util.enterText(driver, phoneNumber, mobNumb);
		util.enterText(driver, cityName, "uytr");
		util.selectDropDownByText(selectState, "AP");
		util.enterText(driver, degreeInformation, "njkhhfnklk");
		util.selectDropDownByText(selectAccessibility, "Mobility");
		util.enterText(driver, emgContactName, "jdkj");
		util.enterText(driver, emgphoneNumber, emgMobNumb);
		util.selectDropDownByText(selectAttendedTimes, "First time");
		util.selectDropDownByText(selectEthnicity, "Asian - Other");
		util.selectDropDownByText(selectGender, "Male");
		util.selectDropDownByText(selectProfessionalLevel, "Student");
		util.selectDropDownByText(selectYearsInProfession, "0-5 years");
		Utility.highLightElement(driver, attestCheckobox);
		attestCheckobox.click();
		util.selectDropDownByText(selectAttest, "Yes");
		util.enterText(driver, printedNameAndSignature, "nkfdjlfre");
	}
	
	public void clickRegistrationButton() throws Throwable {
		continueButtonInRegistration.click();
		Thread.sleep(15000);
		util.waitUntilElement(driver, continueButtonInRegistration);
		continueButtonInRegistration.click();
	}
	
	public void agendaModule() throws Throwable {
	    Assert.assertTrue(agenda.isEnabled());
		Thread.sleep(15000);
		util.waitUntilElement(driver, continueButtonInAgenda);
		Utility.highLightElement(driver, continueButtonInAgenda);
		continueButtonInAgenda.click();
	}
	
	public void checkoutModule(String cardNumber, String month, String year) throws Throwable {
		Thread.sleep(14000);
		util.waitUntilElement(driver, cardHolderName);
		Utility.highLightElement(driver, cardHolderName);
		System.out.println(cardHolderName.getAttribute("value"));
		util.waitUntilElement(driver, creditCardPaymentFrame);
		util.switchToFrameUsingWebElement(driver, creditCardPaymentFrame);
		util.waitUntilElement(driver, cardNumFrame2);
		util.switchToFrameUsingWebElement(driver, cardNumFrame2);
		Utility.highLightElement(driver, creditCardNumber);
		util.waitUntilElement(driver, creditCardNumber);
		driver.switchTo().defaultContent();
		util.enterText(driver, creditCardNumber, cardNumber); 
		util.selectDropDownByText(expMonth, month);
		util.selectDropDownByText(expYear, year);
		processPayment.click();
		
	}
	
}
