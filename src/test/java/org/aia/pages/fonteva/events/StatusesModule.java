package org.aia.pages.fonteva.events;

import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class StatusesModule {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	Events events;
	Actions act;
	static Logger log = Logger.getLogger(StatusesModule.class);
	public String salesOrder = "";
	public String aiaNumber = "";

	public StatusesModule(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
		events = PageFactory.initElements(driver, Events.class);
	}

	// EventApi:EventBuilderStatuses
		@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderStatuses']")
		WebElement eventBuilderStatuses;

		@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[contains(text(),'statuses for your event.')]")
		WebElement statusespageText; 
		
		@FindBy(xpath = "//button[text()='New Status']") WebElement newStatuses;
		
		@FindBy(xpath = "//h2[text()='Create Event Status']") WebElement headerInNewStatusPopup;
		
		@FindBy(css = "div[data-label='Status Name'] input") WebElement statusName;
		
		@FindBy(css = "label[data-label='Registration Open'] input") WebElement registrationOpenInNewStatusPopup;
		
		@FindBy(css = "label[data-label='Published To Portal'] input") WebElement publishPortalInNewStatusPopup;
		
		@FindBy(css = "button[aria-label='Save & Close' ][data-name='saveStatus']") WebElement saveCloseButtonInNewStatusPopup;
		
		
		
		
		public void editEventStatuses() {
			util.waitUntilElement(driver, eventBuilderStatuses);
			eventBuilderStatuses.click();
			log.info("Event Statuses is clicked successfully");
			util.waitUntilElement(driver, statusespageText);
			Assert.assertTrue(statusespageText.isDisplayed());
			log.info("statusespageText is displayed");
		}
}
