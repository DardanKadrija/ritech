@Hovers
Feature: Hover Functionality
  As a user
  I want to hover over profiles on the hovers page
  So that I can verify the name appears for each profile

  Scenario: Hover over each profile and verify the name appears
    Given I navigate to the hovers page
    When I hover over each profile
    Then I should verify the name appears for each profile
