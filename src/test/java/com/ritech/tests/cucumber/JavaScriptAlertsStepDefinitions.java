package com.ritech.tests.cucumber;

import com.ritech.pages.JavaScriptAlertsPage;
import com.ritech.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Step definitions for JavaScript Alerts feature
 */
public class JavaScriptAlertsStepDefinitions {
    private JavaScriptAlertsPage javaScriptAlertsPage;

    @Before
    public void setUp() {
        DriverManager.initializeDriver();
        javaScriptAlertsPage = new JavaScriptAlertsPage();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Given("I navigate to the JavaScript alerts page")
    public void i_navigate_to_the_javascript_alerts_page() {
        javaScriptAlertsPage.navigateToAlertsPage();
    }

    @When("I click on the JS Alert button")
    public void i_click_on_the_js_alert_button() {
        javaScriptAlertsPage.clickJSAlert();
    }

    @When("I click on the JS Confirm button")
    public void i_click_on_the_js_confirm_button() {
        javaScriptAlertsPage.clickJSConfirm();
    }

    @When("I click on the JS Prompt button")
    public void i_click_on_the_js_prompt_button() {
        javaScriptAlertsPage.clickJSPrompt();
    }

    @When("I accept the alert")
    public void i_accept_the_alert() {
        javaScriptAlertsPage.acceptJSAlert();
    }

    @When("I accept the confirm alert")
    public void i_accept_the_confirm_alert() {
        javaScriptAlertsPage.acceptJSConfirm();
    }

    @When("I dismiss the confirm alert")
    public void i_dismiss_the_confirm_alert() {
        javaScriptAlertsPage.dismissJSConfirm();
    }

    @When("I enter {string} in the prompt and accept")
    public void i_enter_in_the_prompt_and_accept(String text) {
        javaScriptAlertsPage.acceptJSPrompt(text);
    }

    @When("I dismiss the prompt alert")
    public void i_dismiss_the_prompt_alert() {
        javaScriptAlertsPage.dismissJSPrompt();
    }

    @Then("I should see the result message {string}")
    public void i_should_see_the_result_message(String expectedMessage) {
        Assert.assertTrue(javaScriptAlertsPage.isResultMessageDisplayed(),
            "Result message should be displayed after interacting with alert");

        String actualMessage = javaScriptAlertsPage.getResultMessage();
        Assert.assertEquals(actualMessage, expectedMessage,
            "Result message should match expected message");
    }
}
