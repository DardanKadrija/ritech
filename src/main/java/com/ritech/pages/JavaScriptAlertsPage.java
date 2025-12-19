package com.ritech.pages;

import com.ritech.base.BasePage;
import com.ritech.utils.ConfigReader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for JavaScript Alerts page
 */
public class JavaScriptAlertsPage extends BasePage {

    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    private WebElement jsAlertButton;

    @FindBy(xpath = "//button[text()='Click for JS Confirm']")
    private WebElement jsConfirmButton;

    @FindBy(xpath = "//button[text()='Click for JS Prompt']")
    private WebElement jsPromptButton;

    @FindBy(id = "result")
    private WebElement resultMessage;

    /**
     * Navigate to JavaScript Alerts page
     */
    public void navigateToAlertsPage() {
        navigateTo(ConfigReader.getBaseUrl() + "/javascript_alerts");
    }

    /**
     * Click on JS Alert button
     */
    public void clickJSAlert() {
        waitForElementToBeClickable(jsAlertButton);
        click(jsAlertButton);
    }

    /**
     * Click on JS Confirm button
     */
    public void clickJSConfirm() {
        waitForElementToBeClickable(jsConfirmButton);
        click(jsConfirmButton);
    }

    /**
     * Click on JS Prompt button
     */
    public void clickJSPrompt() {
        waitForElementToBeClickable(jsPromptButton);
        click(jsPromptButton);
    }

    /**
     * Handle JS Alert by accepting it
     */
    public void acceptJSAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    /**
     * Handle JS Confirm by accepting it
     */
    public void acceptJSConfirm() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    /**
     * Handle JS Confirm by dismissing it
     */
    public void dismissJSConfirm() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    /**
     * Handle JS Prompt by accepting with text input
     * @param text text to enter in the prompt
     */
    public void acceptJSPrompt(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }

    /**
     * Handle JS Prompt by dismissing it
     */
    public void dismissJSPrompt() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    /**
     * Get the text from JS Alert
     * @return alert text
     */
    public String getJSAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    /**
     * Get the result message after interacting with alerts
     * @return result message text
     */
    public String getResultMessage() {
        waitForElementToBeVisible(resultMessage);
        return getText(resultMessage);
    }

    /**
     * Verify if result message is displayed
     * @return true if result message is displayed
     */
    public boolean isResultMessageDisplayed() {
        return isDisplayed(resultMessage);
    }
}
