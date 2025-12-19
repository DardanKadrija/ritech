# Selenium UI Automation

Java Selenium project for UI test automation using Page Object Model.

## Setup

Requirements:
- Java JDK 11 or higher
- Maven 3.6+

Install dependencies:
```
mvn clean install
```

## Configuration

Update `src/main/resources/config.properties`:

```
browser=chrome
headless=false
base.url=https://www.example.com
implicit.wait=10
page.load.timeout=30
```

## Project Structure

```
src/
├── main/java/com/ritech/
│   ├── base/
│   │   ├── BasePage.java
│   │   └── BaseTest.java
│   ├── pages/          # Add your page objects here
│   └── utils/
│       ├── ConfigReader.java
│       └── DriverManager.java
└── test/java/com/ritech/
    └── tests/          # Add your test classes here
```

## Running Tests

Run all tests:
```
mvn test
```

Run specific test:
```
mvn test -Dtest=YourTestClass
```

Or run from IDE using testng.xml

## Writing Tests

Create page objects in `src/main/java/com/ritech/pages/` extending `BasePage`.

Create test classes in `src/test/java/com/ritech/tests/` extending `BaseTest`.

## Dependencies

- Selenium WebDriver 4.15.0
- TestNG 7.8.0
- WebDriverManager 5.6.2
- ExtentReports 5.1.1
- Log4j2 2.21.1
