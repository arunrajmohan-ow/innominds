package org.aia.pages.fonteva.events;

import static org.junit.Assert.assertTrue;

import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import groovy.transform.Final;

public class AgendaModule {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	Events events;
	Actions act;
	static Logger log = Logger.getLogger(AgendaModule.class);
	public String salesOrder = "";
	public String aiaNumber = "";

	public AgendaModule(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
		events = PageFactory.initElements(driver, Events.class);
	}

	// EventApi:EventBuilderAgenda
		@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderAgenda']")
		WebElement eventBuilderAgenda;

		@FindBy(css = "table[class='slds-table slds-table_header-fixed slds-table_bordered slds-table_edit'] tr:nth-child(5)  th lightning-base-formatted-text")
		WebElement scheduleItemDisplayName;

		@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//a[@data-tab='agenda']")
		WebElement agendaTab;
		
		@FindBy(xpath = "//button[text()='New Schedule Item']") WebElement newScheduleItem;
		
		@FindBy(xpath = "//h2[text()='Add Schedule Item']") WebElement headerPopupInSceduleItem;
		
		@FindBy(xpath = "label[data-label='Active when the event is active'] input") WebElement activeCheckboxInsceduleItem;
		
		@FindBy(css = "div[data-label='Schedule item name'] input") WebElement sceduleItemName;
		
		@FindBy(css = "div[data-label='Capacity'] input") WebElement capacityInscheduleItem;
		
		@FindBy(css = "div[data-label='Display Name'] input") WebElement displayNameInscheduleItem;
		
		@FindBy(css = "div[data-label='Price'] input") WebElement priceInscheduleItem;
		
		@FindBy(css = "div[data-label='Start Date'] input") WebElement startDateInscheduleItem;
		
		@FindBy(xpath  = "//div[@data-label='Start Time']//select[@name='Start Time']") WebElement startTimeInscheduleItem;
		
		@FindBy(xpath = "//div[@class='slds-form-element display-element is-required']//div[@data-name='startMin']//select") WebElement startMinInScheduleItem;
		
		@FindBy(xpath = "//div[@class='slds-form-element display-element is-required']//div[@data-name='startAMPM']//select") WebElement startAmPmSceduleItem;
		
		@FindBy(xpath = "//label[text()='Duration']/following::div[contains(@class,'selectize-input items has-options not-full')]/input")WebElement durationInScheduleItem;
		
		@FindBy(xpath = "//label[@data-label=\"Allow Conflicts\"]/input") WebElement allowConflictsInScheduleItem;
		
		@FindBy(xpath = "//label[text()='Speaker']/following::div[contains(@class,'selectize-input items not-full has-options')]") WebElement speakerInScheduleItem;
		
		@FindBy(xpath = "//label[text()='Description']/following::div[@data-name='description']//div[@class='fr-element fr-view cke_editable']") WebElement descriptionInscheduleItem;
		
		@FindBy(xpath = "//button[@data-name='saveUpdateScheduleItem']") WebElement buttonsInScheduleItem;
		
	
		public void clickEventAgenda() {
			util.waitUntilElement(driver, eventBuilderAgenda);
			eventBuilderAgenda.click();
			log.info("Event Agenda is clicked successfully");
			util.waitUntilElement(driver, agendaTab);
			Assert.assertTrue(agendaTab.isDisplayed());
			log.info("agendaTab is displayed");
		}
		
		public void clickNewScheduleItem() {
			Utility.waitForWebElement(driver, newScheduleItem, 10);
			newScheduleItem.click();
			log.info("New Schedule itm is clicked successfully");
			Utility.waitForWebElement(driver, headerPopupInSceduleItem, 10);
			Assert.assertTrue(headerPopupInSceduleItem.isDisplayed());
		}
		
		public void ValidateActiveCheckBoxInScheduleItem() throws Throwable {
			Thread.sleep(5000);
			util.waitUntilElement(driver, activeCheckboxInsceduleItem);
			Utility.highLightElement(driver, activeCheckboxInsceduleItem);
			if (activeCheckboxInsceduleItem.isSelected()) {
				System.out.println("Active checkobox is enabled");
			} else {
				util.clickUsingJS(driver, activeCheckboxInsceduleItem);
				log.info("Active CheckBox is clicked");
			}
		}
		
		
		
		
		

}
