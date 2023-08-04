package org.aia.pages.membership;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.aia.utility.Utility;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class SignUpPage {
	
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	
	public SignUpPage(WebDriver Idriver)
	{
		this.driver=Idriver;
	}

	@FindBy(xpath="//*[@id=\"email \"]") WebElement enteremail;
	
	@FindBy(xpath="//p[text()='Continue']")WebElement continuebtn;
	
	@FindBy(xpath = "//p[text()='Join AIA']//parent::span//parent::button") WebElement joinAIABtn;
	
	@FindBy(xpath="//p[text()='Create an account']")WebElement createaccount;
	
	@FindBy(xpath="//input[@formcontrolname='firstName']")	WebElement firstName;
	
	@FindBy(xpath="//input[@formcontrolname='lastName']") WebElement lastName;
	
	@FindBy(xpath="//input[@formcontrolname='email']") WebElement emailAddress;
	
	@FindBy(xpath="//mat-select[@formcontrolname='mobilePhoneCountry']") WebElement mobileCountry;
	
	@FindBy(xpath="//span[text()=' United States of America (+1) ']") WebElement mobileCountryoption;	
	
	@FindBy(xpath="//input[@formcontrolname='mobilePhone']") WebElement mobilePhoneNum;
	
	@FindBy(xpath="//input[@formcontrolname='password']") WebElement desirdPwd;
	
	@FindBy(xpath="//input[@formcontrolname='confirmPassword']") WebElement confrmPwd;
	
	@FindBy(xpath="//span[text()='Sign Up']") WebElement signUpSubmitbtn;
	
	@FindBy(xpath="//iframe[@title='reCAPTCHA']") WebElement captchaFrame;
	
	@FindBy(xpath="//div[@class = 'recaptcha-checkbox-border']") WebElement captchaChckbx;
	
	@FindBy(xpath="//span[text()= 'Close']") WebElement closebtn;
	
	 String fName;
	 String lName;
	 String mobNumb;
	 String password;
	 public String emailaddressdata;
	 public String  emailPrefix;
	 public String emailDomain;
	 ArrayList<String> list = new ArrayList<String>();
	 
	 
	 public void gotoMembershipSignUpPage(String emaildata)
		{
			util.waitUntilElement(driver, enteremail);
			enteremail.sendKeys(emaildata);
			util.waitUntilElement(driver, continuebtn);
			continuebtn.click();
			util.waitUntilElement(driver, createaccount);
			createaccount.click();
		}

	public ArrayList<String> signUpData() throws Exception { 
		
		fName = "autofn"+RandomStringUtils.randomAlphabetic(4);
		  list.add(0, fName);
		  System.out.println(fName); 
		  lName = "autoln"+RandomStringUtils.randomAlphabetic(4);
		  list.add(1, lName);
		  System.out.println("Full name is:"+fName+""+lName);
		  mobNumb = "012345"+String.format("%05d", new Random().nextInt(10000));
		  list.add(2, mobNumb);
		  System.out.println(mobNumb);
		  DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
		  Date date = new Date();
		  System.out.println(date.toString());
		  String date1= dateFormat.format(date);
		  System.out.println(date1);
		  emailPrefix = "auto_"+RandomStringUtils.randomAlphabetic(4).toLowerCase()+date1;
		  list.add(3, emailPrefix);
		  emailDomain = "@architects-team.m8r.co";
		  list.add(4, emailDomain);
		  emailaddressdata = emailPrefix + emailDomain;
		  list.add(5, emailaddressdata);		 
		  System.out.println("Email address is:"+emailaddressdata);
		  password = "Login_123";
		  list.add(6, password);
		  
		  return list;
		  }
	
	@Step("Enter user details and click on submit button")
	public void signUpUser( ) throws Exception
	{
		util.waitUntilElement(driver, firstName);
		System.out.println("FirstName is displayed"); 
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		emailAddress.sendKeys(emailaddressdata);
		util.waitUntilElement(driver, mobileCountry);
		mobileCountry.click();
		util.waitUntilElement(driver, mobileCountryoption);
		mobileCountryoption.click();
		mobilePhoneNum.sendKeys(mobNumb);
		desirdPwd.sendKeys(password);
		confrmPwd.sendKeys(password); 
		
		//Add this code if you encounter captcha
		 /* WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(captchaFrame));
		  
		  wait.until(ExpectedConditions.elementToBeClickable(captchaChckbx)).click();
		  
		  Thread.sleep(10000); driver.switchTo().defaultContent();*/
		signUpSubmitbtn.click();
	}
	
	/**
	 * @param emaildata
	 */
	public void joinAIABtn(String emaildata) {
		util.waitUntilElement(driver, enteremail);
		enteremail.sendKeys(emaildata);
		util.waitUntilElement(driver, continuebtn);
		continuebtn.click();
		util.waitUntilElement(driver, joinAIABtn);
		joinAIABtn.click();
	}
	
}
