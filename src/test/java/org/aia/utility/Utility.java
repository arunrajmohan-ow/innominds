package org.aia.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {
	
	
	WebDriverWait wait;
	
	public Utility(WebDriver driver,int time) 
	{
		
	}
	
	
	public  void acceptAlert()
	{
		wait.until(ExpectedConditions.alertIsPresent());
		//wait.until(Exp)
		//wait.until(ExpectedConditions.alertIsPresent()).accept();
	}
	
	public void switchToFrame(String frameText)
	{
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameText));
	}
	
	public void switchToFrame(WebElement frameElement)
	{
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	public  void dismissAlert()
	{
		wait.until(ExpectedConditions.alertIsPresent()).dismiss();
	}
	
	public String getAlertText()
	{
		return wait.until(ExpectedConditions.alertIsPresent()).getText();
	}
	
	public static String captureScreenshot(WebDriver driver)
	{
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		
		String path=System.getProperty("user.dir")+"\\Screenshots\\"+"AIA"+getCurrentDateTime()+".png";
		
		try 
		{
			FileHandler.copy(ts.getScreenshotAs(OutputType.FILE),new File(path));
		} 
		catch (WebDriverException e) 
		{
		System.out.println("Unable to capture screenshots "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Unable to capture screenshots "+e.getMessage());

		}
		
		return path;
	}
	
	public static String captureScreenshotFromBase64(WebDriver driver)
	{
	
		String newBase=null;
		TakesScreenshot ts=(TakesScreenshot)driver;
				
		try 
		{

	String mybase=	ts.getScreenshotAs(OutputType.BASE64);
		
	newBase="data:image/png;base64,"+mybase;
	
	System.out.println(mybase);
	
		} 
		catch (WebDriverException e) 
		{
		System.out.println("Unable to capture screenshots "+e.getMessage());
		} 
		
		return newBase;
	}
	
	
	
	public static String getCurrentDateTime()
	{
	
		DateFormat myCustomDateFormat=new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		
		Date date=new Date();
		
		return myCustomDateFormat.format(date);
	}
	
	
	public boolean waitForWebElementDisappear(WebElement ele)
	{
		return wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public boolean waitForWebElementDisappear(String xpath)
	{
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
	}
	
	
	public WebElement waitForPresence(WebDriver driver,String xpath)
	{
		
		WebElement element = null;
		int x = 0;
		
		List<WebElement> elements=driver.findElements(By.xpath(xpath));
		
		if(elements.size()>0)
		{
			x=elements.get(0).getLocation().getX();
		}
	
		while(x>0)
		{
			element=driver.findElements(By.xpath(xpath)).get(0);
			break;
		}
		
		return element;
		
	}
	
	
	public static WebElement waitForWebElement(WebDriver driver,String xpath,int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		WebElement ele=driver.findElement(By.xpath(xpath));
		
		highLightElement(driver, ele);
		
		return ele;
		
	}
	
	public static WebElement waitForWebElement(WebDriver driver,WebElement element,int time)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.visibilityOf(element));
		WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(element));
	
		highLightElement(driver, ele);
		
		return ele;
	}
	
	public void waitUntilElement(WebDriver driver, WebElement element) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	
	public static void highLightElement(WebDriver driver, WebElement element)
	{
		
	JavascriptExecutor js=(JavascriptExecutor)driver; 
	 
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	 
	try 
	{
	Thread.sleep(1000);
	} 
	catch (InterruptedException e) {
	 
	System.out.println(e.getMessage());
	} 
	 
	js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 
	 
	}
	
	
}
