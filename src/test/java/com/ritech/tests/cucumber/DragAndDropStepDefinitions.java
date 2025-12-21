package com.ritech.tests.cucumber;

import com.ritech.pages.DragAndDropPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * Step definitions for Drag and Drop feature
 */
public class DragAndDropStepDefinitions {
    private DragAndDropPage dragAndDropPage;
    private String initialColumnAText;
    private String initialColumnBText;

    @Given("I navigate to the drag and drop page")
    public void i_navigate_to_the_drag_and_drop_page() {
        dragAndDropPage = new DragAndDropPage();
        dragAndDropPage.navigateToDragAndDropPage();
        // Store initial positions
        initialColumnAText = dragAndDropPage.getColumnAText();
        initialColumnBText = dragAndDropPage.getColumnBText();
    }

    @When("I drag element A to element B")
    public void i_drag_element_a_to_element_b() {
        dragAndDropPage.dragAToB();
        // Wait a bit for the drag and drop to complete
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("the positions of elements A and B should be swapped")
    public void the_positions_of_elements_a_and_b_should_be_swapped() {
        String finalColumnAText = dragAndDropPage.getColumnAText();
        String finalColumnBText = dragAndDropPage.getColumnBText();
        
        // After dragging A to B, A should have B's original text and B should have A's original text
        Assert.assertEquals(finalColumnAText, initialColumnBText, 
            "Column A should now have the text that was originally in Column B");
        Assert.assertEquals(finalColumnBText, initialColumnAText, 
            "Column B should now have the text that was originally in Column A");
    }
}
