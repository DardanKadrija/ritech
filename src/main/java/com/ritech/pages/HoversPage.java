package com.ritech.pages;

import com.ritech.base.BasePage;
import com.ritech.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Object for Hovers page
 */
public class HoversPage extends BasePage {

    @FindBy(className = "figure")
    private List<WebElement> profileFigures;

    /**
     * Navigate to hovers page
     */
    public void navigateToHoversPage() {
        navigateTo(ConfigReader.getBaseUrl() + "/hovers");
    }

    /**
     * Get all profile figures
     * @return list of profile figure elements
     */
    public List<WebElement> getProfileFigures() {
        waitForElementToBePresent(By.className("figure"));
        return profileFigures;
    }

    /**
     * Hover over a specific profile by index (0-based)
     * @param index the index of the profile to hover over
     */
    public void hoverOverProfile(int index) {
        List<WebElement> profiles = getProfileFigures();
        if (index < 0 || index >= profiles.size()) {
            throw new IllegalArgumentException("Profile index out of bounds: " + index);
        }
        WebElement profile = profiles.get(index);
        waitForElementToBeVisible(profile);
        
        Actions actions = new Actions(driver);
        actions.moveToElement(profile).perform();
    }

    /**
     * Get the name text that appears after hovering over a profile
     * @param index the index of the profile (0-based)
     * @return the name text displayed after hover
     */
    public String getProfileName(int index) {
        List<WebElement> profiles = getProfileFigures();
        if (index < 0 || index >= profiles.size()) {
            throw new IllegalArgumentException("Profile index out of bounds: " + index);
        }
        WebElement profile = profiles.get(index);
        
        // Find the h5 element within the profile figure that contains the name
        WebElement nameElement = profile.findElement(By.tagName("h5"));
        waitForElementToBeVisible(nameElement);
        return getText(nameElement);
    }

    /**
     * Verify if profile name is displayed after hover
     * @param index the index of the profile (0-based)
     * @return true if the name is displayed
     */
    public boolean isProfileNameDisplayed(int index) {
        try {
            List<WebElement> profiles = getProfileFigures();
            if (index < 0 || index >= profiles.size()) {
                return false;
            }
            WebElement profile = profiles.get(index);
            WebElement nameElement = profile.findElement(By.tagName("h5"));
            return isDisplayed(nameElement);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the number of profiles on the page
     * @return number of profiles
     */
    public int getProfileCount() {
        return getProfileFigures().size();
    }
}
