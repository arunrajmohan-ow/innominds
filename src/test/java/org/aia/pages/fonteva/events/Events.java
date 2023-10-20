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

	@FindBy(xpath = "//span[text()='App Launcher']//parent::div")
	WebElement appLauncher;

	@FindBy(xpath = "//div//input[@id='input-148']")
	WebElement appLauncherSearchBox;

	@FindBy(xpath = "//lightning-icon[@icon-name='standard:person_account']//ancestor::lightning-avatar//following-sibling::lightning-formatted-rich-text//b")
	WebElement appLauncherEventsValue;

	@FindBy(xpath = "//a[contains(@class,'label-action dndItem')]/span[text()='Events']")
	WebElement eventsLink;

	// Delete event
	@FindBy(xpath  = "(//div[@class='forceVirtualActionMarker forceVirtualAction']/a)[1]")
	WebElement actionColumnHeader;

	@FindBy(css = "div [class='branding-actions actionMenu'] a[data-target-selection-name*='Delete']")
	WebElement deleteEvent;

	@FindBy(css = "div [class='modal-header slds-modal__header'] h2")
	WebElement deleteEventPopupHeader;

	@FindBy(css = "div [class='detail slds-text-align--center']")
	WebElement deletePopupText;

	@FindBy(css = "div[data-aura-class='forceModalActionContainer--footerAction forceModalActionContainer'] button[title='Cancel'] span")
	WebElement cancelButtonInDeletePopup;

	@FindBy(css = "div[data-aura-class='forceModalActionContainer--footerAction forceModalActionContainer'] button[title='Delete'] span")
	WebElement deleteButtonInDeletePopup;

	@FindBy(css = "div[data-aura-class='forceToastMessage'] div:nth-child(2) span")
	WebElement toasMsgafterDelete;

	// created event
	@FindBy(xpath = "//tbody/tr[1]/th[1]/span[1]/a")
	WebElement createdEvent;

	@FindBy(xpath = "//div[@data-name='cloneEventTitle']")
	WebElement cloneEventHeader;

	@FindBy(xpath = "//a[@class='forceActionLink' and @title='New']")
	WebElement eventNewButton;

	@FindBy(css = "button[class*='_neutral search-button slds-truncate']")
	WebElement globSearch;

	@FindBy(xpath = "//label[contains(text(),'Search')]/following-sibling::div/input[contains(@aria-controls,'suggestionsList')]")
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

	public void eventsTab() throws Exception {
		if (!eventsLink.isDisplayed()) {
			util.waitUntilElement(driver, appLauncher);
			appLauncher.click();
			util.waitUntilElement(driver, appLauncherSearchBox);
			appLauncherSearchBox.sendKeys("Events");
			Thread.sleep(2000);
			util.scrollingElementUsingJS(driver, appLauncherEventsValue);
			appLauncherEventsValue.click();
		}
		util.waitUntilElement(driver, eventsLink);
		util.clickUsingJS(driver, eventsLink);
		log.info("Events clickd successfully");
		Logging.logger.info("Events clickd successfully");
	}

	/**
	 * @return Event name This method click already exist event in the top of the
	 *         row.
	 */
	public String clickCreatedEvent() throws Throwable {
		util.waitUntilElement(driver, createdEvent);
		String eventName = createdEvent.getText();
		createdEvent.click();
		Thread.sleep(3000);
		return eventName;
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

	public void clickActionsInEvents(String action) {
		util.waitUntilElement(driver, actionColumnHeader);
		util.clickUsingJS(driver, actionColumnHeader);
		switch (action) {
		case "Delete":
			util.waitUntilElement(driver, deleteEvent);
			util.clickUsingJS(driver, deleteEvent);
			break;
		case "Edit":
			// TODO
			break;
		case "Change Owner":
			// TODO
			break;
		}
	}

	public void validateDeletePopup() {
		util.waitUntilElement(driver, deleteEventPopupHeader);
		Assert.assertTrue(deleteEventPopupHeader.isDisplayed());
		String header = deleteEventPopupHeader.getText();
		log.info(header);
		String deleteText = deletePopupText.getText();
		log.info(deleteText);
	}

	/**
	 * @param action
	 */
	public void clickActionsInDeletePopup(String action) {
		util.waitUntilElement(driver, deleteButtonInDeletePopup);
		switch (action) {
		case "Delete":
			util.clickUsingJS(driver, deleteButtonInDeletePopup);
			break;
		case "Cancel":
			util.clickUsingJS(driver, deleteButtonInDeletePopup);
			break;
		}
	}

	public void validateToasteMessage() {
		util.waitUntilElement(driver, toasMsgafterDelete);
		String message = toasMsgafterDelete.getText();
		log.info("Toaste message is visible after deleted the event" + message);
	}
}
