package org.aia.pages.fonteva.events;

import java.util.List;

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
	Actions act;
	static Logger log = Logger.getLogger(EditCloneEvent.class);
	public String salesOrder ="";
	public String aiaNumber = "";
	

	public TicketModule(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
	}

	
	
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderTickets']")
	WebElement eventBuilderTickets;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-name='ticketSalesStartDate']//input")
	WebElement eventTicketSalesStartDate;

	@FindBy(xpath = "(//div[@class='windowViewMode-normal oneContent active lafPageHost']//td/div[@data-name='More_Options'])[1]")
	WebElement actionsColoumnInTicketTypes;

	@FindAll(value = { @FindBy(xpath = "(//div[@class='windowViewMode-normal oneContent active lafPageHost']//ul[@class='dropdown__list'])[1]/li") })
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
	WebElement ticketalesStartDate;
	
	@FindBy(xpath = "//label[contains(text(),'Registration Instructions')]")
	WebElement registrationInstructions;
	
	@FindBy(xpath = "//label[contains(text(),'Attendee Modal Instructions')]")
	WebElement attendeeModelInstructions;
	
	@FindBy(xpath = "//span[contains(text(),'Enable QR Code for Event Pass')]")
	WebElement enableQRCodeForEventPass;   
	
	
	@FindBy(xpath = "//button[contains(text(),'Save & Continue')]//preceding-sibling::button[contains(text(),'Cancel')]")
	WebElement createTicketTypeCancelButton;
	
	
	//create Ticket type popup 
	@FindBy(xpath = "//span[contains(text(),'Is Published')]")
	WebElement isPublished ;
	
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
	


	
	
	public void verifyAllFIeldsTicketModule() throws InterruptedException{
		util.waitUntilElement(driver, eventBuilderTickets);
		eventBuilderTickets.click();
		util.waitUntilElement(driver, manageInventory);
		//util.wait(5000);
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
		util.scrollingElementUsingJS(driver, ticketalesStartDate);
		Assert.assertTrue(ticketalesStartDate.isDisplayed(), "ticketalesStartDate field is not available");
		util.scrollingElementUsingJS(driver, registrationInstructions);
		Assert.assertTrue(registrationInstructions.isDisplayed(), "registration Instructions field is not available");
		util.scrollingElementUsingJS(driver, attendeeModelInstructions);
		Assert.assertTrue(attendeeModelInstructions.isDisplayed(), "attendee Model Instructions field is not available");
	}



	public void verifyAllFIeldsInCreateTicketpivotTabPopUp() throws InterruptedException {
		util.waitUntilElement(driver, eventBuilderTickets);
		eventBuilderTickets.click();
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
		
		util.scrollingElementUsingJS(driver, ticketalesStartDate);
		Assert.assertTrue(ticketalesStartDate.isDisplayed(), "ticketalesStartDate field is not available");
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
