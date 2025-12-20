# Selenium UI Automation Framework

A comprehensive Java-based Selenium WebDriver automation framework using Page Object Model (POM) design pattern, TestNG for test execution, and Cucumber for Behavior-Driven Development (BDD) testing.

## 🚀 Features

- **Multi-browser support**: Chrome, Firefox, and Edge
- **BDD Testing**: Cucumber integration for readable, maintainable test scenarios
- **Page Object Model**: Clean separation of page logic and test code
- **Parallel Execution**: TestNG parallel execution support
- **Automatic Driver Management**: WebDriverManager handles browser driver setup
- **Comprehensive Reporting**: Cucumber HTML/JSON reports and ExtentReports support
- **Logging**: Log4j2 for detailed test execution logs
- **Headless Mode**: Support for headless browser execution

## 📋 Prerequisites

- **Java JDK 11** or higher
- **Maven 3.6+**
- **Internet connection** (for downloading dependencies and browser drivers)

## 🛠️ Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ritech-main
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```
   This will download all required dependencies including Selenium, TestNG, Cucumber, and WebDriverManager.

3. **Verify installation**
   ```bash
   mvn -version
   java -version
   ```

## ⚙️ Configuration

Update `src/main/resources/config.properties` to configure your test environment:

```properties
# Browser Configuration
browser=chrome                    # Options: chrome, firefox, edge
headless=false                    # Set to true for headless execution

# Timeout Configuration (in seconds)
implicit.wait=10                  # Implicit wait timeout
page.load.timeout=30              # Page load timeout
explicit.wait=10                  # Explicit wait timeout

