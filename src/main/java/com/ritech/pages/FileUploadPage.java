package com.ritech.pages;

import com.ritech.base.BasePage;
import com.ritech.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for File Upload page
 */
public class FileUploadPage extends BasePage {

    @FindBy(id = "file-upload")
    private WebElement fileUploadInput;

    @FindBy(id = "file-submit")
    private WebElement fileSubmitButton;

    @FindBy(id = "uploaded-files")
    private WebElement uploadedFiles;

    @FindBy(tagName = "h3")
    private WebElement successMessage;

    /**
     * Navigate to file upload page
     */
    public void navigateToUploadPage() {
        navigateTo(ConfigReader.getBaseUrl() + "/upload");
    }

    /**
     * Upload a file by providing the file path
     * @param filePath absolute path to the file to upload
     */
    public void uploadFile(String filePath) {
        waitForElementToBeVisible(fileUploadInput);
        fileUploadInput.sendKeys(filePath);
        click(fileSubmitButton);
    }

    /**
     * Get the success message after file upload
     * @return success message text
     */
    public String getSuccessMessage() {
        waitForElementToBeVisible(successMessage);
        return getText(successMessage);
    }

    /**
     * Get the name of the uploaded file
     * @return uploaded file name
     */
    public String getUploadedFileName() {
        waitForElementToBeVisible(uploadedFiles);
        return getText(uploadedFiles);
    }

    /**
     * Verify if success message is displayed
     * @return true if success message is displayed
     */
    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }
}

