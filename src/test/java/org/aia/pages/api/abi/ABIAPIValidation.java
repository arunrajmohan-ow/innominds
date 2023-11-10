package org.aia.pages.api.abi;

import static io.restassured.RestAssured.given;

import org.aia.pages.api.events.FontevaConnection;
import org.aia.utility.DataProviderFactory;
import org.aia.utility.Utility;
import org.openqa.selenium.WebDriver;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ArchitectureBillingIndexAPIValidation {
	WebDriver driver;
	static String ABI_URI = DataProviderFactory.getConfig().getValue("abi_uri_qa");
	static FontevaConnection bt = new FontevaConnection();
	private static final String bearerToken = bt.getbearerToken();

	public ArchitectureBillingIndexAPIValidation(WebDriver Idriver) {
		this.driver = Idriver;
	}

	Utility util = new Utility(driver, 10);
	
	public void uriSetUp() {
				RestAssured.baseURI = ABI_URI;
	}
	
	public Response verifyABISubscription() {
		Response response = given().contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.header("Authorization", "Bearer " + bearerToken)
				.header("Content-Type", ContentType.JSON)
				.header("Accept", ContentType.JSON)
		.when().get(ABI_URI)
				.then().log().all()
				.statusCode(200)
				.extract()
				.response();
		return response;

	}
}
