package com.ritech.tests.cucumber;

import com.ritech.pages.WindowsPage;
import com.ritech.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Step definitions for Windows feature
 */
public class WindowsStepDefinitions {
    private WindowsPage windowsPage;
    private String originalWindowHandle;

    @After
    public void switchBackToOriginalWindow() {
        // Switch back to original window before closing
        if (originalWindowHandle != null && windowsPage != null) {
            try {
                windowsPage.switchToWindow(originalWindowHandle);
            } catch (Exception e) {
                // If original window is already closed, continue
            }
        }
    }

    @Given("I navigate to the windows page")
    public void i_navigate_to_the_windows_page() {
        windowsPage = new WindowsPage();
        windowsPage.navigateToWindowsPage();
    }

    @When("I click {string} to open a new window")
    public void i_click_to_open_a_new_window(String linkText) {
        // Store the original window handle and count before clicking
        originalWindowHandle = DriverManager.getDriver().getWindowHandle();
        int originalWindowCount = DriverManager.getDriver().getWindowHandles().size();
        windowsPage.clickHereToOpenNewWindow();
        // Wait for the new window to open
        windowsPage.waitForNewWindow(originalWindowCount);
    }

    @And("I switch to the new window")
    public void i_switch_to_the_new_window() {
        windowsPage.switchToNewWindow();
    }

    @Then("I should verify the new window content")
    public void i_should_verify_the_new_window_content() {
        // Verify that we're in a new window (different URL)
        String currentUrl = windowsPage.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, 
            "https://the-internet.herokuapp.com/windows",
            "Current URL should be different from the original windows page");
        
        // Verify the new window has content (typically contains "New Window" text)
        String pageHeading = windowsPage.getPageHeading();
        Assert.assertNotNull(pageHeading, "Page heading should not be null");
        Assert.assertFalse(pageHeading.isEmpty(), "Page heading should not be empty");
        
        // Verify the page contains expected content
        boolean hasContent = windowsPage.verifyNewWindowContent("New Window");
        Assert.assertTrue(hasContent, 
            "New window should contain 'New Window' text");
    }
}
