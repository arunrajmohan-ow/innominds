package org.aia.testcases.events;

import static io.restassured.RestAssured.given;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.aia.utility.DataProviderFactory;
import org.aia.utility.DateUtils;
import org.aia.utility.FailedTestRun;
import org.asynchttpclient.util.Assertions;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners(org.aia.utility.GenerateReportsListener.class)

public class EventAPIValidation {

	static String EVENT_URI = DataProviderFactory.getConfig().getValue("event_uri_qa");
	static FontevaConnection bt = new FontevaConnection();
	private static final String bearerToken = bt.getbearerToken();
	static String eventName="TestEvent_"+DateUtils.getFormatedDate(new Date(), "MMddyyyyHHmmss");
	static String startDate=DateUtils.getFormatedDate(new Date(), "yyyy-MM-dd");
	static String time=DateUtils.getFormatedDate(new Date(), "hh:mm a");
	static String endDate=DateUtils.getFormatedDate(DateUtils.getDateAfter(new Date(), 5),"yyyy-MM-dd");
	static String eventKey=DateUtils.getFormatedDate(new Date(), "MMddyyyyHHmmssa");
	static String body = "{ \"Name\" : "+"\""+eventName+"\","
			+ " \"EventApi__Start_Date__c\" : "+"\""+startDate+"\","
			+ " \"EventApi__Start_Time__c\" : "+"\""+time+"\","
			+ " \"EventApi__End_Time__c\": "+"\""+time+"\","
			+ " \"EventApi__End_Date__c\":  "+"\""+endDate+"\","
			+ " \"EventApi__Event_Category__c\" : \"a1X1U000001UeeCUAS\","
			+ " \"EventApi__Event_Key__c\" : "+"\""+eventKey+"\""
			+ "  }";	
	
	@BeforeTest
	public void uriSetUp() {
		RestAssured.baseURI = DataProviderFactory.getConfig().getValue("event_uri_qa");
	}
	
	@Test(retryAnalyzer=FailedTestRun.class)
	public void cloneEvent(ITestContext context) throws InterruptedException {
		
		Response response = given().contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.header("Authorization", "Bearer " + bearerToken)
									.header("Content-Type", ContentType.JSON)
									.header("Accept", ContentType.JSON)
									.and()
									.body(body)
							.when().post(EVENT_URI)
									.then().log().all()
									.statusCode(201)
									.extract()
									.response();
		
		JsonPath jsonPath = response.jsonPath();
		String eventID = jsonPath.getString("id");
		context.setAttribute("eventId", eventID);
		Thread.sleep(1000);
		//Assertions.assertEquals(201, response.statusCode());

	}
	
	@Test(dependsOnMethods = "cloneEvent", retryAnalyzer=FailedTestRun.class)
	public void verifyEvent(ITestContext context) throws InterruptedException {
		String Id=(String) context.getAttribute("eventId");
		System.out.println("EVENTID: "+Id);
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", "Bearer " + bearerToken)
				.header("Content-Type", ContentType.JSON)
				.header("Accept", ContentType.JSON)
		.when().get(EVENT_URI+"/"+Id)
				.then().log().all()
				.statusCode(200)
				.extract()
				.response();

		//Assertions.assertEquals(200, response.statusCode());
	}
	
	/*
	 * @Test(dependsOnMethods="cloneEvent") public void updateEvent(ITestContext
	 * context) { //Todo }
	 * 
	 * @AfterTest public void logResults(ITestContext context) { //Todo }
	 */
	
	/*
	 * public static void main (String args[]) {
	 * //System.out.println(cloneEvent().asPrettyString());
	 * System.out.println("Id: "+cloneEvent(null).then().contentType(ContentType.
	 * JSON).extract().path("id"));
	 * 
	 * }
	 */
	
}
