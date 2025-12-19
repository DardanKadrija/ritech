package com.ritech.pages;

import com.ritech.base.BasePage;
import com.ritech.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for Checkboxes page
 */
public class CheckboxesPage extends BasePage {

    /**
     * Navigate to checkboxes page
     */
    public void navigateToCheckboxesPage() {
        navigateTo(ConfigReader.getBaseUrl() + "/checkboxes");
    }

    /**
     * Get all checkboxes on the page
     * @return list of checkbox elements
     */
    public List<WebElement> getCheckboxes() {
        waitForElementToBePresent(By.cssSelector("input[type='checkbox']"));
        return findElements(By.cssSelector("input[type='checkbox']"));
    }

    /**
     * Select a checkbox by index (0-based)
     * @param index the index of the checkbox to select
     */
    public void selectCheckbox(int index) {
        List<WebElement> checkboxes = getCheckboxes();
        if (index >= 0 && index < checkboxes.size()) {
            WebElement checkbox = checkboxes.get(index);
            waitForElementToBeClickable(checkbox);
            if (!checkbox.isSelected()) {
                click(checkbox);
            }
        } else {
            throw new IndexOutOfBoundsException("Checkbox index " + index + " is out of bounds");
        }
    }

    /**
     * Select a checkbox by index (1-based, as specified in test)
     * @param checkboxNumber the checkbox number (1-based)
     */
    public void selectCheckboxByNumber(int checkboxNumber) {
        selectCheckbox(checkboxNumber - 1);
    }

    /**
     * Verify if a checkbox is checked by index (0-based)
     * @param index the index of the checkbox to verify
     * @return true if checkbox is checked, false otherwise
     */
    public boolean isCheckboxChecked(int index) {
        List<WebElement> checkboxes = getCheckboxes();
        if (index >= 0 && index < checkboxes.size()) {
            return checkboxes.get(index).isSelected();
        } else {
            throw new IndexOutOfBoundsException("Checkbox index " + index + " is out of bounds");
        }
    }

    /**
     * Verify if a checkbox is checked by index (1-based, as specified in test)
     * @param checkboxNumber the checkbox number (1-based)
     * @return true if checkbox is checked, false otherwise
     */
    public boolean isCheckboxCheckedByNumber(int checkboxNumber) {
        return isCheckboxChecked(checkboxNumber - 1);
    }
}