# Application URL
base.url=https://the-internet.herokuapp.com
```

### Browser Options

- **chrome**: Google Chrome browser
- **firefox**: Mozilla Firefox browser
- **edge**: Microsoft Edge browser

### Headless Mode

Set `headless=true` in `config.properties` to run tests without opening a browser window. Useful for CI/CD pipelines and faster execution.

## 🧪 Running Tests

### Run All Tests

Execute all tests using Maven:
```bash
mvn test
```

This will run both TestNG tests and Cucumber BDD tests as configured in `testng.xml`.

### Run Specific Test Suites

**Run TestNG tests only:**
```bash
mvn test -Dtest=FileUploadTest
```

**Run Cucumber BDD tests:**
```bash
mvn test -Dtest=CucumberTestRunner
```

**Run tests by tags (Cucumber):**
Edit `CucumberTestRunner.java` to modify the `tags` parameter, or run specific features:
```bash
mvn test -Dcucumber.filter.tags="@FileUpload"
```

### Run from IDE

1. **IntelliJ IDEA / Eclipse:**
   - Right-click on `testng.xml` → Run
   - Or right-click on individual test classes → Run

2. **Run specific Cucumber feature:**
   - Right-click on any `.feature` file in `src/test/resources/features/` → Run

### Parallel Execution

The framework supports parallel test execution via TestNG. Configure parallel execution in `testng.xml`:
```xml
<suite name="Selenium UI Automation Suite" parallel="tests" thread-count="2">
```

## 📊 Test Reports & Output

### What to Expect After Test Completion

After tests complete, you'll find the following outputs:

#### 1. **Cucumber Reports** (BDD Tests)
- **Location**: `target/cucumber-reports/`
- **HTML Report**: `target/cucumber-reports/index.html` - Open in browser for detailed test results
- **JSON Report**: `target/cucumber-reports/Cucumber.json` - For CI integration
- **JUnit XML**: `target/cucumber-reports/Cucumber.xml` - For CI tools like Jenkins

#### 2. **TestNG Reports**
- **Location**: `test-output/`
- **HTML Report**: `test-output/index.html` - TestNG HTML report
- **XML Report**: `test-output/testng-results.xml` - For CI integration

#### 3. **Maven Surefire Reports**
- **Location**: `target/surefire-reports/`
- Contains test execution summaries and stack traces

#### 4. **Log Files**
- **Location**: `logs/` (if configured)
- Detailed execution logs via Log4j2

#### 5. **ExtentReports** (if implemented)
- **Location**: `extent-reports/`
- Rich HTML reports with screenshots and test details

### Viewing Reports

1. **Cucumber HTML Report:**
   ```bash
   # Open in browser
   start target/cucumber-reports/index.html    # Windows
   open target/cucumber-reports/index.html      # macOS
   xdg-open target/cucumber-reports/index.html # Linux
   ```

2. **TestNG Report:**
   ```bash
   start test-output/index.html    # Windows
   open test-output/index.html      # macOS
   ```

## 📁 Project Structure

```
ritech-main/
├── src/
│   ├── main/
│   │   ├── java/com/ritech/
│   │   │   ├── base/
│   │   │   │   ├── BasePage.java          # Base page class with common methods
│   │   │   │   └── BaseTest.java          # Base test class with setup/teardown
│   │   │   ├── pages/                     # Page Object classes
│   │   │   │   ├── CheckboxesPage.java
│   │   │   │   ├── DragAndDropPage.java
│   │   │   │   ├── FileUploadPage.java
│   │   │   │   ├── HoversPage.java
│   │   │   │   ├── JavaScriptAlertsPage.java
│   │   │   │   └── WindowsPage.java
│   │   │   └── utils/
│   │   │       ├── ConfigReader.java      # Configuration property reader
│   │   │       └── DriverManager.java     # WebDriver initialization
│   │   └── resources/
│   │       ├── config.properties          # Test configuration
│   │       └── log4j2.xml                 # Logging configuration
│   └── test/
│       ├── java/com/ritech/tests/
│       │   ├── cucumber/                  # Cucumber step definitions
│       │   │   ├── CucumberTestRunner.java
│       │   │   ├── CheckboxesStepDefinitions.java
│       │   │   ├── DragAndDropStepDefinitions.java
│       │   │   ├── FileUploadStepDefinitions.java
│       │   │   ├── HoversStepDefinitions.java
│       │   │   ├── JavaScriptAlertsStepDefinitions.java
│       │   │   └── WindowsStepDefinitions.java
│       │   └── FileUploadTest.java        # TestNG test class
│       └── resources/
│           └── features/                  # Cucumber feature files
│               ├── Checkboxes.feature
│               ├── DragAndDrop.feature
│               ├── FileUpload.feature
│               ├── Hovers.feature
│               ├── JavaScriptAlerts.feature
│               └── Windows.feature
├── testng.xml                            # TestNG suite configuration
├── pom.xml                               # Maven dependencies
└── README.md
```

## 🧩 Test Features Covered

The framework includes tests for the following scenarios:

1. **File Upload** (`@FileUpload`)
   - Upload files and verify success messages

2. **Drag and Drop** (`@DragAndDrop`)
   - Drag and drop elements functionality

3. **JavaScript Alerts** (`@JavaScriptAlerts`)
   - Handle various types of JavaScript alerts

4. **Windows Handling** (`@Windows`)
   - Multiple window/tab handling

5. **Hovers** (`@Hovers`)
   - Mouse hover interactions

6. **Checkboxes** (`@Checkboxes`)
   - Checkbox selection and verification

## 🔄 CI/CD Integration

This project is configured for **Jenkins CI/CD** with a ready-to-use `Jenkinsfile`. See the [Jenkins Pipeline](#jenkins-pipeline) section below for detailed setup instructions.

### GitHub Actions (Alternative)

Create `.github/workflows/ci.yml`:

```yaml
name: Selenium UI Tests

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
        cache: maven
    
    - name: Install dependencies
      run: mvn clean install -DskipTests
    
    - name: Run tests
      run: mvn test
    
    - name: Publish Cucumber Report
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: cucumber-report
        path: target/cucumber-reports/
    
    - name: Publish TestNG Report
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: testng-report
        path: test-output/
