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
		URL url = new URL(driver.getCurrentUrl());
		InputStream is = url.openStream();
		BufferedInputStream fileParse = new BufferedInputStream(is);
		PDDocument document = PDDocument.load(fileParse);
		String pdfContent = new PDFTextStripper().getText(document);
		Assert.assertTrue(pdfContent.contains(receiptNo));
//	  util.waitUntilElement(driver, getReceiptText);
//	  String receiptText =	getReceiptText.getText();
//	  System.out.println(receiptText);  
//	  receiptText.contains(receiptNo);
	}
}
