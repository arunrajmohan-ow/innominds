package org.aia.testcases.events;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.aia.utility.DataProviderFactory;
import org.aia.utility.DateUtils;
import org.aia.utility.FailedTestRun;
import org.aia.utility.GenerateReports;
import org.aia.utility.GenerateReportsListener;
import org.aia.utility.Utility;
import org.asynchttpclient.util.Assertions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Listeners(org.aia.utility.GenerateReportsListener.class)

public class TestEventCreation extends GenerateReportsListener {
	
	static Response response;
	static JSONObject jo;
	static Map<String, Object> responseMap=new HashMap<String,Object>();
	static String EVENT_URI = DataProviderFactory.getConfig().getValue("event_uri_qa");
	static FontevaConnection bt = new FontevaConnection();
	private static final String bearerToken = bt.getbearerToken();
	static String eventName = "TestEvent_" + DateUtils.getFormatedDate(new Date(), "MMddyyyyHHmmss");
	static String startDate = DateUtils.getFormatedDate(new Date(), "yyyy-MM-dd");
	static String time = DateUtils.getFormatedDate(new Date(), "hh:mm a");
	static String endDate = DateUtils.getFormatedDate(DateUtils.getDateAfter(new Date(), 5), "yyyy-MM-dd");
	static String eventKey = DateUtils.getFormatedDate(new Date(), "MMddyyyyHHmmssa");
	static String body = "{ \"Name\" : " + "\"" + eventName + "\"," + " \"EventApi__Start_Date__c\" : " + "\""
			+ startDate + "\"," + " \"EventApi__Start_Time__c\" : " + "\"" + time + "\","
			+ " \"EventApi__End_Time__c\": " + "\"" + time + "\"," + " \"EventApi__End_Date__c\":  " + "\"" + endDate
			+ "\"," + " \"EventApi__Event_Category__c\" : \"a1X1U000001UeeCUAS\"," + " \"EventApi__Event_Key__c\" : "
			+ "\"" + eventKey + "\"" + "  }";

	@BeforeMethod
	public void uriSetUp() {
		RestAssured.baseURI = DataProviderFactory.getConfig().getValue("event_uri_qa");
	}

	@Test(retryAnalyzer = FailedTestRun.class)
	public void cloneEvent(ITestContext context) throws InterruptedException {

		response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.header("Authorization", "Bearer " + bearerToken).header("Content-Type", ContentType.JSON)
				.header("Accept", ContentType.JSON).and().body(body).when().post(EVENT_URI).then().log().all()
				.statusCode(201).extract().response();

		JsonPath jsonPath = response.jsonPath();
		String eventID = jsonPath.getString("id");
		context.setAttribute("eventId", eventID);
		}

	@Test(dependsOnMethods = "cloneEvent", retryAnalyzer = FailedTestRun.class)
	public void verifyEvent(ITestContext context) throws InterruptedException {
		String Id = (String) context.getAttribute("eventId");
		System.out.println("EVENTID: " + Id);
		response = given().contentType(ContentType.JSON).accept(ContentType.JSON)	
				.header("Authorization", "Bearer " + bearerToken).header("Content-Type", ContentType.JSON)
				.header("Accept", ContentType.JSON).when().get(EVENT_URI + "/" + Id).then().log().all().statusCode(200)
				.extract().response();
		JSONObject jo = new JSONObject(response.getBody().asString());
		for(Map.Entry<String, Object> entry:Utility.parseJSONObjectToMap(jo).entrySet()) {context.setAttribute(entry.getKey(), entry.getValue().toString());}
		report.logTestInfo("EventId: "+(String) context.getAttribute("Id"));
		report.logTestInfo("Name: "+(String) context.getAttribute("Name"));
		report.logTestInfo("OwnerId: "+(String) context.getAttribute("OwnerId"));
		report.logTestInfo("StartDate: "+(String) context.getAttribute("EventApi__Start_Date__c"));
		report.logTestInfo("EndDate: "+(String) context.getAttribute("EventApi__End_Date__c"));
		report.logTestInfo("CreatedById: "+(String) context.getAttribute("CreatedById"));
		report.logTestInfo("LastModified Date: "+(String) context.getAttribute("LastModifiedDate"));
		report.logTestInfo("LastModifiedById: "+(String) context.getAttribute("LastModifiedById"));
		Assert.assertEquals(eventName, context.getAttribute("Name"));
		Assert.assertEquals(startDate, context.getAttribute("EventApi__Start_Date__c"));
		Assert.assertEquals(endDate, context.getAttribute("EventApi__End_Date__c"));
		Assert.assertEquals(eventKey, context.getAttribute("EventApi__Event_Key__c"));	
	}

	@AfterMethod 
	public void parseResponse(ITestContext context) {
		System.out.println("@AfterMethod");  
	  }
	 
	
	}

