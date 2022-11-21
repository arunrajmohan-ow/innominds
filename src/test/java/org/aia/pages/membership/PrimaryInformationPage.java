package org.aia.pages.membership;

import org.aia.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class PrimaryInformationPage {

	
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	String zipCode = "91942";
	String licenseDate = "12/03/2021";
	String graduateDate = "12/03/2021";
	String address = "Jan Drive, La Mesa, CA, USA";
	String addressStreet = "Jan Dr";
	String addressCity = "La Mesa";
	String addressState = "CA";
	String addressCountry = "United States";
	String addressZipCode = "91942";
	String CREDIT_CARD_NUMBER = "4111111111111111";
	String CREDIT_CARD_EXP_MONTH = "02";
	String CREDIT_CARD_EXP_YEAR = "2027";
	String LICENSE_EXP_DATE = "Dec 3, 2029";
	String LICENSE_NUMBER = "123456";
	String LICENSE_COUNTRY = "Afganisthan";
	
	public PrimaryInformationPage(WebDriver IDriver) 
	{
		this.driver = IDriver;
	}
	
	
	@FindBy(xpath="//h1[text()= 'Welcome to AIA']") WebElement welocomeToAIA;
		
	@FindBy(xpath="//div[@id='country']") WebElement licenseCountry;
	
	@FindBy(xpath="//input[@name='zipCode']") WebElement zipCode1;
	
	@FindBy(xpath="//input[@name='licenseDate']") WebElement originalLicenseDate;
	
	@FindBy(xpath="//input[@id='gradDate']") WebElement gradDate;
	
	@FindBy(xpath="//label[@id='careerType']//parent::div") WebElement careerTypedrpdwn;

	@FindBy(xpath="//label[text()='Search for your address']//following-sibling::div/input") WebElement searchAddress;
	
	@FindBy(xpath="//label[text()='Street address']//following-sibling::div/input") WebElement streetAddress;
	
	@FindBy(xpath="//label[text()='Apartment, suit, etc. (optional)']//following-sibling::div") WebElement apartment;

	@FindBy(xpath="//label[text()='City']//following-sibling::div/input") WebElement city;

	@FindBy(xpath="//label[text()='State']//following-sibling::div/input") WebElement state;

	@FindBy(xpath="//input[@id='addressCountry']") WebElement country2;

	@FindBy(xpath="//label[text()='Zip Code']//following-sibling::div/input") WebElement zipCode2;

	@FindBy(xpath="//p[text()='Next']//parent::span/parent::button") WebElement nextButn;
	


	/*
	 * Choose the option that best describes you to get Membership Types : 
	 * Associate(graduate-no fees)
	 * Architect(US architect license holder)
	 * International(foreign members holding architecture license)
	 * Allied(allied people like designers,plumbers etc)
	 */
	public void selfDescribe_selectRadioBtnByValue( String string) {

		Utility.waitForWebElement(driver, welocomeToAIA, 10);
		String radioBtnValue = string;
		String value="";
		switch(radioBtnValue)
		{
		case "activeUSLicense" :  	value= "I hold an active architecture license from a U.S. licensing authority.";
		Reporter.log("LOG : INFO - Membership Type : Architect member");
		break;
		case "activeNonUSLicense":  value= "I hold an active architecture license from a non-U.S. licensing authority.";
		Reporter.log("LOG : INFO - Membership Type :  Intl Associate member.");
		break;
		case "graduate":  			value= "I am a new graduate.";
		Reporter.log("LOG : INFO - Membership Type :  Associate member.");
		break;
		case "axp":  				value= "I am enrolled in AXP.";
		break;
		case "noLicense":  			value= "I am not licensed, but I have an architecture degree.";
		break;
		case "supervision":  		value= "I do not have an architecture degree, but I am working under the supervision of a licensed architect.";
		break;
		case "faculty":  			value= "I am a faculty member in an architecture program.";
		break;
		case "allied":  			value= "I am an allied professional.";
		Reporter.log("LOG : INFO -Membership Type : Allied member");
		break;
		}
		
	 driver.findElement(By.xpath("//span[text()= '"+value+"'] //parent::label")).click();

	}
	
	
	public void selectCountry() {
		WebElement e1 = Utility.waitForWebElement(driver, licenseCountry, 10);
		String countryName = e1.getText();
		e1.click();
		WebElement e2 = Utility.waitForWebElement(driver, "//div[@id='menu-country']//div[3]/ul/li[text()='"+countryName+"']", 10);
		e2.click();
	}
	
	public void selectCareerType(String careerType) {
		WebElement e1 = Utility.waitForWebElement(driver, careerTypedrpdwn, 10);
		e1.click();
		WebElement e2 = Utility.waitForWebElement(driver, "//li[text()='"+careerType+"']", 10);
		e2.click();
	}


	public void enterAddressDetails() throws InterruptedException {
		util.enterText(driver, searchAddress, address);
		util.enterText(driver, streetAddress, addressStreet);
		util.enterText(driver, city, addressCity);
		util.enterText(driver, state, addressState);
		util.enterText(driver, country2, addressCountry);
		util.enterText(driver, zipCode2, zipCode);
	}
	
	public void enterPrimaryInfo(String radbtnString, String careerType) throws InterruptedException {
		selfDescribe_selectRadioBtnByValue(radbtnString);
		if (!radbtnString.contentEquals("allied")) {
			selectCountry();
			util.enterText(driver, zipCode1, zipCode);
		}
		
		if(radbtnString.contentEquals("activeUSLicense")||radbtnString.contentEquals("activeNonUSLicense"))
		{
			util.enterText(driver, originalLicenseDate, licenseDate);
			selectCareerType(careerType);
		} 
		else if(radbtnString.contentEquals("graduate")||radbtnString.contentEquals("axp")||radbtnString.contentEquals("noLicense")) {
			util.enterText(driver, gradDate, graduateDate);
		} 
		else if(radbtnString.contentEquals("faculty")) {
			selectCareerType(careerType);
		}
		
		enterAddressDetails();
		nextButn.click();
		
	}	

}