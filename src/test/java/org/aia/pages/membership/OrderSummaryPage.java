package org.aia.pages.membership;

import org.aia.utility.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderSummaryPage {

	WebDriver driver;
	Utility util = new Utility(driver, 10);
	
	public OrderSummaryPage(WebDriver Idriver) 
	{
		this.driver = Idriver;
	}

	@FindBy(xpath="//p[text()='Dues:']") WebElement duesLabel;
	
	@FindBy(xpath="//input[@id='confirmLicense']/parent::span") WebElement confirmLicense;
	
	@FindBy(xpath="//input[@id='confirmAlliedProfessional']/parent::span") WebElement confirmAlliedProfessional;
	
	@FindBy(xpath="//input[@id='confirmTerms']") WebElement confirmTerms;
	
	@FindBy(xpath="//p[text()='Pay in Full']") WebElement payInFull;
	
	@FindBy(xpath="//p[text()='Pay in installments']") WebElement payInInstallments;
	
	@FindBy(xpath="//p[text()='Exit']") WebElement exitBtn;
	
	//Testing Env
	@FindBy(xpath="//p[text()='Pay now']") WebElement payNowBtn;
	
	@FindBy(xpath="//*[@id='menu-subscriptionPlanId']/div[3]/ul/li[1]/text()") WebElement devpayinFull;
	
	@FindBy(xpath="//*[@id='menu-subscriptionPlanId']/div[3]/ul/li[2]") WebElement sixInstallments;
	
	@FindBy(xpath="//input[@id='confirmInstallments']/parent::span") WebElement confirmInstallments;
	
	@FindBy(xpath="//form/table[2]/tbody/tr/td[2]") WebElement totalMembershipDues;
	
	@FindBy(xpath="//label[text()='Installments']/following-sibling::div/div") WebElement Installments;	
	
	@FindBy(xpath="//p[text()='Pay offline']") WebElement payoffline;
	
	@FindBy(xpath="//span[text()='ArchiPAC donation']/preceding-sibling::span") WebElement pacChkBx;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[2]/div[1]/form/table[3]/tbody/tr/td[2]/span") WebElement pacValue;
	
	public void confirmTerms(String text) throws InterruptedException {
		if(text.contentEquals("activeNonUSLicense")||text.contentEquals("supervision")||text.contentEquals("noLicense")||text.contentEquals("graduate")
				||text.contentEquals("axp")||text.contentEquals("faculty")) 
		{
			util.waitUntilElement(driver, duesLabel);
			Thread.sleep(10000);
			//util.waitUntilElement(driver, confirmTerms);
			confirmTerms.click();
		}
		
		else if(text.contentEquals("activeUSLicense")) 
		{
			Thread.sleep(10000);
			util.waitUntilElement(driver, duesLabel);
			Thread.sleep(10000);
			util.waitUntilElement(driver, confirmLicense);
			Thread.sleep(10000);
			confirmLicense.click();
			confirmTerms.click();
		}
		
		else if(text.contentEquals("allied"))
		{
			util.waitUntilElement(driver, duesLabel);
			Thread.sleep(10000);
			//util.waitUntilElement(driver, confirmTerms);
			confirmTerms.click();
			confirmAlliedProfessional.click();
		}
		
	}

	// Staging Env 
	public void clickonPayInFull() throws InterruptedException
	{
		payInFull.click();
	}
	
	// Testing Env 
	public void clickonPayNow()
	{
		payNowBtn.click();
	}
	
	public void clickonPayOffline()
	{
		payoffline.click();
		
	}

	// Staging Env 
	public void clickonPayInInstallments()
	{
		util.waitUntilElement(driver, duesLabel);
		confirmLicense.click();
		confirmTerms.click();
		payInInstallments.click();
	}
	
	// Testing Env 
	public String payInInstallmentsClick(String text) throws InterruptedException
	{	
		String totalMembership= null;

		if(text.contentEquals("activeUSLicense")||text.contentEquals("allied"))
		{
			util.waitUntilElement(driver, Installments);
			totalMembership = totalMembershipDues.getText();
			Installments.click();
			sixInstallments.click();
			util.waitUntilElement(driver, confirmInstallments);
			confirmInstallments.click();
		}
		
		if(text.contentEquals("graduate")||text.contentEquals("axp")||text.contentEquals("noLicense")||text.contentEquals("supervision")
				||text.contentEquals("faculty"))
		{

			totalMembership = totalMembershipDues.getText();
			System.out.println("Continue");
		}
		
		if(text.contentEquals("activeNonUSLicense"))
		{
			totalMembership = totalMembershipDues.getText();
			Installments.click();
			sixInstallments.click();
			util.waitUntilElement(driver, confirmInstallments);
			confirmInstallments.click();
		}
		payNowBtn.click();
		Thread.sleep(5000);
		
		return totalMembership;
	}
	
	public int GetPacDonationAmount() {
		String pac = pacValue.getText();
		int i  = pac.indexOf(".");
		String p = pac.substring(2, i);
		pacChkBx.click();
		String total = totalMembershipDues.getText();
		 i  = total.indexOf(".");
		String t = total.substring(2, i);
		
		int amnt =  Integer.parseInt(t) + Integer.parseInt(p);
		return amnt;
	}

}