```

### Jenkins Pipeline

The project includes a `Jenkinsfile` for Jenkins CI/CD integration. Follow these steps to set up Jenkins:

#### Prerequisites

1. **Jenkins Installation**
   - Install Jenkins (version 2.400+ recommended)
   - Ensure Jenkins has internet access for downloading dependencies

2. **Required Jenkins Plugins**
   Install the following plugins via **Manage Jenkins → Plugins**:
   - **HTML Publisher Plugin** - For publishing HTML test reports
   - **JUnit Plugin** - For parsing test results
   - **Maven Integration Plugin** - For Maven support
   - **Pipeline Plugin** - For Pipeline support
   - **AnsiColor Plugin** (optional) - For colored console output
   - **Email Extension Plugin** (optional) - For email notifications

3. **Configure Global Tools**
   Go to **Manage Jenkins → Global Tool Configuration**:
   - **JDK**: Add JDK 11 installation (name it `JDK-11`)
     - Path: `/usr/lib/jvm/java-11-openjdk-amd64` (Linux) or your JDK 11 path
   - **Maven**: Add Maven installation (name it `Maven-3.8`)
     - Version: 3.8.x or higher
     - Or use "Install automatically" option

#### Jenkins Job Setup

**Option 1: Using Jenkinsfile (Recommended)**

1. **Create a New Pipeline Job**
   - Click **New Item** → Enter job name → Select **Pipeline** → Click **OK**

2. **Configure Pipeline**
   - **Pipeline Definition**: Select "Pipeline script from SCM"
   - **SCM**: Select Git
   - **Repository URL**: Enter your repository URL
   - **Credentials**: Add if repository is private
   - **Branch Specifier**: `*/main` or your main branch
   - **Script Path**: `Jenkinsfile` (default)

3. **Build Triggers** (Optional)
   - **Poll SCM**: `H/15 * * * *` (every 15 minutes)
   - **GitHub hook trigger for GITScm polling** (if using GitHub)

4. **Save and Build**
   - Click **Save**
   - Click **Build Now** to trigger the first build

**Option 2: Manual Pipeline Script**

If you prefer to configure manually, copy the pipeline script from `Jenkinsfile` into Jenkins Pipeline configuration.

#### Jenkinsfile Features

The included `Jenkinsfile` provides:

- ✅ **Automatic Build**: Compiles and installs dependencies
- ✅ **CI Configuration**: Automatically sets headless mode for CI
- ✅ **Test Execution**: Runs all TestNG and Cucumber tests
- ✅ **Report Publishing**: Publishes Cucumber and TestNG HTML reports
- ✅ **JUnit Integration**: Parses test results for Jenkins dashboard
- ✅ **Artifact Archiving**: Archives all test reports and logs
- ✅ **Build Notifications**: Success/failure notifications (email configurable)

#### Environment Variables

You can customize the pipeline by setting environment variables:

- `HEADLESS`: Set to `true` for headless execution (default: `true`)
- `BROWSER`: Browser to use - `chrome`, `firefox`, or `edge` (default: `chrome`)
- `BASE_URL`: Application URL (default: `https://the-internet.herokuapp.com`)

#### Viewing Test Reports in Jenkins

After a build completes:

1. **Cucumber Report**: Click **Cucumber HTML Report** link in the build page
2. **TestNG Report**: Click **TestNG HTML Report** link in the build page
3. **Test Results**: View **Test Result** section for JUnit-style results
4. **Console Output**: Click **Console Output** for detailed execution logs
5. **Artifacts**: Download reports from **Build Artifacts** section

#### Jenkins Pipeline Stages

The pipeline executes the following stages:

1. **Checkout**: Clones the repository
2. **Build**: Installs Maven dependencies
3. **Update Config for CI**: Configures headless mode and browser settings
4. **Run Tests**: Executes all Selenium tests
5. **Publish Reports**: Publishes HTML reports and test results
6. **Archive Artifacts**: Archives all test reports and logs

#### Troubleshooting Jenkins Setup

1. **Build fails with "Maven not found"**
   - Verify Maven is configured in Global Tool Configuration
   - Check the tool name matches `Maven-3.8` in Jenkinsfile

2. **Build fails with "JDK not found"**
   - Verify JDK 11 is configured in Global Tool Configuration
   - Check the tool name matches `JDK-11` in Jenkinsfile

