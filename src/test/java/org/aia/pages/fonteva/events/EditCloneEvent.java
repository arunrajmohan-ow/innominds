package org.aia.pages.fonteva.events;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aia.utility.DateUtils;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import groovy.transform.Final;

public class EditCloneEvent {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	Events events;
	Actions act;
	static Logger log = Logger.getLogger(EditCloneEvent.class);
	public String salesOrder = "";
	public String aiaNumber = "";

	public EditCloneEvent(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
		events = PageFactory.initElements(driver, Events.class);
	}

	@FindBy(xpath = "//li[contains(@data-target-selection-name,'EventApi__Event__c.Edit')]//lightning-button//button")
	WebElement EditButton;

	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] span[class*='-dropdown--trigger event-builder-action-button slds-p-horizontal--small'] span svg use")
	WebElement hoverSaveDropDown;

	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] ul[data-name='Save_Exit_Event_Builder'] li a")
	WebElement saveExitButton;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//a[contains(text(),'https://aia--testing.sandbox.my.site.com/NationalE')]")
	WebElement eventUrl;

	// EventInfo locators
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-name='name']/input")
	WebElement eventInfoName;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-name='displayName']/input")
	WebElement eventDisplayName;

	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] div[data-name='startDate'] div input")
	WebElement eventStartDate;

	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] div[data-name='endDate'] div input")
	WebElement eventEndDate;

	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] select[name='Start Time']")
	WebElement eventStartTime;

	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] select[name='End Time']")
	WebElement eventEndTime;

	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] select[name='Time Zone']")
	WebElement eventTimeZone;

	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] div[data-name='registrationTimer'] input")
	WebElement eventRegistrationTimer;

	// Invitation locators
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderInvitationOnly']")
	WebElement eventBuilderInvitation;

	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] a[data-panel='attendeeCreationTab']")
	WebElement attendeeCreationTab;

	// Venues locators
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderVenues']")
	WebElement eventBuilderVenues;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[contains(text(),'Venues')]")
	WebElement VenuesTab;

	// Event AccessPermissions
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderAccessPermissions']")
	WebElement eventBuilderAccessPermissions;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//th[contains(text(),'Access to this Item')]")
	WebElement AcessPermissionTab;

	// Event Speakers
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderSpeakers']")
	WebElement eventBuilderSpeakers;

	@FindBy(xpath = "(//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[contains(text(),'Speakers')])[2]")
	WebElement speakersTab;

	// EventApi:EventBuilderAgenda
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderAgenda']")
	WebElement eventBuilderAgenda;
	
	@FindBy(css = "table[class='slds-table slds-table_header-fixed slds-table_bordered slds-table_edit'] tr:nth-child(5)  th lightning-base-formatted-text")
	WebElement scheduleItemDisplayName;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//a[@data-tab='agenda']")
	WebElement agendaTab;

	// EventApi:EventBuilderSponsorPackages
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderSponsorPackages']")
	WebElement eventBuilderSponsorPackages;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//a[@data-tab='sponsorPackages']")
	WebElement sponcerPackageTab;

	// EventApi:EventBuilderStatuses
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderStatuses']")
	WebElement eventBuilderStatuses;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[contains(text(),'statuses for your event.')]")
	WebElement statusespageText;

	// EventApi:EventBuilderPages
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderPages']")
	WebElement eventBuilderPages;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[contains(text(),'pages for your selected status')]")
	WebElement eventPagesText;

	@FindBy(xpath = "(//lightning-formatted-text[@title='AIA Contact Number']/following::div/lightning-formatted-text)[1]")
	WebElement getAIANumber;

	@FindBy(xpath = "//lightning-formatted-text[text()='Account Name']/following-sibling::div/a")
	WebElement accountName;

	@FindBy(xpath = "//a//slot/span[contains(text(),'Sales Orders')]")
	WebElement salesOrderLink;

	@FindBy(xpath = "//th[@data-label='Sales Order #']//a/slot/slot/span")
	WebElement salesOrderText;

	@FindBy(css = "button[class*='_neutral search-button slds-truncate']")
	WebElement globSearch;

	@FindBy(xpath = "//label[text()='Search...']/following-sibling::div/input")
	WebElement globSearchInput;

	@FindBy(xpath = "//input[@placeholder='Search this list...']")
	WebElement searchEvents;

	@FindBy(xpath = "//span[@class='slds-grid slds-grid--align-spread forceInlineEditCell']//a[@data-refid='recordId']")
	WebElement eventName;

	// Date selection on Calendar in Event info

	@FindBy(xpath = "//label[contains(text(),'Start Date')]//parent::span//parent::div//following-sibling::div//input")
	WebElement startDateCalender;

	@FindBy(xpath = "//label[contains(text(),'End Date')]//parent::span//parent::div//following-sibling::div//input")
	WebElement endDateCalendar;

	@FindBy(xpath = "//a[@title='Go to previous month']")
	WebElement perviousMonthsButton;

	@FindBy(xpath = "//a[@title='Go to next month']")
	WebElement nextMonthsButton;

	@FindBy(xpath = "//label[contains(text(),'Start Time')]//parent::span//following-sibling::div//div//select")
	WebElement startTimeHoursDropDown;

	@FindBy(xpath = "(//div[@class='slds-select_container'])[2]//select")
	WebElement startTimeMinutesDropDown;

	@FindBy(xpath = "(//div[@class='slds-select_container'])[3]//select")
	WebElement startTimePeriodDropDown;

	@FindBy(xpath = "//label[contains(text(),'End Time')]//parent::span//following-sibling::div//div//select")
	WebElement endTimeHoursDropDown;

	@FindBy(xpath = "(//div[@class='slds-select_container'])[5]//select")
	WebElement endTimeMinutesDropDown;

	@FindBy(xpath = "(//div[@class='slds-select_container'])[6]//select")
	WebElement endTimePeriodDropDown;

	String DurationOfStartEndDates = "(//span[text() ='Duration']//following-sibling::span)[2]//span";

	@FindBy(xpath = "(//span[text() ='Duration']//following-sibling::span)[2]//span")
	WebElement DurationOfStartEndDate;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement eventSaveButton;

	@FindBy(xpath = "//span[contains(text(),'24-Hour Time')]//preceding-sibling::span")
	WebElement a24HoursTimeCheckBoxXpath;

	@FindBy(xpath = "//label[contains(text(),'Registration Timer (in minutes)')]//parent::span//following-sibling::div//input")
	WebElement RegistrationTimerInputBox;

	public void getAIAData() throws Throwable {
		util.waitUntilElement(driver, getAIANumber);
		Utility.highLightElement(driver, getAIANumber);
		aiaNumber = getAIANumber.getText();
		log.info(getAIANumber.getText());
		Utility.highLightElement(driver, accountName);
		accountName.click();

		// navigate to main events page
		events.eventsTab();
		Thread.sleep(4000);
	}

	public void clickEditButton() {
		util.waitUntilElement(driver, EditButton);
		util.clickUsingJS(driver, EditButton);
		System.out.println("VERIFIED: Edit Event page is displayed");
	}

	/**
	 * @param ExpectedEventName Here We can get the values Event name, Event Start
	 *                          date, Event end date, Event start time, Event end
	 *                          time,Event TimeZone, Registration timer.
	 */
	public void editEventInfo(String ExpectedEventName) {
		util.waitUntilElement(driver, eventInfoName);
		log.info(eventInfoName.getAttribute("value"));
		System.out.println(eventInfoName.getAttribute("value"));
		Assert.assertEquals(eventInfoName.getAttribute("value"), ExpectedEventName);

		log.info(eventDisplayName.getAttribute("value"));
		System.out.println(eventDisplayName.getAttribute("value"));
		Assert.assertEquals(eventDisplayName.getAttribute("value"), ExpectedEventName);

		log.info(eventStartDate.getAttribute("value"));
		String startDateStr = eventStartDate.getAttribute("value");
		System.out.println(eventStartDate.getAttribute("value"));
		Assert.assertEquals(eventStartDate.getAttribute("value"),
				new SimpleDateFormat("MM/dd/yyyy").format(new Date()));

		log.info(eventEndDate.getAttribute("value"));
		String endDateStr = eventEndDate.getAttribute("value");
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = dateFormat.parse(startDateStr);
			endDate = dateFormat.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (endDate.compareTo(startDate) >= 0) {
			System.out.println("End date is equal to or after the start date.");
		} else {
			System.out.println("End date is before the start date.");
		}

		log.info(eventStartTime.getAttribute("value"));
		System.out.println(eventStartTime.getAttribute("value"));
		Assert.assertEquals(eventStartTime.getAttribute("value"), "12");

		log.info(eventEndTime.getAttribute("value"));
		System.out.println(eventEndTime.getAttribute("value"));
		Assert.assertEquals(eventEndTime.getAttribute("value"), "5");

		log.info(eventTimeZone.getAttribute("value"));
		System.out.println(eventTimeZone.getAttribute("value"));
		Assert.assertEquals(eventTimeZone.getAttribute("value"), "(GMT-04:00) America/New_York");

		log.info(eventRegistrationTimer.getAttribute("value"));
		System.out.println(eventRegistrationTimer.getAttribute("value"));
		Assert.assertEquals(eventRegistrationTimer.getAttribute("value"), "30");
	}

	public void editEventInvitation() {
		util.waitUntilElement(driver, eventBuilderInvitation);
		eventBuilderInvitation.click();
		log.info("Event Invitation is clicked successfully");
		util.waitUntilElement(driver, attendeeCreationTab);
		Assert.assertTrue(attendeeCreationTab.isDisplayed());
		log.info("attendeeCreationTab is displayed");
	}

	public void editEventVenues() {
		util.waitUntilElement(driver, eventBuilderVenues);
		eventBuilderVenues.click();
		log.info("Event Venues is clicked successfully");
		util.waitUntilElement(driver, VenuesTab);
		Assert.assertTrue(VenuesTab.isDisplayed());
		log.info("VenuesTab is displayed");
	}

	public void editEventAccessPermissions() {
		util.waitUntilElement(driver, eventBuilderAccessPermissions);
		eventBuilderAccessPermissions.click();
		log.info("Event AccessPErmission is clicked successfully");
		util.waitUntilElement(driver, AcessPermissionTab);
		Assert.assertTrue(AcessPermissionTab.isDisplayed());
		log.info("AcessPermissionTab is displayed");
	}

	public void editEventAgenda() {
		util.waitUntilElement(driver, eventBuilderAgenda);
		eventBuilderAgenda.click();
		log.info("Event Agenda is clicked successfully");
		util.waitUntilElement(driver, agendaTab);
		Assert.assertTrue(agendaTab.isDisplayed());
		log.info("agendaTab is displayed");
	}
	
	
	public String getSceduleItemsInAgenda() {
		util.scrollingElementUsingJS(driver, scheduleItemDisplayName);
		Utility.waitForWebElement(driver, scheduleItemDisplayName, 30);
		String scheduleItemName = scheduleItemDisplayName.getText();
		return scheduleItemName;
	}

	public void editEventSpeakers() {
		util.waitUntilElement(driver, eventBuilderSpeakers);
		eventBuilderSpeakers.click();
		log.info("Event Speakers is clicked successfully");
		util.waitUntilElement(driver, speakersTab);
		Assert.assertTrue(speakersTab.isDisplayed());
		log.info("speakersTab is displayed");
	}

	public void editEventSponsorPackages() {
		util.waitUntilElement(driver, eventBuilderSponsorPackages);
		eventBuilderSponsorPackages.click();
		log.info("Event Sponsor is clicked successfully");
		util.waitUntilElement(driver, sponcerPackageTab);
		Assert.assertTrue(sponcerPackageTab.isDisplayed());
		log.info("sponcerPackageTab is displayed");
	}

	public void editEventStatuses() {
		util.waitUntilElement(driver, eventBuilderStatuses);
		eventBuilderStatuses.click();
		log.info("Event Statuses is clicked successfully");
		util.waitUntilElement(driver, statusespageText);
		Assert.assertTrue(statusespageText.isDisplayed());
		log.info("statusespageText is displayed");
	}

	public void editEventPages() {
		util.waitUntilElement(driver, eventBuilderPages);
		eventBuilderPages.click();
		log.info("Event Pages is clicked successfully");
		util.waitUntilElement(driver, eventPagesText);
		Assert.assertTrue(eventPagesText.isDisplayed());
		log.info("eventPagesText is displayed");
	}

	public void saveExitButton() throws Throwable {
		Utility.highLightElement(driver, hoverSaveDropDown);
		act.moveToElement(hoverSaveDropDown);
		hoverSaveDropDown.click();
		log.info("saveClose dropdown is clicked");
		util.waitUntilElement(driver, saveExitButton);
		saveExitButton.click();
		log.info("saveExit button is clicked sucessfully");
	}

	public void clickEventUrl() throws Throwable {
		Thread.sleep(5000);
		util.waitUntilElement(driver, eventUrl);
		util.scrollingElementUsingJS(driver, eventUrl);
		log.info("Scrolled event url");
		util.clickUsingJS(driver, eventUrl);
		log.info("event url is clicked sucessfully");
	}

	public void editCloneEventDateAndTime() throws InterruptedException {
		// Select Start Date and Time
		if (EventConfig.select24HoursTime == true) {
			util.waitUntilElement(driver, a24HoursTimeCheckBoxXpath);
			Thread.sleep(5000);
			util.scrollingElementUsingJS(driver, a24HoursTimeCheckBoxXpath);
			a24HoursTimeCheckBoxXpath.click();
		}

		util.waitUntilElement(driver, startDateCalender);
		util.scrollingElementUsingJS(driver, startDateCalender);
		startDateCalender.clear();
		startDateCalender.sendKeys(EventConfig.startDateWithTime.split(" ")[0]);

		util.selectDropDownByText(startTimeHoursDropDown, EventConfig.startDateWithTime.split(" ")[1].split(":")[0]);
		util.selectDropDownByText(startTimeMinutesDropDown, EventConfig.startDateWithTime.split(" ")[1].split(":")[1]);

		if (EventConfig.select24HoursTime == false) {
			util.selectDropDownByText(startTimePeriodDropDown, EventConfig.startTimePeriodType);
		}

		// Select End Date and Time
		util.scrollingElementUsingJS(driver, endDateCalendar);
		util.waitUntilElement(driver, endDateCalendar);
		endDateCalendar.clear();
		endDateCalendar.sendKeys(EventConfig.endDateWithTime.split(" ")[0]);

		util.selectDropDownByText(endTimeHoursDropDown, EventConfig.endDateWithTime.split(" ")[1].split(":")[0]);
		util.selectDropDownByText(endTimeMinutesDropDown, EventConfig.endDateWithTime.split(" ")[1].split(":")[1]);

		if (EventConfig.select24HoursTime == false) {
			util.selectDropDownByText(endTimePeriodDropDown, EventConfig.endTimePeriodType);
		}
	}

	public void verifyTimeDurationOfEditCloneEventDateAndTime() throws ParseException {
		boolean value = true;
		util.scrollingElementUsingJS(driver, DurationOfStartEndDate);
		List<WebElement> totalElementTexts = driver.findElements(By.xpath(DurationOfStartEndDates));
		String exactTotalElementText = null;
		for (WebElement totalElm : totalElementTexts) {
			if (!totalElm.getText().isBlank()) {
				exactTotalElementText = exactTotalElementText + totalElm.getText();
			}
		}
		System.out.println("UI date validation Text" + exactTotalElementText);

		ArrayList<String> values = DateUtils.findDifferenceBetweenTwoDates(EventConfig.startDateWithTime,
				EventConfig.endDateWithTime);
		for (String dateTime : values) {
			System.out.println("Expected date validation Text" + dateTime);
		}
		for (int i = 0; i < values.size(); i++) {
			if (!values.get(i).startsWith("0")) {
				if (!exactTotalElementText.contains(values.get(i))) {
					value = false;
					break;
				}
				;
			}
		}
		Assert.assertTrue(value, "Assert failure:- giving date duration is wrong");
	}

	public void verifyUserAbleToProvidedataInRegistrationTimerInputBox() throws InterruptedException {
		util.waitUntilElement(driver, RegistrationTimerInputBox);
		util.scrollingElementUsingJS(driver, RegistrationTimerInputBox);
		RegistrationTimerInputBox.clear();
		RegistrationTimerInputBox.sendKeys(EventConfig.RegistrationTimer);
		String PickDate = RegistrationTimerInputBox.getAttribute("value");
		System.out.println("PickDate :" + PickDate);
		System.out.println("EventConfig.ticketalesStartDate : " + EventConfig.RegistrationTimer);
		Assert.assertEquals(RegistrationTimerInputBox.getAttribute("value"), EventConfig.RegistrationTimer);
	}

}
