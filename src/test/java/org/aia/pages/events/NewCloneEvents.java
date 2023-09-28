package org.aia.pages.events;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Logging;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;




public class NewCloneEvents {
	
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	ConfigDataProvider testData;
	public String newEvent = "";
	static Logger log = Logger.getLogger(NewCloneEvents.class);

	
	
	public NewCloneEvents(WebDriver Idriver)
	{
		this.driver=Idriver;
		executor = (JavascriptExecutor) driver;
		testData = new ConfigDataProvider();
	
	}
	
	@FindBy(xpath  = "//a[contains(@class,'label-action dndItem')]/span[text()='Events']") WebElement eventsLink;
	
	@FindBy(xpath = "//a[@class='forceActionLink' and @title='New']") WebElement eventNewButton;
	
	@FindBy(xpath = "//div[@data-name='cloneEventTitle']") WebElement cloneEventHeader;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//input[@name='eventCreation']/following::span[text()='Clone an existing event']/preceding-sibling::input") WebElement existingCloneEvent;
	
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-name='eventObjCloneName']/Input") WebElement eventName;
	
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-name='startDate']//div/input") WebElement eventStartDate;
	
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-name='cloneEvent']//select[@name='Event Category']") WebElement selectCategory;
	
	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] div[class*=selectize-input] input") WebElement eventSearch;
	
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-name='cloneOptions']//div/label[contains(text(),'Select which segments you would like to clone')]") WebElement eventOptionTitle;
	
	@FindAll(value = { @FindBy(css = "div[class=selectize-dropdown-content] div") }) List<WebElement> eventOptions;
	
	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] button[data-name='cloneContinueModal']") WebElement eventCloneButton;
	
	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] label[data-name='ticketCheck'] input") WebElement ticketCheckbox;
	
	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] label[data-name='scheduleCheck'] input") WebElement scheduleCheckbox;
	
	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] label[data-name='venueCheck'] input") WebElement venueCheckbox;
	
	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] label[data-name='vendorCheck'] input") WebElement vendorCheckbox;
	
	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] label[data-name='speakerCheck'] input") WebElement speakerCheckbox;
	
	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] label[data-name='sponsorCheck'] input") WebElement sponsorCheckbox;
	
	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] label[data-name='eventStatusPageCheck'] input") WebElement eventStatusPageCheckbox;
	
	@FindBy(css = "div[class='windowViewMode-normal oneContent active lafPageHost'] button[data-name='finishCloneModal']") WebElement eventFinishCloneButon;
	
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//slot[contains(@class,\"slds-page-header__title slds-m-right--small slds\")]//lightning-formatted-text") WebElement eventNameHeader;
	
	
	
	public void newCloneEvent(String eventCategory) throws InterruptedException, Throwable {
		
		util.waitUntilElement(driver, eventsLink);
		util.clickUsingJS(driver, eventsLink);
		log.info("Events clickd successfully");
		Logging.logger.info("Events clickd successfully");
		util.waitUntilElement(driver, eventNewButton);
		eventNewButton.click();
		log.info("Even New button is clicked");
		util.waitUntilElement(driver, cloneEventHeader);
		boolean cloneEventPopup = cloneEventHeader.isDisplayed();
		Assert.assertTrue(cloneEventPopup);
		log.info("Clone Event pop is displayed");
		util.waitUntilElement(driver, existingCloneEvent);
		Thread.sleep(5000);
		boolean cloneEventRadioButton = existingCloneEvent.isSelected();
		Assert.assertTrue(cloneEventRadioButton);
		log.info("Existing clone event radio button is selected");
		
		String eName = "TestQA" + new SimpleDateFormat("MMddyyyyHHmmss").format(new Date());
		
		util.enterText(driver, eventName, eName);
		log.info("Entered Event name as" + eName);
		util.enterText(driver, eventStartDate, new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
		log.info("Event Date is Entered");
		util.waitUntilElement(driver, selectCategory);
		util.selectDropDownByText(selectCategory, eventCategory);
		log.info("Event category slected" + eventCategory);
		Thread.sleep(2000);
		util.waitUntilElement(driver, eventSearch);
		util.enterText(driver, eventSearch, "Testing Event");
		log.info("event list size"+ eventOptions.size());
		for (int i = 0; i < eventOptions.size(); i++) {
			String event = eventOptions.get(i).getText();
			
			if(event.contains("Testing Event")) {
				eventOptions.get(i).click();
				System.out.println("matched");
				break;
			}else {
				System.out.println("not matched");
			}
		}
		eventCloneButton.click();
	}
	
	public void verifyCloneEventSegmentCheckBoxs() throws InterruptedException {
		
		util.waitUntilElement(driver, eventOptionTitle);
		log.info(eventOptionTitle.getText());
		util.waitUntilElement(driver, ticketCheckbox);
		Assert.assertTrue(ticketCheckbox.isDisplayed());
		System.out.println("VERIFIED: Ticket checkbox is selected");
		log.info("VERIFIED: Ticket checkbox is selected");
		Assert.assertTrue(scheduleCheckbox.isDisplayed());
		System.out.println("VERIFIED: scheduleCheckbox is selected");
		log.info("VERIFIED: scheduleCheckbox is selected");
		Assert.assertTrue(venueCheckbox.isDisplayed());
		System.out.println("VERIFIED: venueCheckbox is selected");
		log.info("VERIFIED: venueCheckbox is selected");
		Assert.assertTrue(speakerCheckbox.isDisplayed());
		System.out.println("VERIFIED: speakerCheckbox is selected");
		log.info("VERIFIED: speakerCheckbox is selected");
		Assert.assertTrue(eventStatusPageCheckbox.isDisplayed());
		System.out.println("VERIFIED: eventStatusPageCheckbox is selected");
		log.info("VERIFIED: eventStatusPageCheckbox is selected");
		eventFinishCloneButon.click();
		log.info("Clone button is clicked sucessfully");
		Thread.sleep(8000);
		util.waitUntilElement(driver, eventNameHeader);
		newEvent = eventNameHeader.getText();
		log.info(newEvent);
		testData.testDataProvider().setProperty("eventName", newEvent);
		System.out.println(eventNameHeader);
		Assert.assertTrue(eventNameHeader.isDisplayed());
		log.info("eventName header is displayed");
	}
}
