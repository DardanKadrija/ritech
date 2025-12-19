package com.ritech.tests.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Cucumber Test Runner for BDD tests
 */
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.ritech.tests.cucumber",
    plugin = {
        "pretty",
        "html:target/cucumber-reports",
        "json:target/cucumber-reports/Cucumber.json",
        "junit:target/cucumber-reports/Cucumber.xml"
    },
    monochrome = true,
    tags = "@FileUpload or not @ignore"
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}