3. **Reports not showing**
   - Ensure HTML Publisher Plugin is installed
   - Check that report files exist in `target/cucumber-reports/` and `test-output/`

4. **Tests fail in Jenkins but pass locally**
   - Ensure headless mode is enabled (set in Jenkinsfile)
   - Check Jenkins has display/Xvfb for browser execution
   - Verify all dependencies are downloaded correctly

5. **Browser not launching**
   - Install required browser on Jenkins server
   - For headless Chrome, ensure Chrome is installed
   - Consider using Docker agents for consistent environments

#### Using Docker with Jenkins (Recommended)

For more reliable and consistent test execution, use Docker agents:

1. **Install Docker Plugin** in Jenkins
   - Go to **Manage Jenkins → Plugins**
   - Install **Docker Pipeline Plugin**

2. **Configure Docker Agent** in Jenkinsfile:
   ```groovy
   agent {
       docker {
           image 'maven:3.8-openjdk-11'
           args '-v /var/run/docker.sock:/var/run/docker.sock'
       }
   }
   ```

3. **Or use Docker Compose** for Selenium Grid:
   - Use Selenium Grid Docker images for browser execution
   - Configure Jenkinsfile to connect to Selenium Hub

**Benefits of Docker:**
- Consistent test environments
- No need to install browsers on Jenkins server
- Easy to scale and parallelize tests
- Isolated test execution

### GitLab CI

Create `.gitlab-ci.yml`:

```yaml
image: maven:3.8-openjdk-11

stages:
  - test

selenium-tests:
  stage: test
  script:
    - mvn clean install -DskipTests
    - mvn test
  artifacts:
    when: always
    paths:
      - target/cucumber-reports/
      - test-output/
    reports:
      junit: target/cucumber-reports/Cucumber.xml
```

### CI Best Practices

1. **Use Headless Mode**: Set `headless=true` in `config.properties` for CI environments
2. **Parallel Execution**: Configure appropriate thread count based on CI resources
3. **Artifact Storage**: Always store test reports as artifacts for later analysis
4. **Failure Notifications**: Configure email/Slack notifications on test failures
5. **Docker Support**: Consider using Docker for consistent test environments

## 📚 Dependencies

- **Selenium WebDriver** 4.15.0 - Browser automation
- **TestNG** 7.8.0 - Test framework
- **Cucumber** 7.14.0 - BDD framework
- **WebDriverManager** 5.6.2 - Automatic driver management
- **ExtentReports** 5.1.1 - Advanced reporting
- **Log4j2** 2.21.1 - Logging framework

## 🐛 Troubleshooting

### Common Issues

1. **Browser driver not found**
   - WebDriverManager should handle this automatically
   - Ensure internet connection is available
   - Check browser is installed

2. **Tests fail with timeout**
   - Increase timeout values in `config.properties`
   - Check network connectivity
   - Verify application URL is accessible

3. **Parallel execution issues**
   - Reduce `thread-count` in `testng.xml`
   - Ensure ThreadLocal WebDriver is used (already implemented)

4. **Cucumber reports not generated**
   - Check `target/cucumber-reports/` directory exists
   - Verify Cucumber plugin configuration in `CucumberTestRunner.java`

## 📝 Writing New Tests

### Adding a New Page Object

1. Create a new class in `src/main/java/com/ritech/pages/`
2. Extend `BasePage`
3. Add page-specific methods

```java
public class NewPage extends BasePage {
    public NewPage(WebDriver driver) {
        super(driver);
    }
    
    // Add your page methods here
}
```

### Adding a New Cucumber Test

1. Create feature file in `src/test/resources/features/`
2. Create step definitions in `src/test/java/com/ritech/tests/cucumber/`
3. Add tag to `CucumberTestRunner.java` if needed

### Adding a New TestNG Test

1. Create test class in `src/test/java/com/ritech/tests/`
2. Extend `BaseTest`
3. Add test methods with `@Test` annotation
4. Update `testng.xml` to include the new test

