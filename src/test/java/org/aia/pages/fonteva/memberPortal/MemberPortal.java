package org.aia.pages.fonteva.memberPortal;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MemberPortal {
  
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	Actions act;
	static Logger log = Logger.getLogger(AccountAcessForContact.class);

	public MemberPortal(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
	}
	
	@FindBy(xpath = "//a[contains(@href, '/lightning/r/Account/')]")
	WebElement accountsLink;
	
	@FindBy(xpath = "//records-highlights-details-item//records-hoverable-link//a[contains(@href, 'lightning/r/Account')]")
	WebElement contactsLink;
	
	@FindBy(xpath = "//records-highlights-details-item//p[@title = 'Primary Contact']/following-sibling::*//a")
	WebElement primaryContactsLink;
	
	@FindBy(xpath = "//span[text()='Enable Customer User']/parent::a")
	WebElement enableCustomerUserOpt;
	
	@FindBy(xpath = "//lightning-button-menu[contains(@data-target-reveals,'Disable_Auto_Renew')]//button")
	WebElement moreActionBtn;

	@FindBy(xpath = "//span[text()='Log in to Experience as User']//ancestor::a")
	WebElement loginAsExpUserOpt;
	
	@FindBy(xpath = "//span//select[@id='Profile']")
	WebElement userProfiledrpdwn;
	
	@FindBy(xpath = "//td[@id='bottomButtonRow']//input[@value= ' Save ' ]")
	WebElement userProfileSave;
	
	@FindBy(xpath = "(//a[text()='Membership'])[2]")
	WebElement membership;
	
	@FindBy(xpath = "//div//h2[text()='Membership & Chapter Assignment']")
	WebElement chapterAssignment;
	
	@FindBy(xpath = "//h2//span[text()='Membership Transfer Request']")
	WebElement transferRequest;
	
	@FindBy(xpath = "//p[text()='AIA Number']")
	WebElement aiaNumberTxt;
	
	@FindBy(xpath = "//p[text()='Membership Type']")
	WebElement membershipTypeTxt;
	
	@FindBy(xpath = "//p[text()=' Local Assignment']")
	WebElement localAssignmetTxt;
	
	@FindBy(css = "//p[contains(text(), 'State Assignment')]")
	WebElement stateAssignmetTxt;

	@FindBy(xpath = "//p[contains(text(), ' Section Assignment')]")
	WebElement sectionAssignmetTxt;

	@FindBy(xpath = "//p[contains(text(), 'Status')]")
	WebElement statusTxt;

	@FindBy(xpath = "(//a[text()='Interest Areas'])[2]")
	WebElement interestArea;
	
	@FindBy(xpath = "//span[text()='Academy of Architecture for Health (AAH)']")
	WebElement aahText;
	
	@FindBy(xpath = "//span[text()='Academy of Architecture for Justice (AAJ)']")
	WebElement aajText;
	
	@FindBy(xpath = "//span[text()='Building Performance (BPKC)']")
	WebElement bpkcText;
	
	@FindBy(xpath = "//span[text()='Committee on Architecture for Education (CAE)']")
	WebElement caeTxt;
	
	@FindBy(xpath = "//span[text()='Committee on Design (COD)']")
	WebElement codTxt;
	
	@FindBy(xpath = "//span[text()='Committee on the Environment (COTE®)']")
	WebElement coteTxt;
	
	@FindBy(xpath = "//span[text()='Construction Contract Administration (CCA)']")
	WebElement ccaTxt;
	
	@FindBy(xpath = "//span[text()='Corporate Architects and Facility Management (CAFM)']")
	WebElement cafmTxt;
	
	@FindBy(xpath = "//span[text()='Custom Residential Architects Network (CRAN®)']")
	WebElement cranTxt;
	
	@FindBy(xpath = "//span[text()='Design for Aging (DFA)']")
	WebElement dfaTxt;
	
	@FindBy(xpath = "//span[text()='Historic Resources Committee (HRC)']")
	WebElement hrcTxt;
	
	@FindBy(xpath = "//span[text()='Housing and Community Development (HCD)']")
	WebElement hcdTxt;
	
	@FindBy(xpath = "//span[text()='Interfaith Design (ID)']")
	WebElement idTxt;
	
	@FindBy(xpath = "//span[text()='Interior Architecture Knowledge Community (IAKC)']")
	WebElement iakcTxt;
	
	@FindBy(xpath = "//span[text()='Practice Management Knowledge Community (PMKC)']")
	WebElement pmkcTxt;
	
	@FindBy(xpath = "//span[text()='Project Delivery Knowledge Community (PD)']")
	WebElement pdTxt;
	
	@FindBy(xpath = "//span[text()='Regional and Urban Design (RUDC)']")
	WebElement rudcTxt;
	
	@FindBy(xpath = "//span[text()='Retail and Entertainment (REKC)']")
	WebElement rekcTxt;
	
	@FindBy(xpath = "//span[text()='Small Project Design (SPD)']")
	WebElement spdTxt;
	
	@FindBy(xpath = "//span[text()='Technology in Architectural Practice (TAP)']")
	WebElement tapTxt;
	
	@FindBy(xpath = "(//a[text()='Interest Areas'])[2]")
	WebElement demographics;
	
	@FindBy(xpath = "//p[text()='Birthdate']")
	WebElement birthdate;
	
	@FindBy(xpath = "//p[text()='Gender Identity']")
	WebElement genderIdentity;
	
	@FindBy(xpath = "//h2[text()='Demographics']")
	WebElement demographicsTxt;
	
	@FindBy(xpath = "//p[text()='Gender']")
	WebElement gender;
	
	@FindBy(xpath = "//p[text()='LGBTQIA+']")
	WebElement lgbtqTxt;
	
	@FindBy(xpath = "//p[text()='Preferred Pronouns']")
	WebElement preferredPronounsTxt;
	
	@FindBy(xpath = "//p[text()='Race and ethnicity']")
	WebElement raceEthnicityTxt;
	
	@FindBy(xpath = "//p[text()='Primary race/ethnicity']")
	WebElement primaryEthnicityTxt;
	
	@FindBy(xpath = "//p[text()='Secondary race/ethnicity']")
	WebElement secondaryEthnicityTxt;
	
	@FindBy(xpath = "//p[text()='Diverse abilities']")
	WebElement diverseAbilitiesTxt;
	
	@FindBy(xpath = "//p[text()='Diverse Ability']")
	WebElement diverseAbilityTxt;
	
	@FindBy(xpath = "//p[text()='Ability Notes']")
	WebElement abilityNotesTxt;
	
	@FindBy(xpath = "(//a[text()='Contact Information'])[2]")
	WebElement contactInformation;
	
	@FindBy(xpath = "//p//lightning-formatted-text[contains(text(),'autofn')]")
	WebElement firstNameCI;
	
	
	@FindBy(xpath = "//p//lightning-formatted-text[contains(text(),'autoln')]")
	WebElement lastNameCI;
	
	@FindBy(xpath = "(//lightning-formatted-address//div[text()='United States'])[1]")
	WebElement homeAddressCI;
	
	@FindBy(xpath = "(//input[@placeholder='you@example.com'])[1]")
	WebElement personalEmail;
	
	@FindBy(xpath = "(//p//lightning-formatted-text[contains(text(),'40')]")
	WebElement aiaNumber;
	
	@FindBy(xpath = "((//a[text()='Communication Preferences'])[1]")
	WebElement communicationPreferences;
	
	
	@FindBy(xpath = "(//p[text()='Do Not Contact by Phone']")
	WebElement contactByPhoneTxt;
	
	@FindBy(xpath = "(///p[text()='Do Not Contact by Email']")
	WebElement contactByEmailTxt;
	
	@FindBy(xpath = "(///p[text()='Do Not Contact by Post']")
	WebElement contactByPostTxt;
	
	 public void navigateToMemberPortal(String profile) throws InterruptedException {
		   JavascriptExecutor js = (JavascriptExecutor) driver;
		   util.waitUntilElement(driver, accountsLink);
			js.executeScript("arguments[0].click();", accountsLink);
			Thread.sleep(20000);
			util.waitUntilElement(driver, primaryContactsLink);
			js.executeScript("arguments[0].click();", primaryContactsLink);
			//primaryContactsLink.click();
			Thread.sleep(50000);
			util.waitUntilElement(driver, contactsLink);
			js.executeScript("arguments[0].click();", contactsLink);
			//contactsLink.click();
			Thread.sleep(20000);
			util.waitUntilElement(driver, primaryContactsLink);
			js.executeScript("arguments[0].click();", primaryContactsLink);
			Thread.sleep(50000);
			util.waitUntilElement(driver, moreActionBtn);
			moreActionBtn.click();
			util.waitUntilElement(driver, enableCustomerUserOpt);
			js.executeScript("arguments[0].click();", enableCustomerUserOpt);
			Thread.sleep(50000);
			driver.switchTo().frame(0);
			WebElement userProfiledrpdwn = driver.findElement(By.id("Profile"));
			Select dropdown = new Select(userProfiledrpdwn);
	        dropdown.selectByVisibleText(profile);
	        util.waitUntilElement(driver, userProfileSave);
	        userProfileSave.click();
	        driver.navigate().back();
	        driver.navigate().back();
	   }
	 
	public void validateContactInformationpage(String firstName, String lastName , String email, String address ) {
		util.waitUntilElement(driver, contactInformation);
		contactInformation.click();
		util.waitUntilElement(driver, firstNameCI);
		assertEquals(firstNameCI.getText(), firstName);
		util.waitUntilElement(driver, lastNameCI);
		util.waitUntilElement(driver, personalEmail);
		assertTrue(personalEmail.isDisplayed());
		util.waitUntilElement(driver, homeAddressCI);
		assertTrue(homeAddressCI.isDisplayed());
		
	}
	

	
	public void validateMembershipFields() {
		util.waitUntilElement(driver, membership);
		membership.click();
		
		util.waitUntilElement(driver, aiaNumber);
		assertTrue(aiaNumber.isDisplayed());
		
		util.waitUntilElement(driver, chapterAssignment);
		assertTrue(chapterAssignment.isDisplayed());
		util.waitUntilElement(driver, transferRequest);
		assertTrue(transferRequest.isDisplayed());
		assertTrue(aiaNumberTxt.isDisplayed());
		util.waitUntilElement(driver, membershipTypeTxt);
		assertTrue(membershipTypeTxt.isDisplayed());
		util.waitUntilElement(driver, localAssignmetTxt);
		assertTrue(localAssignmetTxt.isDisplayed());
		util.waitUntilElement(driver, stateAssignmetTxt);
		assertTrue(sectionAssignmetTxt.isDisplayed());
	}
	
	
	public void validateInterestPageFields() {
		util.waitUntilElement(driver, interestArea);
		interestArea.click();
		util.waitUntilElement(driver, aahText);
		assertTrue(aahText.isDisplayed());
		util.waitUntilElement(driver, aajText);
		assertTrue(aajText.isDisplayed());
		assertTrue(bpkcText.isDisplayed());
		util.waitUntilElement(driver, caeTxt);
		assertTrue(caeTxt.isDisplayed());
		util.waitUntilElement(driver, codTxt);
		assertTrue(codTxt.isDisplayed());
		util.waitUntilElement(driver, coteTxt);
		assertTrue(coteTxt.isDisplayed());
		assertTrue(cafmTxt.isDisplayed());
		assertTrue(cranTxt.isDisplayed());
		assertTrue(dfaTxt.isDisplayed());
		assertTrue(hrcTxt.isDisplayed());
		assertTrue(hcdTxt.isDisplayed());
		assertTrue(idTxt.isDisplayed());
		assertTrue(iakcTxt.isDisplayed());
		assertTrue(pmkcTxt.isDisplayed());
		assertTrue(pdTxt.isDisplayed());
		assertTrue(rudcTxt.isDisplayed());
		assertTrue(rekcTxt.isDisplayed());
		assertTrue(spdTxt.isDisplayed());
		assertTrue(tapTxt.isDisplayed());
	}
	
	public void verifyDemographicsFields() {
		util.waitUntilElement(driver, demographics);
		demographics.click();
		util.waitUntilElement(driver, demographicsTxt);
		assertTrue(demographicsTxt.isDisplayed());
		util.waitUntilElement(driver, birthdate);
		assertTrue(birthdate.isDisplayed());
		assertTrue(genderIdentity.isDisplayed());
		util.waitUntilElement(driver, gender);
		assertTrue(gender.isDisplayed());
		util.waitUntilElement(driver, lgbtqTxt);
		assertTrue(lgbtqTxt.isDisplayed());
		util.waitUntilElement(driver, preferredPronounsTxt);
		assertTrue(preferredPronounsTxt.isDisplayed());
		util.waitUntilElement(driver, raceEthnicityTxt);
		assertTrue(raceEthnicityTxt.isDisplayed());
		assertTrue(primaryEthnicityTxt.isDisplayed());
		assertTrue(secondaryEthnicityTxt.isDisplayed());
		assertTrue(diverseAbilitiesTxt.isDisplayed());
		assertTrue(diverseAbilityTxt.isDisplayed());
		assertTrue(abilityNotesTxt.isDisplayed());
	}
	
	public void verifyContactPage() {
		util.waitUntilElement(driver, demographics);
		demographics.click();
		util.waitUntilElement(driver, demographicsTxt);
		assertTrue(demographicsTxt.isDisplayed());
		util.waitUntilElement(driver, birthdate);
		assertTrue(birthdate.isDisplayed());
		assertTrue(genderIdentity.isDisplayed());
		util.waitUntilElement(driver, gender);
		assertTrue(gender.isDisplayed());
		util.waitUntilElement(driver, lgbtqTxt);
		assertTrue(lgbtqTxt.isDisplayed());
		util.waitUntilElement(driver, preferredPronounsTxt);
		assertTrue(preferredPronounsTxt.isDisplayed());
		util.waitUntilElement(driver, raceEthnicityTxt);
		assertTrue(raceEthnicityTxt.isDisplayed());
		assertTrue(primaryEthnicityTxt.isDisplayed());
		assertTrue(secondaryEthnicityTxt.isDisplayed());
		assertTrue(diverseAbilitiesTxt.isDisplayed());
		assertTrue(diverseAbilityTxt.isDisplayed());
		assertTrue(abilityNotesTxt.isDisplayed());
	}
	
	public void verifyContactPageInfo() {
		util.waitUntilElement(driver, contactInformation);
		contactInformation.click();
		util.waitUntilElement(driver, firstNameCI);
		assertTrue(firstNameCI.isDisplayed());
		util.waitUntilElement(driver, lastNameCI);
		assertTrue(lastNameCI.isDisplayed());
	
		util.waitUntilElement(driver, aiaNumberTxt);
		assertTrue(aiaNumberTxt.isDisplayed());
		util.waitUntilElement(driver, membershipTypeTxt);
		assertTrue(membershipTypeTxt.isDisplayed());
		assertTrue(localAssignmetTxt.isDisplayed());
		assertTrue(stateAssignmetTxt.isDisplayed());
		assertTrue(sectionAssignmetTxt.isDisplayed());
		assertTrue(homeAddressCI.isDisplayed());
		
		
		
	}
	
	public void verifycommunicationPreferences() {
		util.waitUntilElement(driver, communicationPreferences);
		communicationPreferences.click();
		util.waitUntilElement(driver, contactByPhoneTxt);
		assertTrue(contactByPhoneTxt.isDisplayed());
		util.waitUntilElement(driver, contactByEmailTxt);
		assertTrue(contactByEmailTxt.isDisplayed());
	
		util.waitUntilElement(driver, contactByPostTxt);
		assertTrue(contactByPostTxt.isDisplayed());

		
		
		
	}
	

	
	

}
