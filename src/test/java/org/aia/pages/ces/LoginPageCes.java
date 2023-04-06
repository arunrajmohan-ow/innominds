package org.aia.pages.ces;

import org.aia.utility.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class LoginPageCes {

	WebDriver driver;
	Utility util = new Utility(driver, 30);
	
		public LoginPageCes(WebDriver Idriver)
		{
			this.driver=Idriver;
		}
	
	@FindBy(xpath="//input[@formcontrolname= 'username']")
	public WebElement emailAddress;
	
	@FindBy(xpath="//input[@formcontrolname= 'password']")
	public WebElement password;
	
	@FindBy(xpath="//button[@type= 'submit']")
	public WebElement submitbtn;
	
	@FindBy(xpath="//a[text()= 'Forgot password?']")
	public WebElement forgotpwd;
	
	@FindBy(xpath="//a[text()='Sign up']")
	public WebElement signUplink;

	
	@Step("Enter credentials with username {0} and password {1} and click on submit button")
	public void loginToCes(String uname,String pwd)
	{
		util.waitUntilElement(driver, emailAddress);
		System.out.println("Waiting for the email text field to appear");
		
		System.out.println("Email Text field displayed");
		emailAddress.sendKeys(uname);
		password.sendKeys(pwd);
		submitbtn.click();

			
	}
	
	
	  @Step("Click on Signup link present in LoginPage") 
	  public void clickonsignuplink() {
	  
	  System.out.println("Waiting for the email text field to appear");
	  util.waitUntilElement(driver, emailAddress);
	  System.out.println("Email Text field displayed");
	  
	  signUplink.click();
	  
	  
	  }
	 

}
