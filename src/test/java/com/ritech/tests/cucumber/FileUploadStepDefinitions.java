package com.ritech.tests.cucumber;

import com.ritech.pages.FileUploadPage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Step definitions for File Upload feature
 */
public class FileUploadStepDefinitions {
    private FileUploadPage fileUploadPage;
    private Path testFile;
    private String uploadedFileName;

    @After
    public void cleanUpTestFile() {
        // Clean up: delete the temporary file
        if (testFile != null) {
            try {
                Files.deleteIfExists(testFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Given("I navigate to the file upload page")
    public void i_navigate_to_the_file_upload_page() {
        fileUploadPage = new FileUploadPage();
        fileUploadPage.navigateToUploadPage();
    }

    @When("I upload a file")
    public void i_upload_a_file() throws IOException {
        // Create a temporary test file
        testFile = Files.createTempFile("test-upload", ".txt");
        Files.write(testFile, "This is a test file for upload".getBytes());
        String filePath = testFile.toAbsolutePath().toString();
        
        // Upload the file
        fileUploadPage.uploadFile(filePath);
        
        // Store the expected file name for verification
        uploadedFileName = new File(filePath).getName();
    }

    @Then("I should see a success message {string}")
    public void i_should_see_a_success_message(String expectedMessage) {
        Assert.assertTrue(fileUploadPage.isSuccessMessageDisplayed(), 
            "Success message should be displayed after file upload");
        
        String actualMessage = fileUploadPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, expectedMessage, 
            "Success message should match expected message");
    }

    @Then("the uploaded file name should be displayed")
    public void the_uploaded_file_name_should_be_displayed() {
        String actualFileName = fileUploadPage.getUploadedFileName();
        Assert.assertEquals(actualFileName, uploadedFileName, 
            "Uploaded file name should match the uploaded file");
    }
}

