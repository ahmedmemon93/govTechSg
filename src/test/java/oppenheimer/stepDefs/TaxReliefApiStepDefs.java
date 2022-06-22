package oppenheimer.stepDefs;

import cucumber.TestContext;
import io.cucumber.java.en.And;
import oppenheimer.helpers.Context;
import oppenheimer.pojos.request.WorkingHero;
import oppenheimer.pojos.response.TaxReliefResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaxReliefApiStepDefs {
    TestContext testConext;

    public TaxReliefApiStepDefs(TestContext testContext) {
        this.testConext = testContext;
    }

    public String taxReliefCalculate() {
        final DecimalFormat df = new DecimalFormat("0.00");
        List<WorkingHero> workingHero = (List<WorkingHero>) testConext.getScenarioContext().getContext(Context.WORKINGHERO_OBJECT);
        double salary = Double.valueOf(workingHero.get(0).getSalary());
        double paidtax = Double.valueOf(workingHero.get(0).getTax());
        int age = calcuteAge(workingHero.get(0).getBirthday());
        String gender = workingHero.get(0).getGender();
        int genderBonus = 0;
        double variable = 0;
        if (age <= 18) {
            variable = 1;
        } else if (age <= 35) {
            variable = 0.8;
        } else if (age <= 50) {
            variable = 0.5;
        } else if (age <= 75) {
            variable = 0.367;
        } else if (age >= 76) {
            variable = 0.05;
        }
        if (gender.equalsIgnoreCase("F")) {
            genderBonus = 500;
        }
        BigDecimal taxRelief = new BigDecimal(((salary - paidtax) * variable) + genderBonus);
        Double finalTaxRelief = taxRelief.setScale(2, RoundingMode.CEILING).doubleValue();
        if (finalTaxRelief <= 50.00) {
            return "50.00";
        } else return df.format(finalTaxRelief);
    }

    public int calcuteAge(String dateOfBirthStr) {
        return Period.between(LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ofPattern("ddMMyyyy")), LocalDate.now()).getYears();
    }

    @And("Validate that tax relief return in relief API is calculated correctly")
    public void calculateTaxRelief() {
        TaxReliefResponse[] response = (TaxReliefResponse[]) testConext.getScenarioContext().getContext(Context.TEX_REFLIEF_RESPONSE);
        assertEquals(taxReliefCalculate(), response[0].getRelief(), "Invalid value calcualted for tax relief");
    }

    @And("Validate that natid is masked after 5 characters")
    public void natidFileMaskedValidation() {
        TaxReliefResponse[] response = (TaxReliefResponse[]) testConext.getScenarioContext().getContext(Context.TEX_REFLIEF_RESPONSE);
        for (int i = 0; i < response.length; i++) {
            String maskedString = response[i].getNatid().substring(4);
            Set<Character> storeCharater = new HashSet<>();
            for (char a : maskedString.toCharArray()) {
                storeCharater.add(a);
            }
            assertTrue(storeCharater.size() == 1, "natid not masked correctly " + maskedString);
        }
    }


}
