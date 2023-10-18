package org.aia.pages.fonteva.abi;

import org.aia.pages.fonteva.membership.ContactCreateUser;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ABILoginPage {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	static Logger log = Logger.getLogger(ContactCreateUser.class);
	ContactCreateUser createOrder;
	Actions action;
	JavascriptExecutor executor;

	/**
	 * @param Idriver
	 */
	public ABILoginPage(WebDriver Idriver) {
		this.driver = Idriver;
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
		createOrder = new ContactCreateUser(driver);

	}

	@FindBy(xpath = "//a/slot/span[contains(text(),'Memberships')]")
	WebElement membership;
}
