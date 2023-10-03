package org.aia.pages.events;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.aia.utility.ConfigDataProvider;
import org.aia.utility.Utility;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ViewRecipts {
	WebDriver driver;
	Utility util = new Utility(driver, 30);
	JavascriptExecutor executor;
	ConfigDataProvider testData;
	static Logger log = Logger.getLogger(EventRegistration.class);
	String pdfContent= "";

	public ViewRecipts(WebDriver IDriver) {
		this.driver = IDriver;
		executor = (JavascriptExecutor) driver;
		testData = new ConfigDataProvider();
	}

	@FindBy(xpath = "//body//embed")
	WebElement getReceiptText;

	public void getReceiptBody(String receiptNo, int specTab) throws Throwable {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(specTab));
		Thread.sleep(10000);
		
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("generateMultiplePDF")) {
			URL url = new URL(currentUrl);

			// Open stream method is used to open the pdf file
			InputStream is = url.openStream();

			// using the Buffered input class(creating the object file parse)
			BufferedInputStream fileParse = new BufferedInputStream(is);

			// PD document is coming from PDF box
			PDDocument document = null;

			// Initialize the document from load method(load buffered input class)
			document = PDDocument.load(fileParse);

			// creating object he he & returning the content
			PDFTextStripper strip = new PDFTextStripper();

			strip.setStartPage(1);
			pdfContent = strip.getText(document);

			// Printing the content on console
			System.out.println(pdfContent);
		}
		Assert.assertTrue(pdfContent.contains("Total: $400.00"));;
		log.info("verified total amount in receipts");
		
		//pending validations
//	  Assert.assertTrue(pdfContent.contains(receiptNo.replace("#", "")));

	}
}
