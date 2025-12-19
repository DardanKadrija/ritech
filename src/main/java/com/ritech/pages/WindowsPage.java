package com.ritech.pages;

import com.ritech.base.BasePage;
import com.ritech.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

/**
 * Page Object for Windows page
 */
public class WindowsPage extends BasePage {

    @FindBy(linkText = "Click Here")
    private WebElement clickHereLink;

    /**
     * Navigate to windows page
     */
    public void navigateToWindowsPage() {
        navigateTo(ConfigReader.getBaseUrl() + "/windows");
    }

    /**
     * Click on "Click Here" link to open a new window
     */
    public void clickHereToOpenNewWindow() {
        waitForElementToBeVisible(clickHereLink);
        click(clickHereLink);
    }

    /**
     * Wait for a new window to open
     * @param originalWindowCount the number of windows before clicking
     */
    public void waitForNewWindow(int originalWindowCount) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(originalWindowCount + 1));
    }

    /**
     * Switch to the new window
     * @return the handle of the original window
     */
    public String switchToNewWindow() {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        
        // Find the new window (not the original one)
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                return originalWindow;
            }
        }
        return originalWindow;
    }

    /**
     * Switch back to the original window
     * @param windowHandle the handle of the window to switch to
     */
    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    /**
     * Get the current page title
     * @return page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get the current page URL
     * @return page URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get the text content of the page (h3 heading)
     * @return page heading text
     */
    public String getPageHeading() {
        WebElement heading = findElement(org.openqa.selenium.By.tagName("h3"));
        waitForElementToBeVisible(heading);
        return getText(heading);
    }

    /**
     * Verify if the new window contains expected content
     * @param expectedText expected text to verify
     * @return true if the text is found on the page
     */
    public boolean verifyNewWindowContent(String expectedText) {
        String pageText = driver.getPageSource();
        return pageText.contains(expectedText);
    }
}
