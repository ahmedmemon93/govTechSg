package oppenheimer.stepDefs;

import cucumber.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import oppenheimer.APIRequests.CustomeRequest;
import oppenheimer.helpers.Context;
import oppenheimer.helpers.EndPoints;
import oppenheimer.pojos.response.WorkingHero;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class InsertRecordApiStepDefs {
    TestContext testConext;
    public InsertRecordApiStepDefs(TestContext testContext){
        testConext = testContext;
    }
    @Given("User creates a API request with following values")
    public void user_creates_a_api_request_with_following_values(DataTable dataTable) throws JsonProcessingException {
        Map<String, String> requestMap = dataTable.asMap();
        CustomeRequest customeRequest = new CustomeRequest();
        WorkingHero workingHero = WorkingHero.builder().birthday(requestMap.get("birthday"))
                .gender(requestMap.get("gender")).name(requestMap.get("name"))
                .natid("natid").salary(requestMap.get("salary"))
                .tax(requestMap.get("tax")).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(workingHero);
        testConext.getScenarioContext().setContext(Context.API_REQUEST,request);
    }

    @Given("User creates an API request with following values")
    public void user_creates_an_api_request_with_following_values(DataTable dataTable) throws JsonProcessingException {
        List<Map<String, String>> requestlistMap = dataTable.asMaps();
        List<Object> listOfHeros = new ArrayList<>();
        for (Map<String, String> requestMap: requestlistMap) {
            WorkingHero workingHero = WorkingHero.builder().birthday(requestMap.get("birthday"))
                    .gender(requestMap.get("gender")).name(requestMap.get("name"))
                    .natid(requestMap.get("natid")).salary(requestMap.get("salary"))
                    .tax(requestMap.get("tax")).build();
            listOfHeros.add(workingHero);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(listOfHeros);
        testConext.getScenarioContext().setContext(Context.API_REQUEST,request);
    }
    @When("^user calls end point (.*) Api with given request body$")
    public void user_calls_insert_api_with_given_request_body(String apiEndPoint) throws JsonProcessingException {
        String request = testConext.getScenarioContext().getContext(Context.API_REQUEST).toString();
        CustomeRequest customeRequest = new CustomeRequest();
        customeRequest.postAPICall(EndPoints.valueOf(apiEndPoint),request, testConext);
    }
    @Then("^API should return status code (.*)$")
    public void api_should_return_status_code(Integer status) {
        Response response = (Response)testConext.getScenarioContext().getContext(Context.API_RESPONSE);
        assertEquals(status,response.getStatusCode(), String.format("Status not matched expected: %s Acutal %s", status,response.getStatusCode()));
    }
    @And("response body should have correct values")
    public void response_body_should_have_correct_values() throws InterruptedException {
        Response response = (Response)testConext.getScenarioContext().getContext(Context.API_RESPONSE);

        System.out.println(response.getBody().asString());
        assertEquals("Alright", response.getBody().asString());
    }

}
