package oppenheimer.APIRequests;

import cucumber.TestContext;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import oppenheimer.helpers.Context;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


public class CustomeRequest {

    public CustomeRequest() {
        RestAssured.baseURI = "http://localhost:8080/";
    }

    public RequestSpecification getDefaultHeaders() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .build().header("Content-Type", "application/json");
        return requestSpecification;
    }

    public Response postAPICall(String path, String requestBody, HashMap<String, String> headers) {
        return given().spec(getDefaultHeaders())
                .headers(headers)
                .body(requestBody)
                .when()
                .log().all()
                .get(path)
                .thenReturn();
    }

    public Response postAPICall(String path, String requestBody, TestContext testContext) {
        Response response = given().log().all().spec(getDefaultHeaders())
                .body(requestBody)
                .when()
                .post(path)
                .then().log().all().extract().response();
        testContext.getScenarioContext().setContext(Context.API_RESPONSE, response);
        return response;
    }

    public Response getAPICall(String path, TestContext testContext) {
        Response response = given().log().all().spec(getDefaultHeaders())
                .when()
                .get(path)
                .then().log().all().extract().response();
        response.getBody();
        testContext.getScenarioContext().setContext(Context.API_RESPONSE, response);
        return response;
    }

    public Response getAPICall(String path, String requestBody, TestContext testContext) {
        Response response = given().log().all().spec(getDefaultHeaders())
                .body(requestBody)
                .when()
                .get(path)
                .then().log().all().extract().response();
        response.getBody();
        testContext.getScenarioContext().setContext(Context.API_RESPONSE, response);
        return response;
    }

    public Response postAPICall(String path, TestContext testContext) {
        Response response = given().log().all().spec(getDefaultHeaders())
                .when()
                .post(path)
                .then().log().all().extract().response();
        response.getBody();
        testContext.getScenarioContext().setContext(Context.API_RESPONSE, response);
        return response;
    }
}
