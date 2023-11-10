package org.aia.pages.abi;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.aia.pages.api.MailinatorAPI;
import org.aia.pages.api.abi.ABIAPIValidation;
import org.aia.utility.ConfigDataProvider;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Utility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ABISignUpPage {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	ConfigDataProvider data = new ConfigDataProvider();
	ABIAPIValidation apiValidation;
	MailinatorAPI mailinator = new MailinatorAPI(driver);

	public ABISignUpPage(WebDriver Idriver) {
		this.driver = Idriver;
	}

	@FindBy(xpath = "//a[@title='Log In']")
	WebElement loginBtn;

	@FindBy(xpath = "//a[@routerlink='/signup']")
	WebElement signUpLink;

	@FindBy(xpath = "//input[@formcontrolname='username']")
	WebElement emailTxt;

	@FindBy(xpath = "//input[@formcontrolname='password']")
	WebElement passwordTxt;

	@FindBy(xpath = "//span[text()='Sign In']")
	WebElement signInBtn;

	@FindBy(xpath = "//div[@class='pfm-tile-img']")
	WebElement abiImage;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement autoRenewChkBx;

	@FindBy(xpath = "//button[text()='Continue']")
	WebElement continueBtn;

	@FindBy(xpath = "//button[@class='slds-button slds-button_brand pfm-button']")
	WebElement addToCart;

	@FindBy(xpath = "//div[@data-aura-class='LTEShoppingCartIcon']")
	WebElement cartIcon;

	@FindBy(xpath = "//button[text()='View Cart']")
	WebElement viewCart;

	@FindBy(xpath = "//button[@data-name='checkoutButton']")
	WebElement checkOut;

	@FindBy(xpath = "//div[@data-name='mainPopupKnownAddressDiv']//button[@data-name='addressCreateButton']")
	WebElement createAddress;

	@FindBy(xpath = "//div[@data-name='mainPopupKnownAddressDiv']//input[@type='text' and starts-with(@class,'slds-input')]")
	WebElement name;

	@FindBy(xpath = "//select[@name='Type']")
	WebElement type;

	@FindBy(xpath = "//input[@aria-label='Enter your address']")
	WebElement addresstxt;

	@FindBy(xpath = "//h2[text()='New Address']//parent::div//following-sibling::div[contains(@class,'l__footer')]//button[text()='Save']")
	WebElement saveBtn;

	@FindBy(xpath = "//button[text()='Proceed to Checkout']")
	WebElement proceedToCheckOut;
	
	@FindBy(xpath = "//iframe[@title='Credit Card Input Frame']")
	WebElement iFrame1;
	
	@FindBy(xpath = "//iframe[@title='Card number']")
	WebElement iFrame2;
	

	@FindBy(xpath = "//input[@id='card_number']")
	WebElement ccNumberTxt;
	
	@FindBy(xpath = "//select[@name='Exp year']")
	WebElement expYear;

	@FindBy(xpath = "//button[@aria-label='Process payment']")
	WebElement processPymtBtn;

	@FindBy(xpath = "//span[text()='View Receipt']")
	WebElement viewReceipt;
	
	@FindBy(xpath = "//button[text()='Login']")
	WebElement pymtLoginBtn;

	public void clickLoginLink() throws Exception {
		util.waitUntilElement(driver, loginBtn);
		loginBtn.click();
	}
	
	public void goToSignUpLink() throws Exception {
		util.waitUntilElement(driver, loginBtn);
		loginBtn.click();
		util.waitUntilElement(driver, signUpLink); 
		signUpLink.click();
	}

	@SuppressWarnings("static-access")
	public void signInUser(String email, String password) throws Exception {
		util.waitForWebElement(driver, emailTxt, 3000);
		emailTxt.sendKeys(email);
		util.waitForWebElement(driver, passwordTxt,3000);
		passwordTxt.sendKeys(password);
		util.waitForWebElement(driver, signInBtn, 3000);
		signInBtn.click();
		util.waitForPageLoad(driver);
        }

	@SuppressWarnings("static-access")
	public ArrayList<String> getPDFContent() throws URISyntaxException, InterruptedException, IOException {
		List<String> pdfDetails=new ArrayList<String>();
		Scanner scnr;
		util.waitForWebElement(driver, viewReceipt, 5000);
		viewReceipt.click();
		Thread.sleep(10000);
		Set<String> links = driver.getWindowHandles();
		String currWin = driver.getWindowHandle();
		Thread.sleep(10000);
		for (String s1 : links)
			if (!s1.contentEquals(currWin)) {
				driver.switchTo().window(s1);
				String currentUrl = driver.getCurrentUrl();
				System.out.println("currentUrl: "+currentUrl);
				if (currentUrl.contains("signupSuccess")) {
					continue;
				} else if (currentUrl.contains("generateMultiplePDF")) {
					//String path = "https://fonteva-io.herokuapp.com/generateMultiplePDF/dev/join?doc=https%3A%2F%2Faia--testing.sandbox.my.site.com%2Fecommerce%2Fs%2Freceipt%3FgeneratePDF%3Dtrue%26language%3Den_US%26id%3DKEQx4mKsEnk5PdW3nZTE6QhNQlxGVAEKZBcZr5Jgm8g%3D&doc=https%3A%2F%2Faia--testing.sandbox.my.site.com%2Fecommerce%2Fs%2Fsales-order%3FgeneratePDF%3Dtrue%26language%3Den_US%26id%3DHQEJO7wHomVY0Q52c561YthzO-2j6JVFxpaBvgSO75o%3D";
					URI uri=new URI(currentUrl);
					URL url=uri.toURL();
					InputStream is = url.openStream();
					BufferedInputStream fileParse = new BufferedInputStream(is);
					PDDocument document = null;
					document = PDDocument.load(fileParse);
					PDFTextStripper strip = new PDFTextStripper();
					strip.setStartPage(2);
					String pdfContent = strip.getText(document);
					System.out.println("*********************************************************************************************");
					System.out.println("pdfContent: "+pdfContent);
					System.out.println("*********************************************************************************************");
					scnr=new Scanner(pdfContent);
					while (scnr.hasNextLine()) {
						String line = scnr.nextLine();
						String[] matches=new String[] {"Sales Order #", "NY CITY TAX $", "NY SPECIAL TAX $", "NY STATE TAX $", "Subtotal $", "Taxes $", "Total $"};
						for(String match:matches) {
						if(line.contains(match)){
							//line.replaceAll("[^A-Za-z0-9]","");
							pdfDetails.add(line);
												}
											}
									}
									scnr.close();
								}
							}
						return (ArrayList<String>) pdfDetails;
						}
	
	
	@SuppressWarnings({ "static-access", "null" })
	public ArrayList<String> subscribeToABI(String user, String address ) throws InterruptedException, URISyntaxException, IOException {
		util.waitForWebElement(driver, abiImage, 5000);
		abiImage.click();
		util.waitForWebElement(driver, autoRenewChkBx, 5000);
		util.waitForWebElement(driver, continueBtn, 5000);
		continueBtn.click();
		Thread.sleep(10000);
		util.waitForWebElement(driver, addToCart, 5000);
		addToCart.click();
		Thread.sleep(10000);	
		util.waitForWebElement(driver, cartIcon, 5000); cartIcon.click();
		/*
		 * util.waitForWebElement(driver, cartIcon, 5000); cartIcon.click();
		 * util.waitForWebElement(driver, viewCart, 5000); viewCart.click();
		 * util.waitForWebElement(driver, addToCart, 5000); addToCart.click();
		 */
		util.waitForWebElement(driver, checkOut, 5000);
		checkOut.click();
		util.waitForWebElement(driver, createAddress, 5000);
		createAddress.click();
		util.waitForWebElement(driver, name, 5000);
		name.sendKeys(user);
		util.waitForWebElement(driver, type, 5000);
		util.selectDrp(type).selectByValue("Home");
		util.waitForWebElement(driver, addresstxt, 5000);
		// 1735 York Avenue, NewYork, NY, USA
		addresstxt.sendKeys(address+Keys.ENTER);
		Thread.sleep(3000);
		addresstxt.sendKeys(Keys.ENTER);
		util.waitForWebElement(driver, saveBtn, 5000);
		saveBtn.click();
		util.waitForWebElement(driver, proceedToCheckOut, 5000);
		proceedToCheckOut.click();
		
		Thread.sleep(3000);
		util.waitForWebElement(driver, iFrame1, 5000);
		driver.switchTo().frame(iFrame1);
		
		util.waitForWebElement(driver, iFrame2, 5000);
		driver.switchTo().frame(iFrame2);
		
		util.waitForWebElement(driver, ccNumberTxt, 5000);
		ccNumberTxt.sendKeys(DataProviderFactory.getConfig().getValue("ccNumber"));
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		util.waitForWebElement(driver, expYear, 5000);
		util.selectDrp(expYear).selectByValue("2024");
		Thread.sleep(3000);

		util.waitForWebElement(driver, processPymtBtn, 5000);
		processPymtBtn.click();
		Thread.sleep(3000);

		ArrayList<String> salesOrderDetails=getPDFContent();
		String salesOrderId=salesOrderDetails.get(0).replaceAll("[^A-Za-z0-9]","").substring(10);

		System.out.println("******************PDF Details*****************");
		salesOrderDetails.set(0,"SalesOrder # "+salesOrderId);
		for(String pdfDetail:salesOrderDetails) {System.out.println(pdfDetail);}
		System.out.println("**********************************************");


		//HashMap<String,HashMap<String, Object>> salesOrderReport=new HashMap<String,HashMap<String, Object>>();
		//HashMap<String, Object> pdfDetails = new HashMap<String, Object>();
		//salesOrderReport.put(salesOrderId, pdfDetails);
		return salesOrderDetails;
		
		}
	
		@SuppressWarnings("static-access")
		public void validateSubscriptionDetails(ArrayList<String> pdfDetails) throws URISyntaxException, InterruptedException, IOException {	
			HashMap<String, Object> apiDetailsMap=new HashMap<String, Object>();
			System.out.println("SalesORDERId: "+pdfDetails.get(0).substring(13));
			apiDetailsMap=apiValidation.getSalesOrderDetails(pdfDetails.get(0).substring(13));
			System.out.println("******************API Details*****************");
			Iterator<Map.Entry<String,Object>> itr=apiDetailsMap.entrySet().iterator();
			while (itr.hasNext()){
				Map.Entry<String, Object> e=itr.next();
				System.out.println(e.getKey()+" : "+String.valueOf(e.getValue()));
				if(e.getKey().contains("AIA/Deltek")){Assert.assertEquals(pdfDetails.get(pdfDetails.indexOf("Subtotal $369.00")).substring(10,15), String.valueOf(e.getValue()));}
				if(e.getKey().contains("STATE TAX")){Assert.assertEquals(pdfDetails.get(pdfDetails.indexOf("NY STATE TAX $14.76")).substring(14,19), String.valueOf(e.getValue()));}
				if(e.getKey().contains("CITY TAX")){Assert.assertEquals(pdfDetails.get(pdfDetails.indexOf("NY CITY TAX $16.61")).substring(13,18), String.valueOf(e.getValue()));}
				if(e.getKey().contains("SPECIAL TAX")){Assert.assertEquals(pdfDetails.get(pdfDetails.indexOf("NY SPECIAL TAX $1.38")).substring(16,20), String.valueOf(e.getValue()));}
			}
			System.out.println("**********************************************");
		}
	
	@SuppressWarnings("static-access")
	public ArrayList<String> proceedToCheckOut() throws InterruptedException, URISyntaxException, IOException {
		util.waitForWebElement(driver, proceedToCheckOut, 5000);
		proceedToCheckOut.click();
		
		util.waitForPageLoad(driver);
		util.waitForWebElement(driver, iFrame1, 5000);
		driver.switchTo().frame(iFrame1);
		
		util.waitForWebElement(driver, iFrame2, 5000);
		driver.switchTo().frame(iFrame2);
		
		util.waitForWebElement(driver, ccNumberTxt, 5000);
		ccNumberTxt.sendKeys("4111 1111 1111 1111");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		
		util.waitForWebElement(driver, expYear, 5000);
		util.selectDrp(expYear).selectByValue("2024");
		Thread.sleep(3000);
		util.waitForWebElement(driver, processPymtBtn, 5000);
		processPymtBtn.click();
		
		ArrayList<String> salesOrderDetails=new ArrayList<String>();
		salesOrderDetails=getPDFContent();
		String salesOrderId=salesOrderDetails.get(0).replaceAll("[^A-Za-z0-9]","").substring(10);

		System.out.println("******************PDF Details*****************");
		salesOrderDetails.set(0,"SalesOrder # "+salesOrderId);
		for(String pdfDetail:salesOrderDetails) {System.out.println(pdfDetail);}
		System.out.println("**********************************************");


		//HashMap<String,HashMap<String, Object>> salesOrderReport=new HashMap<String,HashMap<String, Object>>();
		//HashMap<String, Object> pdfDetails = new HashMap<String, Object>();
		//salesOrderReport.put(salesOrderId, pdfDetails);
		return salesOrderDetails;
								}
	@SuppressWarnings("static-access")
	public void processPayment(String email) throws Exception {
		String pymntURL=mailinator.GetLinks(email.substring(0,email.indexOf("@")), "public-store#");
		driver.navigate().to(pymntURL);
		//util.waitForPageLoad(driver);
		util.waitForWebElement(driver, pymtLoginBtn, 5000);
		util.jseClick(driver, pymtLoginBtn);
		//util.waitForPageLoad(driver);
		signInUser(email,"Login_123");										
				}
			}

