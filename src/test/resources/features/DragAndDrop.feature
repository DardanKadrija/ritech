@DragAndDrop
Feature: Drag and Drop
  As a user
  I want to drag and drop elements
  So that I can verify the drag and drop functionality

  Scenario: Drag element A to B and validate position change
    Given I navigate to the drag and drop page
    When I drag element A to element B
    Then the positions of elements A and B should be swapped
