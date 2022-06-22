package oppenheimer.stepDefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.TestContext;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import oppenheimer.APIRequests.CustomeRequest;
import oppenheimer.helpers.Context;
import oppenheimer.helpers.EndPoints;
import oppenheimer.pojos.response.TaxReliefResponse;

public class BaseSteps {
    TestContext testConext;

    public BaseSteps(TestContext testContext) {
        testConext = testContext;
    }

    @Before
    public void beforeMethod() {

    }

    @When("^user calls (.*) API end point (.*) with given request body$")
    public void user_calls_getAPI_without_body(String methodType, String apiEndPoint) throws JsonProcessingException {
        CustomeRequest customeRequest = new CustomeRequest();
        if (methodType.equalsIgnoreCase("get")) {
            customeRequest.getAPICall(EndPoints.valueOf(apiEndPoint), testConext);
        } else {
            customeRequest.postAPICall(EndPoints.valueOf(apiEndPoint), testConext);
        }
    }

    @And("^Validate relief API response$")
    public void validate_API_response() throws JsonProcessingException {
        Response response = (Response) testConext.getScenarioContext().getContext(Context.API_RESPONSE);
        TaxReliefResponse[] taxReliefResponses;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            taxReliefResponses = objectMapper.readValue(response.getBody().asString(), TaxReliefResponse[].class);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        testConext.getScenarioContext().setContext(Context.TEX_REFLIEF_RESPONSE, taxReliefResponses);
    }
}
