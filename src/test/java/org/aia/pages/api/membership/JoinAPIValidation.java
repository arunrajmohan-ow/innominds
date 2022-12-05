package org.aia.pages.api.membership;

import java.util.HashMap;
import java.util.Map;

import org.aia.utility.DataProviderFactory;
import org.aia.utility.Utility;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JoinAPIValidation 
{
	WebDriver driver;
	public JoinAPIValidation(WebDriver Idriver) 
	{
		this.driver = Idriver;
	}
	Utility util = new Utility(driver, 10);

	String PARAMETERIZED_SEARCH_URI = DataProviderFactory.getConfig().getValue("parameterizedSearch_uri");
	String ACCOUNT_URI = DataProviderFactory.getConfig().getValue("account_uri");
	String SALES_ORDER_URI = DataProviderFactory.getConfig().getValue("account_uri");
	String RECCIPT_URI = DataProviderFactory.getConfig().getValue("account_uri");
	int totalMembershipCount = 0;
	JsonPath jsonPathEval = null;
	int retryCount = 0;
	private static String accountID = null;
	
	FontevaConnection bt = new FontevaConnection(); 
	private static final String bearerToken = DataProviderFactory.getConfig().getValue("access_token");//bt.getbearerToken();;
	
	public void verifyMemebershipCreation(String memberAccount, String enddate, Object dues, String type) 
			throws InterruptedException	
	{
		// GET Account ID
	    while ((totalMembershipCount == 0) && retryCount < 10) {
	    	Response response = 
	    	 given().
			 contentType(ContentType.JSON).
			 accept(ContentType.JSON).
			 header("Authorization", "Bearer " + bearerToken).
			 header("Content-Type",ContentType.JSON).
			 header("Accept",ContentType.JSON).
			 param("q", memberAccount).
			 param("sobject", "Account").
			 when().get(PARAMETERIZED_SEARCH_URI).
			 then().statusCode(200).extract().response();
	    	
			jsonPathEval = response.jsonPath();
			accountID = jsonPathEval.getString("searchRecords[0].Id");
			
			// Use Account ID to fetch account details.
			String SUBSCRIPTIONS_URI = ACCOUNT_URI + "/" + accountID + "/OrderApi__Subscriptions__r";
			
			response = 
			    	 given().
					 header("Authorization", "Bearer " + bearerToken).
					 header("Content-Type",ContentType.JSON).
					 header("Accept",ContentType.JSON).
					 param("fields", "OrderApi__Term_Start_Date__c,"
					 		+ "OrderApi__Term_End_Date__c,"
					 		+ "OrderApi__Term_Dues_Total__c,"
					 		+ "Membership_Type__c,"
					 		+ "OrderApi__Status__c,"
					 		+ "OrderApi__Activated_Date__c,"
					 		+ "OrderApi__Paid_Through_Date__c,"
					 		+ "OrderApi__Days_To_Lapse__c").
					 when().get(SUBSCRIPTIONS_URI).
					 then().statusCode(200).extract().response();
	
			jsonPathEval = response.jsonPath();
	
			totalMembershipCount = jsonPathEval.getInt("totalSize");
			Thread.sleep(10000);
			retryCount = retryCount + 1;
		}
	
	    // Verify if totalMembershipCount is 1 , then account creation was success.
		if (totalMembershipCount > 0) {
			System.out.println("Number of Memberships : " + totalMembershipCount);
			String termStartDate = jsonPathEval.getString("records[0].OrderApi__Term_Start_Date__c");
			String termEndDate = jsonPathEval.getString("records[0].OrderApi__Term_End_Date__c");
			String activatedDate = jsonPathEval.getString("records[0].OrderApi__Activated_Date__c");
			String paidThroughDate = jsonPathEval.getString("records[0].OrderApi__Paid_Through_Date__c");
			Object lapseDays = jsonPathEval.getDouble("records[0].OrderApi__Days_To_Lapse__c");
	
			Double termDues = jsonPathEval.getDouble("records[0].OrderApi__Term_Dues_Total__c");
			String membershipType = jsonPathEval.getString("records[0].Membership_Type__c");
			String membershipStatus = jsonPathEval.getString("records[0].OrderApi__Status__c");
	
			System.out.println("=====================================");
			System.out.println("Membership type :" + membershipType);
			System.out.println("Membership start date :" + termStartDate);
			System.out.println("Membership end date :" + termEndDate);
			System.out.println("Membership term dues :" + termDues);
			System.out.println("=====================================");
	
			assertEquals(membershipStatus, "Active");
			assertEquals(membershipType, type);
			assertEquals(termEndDate, enddate);
			assertEquals(termStartDate, "2022-11-28");//java.time.LocalDate.now().toString());
			assertEquals(activatedDate, "2022-11-28");//java.time.LocalDate.now().toString());
			assertEquals(paidThroughDate, enddate);//java.time.LocalDate.now().toString());
			assertEquals(lapseDays, 398.0);
			
			//assertSame(termDues, dues);
			
		} else {
			System.out.println("No active memberships found!!!");
		}
   }
	
	public void verifySalesOrder(String orderPaidStatus, String closed, Object dues, String posted) 
			throws InterruptedException	{
		// Use Account ID to fetch account details.
		String SALESORDER_URI = ACCOUNT_URI + "/" + accountID + "/OrderApi__Sales_Orders__r";
		
		Response response = 
		    	 given().
				 header("Authorization", "Bearer " + bearerToken).
				 header("Content-Type",ContentType.JSON).
				 header("Accept",ContentType.JSON).
				 param("fields", "OrderApi__Sales_Order_Status__c,"
				 		+ "OrderApi__Status__c,"
				 		+ "OrderApi__Posting_Status__c,"
				 		+ "OrderApi__Amount_Paid__c,"
				 		+ "OrderApi__Paid_Date__c").
				 when().get(SALESORDER_URI).
				 then().statusCode(200).extract().response();

		jsonPathEval = response.jsonPath();
		int totalSalesOrderCount = jsonPathEval.getInt("totalSize");
		
		if (totalSalesOrderCount > 0) {
			System.out.println("Number of Sales order : " + totalSalesOrderCount);
			String closedStatus = jsonPathEval.getString("records[0].OrderApi__Status__c");
			String salesOrderStatus = jsonPathEval.getString("records[0].OrderApi__Sales_Order_Status__c");
			String postingStatus = jsonPathEval.getString("records[0].OrderApi__Posting_Status__c");
			Object amountPaid = jsonPathEval.getDouble("records[0].OrderApi__Amount_Paid__c");
			String salesOrderPaidDate = jsonPathEval.getString("records[0].OrderApi__Paid_Date__c");
	
			System.out.println("=====================================");
			System.out.println("Status :" + closedStatus);
			System.out.println("Status of Sales orders :" + salesOrderStatus);
			System.out.println("Sales orders Posting Status :" + postingStatus);
			System.out.println("Sales orders amount paid :" + amountPaid);
			System.out.println("Sales orders paid date :" + salesOrderPaidDate);
			System.out.println("=====================================");
	
			assertEquals(salesOrderStatus, "Paid");
			assertEquals(closedStatus, "Closed");
			assertEquals(postingStatus, "Posted");
			assertEquals(amountPaid, 638.0);
			assertEquals(salesOrderPaidDate, "2022-11-28");//java.time.LocalDate.now().toString());
			
		} 
		else {
			System.out.println("No Sales order found!!!");
		}
	}
	
	public void verifyReciptDetails(String receipt, Object feePaid) throws InterruptedException
	{
		// Use Account ID to fetch account details.
		String RECIPTS_URI = ACCOUNT_URI + "/" + accountID + "/OrderApi__Receipts__r";
		
		Response response = 
		    	 given().
				 header("Authorization", "Bearer " + bearerToken).
				 header("Content-Type",ContentType.JSON).
				 header("Accept",ContentType.JSON).
				 param("fields", "Name,"
				 		+ "OrderApi__Total__c").
				 when().get(RECIPTS_URI).
				 then().statusCode(200).extract().response();

		jsonPathEval = response.jsonPath();
		int totalReciptCount = jsonPathEval.getInt("totalSize");
		
		if (totalReciptCount > 0) {
			System.out.println("Number of Recipt : " + totalReciptCount);
			String receiptNumber = jsonPathEval.getString("records[0].Name");
			Object totalFeePaid = jsonPathEval.getDouble("records[0].OrderApi__Total__c");
	
			System.out.println("=====================================");
			System.out.println("Receipt number :" + receiptNumber);
			System.out.println("Total fee paid :" + totalFeePaid);
			System.out.println("=====================================");
	
			assertEquals(receiptNumber, "0000105204");
			assertEquals(totalFeePaid, 638.0);
			
		} 
		else {
			System.out.println("No Recipt found!!!");
		}
	}
}