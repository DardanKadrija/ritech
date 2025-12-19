package com.ritech.tests.cucumber;

import com.ritech.pages.HoversPage;
import com.ritech.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Step definitions for Hovers feature
 */
public class HoversStepDefinitions {
    private HoversPage hoversPage;

    @Before
    public void setUp() {
        DriverManager.initializeDriver();
        hoversPage = new HoversPage();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }

    @Given("I navigate to the hovers page")
    public void i_navigate_to_the_hovers_page() {
        hoversPage.navigateToHoversPage();
    }

    @When("I hover over profile {int}")
    public void i_hover_over_profile(Integer profileIndex) {
        // Convert from 1-based to 0-based index
        hoversPage.hoverOverProfile(profileIndex - 1);
    }

    @Then("the name should appear for profile {int}")
    public void the_name_should_appear_for_profile(Integer profileIndex) {
        // Convert from 1-based to 0-based index
        int index = profileIndex - 1;
        
        // Verify that the name is displayed
        boolean isNameDisplayed = hoversPage.isProfileNameDisplayed(index);
        Assert.assertTrue(isNameDisplayed, 
            "Profile name should be displayed after hovering over profile " + profileIndex);
        
        // Verify that the name is not empty
        String profileName = hoversPage.getProfileName(index);
        Assert.assertNotNull(profileName, "Profile name should not be null");
        Assert.assertFalse(profileName.isEmpty(), "Profile name should not be empty");
        Assert.assertTrue(profileName.contains("user"), 
            "Profile name should contain 'user' text");
    }

    @When("I hover over each profile")
    public void i_hover_over_each_profile() {
        // This step is a placeholder - actual hovering and verification happens in the Then step
        // to ensure each profile's name is verified individually
    }

    @Then("I should verify the name appears for each profile")
    public void i_should_verify_the_name_appears_for_each_profile() {
        int profileCount = hoversPage.getProfileCount();
        
        for (int i = 0; i < profileCount; i++) {
            // Hover over the profile first
            hoversPage.hoverOverProfile(i);
            
            // Small delay to ensure hover action completes
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Verify the name is displayed
            boolean isNameDisplayed = hoversPage.isProfileNameDisplayed(i);
            Assert.assertTrue(isNameDisplayed, 
                "Profile name should be displayed after hovering over profile " + (i + 1));
            
            // Verify the name content
            String profileName = hoversPage.getProfileName(i);
            Assert.assertNotNull(profileName, 
                "Profile name should not be null for profile " + (i + 1));
            Assert.assertFalse(profileName.isEmpty(), 
                "Profile name should not be empty for profile " + (i + 1));
            Assert.assertTrue(profileName.contains("user"), 
                "Profile name should contain 'user' text for profile " + (i + 1));
        }
    }
}
