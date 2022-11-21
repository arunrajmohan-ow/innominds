package org.aia.pages.api;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.aia.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Membership_FontevaAPI {

	WebDriver driver;
	
	Utility util = new Utility(driver, 30);
	
	public Membership_FontevaAPI(WebDriver IDriver){
		this.driver = IDriver;
	}

	private static final String bearerToken = "00D7j000000H0DW!ASAAQHTYJ3Z1fOBDeMUmLz1fMYmEd1VwITRJv0FENolz4XRi_5AMnn_DslBj11qvT0ruDQB9AJTHnFpiU9D8bqLTV965GzBA";

	// TEST DATA
	String memberAccount = "automation988";
	String w21MembershipPortalUrl = "https://stg-membership.aia.org";
	String memberEmail = memberAccount + "@architects-team.m8r.co";
	String PARAMETERIZED_SEARCH_URI = "https://aia--w21upgrade.my.salesforce.com/services/data/v56.0/parameterizedSearch";
	String ACCOUNT_URI = "https://aia--w21upgrade.my.salesforce.com/services/data/v56.0/sobjects/Account";
	
	int totalMembershipCount = 0;
	JsonPath jsonPathEval = null;
	int retryCount = 0;

	
	// it takes some time to load details on fonteva after subscription.
	public void validate_membershipaccountdetails() throws InterruptedException {
	while ((totalMembershipCount == 0) && retryCount < 10) {
		Response response = RestAssured.given()
				.headers("Authorization", "Bearer " + bearerToken, "Content-Type", ContentType.JSON, "Accept",
						ContentType.JSON)
				.param("q", memberAccount).param("sobject", "Account").when().get(PARAMETERIZED_SEARCH_URI).then()
				.contentType(ContentType.JSON).extract().response();

		jsonPathEval = response.jsonPath();

		String accountID = jsonPathEval.getString("searchRecords[0].Id");

		String SUBSCRIPTIONS_URI = ACCOUNT_URI + "/" + accountID + "/OrderApi__Subscriptions__r";

		response = RestAssured.given()
				.headers("Authorization", "Bearer " + bearerToken, "Content-Type", ContentType.JSON, "Accept",
						ContentType.JSON)
				.param("fields",
						"OrderApi__Term_Start_Date__c,OrderApi__Term_End_Date__c,OrderApi__Term_Dues_Total__c,Membership_Type__c")
				.when().get(SUBSCRIPTIONS_URI).then().contentType(ContentType.JSON).extract().response();

		jsonPathEval = response.jsonPath();

		totalMembershipCount = jsonPathEval.getInt("totalSize");
		Thread.sleep(10000);
		retryCount = retryCount + 1;
	}

	if (totalMembershipCount > 0) {
		System.out.println("Number of Memberships : " + totalMembershipCount);
		String termStartDate = jsonPathEval.getString("records[0].OrderApi__Term_Start_Date__c");
		String termEndDate = jsonPathEval.getString("records[0].OrderApi__Term_End_Date__c");

		Double termDues = jsonPathEval.getDouble("records[0].OrderApi__Term_Dues_Total__c");
		String membershipType = jsonPathEval.getString("records[0].Membership_Type__c");

		System.out.println("=====================================");
		System.out.println("Membership type :" + membershipType);
		System.out.println("Membership start date :" + termStartDate);
		System.out.println("Membership end date :" + termEndDate);
		System.out.println("Membership term dues :" + termDues);
		System.out.println("=====================================");

		assertEquals(termStartDate, java.time.LocalDate.now().toString());
		assertEquals(termEndDate, "2023-12-31");
		//assertEquals(termDues, 498.0);
		assertEquals(membershipType, "AIA National");
	} else {
		System.out.println("No active memberships found!!!");
	}
}
	
}
