package com.ritech.tests;

import com.ritech.base.BaseTest;
import com.ritech.pages.FileUploadPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Test class for File Upload functionality
 */
public class FileUploadTest extends BaseTest {

    @Test
    public void testFileUpload() throws IOException {
        // Create a temporary test file
        Path testFile = Files.createTempFile("test-upload", ".txt");
        Files.write(testFile, "This is a test file for upload".getBytes());
        String filePath = testFile.toAbsolutePath().toString();

        try {
            FileUploadPage fileUploadPage = new FileUploadPage();
            
            // Navigate to upload page
            fileUploadPage.navigateToUploadPage();
            
            // Upload the file
            fileUploadPage.uploadFile(filePath);
            
            // Verify success message
            Assert.assertTrue(fileUploadPage.isSuccessMessageDisplayed(), 
                "Success message should be displayed after file upload");
            
            String successMessage = fileUploadPage.getSuccessMessage();
            Assert.assertEquals(successMessage, "File Uploaded!", 
                "Success message should be 'File Uploaded!'");
            
            // Verify uploaded file name
            String uploadedFileName = fileUploadPage.getUploadedFileName();
            String expectedFileName = new File(filePath).getName();
            Assert.assertEquals(uploadedFileName, expectedFileName, 
                "Uploaded file name should match the uploaded file");
        } finally {
            // Clean up: delete the temporary file
            Files.deleteIfExists(testFile);
        }
    }
}

