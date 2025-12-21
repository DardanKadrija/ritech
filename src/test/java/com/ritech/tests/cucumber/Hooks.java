package com.ritech.tests.cucumber;

import com.ritech.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Shared hooks for Cucumber scenarios
 * Handles driver initialization and cleanup once per scenario
 */
public class Hooks {

    @Before(order = 0)
    public void setUp() {
        // Initialize driver only if not already initialized
        if (!DriverManager.isDriverInitialized()) {
            DriverManager.initializeDriver();
        }
    }

    @After(order = 0)
    public void tearDown() {
        // Clean up driver after scenario
        DriverManager.quitDriver();
    }
}

