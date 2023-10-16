package org.aia.pages.fonteva.events;

import java.util.ArrayList;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class Events {

	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	ConfigDataProvider testData;
	public String eName = "";
	public String newEvent = "";
	public String startDate = "";
	public String eventId = "";
	static Logger log = Logger.getLogger(NewCloneEvents.class);

	public Events(WebDriver Idriver) {
		this.driver = Idriver;
		executor = (JavascriptExecutor) driver;
		testData = new ConfigDataProvider();
	}

	@FindBy(xpath = "//a[contains(@class,'label-action dndItem')]/span[text()='Events']")
	WebElement eventsLink;

	@FindBy(xpath = "//div[@data-name='cloneEventTitle']")
	WebElement cloneEventHeader;

	@FindBy(xpath = "//a[@class='forceActionLink' and @title='New']")
	WebElement eventNewButton;

	@FindBy(css = "button[class*='_neutral search-button slds-truncate']")
	WebElement globSearch;

	@FindBy(xpath  = "//label[text()='Search...']/following-sibling::div/input")
	WebElement globSearchInput;

	@FindBy(xpath = "//input[@placeholder='Search this list...']")
	WebElement searchEvents;

	@FindBy(xpath = "//span[@class='slds-grid slds-grid--align-spread forceInlineEditCell']//a[@data-refid='recordId']")
	WebElement eventName;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//span[text()='Number of Registered Attendees']/following::lightning-formatted-number[1]")
	WebElement attandessInsalesRegisration;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//span[text()='Number of Registered Attendees']/following::lightning-formatted-number[2]")
	WebElement soldticketsInsalesRegisration;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//span[text()='Number of Registered Attendees']/following::lightning-formatted-number[3]")
	WebElement eventCapacityInsalesRegisration;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//span[text()='Number of Registered Attendees']/following::lightning-formatted-number[4]")
	WebElement ticketsremainInsalesRegisration;

	public void clickEventsModule() {
		util.waitUntilElement(driver, eventsLink);
		util.clickUsingJS(driver, eventsLink);
		log.info("Events clickd successfully");
		Logging.logger.info("Events clickd successfully");
	}

	public void newButtonInEvents() {
		util.waitUntilElement(driver, eventNewButton);
		eventNewButton.click();
		log.info("Even New button is clicked");
	}

	public void validateHeaderCloneEvent() {
		util.waitUntilElement(driver, cloneEventHeader);
		boolean cloneEventPopup = cloneEventHeader.isDisplayed();
		Assert.assertTrue(cloneEventPopup);
		log.info("Clone Event pop is displayed");
	}

	/**
	 * @param email
	 * @throws Throwable
	 */
	public void globalSearch(String email) throws Throwable {
		util.waitUntilElement(driver, globSearch);
		Utility.highLightElement(driver, globSearch);
		globSearch.click();
		globSearchInput.sendKeys(email);
		Thread.sleep(4000);
		globSearchInput.sendKeys(Keys.ENTER);
	}

	/**
	 * @param event
	 * @throws InterruptedException
	 */
	public void eventsSearch(String event) throws InterruptedException {
		util.waitUntilElement(driver, searchEvents);
		Utility.highLightElement(driver, searchEvents);
		util.enterText(driver, searchEvents, event);
		Thread.sleep(3000);
		eventName.click();
	}

	/**
	 * @return
	 * @throws Throwable
	 */
	public ArrayList<String> validateBeforeRegistrationData() throws Throwable {
		Thread.sleep(7000);
		ArrayList<String> beforeRegData = new ArrayList<String>();

		util.waitUntilElement(driver, attandessInsalesRegisration);
		util.scrollingElementUsingJS(driver, attandessInsalesRegisration);
		String attendee = attandessInsalesRegisration.getText();
		beforeRegData.add(0, attendee);
		String soldTickets = soldticketsInsalesRegisration.getText();
		beforeRegData.add(1, soldTickets);
		String eventCapacity = eventCapacityInsalesRegisration.getText();
		beforeRegData.add(2, eventCapacity);
		String remainingTickets = ticketsremainInsalesRegisration.getText();
		beforeRegData.add(3, remainingTickets);
		return beforeRegData;
	}

	/**
	 * @return
	 * @throws InterruptedException
	 */
	public ArrayList<String> validateAfterRegistrationData() throws InterruptedException {
		Thread.sleep(7000);
		ArrayList<String> afterRegData = new ArrayList<String>();
		util.waitUntilElement(driver, attandessInsalesRegisration);
		String attendee = attandessInsalesRegisration.getText();
		afterRegData.add(0, attendee);
		String soldTickets = soldticketsInsalesRegisration.getText();
		afterRegData.add(1, soldTickets);
		String eventCapacity = eventCapacityInsalesRegisration.getText();
		afterRegData.add(2, eventCapacity);
		String remainingTickets = ticketsremainInsalesRegisration.getText();
		afterRegData.add(3, remainingTickets);
		return afterRegData;
	}
}
