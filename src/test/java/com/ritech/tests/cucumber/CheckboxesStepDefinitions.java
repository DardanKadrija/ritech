package com.ritech.tests.cucumber;

import com.ritech.pages.CheckboxesPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Step definitions for Checkboxes feature
 */
public class CheckboxesStepDefinitions {
    private CheckboxesPage checkboxesPage;

    @Given("I navigate to the checkboxes page")
    public void i_navigate_to_the_checkboxes_page() {
        checkboxesPage = new CheckboxesPage();
        checkboxesPage.navigateToCheckboxesPage();
    }

    @When("I select checkbox {int}")
    public void i_select_checkbox(Integer checkboxNumber) {
        checkboxesPage.selectCheckboxByNumber(checkboxNumber);
    }

    @Then("checkbox {int} should be checked")
    public void checkbox_should_be_checked(Integer checkboxNumber) {
        boolean isChecked = checkboxesPage.isCheckboxCheckedByNumber(checkboxNumber);
        Assert.assertTrue(isChecked, 
            "Checkbox " + checkboxNumber + " should be checked");
    }
}
