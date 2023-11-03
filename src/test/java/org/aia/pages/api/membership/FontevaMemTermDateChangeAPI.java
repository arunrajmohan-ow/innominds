package org.aia.pages.api.membership;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.aia.utility.DataProviderFactory;
import org.aia.utility.Utility;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.awaitility.core.Condition;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.glassfish.jersey.message.internal.StringHeaderProvider;
import org.openqa.selenium.WebDriver;

import com.sun.jmx.mbeanserver.Util;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.awaitility.Awaitility;
import org.awaitility.Awaitility.*;
import org.awaitility.Duration.*;
import java.util.concurrent.TimeUnit.*;
import org.hamcrest.Matchers.*;
import org.junit.Assert.*;

public class FontevaMemTermDateChangeAPI {
	WebDriver driver;

	public FontevaMemTermDateChangeAPI(WebDriver driver) {
		this.driver = driver;
	}

	Utility util = new Utility(driver, 10);

	static String PARAMETERIZED_SEARCH_URI = DataProviderFactory.getConfig().getValue("parameterizedSearch_uri");
	static String ACCOUNT_URI = DataProviderFactory.getConfig().getValue("account_uri");
	static String sObjectURI = DataProviderFactory.getConfig().getValue("sobject_uri");
	static String sObjectCompositeURI = DataProviderFactory.getConfig().getValue("sObjectURI");
	static FontevaConnection bt = new FontevaConnection();
	private static final String bearerToken = bt.getbearerToken();
	static JsonPath jsonPathEval = null;
	private static String accountID = null;
	private static String providerId = null;
	private static String membershipId = null;

	public void changeTermDateAPI(String memberAccount, String termDate) throws InterruptedException {
		// From this api we get the provider id
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", "Bearer " + bearerToken).header("Content-Type", ContentType.JSON)
				.header("Accept", ContentType.JSON).param("q", memberAccount).param("sobject", "Account").when()
				.get(PARAMETERIZED_SEARCH_URI).then().statusCode(200).extract().response();

		jsonPathEval = response.jsonPath();
		accountID = jsonPathEval.getString("searchRecords[0].Id");
		System.out.println("Account ID:" + accountID);

		// From this API we try to get membership ID
		String SUBSCRIPTIONS_URI = sObjectURI + "/Account/" + accountID + "/OrderApi__Subscriptions__r";
		System.out.println(SUBSCRIPTIONS_URI);

		response = given().header("Authorization", "Bearer " + bearerToken).header("Content-Type", ContentType.JSON)
				.header("Accept", ContentType.JSON).when().get(SUBSCRIPTIONS_URI);
		Utility.waitForResponse(response, 200);
		response.then().statusCode(200).extract().response();
		Thread.sleep(10000);
		jsonPathEval = response.jsonPath();
		membershipId = jsonPathEval.getString("records[0].Id");
		System.out.println("Membership ID:" + membershipId);

		// From this call we are getting the termID
		String selectTermURI = sObjectURI + "/OrderApi__Subscription__c/" + membershipId + "/OrderApi__Renewals__r";
		response = given().header("Authorization", "Bearer " + bearerToken).header("Content-Type", ContentType.JSON)
				.header("Accept", ContentType.JSON).when().get(selectTermURI).then().statusCode(200).extract()
				.response();
		jsonPathEval = response.jsonPath();
		String termId = jsonPathEval.getString("records[0].Id");
		System.out.println("termId ID:" + termId);
		// Here we change the termend date using termID
		response = given().header("Authorization", "Bearer " + bearerToken).header("Content-Type", ContentType.JSON)
				.header("Accept", ContentType.JSON).when()
				.body("{\r\n"
						+ "    \"allOrNone\": false,\r\n"
						+ "    \"records\": [\r\n"
						+ "        {\r\n"
						+ "            \"attributes\": {\r\n"
						+ "                \"type\": \"OrderApi__Renewal__c\"\r\n"
						+ "            },\r\n"
						+ "             \"id\": \""+termId+"\",\r\n"
						+ "            \"OrderApi__Term_End_Date__c\": \""+termDate+"\"\r\n"
						+ "        }\r\n"
						+ "    ]\r\n"
						+ "}")
				.patch(sObjectCompositeURI).then().statusCode(200).extract().response();

	}

	/**
	 * @param expectedAccountStatus
	 * @param expectedMembershipType Here we getting accountStatus and
	 *                               membershipType using accountID
	 * @throws InterruptedException
	 */
	public void getCESAccountDetails(String expectedAccountStatus, String expectedMembershipType)
			throws InterruptedException {
		String AccountDetailsURI = ACCOUNT_URI + "/" + accountID;
		Response response = given().header("Authorization", "Bearer " + bearerToken)
				.header("Content-Type", ContentType.JSON).header("Accept", ContentType.JSON).when()
				.get(AccountDetailsURI).then().statusCode(200).extract().response();
		jsonPathEval = response.jsonPath();
		String cesAccountStatus = jsonPathEval.getString("AIA_CES_Provider_Status__c");
		String cesMembershipType = jsonPathEval.getString("Membership_Type__c");
		assertEquals(cesAccountStatus, expectedAccountStatus);
		assertEquals(cesMembershipType, expectedMembershipType);
		Thread.sleep(3000);
	}

	/**
	 * Here we validate actual membership status.
	 */
	public void validateCESMembershipCreated(String membershipStatus, String membershipData) {
		String selectTermURI = sObjectURI + "/OrderApi__Subscription__c/" + membershipId + "/OrderApi__Renewals__r";
		Response response = given().header("Authorization", "Bearer " + bearerToken)
				.header("Content-Type", ContentType.JSON).header("Accept", ContentType.JSON).when().get(selectTermURI)
				.then().statusCode(200).extract().response();
		jsonPathEval = response.jsonPath();
		String actualmembershipStatus = jsonPathEval.getString("records[1]." + membershipData + "");
		assertEquals(membershipStatus, actualmembershipStatus);

	}

}