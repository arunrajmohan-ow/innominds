package org.aia.pages.ces;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.aia.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Subscription {

	
	WebDriver driver;
	
	Utility util = new Utility(driver, 30);
	
	public Subscription(WebDriver Idriver) {
		this.driver = Idriver;
	}
	//ArchitectureElements
	
	@FindBy(xpath="//select[@name='Is_your_organization_an_architecture_firm_or_design_firm_in_which_a_principal_or']") WebElement archFirmSubscription;
	
	@FindBy(xpath="//input[@name='Enter_the_AIA_member_number_of_the_principal_owner']") WebElement archFirmAIAMemNum;
	
	@FindBy(xpath="//button[text()='Previous']") WebElement subscriptionPrevious;
	
	@FindBy(xpath="//button[text()='Next']") WebElement subscriptionNext;
	
	//PassportElements

	@FindBy(xpath="//button[text()='Previous']") WebElement passportPrevious;
	
	@FindBy(xpath="//button[text()='Next']") WebElement passportNext;
	
	//ProfessionElements

	@FindBy(xpath="//select[@name='s_your_organization_any_of_the_following']") WebElement profQualifyingQues;
	
	@FindBy(xpath="//*[text()='Upload Files']") WebElement profUploadFile;
	
	@FindBy(xpath="//span[text()='Done']") WebElement pofessionalFileUplaodDoneButton;
	
	@FindBy(xpath="//button[@title='Delete Document']") WebElement pofessionalDeleteBtn;
	
	// Alert : Your company doesn't support the following file types: .txt
	@FindBy(xpath="//div[contains(@id,'help-message')]") WebElement pofessionalUnsupportedFilTypes;

	@FindBy(xpath="//button[text()='Previous']") WebElement professionalPrevious;
	
	@FindBy(xpath="//button[text()='Next']") WebElement professionalNext;
	
	//ConfirmDetailsPage
	@FindBy(xpath="//*[contains(text(),'You will not be able to go back once you continue.')]") WebElement confirmContinueText;
	
	@FindBy(xpath="//*[contains(text(),'Organization Name')]/parent::p") WebElement orgNameValue;
	
	@FindBy(xpath="//button[text()='Previous']") WebElement confirmPrevious;
	
	@FindBy(xpath="//button[text()='Next']") WebElement confirmNext;
	
	
	
	/*
	 * @param : text
	 * @param : aiaMemberNumber
	 * @param : orgType
	 */
	public void SubscriptionType(String text, String isFirmSubscription, String aiaMemberNumber, String orgType) throws InterruptedException {
		if(text.contentEquals("Architecture Firm")||text.contentEquals("Architecture - Single Discipline")||text.contentEquals("Multi-Disciplinary (Architect Led)")||text.contentEquals("Multi-disciplinary (engineer led)")
				||text.contentEquals("Multi-disciplinary (interior led)")||text.contentEquals("Multi-disciplinary (planning led)")||text.contentEquals("Design & construction services")) 
		{
			ArchitectType(isFirmSubscription, aiaMemberNumber);
		}
		
		else if(text.contentEquals("Building product manufacturer")||text.contentEquals("Construction")||text.contentEquals("Consulting")||text.contentEquals("Engineering")
				||text.contentEquals("Interior Design")||text.contentEquals("Landscape")||text.contentEquals("Real Estate/Building owner")||text.contentEquals("Law")
				||text.contentEquals("Press")||text.contentEquals("Other")) 
		{
			PassportType();
		}
		
		else if(text.contentEquals("Institutional")||text.contentEquals("Government/public")||text.contentEquals("Non-profit/trade association")||text.contentEquals("Licensing Board"))
		{
			ProfessionalType(orgType);
		}
		
	}
	
	public void PassportType() throws InterruptedException {

		util.waitUntilElement(driver, passportNext);
		passportNext.click();
		util.waitUntilElement(driver, confirmNext);
		Thread.sleep(3000);
		confirmNext.click();
	}
	
	public void ArchitectType(String isFirmSubscription, String aiaMemberNumber) throws InterruptedException {

		util.waitUntilElement(driver, archFirmSubscription);
		util.selectDropDownByText(archFirmSubscription, isFirmSubscription);
		if(aiaMemberNumber == null || aiaMemberNumber.isEmpty() || aiaMemberNumber.trim().isEmpty()) {
			subscriptionNext.click();
			util.waitUntilElement(driver, confirmNext);
			confirmNext.click();
		}
		else {
			
			archFirmAIAMemNum.sendKeys(aiaMemberNumber);
			subscriptionNext.click();
			util.waitUntilElement(driver, confirmNext);
			confirmNext.click();
		}
	}
	
	/*
	 * @param : orgDetails
	 * e.g. : "Government Agency", "Accredited Academic Institution", "Non-profit", "None"
	 */
	public void ProfessionalType(String orgType) throws InterruptedException {
		util.waitUntilElement(driver, profQualifyingQues);
		if(orgType == null || orgType.isEmpty() || orgType.trim().isEmpty()) {
			util.selectDropDownByText(profQualifyingQues, "None");
		}else {
			util.selectDropDownByText(profQualifyingQues, orgType);
		}
		try {
			
			String PdfFile = System.getProperty("user.dir")+"\\UploadFiles\\FileAIA.pdf";
			util.waitUntilElement(driver, profUploadFile);
			//profUploadFile.sendKeys("C:\\AIA-code\\aia-fonteva-automation\\UploadFiles\\FileAIA.pdf");
			profUploadFile.click();
			Robot robot = new Robot();
			StringSelection ss = new StringSelection(PdfFile);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			Thread.sleep(3000);             
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(3000);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
	    } catch (Exception exp) {
	        exp.printStackTrace();
	    }
		
		util.waitUntilElement(driver, pofessionalFileUplaodDoneButton);
		Thread.sleep(3000);
		pofessionalFileUplaodDoneButton.click();
		util.waitUntilElement(driver, pofessionalDeleteBtn);
		professionalNext.click();
		util.waitUntilElement(driver, confirmNext);
		confirmNext.click();
	}
	
}
