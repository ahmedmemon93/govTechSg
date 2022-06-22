package oppenheimer.stepDefs;

import com.codeborne.selenide.Condition;
import cucumber.TestContext;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UIUploadFileSteps extends BaseStepUISteps {
    String filePath = System.getProperty("user.dir") + "/src/test/resources/files/employeeRecords.csv";

    public UIUploadFileSteps(TestContext testContext) {
        super(testContext);
    }

    @And("^User opens (.*) browser and navigates to (.*) page$")
    public void userOpenBrowser(String browser, String url) {
        openBrowser(browser, url);
    }

    @And("User upload a csv file having correct records")
    public void userUploadsFile() {
        $(By.cssSelector("input.custom-file-input")).parent().should(Condition.be(Condition.visible))
                .scrollIntoView(true);
        selectFile(filePath);
        clickOnRefreshTable();
    }

    public void selectFile(String filePath) {
        $(By.cssSelector("input.custom-file-input")).sendKeys(filePath);
    }

    public void clickOnRefreshTable() {
        $(By.cssSelector("button.btn.btn-primary")).shouldBe(Condition.visible)
                .click();
    }

    @And("TableRecords are visible after upload")
    public void tableIsPresentAfterUpload() {
        $(By.cssSelector("button.btn.btn-primary")).shouldBe(Condition.visible)
                .click();
        $(By.cssSelector("table.table-hover.table-dark")).shouldBe(Condition.visible);
    }

    @And("^Dispense button is present in red color with color code (.*)$")
    public void verifyButtonColor(String color) {
        String colorValue = $(By.cssSelector("a.btn.btn-danger.btn-block")).scrollIntoView(true)
                .should(Condition.visible)
                .getCssValue("background-color");
        assertTrue(colorValue.equals(color), "button color is not matching actual is " + colorValue);
    }
}
