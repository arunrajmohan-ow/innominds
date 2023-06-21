package org.aia.pages.fonteva.membership;

import static org.testng.Assert.*;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

/**
 * @author IM-RT-LP-1483 (Suhas)
 *
 */
public class MemberValueOutreach {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	static Logger log = Logger.getLogger(ContactCreateUser.class);
	Actions action;
	JavascriptExecutor executor;

	/**
	 * @param Idriver
	 */
	public MemberValueOutreach(WebDriver Idriver) {
		driver = Idriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
	}
	
	@FindBy(xpath="//div[contains(@class,'modal__header')]//h2")
	WebElement mvoPopupHeading;
	
	
	/**
	 * 
	 */
	public void createNewMVO() {
		util.waitUntilElement(driver, mvoPopupHeading);
		assertTrue(mvoPopupHeading.isDisplayed());
		assertEquals(mvoPopupHeading.getText(),data.testDataProvider().getProperty("movPopUp"));
		
		
	}
	
	
}
