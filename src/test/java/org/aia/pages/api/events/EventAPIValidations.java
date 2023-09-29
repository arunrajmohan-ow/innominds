package org.aia.pages.api.events;

import static io.restassured.RestAssured.given;

import java.util.Date;

import org.aia.pages.events.NewCloneEvents;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.DateUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;


import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;




public class EventAPIValidations {
    
	static Logger log = Logger.getLogger(NewCloneEvents.class);
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
	

	public void uriSetUp() {
		RestAssured.baseURI = DataProviderFactory.getConfig().getValue("event_uri_qa");
	}
	
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

	}
	
	public void verifyEvent(ITestContext context) throws Throwable {
		String Id=context.getAttribute("eventId").toString();
		System.out.println("EVENTID: "+Id);
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.header("Authorization", "Bearer " + bearerToken)
					.header("Content-Type", ContentType.JSON)
					.header("Accept", ContentType.JSON)
				.when().
					get(EVENT_URI+"/"+Id)
				.then().
					log().all()
					.statusCode(200)
					.extract()
					.response();
		JSONObject obj = new JSONObject(response.asString());
		Assert.assertEquals(obj.get("Name").toString(), context.getAttribute("eventName").toString());
		System.out.println("VERIFIED: Event Name - "+obj.get("Name").toString());
		log.info("VERIFIED: Event Name - "+obj.get("Name").toString());

		
//		Assert.assertEquals(, )

	}
	
}
