@FileUpload
Feature: File Upload
  As a user
  I want to upload a file
  So that I can verify the file upload functionality

  Scenario: Upload a file and verify success message
    Given I navigate to the file upload page
    When I upload a file
    Then I should see a success message "File Uploaded!"
    And the uploaded file name should be displayed

