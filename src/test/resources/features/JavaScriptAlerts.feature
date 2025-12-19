@JavaScriptAlerts
Feature: JavaScript Alerts
  As a user
  I want to interact with different types of JavaScript alerts
  So that I can verify alert handling functionality

  Scenario: Handle JS Alert
    Given I navigate to the JavaScript alerts page
    When I click on the JS Alert button
    And I accept the alert
    Then I should see the result message "You successfully clicked an alert"

  Scenario: Handle JS Confirm - Accept
    Given I navigate to the JavaScript alerts page
    When I click on the JS Confirm button
    And I accept the confirm alert
    Then I should see the result message "You clicked: Ok"

  Scenario: Handle JS Confirm - Dismiss
    Given I navigate to the JavaScript alerts page
    When I click on the JS Confirm button
    And I dismiss the confirm alert
    Then I should see the result message "You clicked: Cancel"

  Scenario: Handle JS Prompt - Accept with text
    Given I navigate to the JavaScript alerts page
    When I click on the JS Prompt button
    And I enter "Test Prompt Text" in the prompt and accept
    Then I should see the result message "You entered: Test Prompt Text"

  Scenario: Handle JS Prompt - Dismiss
    Given I navigate to the JavaScript alerts page
    When I click on the JS Prompt button
    And I dismiss the prompt alert
    Then I should see the result message "You entered: null"
