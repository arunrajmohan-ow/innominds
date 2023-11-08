package org.aia.pages.fonteva.events;

import java.util.List;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.apache.tika.config.Field;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import groovy.transform.Final;

public class SpeakersModule {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	ConfigDataProvider testData;
	Actions act;
	static Logger log = Logger.getLogger(SpeakersModule.class);
	public String salesOrder = "";
	public String aiaNumber = "";

	public SpeakersModule(WebDriver IDriver) {
		this.driver = IDriver;
		testData = new ConfigDataProvider();
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
	}

	// Event Speakers
	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[@data-menu='EventApi:EventBuilderSpeakers']")
	WebElement eventBuilderSpeakers;

	@FindBy(xpath = "(//div[@class='windowViewMode-normal oneContent active lafPageHost']//div[contains(text(),'Speakers')])[2]")
	WebElement speakersTab;

	@FindBy(xpath = "//button[text()='New Speaker']")
	WebElement newSpeaker;

	@FindBy(xpath = "//h2[text()='New Speaker']")
	WebElement newspeakerPopupHeader;

	@FindBy(xpath = "//div[@data-name='speakerName']/input")
	WebElement speakerName;

	@FindBy(xpath = "//select[@name='Status']")
	WebElement selectStatus;

	@FindBy(xpath = "//div[@data-label='Contact Record']//div[contains(@class,'selectize-input items')]/input")
	WebElement contactRecordSearch;

	@FindAll(value = {
			@FindBy(xpath = "//span[@data-name='contact']/following::div[@class='selectize-dropdown-content']/div") })
	List<WebElement> contactRecordOptions;

	@FindBy(css = "label[data-name='isFeatured'] input")
	WebElement featuredCheckbox;

	@FindBy(css = "div[data-name='title'] input")
	WebElement titleInSpeakersPopup;

	@FindBy(css = "div[data-name='companyName'] input")
	WebElement companyNameInSpeakersPopup;

	@FindBy(xpath = "//label[text()='Speaker Photo URL']/following::label[text()='Browse']")
	WebElement speakerPhotoUrl;

	@FindBy(xpath = "(//button[@data-label='Save'])[2]")
	WebElement cropimageSaveButton;

	@FindBy(xpath = "//button[@data-label='Cancel']")
	WebElement cropImageCancelButton;

	@FindBy(xpath = "//button[contains(text(),'Save & Continue')]//preceding-sibling::button[contains(text(),'Cancel')]")
	WebElement cancelButton;

	@FindBy(css = "button[data-name='speakerModalSaveButton']")
	WebElement saveContinueButton;

	@FindBy(xpath = "//span[@data-label='Speaker Bio']/following::span[text()='Type something']")
	WebElement speakerBio;

	@FindBy(css = "div[data-label='Facebook URL'] input")
	WebElement faceBookUrl;

	@FindBy(css = "div[data-label='LinkedIn URL'] input")
	WebElement linkedInUrl;

	@FindBy(css = "div[data-label='Twitter URL'] input")
	WebElement twitterUrl;

	public void eventSpeakersTab() {
		util.waitUntilElement(driver, eventBuilderSpeakers);
		eventBuilderSpeakers.click();
		log.info("Event Speakers is clicked successfully");
		util.waitUntilElement(driver, speakersTab);
		Assert.assertTrue(speakersTab.isDisplayed());
		log.info("speakersTab is displayed");
	}

	public void clickNewSpeaker() {
		Utility.waitForWebElement(driver, newSpeaker, 10);
		newSpeaker.click();
		Assert.assertTrue(newspeakerPopupHeader.isDisplayed(), "New Speaker pop up is diplayed");
	}

	public void enterSpeakerName() {
		Utility.waitForWebElement(driver, speakerName, 10);
		util.enterText(driver, speakerName, testData.testDataProvider().getProperty("speakerName"));

	}

	public void selectStatusInSpeakers() {
		Utility.waitForWebElement(driver, selectStatus, 10);
		util.selectDropDownByText(selectStatus, testData.testDataProvider().getProperty("speakerStatus"));
	}

	public void contactRecordsInNewSpeakerPopup() throws Throwable {
		System.out.println("enter");
		util.waitUntilElement(driver, contactRecordSearch);
		contactRecordSearch.click();
		util.enterText(driver, contactRecordSearch, testData.testDataProvider().getProperty("speakerRecord"));
		Thread.sleep(3000);
		log.info("event list size" + contactRecordOptions.size());
		for (int i = 0; i < contactRecordOptions.size(); i++) {
			String event = contactRecordOptions.get(i).getText();
			if (event.equals(testData.testDataProvider().getProperty("speakerRecord"))) {
				contactRecordOptions.get(i).click();
				System.out.println("matched");
				break;
			} else {
				System.out.println("not matched");
			}
		}
	}

	public void featuredRadioButton() {
		Utility.waitForWebElement(driver, featuredCheckbox, 0);
		Assert.assertFalse(featuredCheckbox.isSelected());
	}

	public void enterTitleInSpeaker() {
		Utility.waitForWebElement(driver, titleInSpeakersPopup, 10);
		util.enterText(driver, titleInSpeakersPopup, "QA title");
	}

	public void enterCompanyInSpeaker() {
		Utility.waitForWebElement(driver, companyNameInSpeakersPopup, 10);
		util.enterText(driver, companyNameInSpeakersPopup, "QA aia company");
	}

	public void speakerPhotoUrlBrowser() {
		util.scrollingElementUsingJS(driver, speakerPhotoUrl);
		util.fileUploadThroughKeyBoardActions(driver, speakerPhotoUrl, EventConfig.venueImageURLInput);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cropImageButtonsInSpeaker() {
		Utility.waitForWebElement(driver, cropimageSaveButton, 20);
		if (cropimageSaveButton.isDisplayed()) {
			cropimageSaveButton.click();
		}
	}

	public void speakerButtonsInnewSpeakerPopup() {
		util.waitUntilElement(driver, saveContinueButton);
		saveContinueButton.click();
	}

	public void VerifySpeakerBio() {
		Assert.assertTrue(speakerBio.isDisplayed(), "SpeakerBio is displayed");
	}

	public void VerifyFaceBookURL() {
		Assert.assertTrue(faceBookUrl.isDisplayed(), "facebook URL is displayed");
	}

	public void VerifyLinkedURL() {
		Assert.assertTrue(linkedInUrl.isDisplayed(), "linkedInURL is displayed");
	}

	public void VerifyTwitterURL() {
		Assert.assertTrue(twitterUrl.isDisplayed(), "twitterUrl is displayed");
	}

}
