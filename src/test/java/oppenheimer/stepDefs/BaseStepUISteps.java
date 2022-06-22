package oppenheimer.stepDefs;

import com.codeborne.selenide.Configuration;
import cucumber.TestContext;
import oppenheimer.helpers.EndPoints;

import static com.codeborne.selenide.Selenide.open;

public class BaseStepUISteps {
    TestContext testConext;

    public BaseStepUISteps(TestContext testContext) {
        testConext = testContext;
        Configuration.browser = "Chrome";
        //Configuration.browserSize="1920x1080";
        Configuration.baseUrl = EndPoints.HOST;
    }

    public void openBrowser(String browser, String url) {
        if (url != null) {
            url = EndPoints.valueOf(url);
        }
        open(url);
    }
}
