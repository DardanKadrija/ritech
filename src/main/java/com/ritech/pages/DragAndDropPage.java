package com.ritech.pages;

import com.ritech.base.BasePage;
import com.ritech.utils.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Drag and Drop page
 */
public class DragAndDropPage extends BasePage {

    @FindBy(id = "column-a")
    private WebElement columnA;

    @FindBy(id = "column-b")
    private WebElement columnB;

    /**
     * Navigate to drag and drop page
     */
    public void navigateToDragAndDropPage() {
        navigateTo(ConfigReader.getBaseUrl() + "/drag_and_drop");
    }

    /**
     * Drag element A to element B using JavaScript (more reliable for HTML5 drag and drop)
     */
    public void dragAToB() {
        waitForElementToBeVisible(columnA);
        waitForElementToBeVisible(columnB);
        
        // Use JavaScript for HTML5 drag and drop as it's more reliable
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "function createEvent(typeOfEvent) {\n" +
                "    var event = document.createEvent(\"CustomEvent\");\n" +
                "    event.initCustomEvent(typeOfEvent, true, true, null);\n" +
                "    event.dataTransfer = {\n" +
                "        data: {},\n" +
                "        setData: function (key, value) {\n" +
                "            this.data[key] = value;\n" +
                "        },\n" +
                "        getData: function (key) {\n" +
                "            return this.data[key];\n" +
                "        }\n" +
                "    };\n" +
                "    return event;\n" +
                "}\n" +
                "\n" +
                "function dispatchEvent(element, event, transferData) {\n" +
                "    if (transferData !== undefined) {\n" +
                "        event.dataTransfer = transferData;\n" +
                "    }\n" +
                "    if (element.dispatchEvent) {\n" +
                "        element.dispatchEvent(event);\n" +
                "    } else if (element.fireEvent) {\n" +
                "        element.fireEvent(\"on\" + event.type, event);\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "var elementToDrag = arguments[0];\n" +
                "var targetElement = arguments[1];\n" +
                "\n" +
                "var dragStartEvent = createEvent('dragstart');\n" +
                "dispatchEvent(elementToDrag, dragStartEvent);\n" +
                "\n" +
                "var dropEvent = createEvent('drop');\n" +
                "dispatchEvent(targetElement, dropEvent, dragStartEvent.dataTransfer);\n" +
                "\n" +
                "var dragEndEvent = createEvent('dragend');\n" +
                "dispatchEvent(elementToDrag, dragEndEvent);\n";
        
        js.executeScript(script, columnA, columnB);
    }

    /**
     * Get the text of column A
     * @return text of column A
     */
    public String getColumnAText() {
        waitForElementToBeVisible(columnA);
        return getText(columnA);
    }

    /**
     * Get the text of column B
     * @return text of column B
     */
    public String getColumnBText() {
        waitForElementToBeVisible(columnB);
        return getText(columnB);
    }

    /**
     * Verify if column A is displayed
     * @return true if column A is displayed
     */
    public boolean isColumnADisplayed() {
        return isDisplayed(columnA);
    }

    /**
     * Verify if column B is displayed
     * @return true if column B is displayed
     */
    public boolean isColumnBDisplayed() {
        return isDisplayed(columnB);
    }
}
