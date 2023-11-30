package org.aia.pages.api.membership;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.aia.utility.DataProviderFactory;
import org.aia.utility.Utility;
import org.openqa.selenium.WebDriver;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpgradeMembershipAPIValidation {
	static WebDriver driver;

	public UpgradeMembershipAPIValidation(WebDriver Idriver) {
		this.driver = Idriver;
	}

	Utility util = new Utility(driver, 10);

	String PARAMETERIZED_SEARCH_URI = DataProviderFactory.getConfig().getValue("parameterizedSearch_uri");
	String ACCOUNT_URI = DataProviderFactory.getConfig().getValue("account_uri");
	String SOBJECT_URI = DataProviderFactory.getConfig().getValue("sobject_uri");
	String RECCIPT_URI = DataProviderFactory.getConfig().getValue("account_uri");
	int totalMembershipCount = 0;
	JsonPath jsonPathEval = null;
	JsonPath jsonPath = null;
	int retryCount = 0;
	private static String accountID = null;

	static FontevaConnection bt = new FontevaConnection();
	// private static final String bearerToken =
	// DataProviderFactory.getConfig().getValue("access_token");//bt.getbearerToken();;
	private static final String bearerToken = bt.getbearerToken();

	public void verifyMemebershipCreation(String memberAccount, String membershipCancelledreson)
			throws InterruptedException {
		// GET Account ID
		while ((totalMembershipCount == 0) && retryCount < 10) {
			Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
					.header("Authorization", "Bearer " + bearerToken).header("Content-Type", ContentType.JSON)
					.header("Accept", ContentType.JSON).param("q", memberAccount).param("sobject", "Account").when()
					.get(PARAMETERIZED_SEARCH_URI).then().statusCode(200).extract().response();

			jsonPathEval = response.jsonPath();
			accountID = jsonPathEval.getString("searchRecords[0].Id");
			System.out.println("Account ID:" + accountID);
			// Use Account ID to fetch account details.
			String SUBSCRIPTIONS_URI = ACCOUNT_URI + "/" + accountID + "/OrderApi__Subscriptions__r";
			response = given().header("Authorization", "Bearer " + bearerToken).header("Content-Type", ContentType.JSON)
					.header("Accept", ContentType.JSON).when().get(SUBSCRIPTIONS_URI).then().statusCode(200).extract()
					.response();
			jsonPath = response.jsonPath();
			totalMembershipCount = jsonPath.getInt("totalSize");
			Thread.sleep(10000);
			retryCount = retryCount + 1;
			//verifyActiveMembership();
			verifyCanclledMembership(membershipCancelledreson);
			
		}
	}

	public void verifyCanclledMembership(String membershipCancelledreson) {
		String cancelledMemId = jsonPath.getString("records[0].Id");
		String membershipUri = SOBJECT_URI + "/OrderApi__Subscription__c/" + cancelledMemId;
		Response response = given().header("Authorization", "Bearer " + bearerToken)
				.header("Content-Type", ContentType.JSON).header("Accept", ContentType.JSON).when().get(membershipUri)
				.then().statusCode(200).extract().response();
		jsonPathEval = response.jsonPath();
		boolean isMembershipCancelled = jsonPathEval.getBoolean("OrderApi__Is_Cancelled__c");
		String membershipCancelledDate = jsonPathEval.getString("OrderApi__Cancelled_Date__c");
		String membershipCancelledReason = jsonPathEval.getString("OrderApi__Cancelled_Reason__c");
		String membershipTypeId = jsonPathEval.getString("OrderApi__Item__c");
		// Get MembershipType
		String membershipTyepeUri = SOBJECT_URI + "/OrderApi__Item__c/" + membershipTypeId;
		response = given().header("Authorization", "Bearer " + bearerToken).header("Content-Type", ContentType.JSON)
				.header("Accept", ContentType.JSON).when().get(membershipTyepeUri).then().statusCode(200).extract()
				.response();
		jsonPathEval = response.jsonPath();
		String membershipName = jsonPathEval.getString("Name");
		assertEquals("Associate - National", membershipName);
		assertTrue(isMembershipCancelled);
		assertEquals(membershipCancelledDate, java.time.LocalDate.now().toString());
		assertEquals(membershipCancelledReason, membershipCancelledreson);
	}

	public void verifyActiveMembership() {
		String activeMemId = jsonPath.getString("records[1].Id");
		String membershipUri = SOBJECT_URI + "/OrderApi__Subscription__c/" + activeMemId;
		Response response = given().header("Authorization", "Bearer " + bearerToken)
				.header("Content-Type", ContentType.JSON).header("Accept", ContentType.JSON).when().get(membershipUri)
				.then().statusCode(200).extract().response();
		jsonPathEval = response.jsonPath();
		String membershipTypeId = jsonPathEval.getString("OrderApi__Item__c");
		// Get MembershipType
		String membershipTyepeUri = SOBJECT_URI + "/OrderApi__Item__c/" + membershipTypeId;
		response = given().header("Authorization", "Bearer " + bearerToken).header("Content-Type", ContentType.JSON)
				.header("Accept", ContentType.JSON).when().get(membershipTyepeUri).then().statusCode(200).extract()
				.response();
		jsonPathEval = response.jsonPath();
		String membershipName = jsonPathEval.getString("Name");
		assertEquals("Architect - National", membershipName);
	}
}
