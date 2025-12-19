@Checkboxes
Feature: Checkboxes Selection
  As a user
  I want to select a checkbox and verify it is checked
  So that I can interact with checkbox elements on the page

  Scenario: Select a checkbox and verify it is checked
    Given I navigate to the checkboxes page
    When I select checkbox 1
    Then checkbox 1 should be checked
