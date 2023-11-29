package org.aia.pages.fonteva.chapterPortal;
import java.util.List;

import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class NavigateToChapterPortal {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	Actions act;
	static Logger log = Logger.getLogger(NavigateToChapterPortal.class);
	public String salesOrder = "";
	public String aiaNumber = "";

	public NavigateToChapterPortal(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		act = new Actions(driver);
	}

	@FindBy(xpath = "//a[@href='/lightning/o/Contact/home']")
	WebElement contactsModule;

	@FindBy(css = "a[title='Allison Garwood Freedland']")
	WebElement contactAccessCP;

	@FindBy(xpath = "//a[@href='javascript:void(0)']/parent::div")
	WebElement showAllLink;

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//a/slot/span[contains(text(),'Portal access')]")
	WebElement portalAccessCount;
	// innerText: "Portal access (10+)"

	@FindBy(xpath = "//div[@class='windowViewMode-normal oneContent active lafPageHost']//span[text()='Show more actions']/parent::button[@part='button button-icon']")
	WebElement dropDownButtonActionContainer;

	@FindAll(value = {
			@FindBy(xpath = "//div[@class='slds-dropdown slds-dropdown_right']//runtime_platform_actions-action-renderer") })
	List<WebElement> showAllOptionInActionContainer;

	@FindAll(value = { @FindBy(xpath = "//div[@class='forceCommunityShowPortalUserMemberList']/ul/li") })
	List<WebElement> portalUserMemberList;

	@FindBy(xpath = "//a[@href='/Chapter/s/my-chapters']")
	WebElement myChapterTab;

	@FindBy(xpath = "//table[@aria-label='All Communities - Chapter Portal']/tbody//th/span/a")
	WebElement comunityGroupName;

	@FindBy(xpath = "//button[text()='Membership']")
	WebElement memberShipButtonInCP;

	public void clickContactsModule() {
		Utility.waitForWebElement(driver, contactsModule, 0);
		try {
			util.clickUsingJS(driver, contactsModule);
		} catch (Exception e) {
			System.out.println("js not clicked");
			contactsModule.click();
		}
	}

	public void clickContactsCPAccess() {
		Utility.waitForWebElement(driver, contactAccessCP, 0);
		util.scrollingElementUsingJS(driver, contactAccessCP);
		contactAccessCP.click();
	}

	public void showAllInRealtedQuickLinks() {
		Utility.waitForWebElement(driver, showAllLink, 0);
		showAllLink.click();
	}

	public void getPortalAccessCount() {
		System.out.println("hjg");
		Utility.waitForWebElement(driver, portalAccessCount, 0);
		System.out.println(portalAccessCount.getText());
	}

	public void clickDropDownInActionContainer() {
		Utility.waitForWebElement(driver, dropDownButtonActionContainer, 0);
		dropDownButtonActionContainer.click();
	}

	public void optionsInactionContainer() throws Throwable {
		Thread.sleep(5000);
		log.info(showAllOptionInActionContainer.size());
		for (int i = 0; i < showAllOptionInActionContainer.size(); i++) {
			String option = showAllOptionInActionContainer.get(i).getText();
			log.info(option);
			switch (option) {
			case "Log in to Experience as User":
				showAllOptionInActionContainer.get(i).click();
				log.info("Log in to Experience as User is clicked");
				Thread.sleep(8000);
				System.out.println(portalUserMemberList.size());
				for (int j = 0; j < portalUserMemberList.size(); j++) {
					String options2 = portalUserMemberList.get(j).getText();
					Thread.sleep(3000);
					log.info(options2);
					if (options2.equalsIgnoreCase("Chapters")) {
						portalUserMemberList.get(j).click();
						log.info("chaptersPortal is clicked successfully");
						Thread.sleep(10000);
					} else {
						log.info("chaptersPortal is not clicked successfully");
					}
				}
				break;
			}
		}
	}

	public void clickMyChapterTab() {
		Utility.waitForWebElement(driver, myChapterTab, 0);
		myChapterTab.click();
	}

	public void getComunityGroup() {
		Utility.waitForWebElement(driver, comunityGroupName, 0);
		log.info(comunityGroupName.getText());
		comunityGroupName.click();
		Utility.waitForWebElement(driver, memberShipButtonInCP, 0);
		if (memberShipButtonInCP.isDisplayed()) {
			log.info("memberShip button is displayed");
		}
	}

}
