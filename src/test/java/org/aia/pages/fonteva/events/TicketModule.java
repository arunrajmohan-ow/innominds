package org.aia.pages.fonteva.events;

import java.util.List;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TicketModule {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	ConfigDataProvider testData;
	Actions act;
	static Logger log = Logger.getLogger(EventInfoModule.class);
	public String salesOrder = "";
	public String aiaNumber = "";

	public TicketModule(WebDriver IDriver) {
		this.driver = IDriver;
		testData = new ConfigDataProvider();
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
	}

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderTickets']")
	WebElement eventBuilderTickets;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-name='ticketSalesStartDate']//input")
	WebElement eventTicketSalesStartDate;

	@FindBy(xpath = "(//div[@class='windowViewMode-normal oneContent active lafPageHost']//td/div[@data-name='More_Options'])[1]")
	WebElement actionsColoumnInTicketTypes;

	@FindAll(value = {
			@FindBy(xpath = "(//div[@class='windowViewMode-normal oneContent active lafPageHost']//ul[@class='dropdown__list'])[1]/li") })
	List<WebElement> actionOptions;

	@FindBy(xpath = "//h2[@id='modal-heading-label'][text()='Edit Ticket Type']")
	WebElement ediTicketTypeHeader;

	@FindBy(xpath = "//label[text()='Price']/following::div/div[@data-name='price']/input")
	WebElement PriceInEditTicketType;

	@FindBy(css = "button[data-name='ticketTypeSaveModalButton']")
	WebElement saveAndContinueButtonInEditTicketType;

	@FindBy(xpath = "//button[contains(text(),'Manage Inventory')]")
	WebElement manageInventory;

	@FindBy(xpath = "//button[contains(text(),'New Ticket Type')]")
	WebElement newTicketType;

	@FindBy(xpath = "//h2[contains(text(),'Create Ticket Type')]")
	WebElement createANewTicketType;

	@FindBy(xpath = "//label[contains(text(),'Ticket Display Order')]")
	WebElement ticketDisplayOrder;

	@FindBy(xpath = "//label[contains(text(),'Ticket Sales Start Date')]//parent::span//following-sibling::div//input")
	WebElement ticketSalesStartDate;

	@FindBy(xpath = "//label[contains(text(),'Registration Instructions')]")
	WebElement registrationInstructions;

	@FindBy(xpath = "//label[contains(text(),'Attendee Modal Instructions')]")
	WebElement attendeeModelInstructions;

	@FindBy(xpath = "//span[contains(text(),'Enable QR Code for Event Pass')]")
	WebElement enableQRCodeForEventPass;

	@FindBy(xpath = "//button[contains(text(),'Save & Continue')]//preceding-sibling::button[contains(text(),'Cancel')]")
	WebElement createTicketTypeCancelButton;

	// create Ticket type popup
	@FindBy(xpath = "//span[contains(text(),'Is Published')]")
	WebElement isPublished;

	@FindBy(xpath = "//span[contains(text(),'Is Active')]")
	WebElement isActive;

	@FindBy(xpath = "//span//label[contains(text(),'Ticket Name')]")
	WebElement ticketName;

	@FindBy(xpath = "(//span//label[contains(text(),'Price')])[1]")
	WebElement price;

	@FindBy(xpath = "//span[contains(text(),'Enable Ticket Waitlisting')]")
	WebElement enableTicketWaitlisting;

	@FindBy(xpath = "//span[contains(text(),'Show Tickets Remaining')]")
	WebElement showTicketsRemaining;

	@FindBy(xpath = "//span[contains(text(),'Restrict Quantity Per Order')]")
	WebElement restrictQuantityPerOrder;

	@FindBy(xpath = "//span//label[contains(text(),'Minimum Quantity')]")
	WebElement minimumQuantity;

	@FindBy(xpath = "(//span//label[contains(text(),'Maximum Quantity')])[1]")
	WebElement maximumQuantity;

	@FindBy(xpath = "(//span//label[contains(text(),'Description')])[2]")
	WebElement description;

	@FindBy(xpath = "//span//label[contains(text(),'Ticket Information')]")
	WebElement ticketInformation;

	@FindBy(xpath = "//span//label[contains(text(),'Ticket Image URL')]")
	WebElement ticketImageURL;

	@FindBy(xpath = "//span[contains(text(),'Is Group Ticket Type')]")
	WebElement isGroupTicketType;

	@FindBy(xpath = "//span//label[contains(text(),'Group Type')]")
	WebElement groupType;

	@FindBy(xpath = "//span//label[contains(text(),'Attendees Per Group')]")
	WebElement attendeesPerGroup;

	@FindBy(xpath = "//span[contains(text(),'Enable Refund Request')]")
	WebElement enableRefundRequest;

	@FindBy(xpath = "//span//label[contains(text(),'Refund Request Policy')]")
	WebElement refundRequestPolicy;

	@FindBy(css = "button[data-name='addNewTicket']")
	WebElement newTicketTypeInTicketsTab;

	@FindBy(xpath = "//label[@data-name='isPublished']/input")
	WebElement isPublishedCheckBox;

	@FindBy(xpath = "//label[@data-name='isActive']/input")
	WebElement isActiveCheckBox;

	@FindBy(xpath = "//div[@data-name='ticketName']/input")
	WebElement ticketNameInCreateTicketType;

	@FindBy(xpath = "//label[text()='Price']/following::div/div[@data-name='price']/input")
	WebElement PriceInCreateTicketType;

	@FindBy(xpath = "//label[@data-name='restrictQuantity']/input")
	WebElement restrictQunperOrderCheckBox;

	@FindBy(xpath = "//div[@data-name='minimumQuantity']/input")
	WebElement miniQuantityInCreateTicketType;

	@FindBy(xpath = "//div[@data-name='maximumQuantity']/input")
	WebElement maxQuantityInCreateTicketType;

	@FindBy(xpath = "//div[@data-name='ticketDescription']/textarea")
	WebElement descriptionInCreateTicketType;

	public void eventTicketsTab() {
		util.waitForJavascript(driver, 90000, 5000);
		Utility.highLightElement(driver, eventBuilderTickets);
		util.waitUntilElement(driver, eventBuilderTickets);
		eventBuilderTickets.click();
		log.info("Event Tickets is clicked successfully");
	}

	public void validateEventTicketSalesStartDate() {
		util.waitUntilElement(driver, eventTicketSalesStartDate);
		Utility.highLightElement(driver, eventTicketSalesStartDate);
		System.out.println(eventTicketSalesStartDate.getAttribute("value"));
		log.info("eventTicketSalesStartDate" + eventTicketSalesStartDate.getAttribute("value"));
	}

	public void clickNewTicketType() throws InterruptedException {
		Thread.sleep(5000);
		util.waitUntilElement(driver, newTicketTypeInTicketsTab);
		Utility.highLightElement(driver, newTicketTypeInTicketsTab);
		newTicketTypeInTicketsTab.click();
	}

	public void publishedCheckBox() throws Throwable {
		Thread.sleep(5000);
		util.waitUntilElement(driver, isPublishedCheckBox);
		Utility.highLightElement(driver, isPublishedCheckBox);
		if (isPublishedCheckBox.isSelected()) {
			System.out.println("is published checkobox is enabled");
		} else {
			util.clickUsingJS(driver, isPublishedCheckBox);
			log.info("IsPublished CheckBox is clicked");
		}
	}

	public void activeCheckBox() throws Throwable {
		Thread.sleep(5000);
		util.waitUntilElement(driver, isActiveCheckBox);
		Utility.highLightElement(driver, isActiveCheckBox);
		if (isActiveCheckBox.isSelected()) {
			System.out.println("is published checkobox is enabled");
		} else {
			util.clickUsingJS(driver, isActiveCheckBox);
			log.info("IsActive CheckBox is clicked");
		}
	}

	public void enterTicketName() {
		Utility.highLightElement(driver, ticketNameInCreateTicketType);
		util.enterText(driver, ticketNameInCreateTicketType, testData.testDataProvider().getProperty("ticketName"));
		log.info("Event Ticket name is enterd");
	}

	/**
	 * @param editTicket Here editTicket = true Then it click on the
	 *                   actionsColumnTicketType in that i validate options.
	 * @throws Throwable
	 */
	public void editEventTicket(Boolean editTicket) throws Throwable {
		String option;
		if (editTicket == true) {
			actionsColoumnInTicketTypes.click();
			for (int i = 0; i < actionOptions.size(); i++) {
				option = actionOptions.get(i).getText();
				if (option.equals("Edit Ticket Type")) {
					actionOptions.get(i).click();
				}
				System.out.println(option);
			}
			;
		}
	}

	public void validateEditTicketTypeHeader() {
		util.waitUntilElement(driver, ediTicketTypeHeader);
		Assert.assertTrue(ediTicketTypeHeader.isDisplayed());
	}

	public void enterPriceInCreateTicketType() throws Throwable {
		util.enterText(driver, PriceInCreateTicketType, "400.00");
		log.info("Price enterd in Create ticket type popup");
	}

	public void restrictQuantityCheckBox() throws Throwable {
		Thread.sleep(5000);
		util.waitUntilElement(driver, restrictQunperOrderCheckBox);
		Utility.highLightElement(driver, restrictQunperOrderCheckBox);
		if (restrictQunperOrderCheckBox.isSelected()) {
			System.out.println("is published checkobox is enabled");
		} else {
			util.clickUsingJS(driver, restrictQunperOrderCheckBox);
			log.info("IsActive CheckBox is clicked");
			util.enterText(driver, miniQuantityInCreateTicketType, "1");
			util.enterText(driver, maxQuantityInCreateTicketType, "1");

		}
	}

	public void enterDescriptionInCreateTicketType() {
		Utility.highLightElement(driver, descriptionInCreateTicketType);
		util.enterText(driver, descriptionInCreateTicketType, "Ticket type description");
	}

	public void buttonsInCreateTicketType(String action) throws InterruptedException {
		switch (action) {
		case "SaveContinue":
			Utility.highLightElement(driver, saveAndContinueButtonInEditTicketType);
			saveAndContinueButtonInEditTicketType.click();
			log.info("Continue button is clicked in Edit ticket type");
			Thread.sleep(5000);
			break;
		case "cancel":
			// TODO
			break;
		}
	}

	public void saveAndContinueButtonInTicketType() {
		try {
			Utility.waitForWebElement(driver, saveAndContinueButtonInEditTicketType, 30);
			saveAndContinueButtonInEditTicketType.click();
			log.info("Continue button is clicked in Edit ticket type");
			Thread.sleep(5000);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyAllFIeldsTicketModule() throws InterruptedException {
		util.waitUntilElement(driver, eventBuilderTickets);
		eventBuilderTickets.click();
		util.waitUntilElement(driver, manageInventory);
		// util.wait(5000);
		Assert.assertTrue(manageInventory.isDisplayed(), "manageInventory field is not available");
		Assert.assertTrue(newTicketType.isDisplayed(), "newTicketType button is not available");
		util.waitUntilElement(driver, newTicketType);
		util.clickUsingJS(driver, newTicketType);
		util.waitUntilElement(driver, createANewTicketType);
		Assert.assertTrue(createANewTicketType.isDisplayed(), "createANewTicketType text is not available");
		util.clickUsingJS(driver, createTicketTypeCancelButton);
		util.waitUntilElement(driver, ticketDisplayOrder);
		util.scrollingElementUsingJS(driver, ticketDisplayOrder);
		Assert.assertTrue(ticketDisplayOrder.isDisplayed(), "ticketDisplayOrder field is not available");
		util.scrollingElementUsingJS(driver, ticketSalesStartDate);
		Assert.assertTrue(ticketSalesStartDate.isDisplayed(), "ticketSalesStartDate field is not available");
		util.scrollingElementUsingJS(driver, registrationInstructions);
		Assert.assertTrue(registrationInstructions.isDisplayed(), "registration Instructions field is not available");
		util.scrollingElementUsingJS(driver, attendeeModelInstructions);
		Assert.assertTrue(attendeeModelInstructions.isDisplayed(),
				"attendee Model Instructions field is not available");
	}

	public void verifyAllFIeldsInCreateTicketpivotTabPopUp() throws InterruptedException {
		util.waitUntilElement(driver, newTicketType);
		util.scrollingElementUsingJS(driver, newTicketType);
		Assert.assertTrue(newTicketType.isDisplayed(), "newTicketType button is not available");
		util.waitUntilElement(driver, newTicketType);
		util.clickUsingJS(driver, newTicketType);
		util.waitUntilElement(driver, createANewTicketType);
		Assert.assertTrue(createANewTicketType.isDisplayed(), "createANewTicketType text is not available");

		util.scrollingElementUsingJS(driver, isPublished);
		Assert.assertTrue(isPublished.isDisplayed(), "isPublished field is not available");

		util.scrollingElementUsingJS(driver, isActive);
		Assert.assertTrue(isActive.isDisplayed(), "isActive field is not available");

		util.scrollingElementUsingJS(driver, ticketName);
		Assert.assertTrue(ticketName.isDisplayed(), "ticketName field is not available");

		util.scrollingElementUsingJS(driver, price);
		Assert.assertTrue(price.isDisplayed(), "price field is not available");

		util.scrollingElementUsingJS(driver, enableTicketWaitlisting);
		Assert.assertTrue(enableTicketWaitlisting.isDisplayed(), "enableTicketWaitlisting field is not available");

		util.scrollingElementUsingJS(driver, showTicketsRemaining);
		Assert.assertTrue(showTicketsRemaining.isDisplayed(), "showTicketsRemaining field is not available");

		util.scrollingElementUsingJS(driver, restrictQuantityPerOrder);
		Assert.assertTrue(restrictQuantityPerOrder.isDisplayed(), "restrictQuantityPerOrder field is not available");

		util.scrollingElementUsingJS(driver, minimumQuantity);
		Assert.assertTrue(minimumQuantity.isDisplayed(), "minimumQuantity field is not available");

		util.scrollingElementUsingJS(driver, maximumQuantity);
		Assert.assertTrue(maximumQuantity.isDisplayed(), "maximumQuantity field is not available");

		util.scrollingElementUsingJS(driver, ticketSalesStartDate);
		Assert.assertTrue(ticketSalesStartDate.isDisplayed(), "ticketSalesStartDate field is not available");

		util.scrollingElementUsingJS(driver, description);
		Assert.assertTrue(description.isDisplayed(), "description field is not available");

		util.scrollingElementUsingJS(driver, ticketInformation);
		Assert.assertTrue(ticketInformation.isDisplayed(), "ticketInformation field is not available");

		util.scrollingElementUsingJS(driver, ticketImageURL);
		Assert.assertTrue(ticketImageURL.isDisplayed(), "ticketImageURL field is not available");

		util.scrollingElementUsingJS(driver, isGroupTicketType);
		Assert.assertTrue(isGroupTicketType.isDisplayed(), "isGroupTicketType field is not available");

		util.scrollingElementUsingJS(driver, groupType);
		Assert.assertTrue(groupType.isDisplayed(), "groupType field is not available");

		util.scrollingElementUsingJS(driver, attendeesPerGroup);
		Assert.assertTrue(attendeesPerGroup.isDisplayed(), "attendeesPerGroup field is not available");

		util.scrollingElementUsingJS(driver, enableRefundRequest);
		Assert.assertTrue(enableRefundRequest.isDisplayed(), "enableRefundRequest field is not available");

		util.scrollingElementUsingJS(driver, refundRequestPolicy);
		Assert.assertTrue(refundRequestPolicy.isDisplayed(), "refundRequestPolicy field is not available");

	}

	public void verifyAllFIelds() throws InterruptedException {
		util.waitUntilElement(driver, eventBuilderTickets);
		eventBuilderTickets.click();
		util.waitUntilElement(driver, manageInventory);
		Assert.assertTrue(manageInventory.isDisplayed(), "manageInventory field is not available");
		Assert.assertTrue(newTicketType.isDisplayed(), "newTicketType button is not available");
		util.waitUntilElement(driver, newTicketType);
		util.clickUsingJS(driver, newTicketType);
		util.waitUntilElement(driver, createANewTicketType);
		Assert.assertTrue(createANewTicketType.isDisplayed(), "createANewTicketType text is not available");
		util.clickUsingJS(driver, createTicketTypeCancelButton);
		util.waitUntilElement(driver, ticketDisplayOrder);
		util.scrollingElementUsingJS(driver, ticketDisplayOrder);
		Assert.assertTrue(ticketDisplayOrder.isDisplayed(), "ticketDisplayOrder field is not available");
		util.scrollingElementUsingJS(driver, ticketSalesStartDate);
		Assert.assertTrue(ticketSalesStartDate.isDisplayed(), "ticketSalesStartDate field is not available");
		util.scrollingElementUsingJS(driver, registrationInstructions);
		Assert.assertTrue(registrationInstructions.isDisplayed(), "registration Instructions field is not available");
		util.scrollingElementUsingJS(driver, attendeeModelInstructions);
		Assert.assertTrue(attendeeModelInstructions.isDisplayed(),
				"attendee Model Instructions field is not available");
	}

	public void verifyUserAbleToProvidedateIntoTicketSalesStartDate() throws InterruptedException {
		util.waitUntilElement(driver, eventBuilderTickets);
		eventBuilderTickets.click();
		util.waitUntilElement(driver, manageInventory);
		util.scrollingElementUsingJS(driver, ticketSalesStartDate);
		ticketSalesStartDate.clear();
		ticketSalesStartDate.sendKeys(EventConfig.ticketSalesStartDate);
		String PickDate = ticketSalesStartDate.getAttribute("value");
		System.out.println("PickDate :" + PickDate);
		System.out.println("EventConfig.ticketSalesStartDate : " + EventConfig.ticketSalesStartDate);
		Assert.assertEquals(ticketSalesStartDate.getAttribute("value"), EventConfig.ticketSalesStartDate);
	}
}
