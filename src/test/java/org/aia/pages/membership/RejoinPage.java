package org.aia.pages.membership;

import org.aia.utility.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RejoinPage {

	WebDriver driver;
	Utility util = new Utility(driver, 30);

	public RejoinPage(WebDriver Idriver) {
		this.driver = Idriver;
	}

	@FindBy(xpath = "//input[@name=\"email \"] | //input[@name=\"email\"]")
	WebElement enteremail;

	@FindBy(xpath = "//p[text()='Continue']")
	WebElement continuebtn;

	@FindBy(xpath = "//p[text()='Join AIA']/parent::span")
	WebElement reJoinBtn;

	public void reJoinMembership(String emaildata) throws InterruptedException {
		Thread.sleep(5000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
		util.waitUntilElement(driver, enteremail);
		enteremail.sendKeys(emaildata);
		util.waitUntilElement(driver, continuebtn);
		continuebtn.click();
		util.waitUntilElement(driver, reJoinBtn);
		reJoinBtn.click();
	}
}
