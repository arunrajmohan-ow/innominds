package org.aia.pages.api.membership;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static io.restassured.RestAssured.given;
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

public class FontevaConnection {

	public String getbearerToken()
    {
        
       String response = 
        given()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .param("username", "integration@aia.org.w21upgrade")
        .param("password", "x9VKwVwkS3G%23")
        .param("client_id", "3MVG9M6Iz6p_Vt2xTZYciaJsvx0U6S.QqfF.W1C7NzWgL9znX.e1uZGmJVykWmkhx3JZHuHb9PITen6MvVs2w")
        .param("client_secret", "08360A8F316178AC28BD0AC9C38837419D1D6A253CCDC5B96611C8C04D70A5D0")
        .param("grant_type", "password")
        .header("Accept", "application/json")
        .when().
        post("https://aia--w21upgrade.my.salesforce.com/services/oauth2/token").
        then().
        extract().path("access_token");
       return response;
    }
}
