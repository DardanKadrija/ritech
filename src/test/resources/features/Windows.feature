@Windows
Feature: New Window Handle / New Tab
  As a user
  I want to open a new window and switch to it
  So that I can verify the new window content

  Scenario: Open new window and verify its content
    Given I navigate to the windows page
    When I click "Click Here" to open a new window
    And I switch to the new window
    Then I should verify the new window content
