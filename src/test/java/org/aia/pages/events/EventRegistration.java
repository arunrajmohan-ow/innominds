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
	static Logger log = Logger.getLogger(EventRegistration.class);

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
	
	@FindBy(css  = "button[data-name='processBtn']" ) WebElement processPayment;
	
	// New Billing Address
	@FindBy(css = "button[data-name='addressCreateButton']") WebElement addressCreateButton;
	
	@FindBy(css = "div[data-name='name'] input") WebElement billingAddressName;
	
	@FindBy(css = "select[name='Type']") WebElement billingType;//Home
	
	@FindBy(css = "input[placeholder='Enter your address']") WebElement addressSearch;//India
	
	
	//view recipt
	@FindBy(css = "button[data-name='view-receipt']") WebElement viewRecieptInCheckout;
	
	@FindBy(css = "div[class*='slds-text-heading--medium slds']") WebElement paymentSuccessMessage;
	
	
	ArrayList<String> list = new ArrayList<String>();

	public void selectTicketQuantity() throws Throwable {
		Thread.sleep(7000);
		util.waitUntilElement(driver, ticketTypeQuantity);
		util.scrollingElementUsingJS(driver, ticketTypeQuantity);
		Utility.highLightElement(driver, ticketTypeQuantity);
		util.selectDropDownByText(ticketTypeQuantity, "1");
		log.info("ticketTypeQuantity dropdown selected as 1");
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
		String state = "AP";
		String accessibility = "Mobility";
		String attendedTimes = "First time";
		String ethnicity = "Asian - Other";
		String gender = "Male";
		String professionalLevel = "Student";
		String yearsInProfession = "0-5 years";
		String attest = "Yes";
		util.waitUntilElement(driver, firstName);
		System.out.println(firstName.getAttribute("value"));
		log.info(firstName.getAttribute("value"));
		System.out.println(lastName.getAttribute("value"));
		log.info(lastName.getAttribute("value"));
		System.out.println(email.getAttribute("value"));
		log.info(email.getAttribute("value"));
		util.enterText(driver, badgeName, nickName);
		log.info("BadgeName enterd as" + nickName);
		String company = "poiu" + RandomStringUtils.randomAlphabetic(4);
		util.enterText(driver, companyName, company);
		log.info("CompanyName enterd as " + company);
		String title = "jhgf" + RandomStringUtils.randomAlphabetic(4);
		util.enterText(driver, titleName, title);
		log.info("TitleName enterd as " + title);
		util.enterText(driver, phoneNumber, mobNumb);
		log.info("PhoneNumber enterd as" + mobNumb);
		String city = "apk" + RandomStringUtils.randomAlphabetic(4);
		util.enterText(driver, cityName, city);
		log.info("CityName enterd as" + city);
		util.selectDropDownByText(selectState, state);
		log.info("State dropdown selected as" + state);
		String degreeInfo = "mba" + RandomStringUtils.randomAlphabetic(4);
		util.enterText(driver, degreeInformation, degreeInfo);
		log.info("degreeInformation dropdown selected as" + degreeInfo);
		util.selectDropDownByText(selectAccessibility, accessibility);
		log.info("Accessibility dropdown selected as" + accessibility);
		String emgName = "awer" + RandomStringUtils.randomAlphabetic(4);
		util.enterText(driver, emgContactName, emgName);
		log.info("Emergency contact name enterd as" + emgName);
		util.enterText(driver, emgphoneNumber, emgMobNumb);
		log.info("Emergency phone number enterd as" + emgMobNumb);
		util.selectDropDownByText(selectAttendedTimes, attendedTimes);
		log.info("AttendedTimes dropdown selected as" + attendedTimes);
		util.selectDropDownByText(selectEthnicity, ethnicity);
		log.info("Ethnicity dropdown selected as"+ ethnicity);
		util.selectDropDownByText(selectGender, gender);
		log.info("Gender dropdown selected as"+ gender);
		util.selectDropDownByText(selectProfessionalLevel, professionalLevel);
		log.info("ProfessionalLevel dropdown selected as" + professionalLevel);
		util.selectDropDownByText(selectYearsInProfession, yearsInProfession);
		log.info("YearsInProfession dropdown selected as" + yearsInProfession);
		Utility.highLightElement(driver, attestCheckobox);
		attestCheckobox.click();
		log.info("attest checkbox is clicked sucessfully");
		util.selectDropDownByText(selectAttest, attest);
		log.info("attest dropdown selected as"+ attest);
		String nameAndSign = "lkjh" + RandomStringUtils.randomAlphabetic(4);
		util.enterText(driver, printedNameAndSignature, nameAndSign);
		log.info("printedNameAndSignature enterd as" + nameAndSign);
	}
	
	public void clickRegistrationButton() throws Throwable {
		continueButtonInRegistration.click();
		log.info("Continue button is clicked in registration");
		Thread.sleep(18000);
	}
	
	public void validateRegisterReq() {
		util.waitUntilElement(driver, firstName);
		log.info(firstName.getAttribute("value"));
		util.waitUntilElement(driver, continueButtonInRegistration);
		Utility.highLightElement(driver, continueButtonInRegistration);
		continueButtonInRegistration.click();
	}
	
	public void agendaModule() throws Throwable {
		Thread.sleep(15000);
		util.waitUntilElement(driver, continueButtonInAgenda);
		Utility.highLightElement(driver, continueButtonInAgenda);
		continueButtonInAgenda.click();
		log.info("Continue button is clicked in agenda");
	}
	
	public void checkoutModule(String cardNumber, String month, String year) throws Throwable {
		Thread.sleep(14000);
		util.waitUntilElement(driver, cardHolderName);
		Utility.highLightElement(driver, cardHolderName);
		log.info(cardHolderName.getAttribute("value"));
		System.out.println(cardHolderName.getAttribute("value"));
		util.waitUntilElement(driver, creditCardPaymentFrame);
		util.switchToFrameUsingWebElement(driver, creditCardPaymentFrame);
		util.waitUntilElement(driver, cardNumFrame2);
		util.switchToFrameUsingWebElement(driver, cardNumFrame2);
		Utility.highLightElement(driver, creditCardNumber);
		util.waitUntilElement(driver, creditCardNumber);
		util.enterText(driver, creditCardNumber, cardNumber); 
		log.info("Credit Card number enterd as" + cardNumber);
		driver.switchTo().defaultContent();
		util.selectDropDownByText(expMonth, month);
		log.info("ExpMonth selected as" + month);
		util.selectDropDownByText(expYear, year);
		log.info("ExpMonth selected as" + year);
		util.scrollingElementUsingJS(driver, processPayment);
		try {
		util.clickUsingJS(driver, processPayment);
		}catch (Exception e) {
			processPayment.click();	
			System.out.println("normal click method");
		}
		log.info("processPayment is clicked successfully");
		Thread.sleep(7000);
		util.waitUntilElement(driver, paymentSuccessMessage);
		log.info("After Payment success message" + paymentSuccessMessage.getText());
	}
	
}
