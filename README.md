# Automation Testing Framework

A comprehensive, modular automation testing framework built with Java, Maven, and TestNG. This framework supports Web, Desktop, Mobile, and API automation using industry-standard tools and best practices.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Execution](#execution)
- [Test Scenarios](#test-scenarios)
- [Framework Components](#framework-components)
- [Reports](#reports)
- [Troubleshooting](#troubleshooting)

## Features

✅ **Page Object Model (POM)** - Reusable and maintainable web automation
✅ **Selenium WebDriver** - Web automation with Explicit and Implicit Waits
✅ **Appium** - Mobile automation for Android/iOS applications
✅ **WinAppDriver** - Windows desktop application automation
✅ **RestAssured** - API automation with comprehensive assertions
✅ **ExtentReports** - Beautiful HTML test execution reports
✅ **TestNG** - Powerful test framework with parallel execution support
✅ **Log4j 2** - Comprehensive logging throughout the framework
✅ **Data-Driven Testing** - Support for JSON and properties-based test data
✅ **Synchronization** - Explicit waits, implicit waits, and custom wait conditions
✅ **Reusable Utilities** - Common methods for interactions and assertions

## Technology Stack (Tool Versions)

This section lists the main versions used by the framework.

| Component | Version |
|-----------|---------|
| Java | 11+ |
| Maven | 3.6+ |
| Selenium WebDriver | 4.15.0 |
| Appium | 9.0.0 |
| TestNG | 7.8.0 |
| RestAssured | 5.3.2 |
| ExtentReports | 5.0.9 |
| Log4j 2 | 2.20.0 |
| WebDriverManager | 5.6.2 |

## Project Structure

```
automation-testing-framework/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/automationframework/
│   │   │       ├── base/
│   │   │       │   └── DriverManager.java          # Driver initialization & management
│   │   │       ├── config/
│   │   │       │   └── ConfigurationManager.java   # Configuration file handling
│   │   │       ├── pages/
│   │   │       │   ├── BasePage.java              # Base POM class
│   │   │       │   ├── HomePage.java              # Home page object model
│   │   │       │   ├── LoginPage.java             # Login page object model
│   │   │       │   ├── RegisterPage.java          # Registration page object model
│   │   │       │   ├── CartPage.java              # Cart page object model
│   │   │       │   └── CheckoutPage.java          # Checkout page object model
│   │   │       ├── utilities/
│   │   │       │   ├── WaitUtility.java           # Explicit wait methods
│   │   │       │   ├── CommonUtilities.java       # Common interaction methods
│   │   │       │   └── ExtentReportManager.java   # Report generation
│   │   │       ├── api/
│   │   │       │   └── APIHelper.java             # API request/response handling
│   │   │       └── mobile/
│   │   │           └── (Mobile helper classes)
│   │   │
│   │   └── resources/
│   │       ├── config.properties                  # Configuration settings
│   │       ├── log4j2.xml                         # Logging configuration
│   │
│   └── test/
│       └── java/
│           └── com/automationframework/
│               ├── web/
│               │   └── WebAutomationTest.java     # Web automation test suite
│               ├── api/
│               │   └── APIAutomationTest.java     # API automation test suite
│               ├── mobile/
│               │   └── MobileAutomationTest.java  # Mobile automation test suite
│               └── windows/
│                   └── WindowsAutomationTest.java # Windows automation test suite
│
├── test-reports/                                  # Execution reports (generated)
├── logs/                                          # Log files (generated)
├── pom.xml                                        # Maven POM file with dependencies
├── testng.xml                                     # TestNG configuration
├── .gitignore                                     # Git ignore rules
└── README.md                                      # This file
```

## Setup Instructions

Follow these steps to prepare the local environment before running tests.

### Prerequisites

1. **Java Development Kit (JDK)**
   - Download and install Java 11+
   - Set JAVA_HOME environment variable
   ```bash
   # Verify Java installation
   java -version
   ```

2. **Maven**
   - Download and install Apache Maven 3.6+
   - Set MAVEN_HOME environment variable
   ```bash
   # Verify Maven installation
   mvn -version
   ```

3. **Git** (for version control)
   ```bash
   git --version
   ```

### Step 1: Clone or Download the Project

```bash
# Clone the repository
git clone <repository-url>
cd automation-testing-framework

# OR download and extract the project folder
```

### Step 2: Install Dependencies

Navigate to project root and run:

```bash
mvn clean install
```

This will download all required dependencies specified in `pom.xml`.

### Step 3: WebDriver Setup

**Chrome:**
```bash
# WebDriverManager handles Chrome driver automatically
# No manual download needed
```

**Firefox:**
```bash
# WebDriverManager handles Firefox driver automatically
```

**Edge:**
```bash
# WebDriverManager handles Edge driver automatically
```

### Step 4: Appium Setup (for Mobile Testing)

```bash
# Install Node.js (if not already installed)
# Download from https://nodejs.org/

# Install Appium globally
npm install -g appium

# Install Appium Desktop (optional, for UI inspection)
# Download from https://github.com/appium/appium-desktop

# Start Appium server
appium
# Server will start at http://localhost:4723
```

### Step 5: WinAppDriver Setup (for Windows Desktop Testing)

```bash
# Download WinAppDriver from:
# https://github.com/microsoft/WinAppDriver/releases

# Extract and run WinAppDriver.exe
# Server will start at http://127.0.0.1:4723

# Note: Windows 10 or later required
```

### Step 6: Android Emulator/Device Setup (for Mobile Testing)

```bash
# Install Android SDK
# Download Android Studio from https://developer.android.com/studio

# Create or connect an Android virtual device (emulator)
# Or connect a physical Android device via USB

# Verify device connection
adb devices
```

## Configuration

### 1. Update `config.properties`

Edit `src/main/resources/config.properties` to match your environment:

```properties
# Browser Configuration
browser=chrome                    # Options: chrome, firefox, edge
headless=false                   # Run in headless mode
implicitWait=10                 # Implicit wait in seconds
explicitWait=15                 # Explicit wait in seconds
pageLoadTimeout=20              # Page load timeout
scriptTimeout=10                # JavaScript execution timeout

# Application URLs
web.baseURL=https://automationexercise.com
api.baseURL=https://reqres.in

# Appium Configuration
appium.server.url=http://localhost:4723

# Mobile Configuration
mobile.platformName=Android
mobile.automationName=UiAutomator2
mobile.deviceName=emulator-5554          # Update with your device name
mobile.appPackage=com.android.calculator2
mobile.appActivity=com.android.calculator2.Calculator

# Windows Configuration
windows.app=Notepad

# Logging
log.level=INFO
log.path=logs
```

### 2. Update Configuration

Edit `src/main/resources/config.properties` with your environment and test data values.

```properties
# Web Automation Configuration
browser=chrome
headless=false
implicitWait=10
explicitWait=30
pageLoadTimeout=30
scriptTimeout=20
window.maximize=true

# Web Application URLs
web.baseURL=https://automationexercise.com

# API Configuration
api.baseURL=https://reqres.in
api.timeout=10000
```
## Execution (How to execute tests)

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Suite

```bash
# Run Web automation tests only
mvn test -Dtest=WebAutomationTest

# Run API automation tests only
mvn test -Dtest=APIAutomationTest

# Run Mobile automation tests only
mvn test -Dtest=MobileAutomationTest

# Run Windows automation tests only
mvn test -Dtest=WindowsAutomationTest
```

### Run Specific Test Methods

```bash
mvn test -Dtest=WebAutomationTest#testUserRegistration
```

### Run Tests in Parallel

Update `testng.xml` to enable parallel execution:

```xml
<suite name="Automation Suite" parallel="tests" thread-count="3">
```

Then run:

```bash
mvn test
```

### Run Tests with Maven Surefire

```bash
# Run all tests
mvn surefire:test

# Run with custom testng.xml
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```

## Test Scenarios

### 1. Web Automation Tests (`WebAutomationTest.java`)

**Scenario 1: User Registration**
- Navigate to the website
- Fill registration form with valid data
- Verify account creation

**Scenario 2: User Login**
- Navigate to login page
- Enter valid credentials
- Verify successful login

**Scenario 3: Add Product to Cart**
- Navigate to products page
- Add a product to cart
- Verify product in cart

**Scenario 4: Checkout**
- Add product to cart
- Proceed to checkout
- Validate order summary

### 2. API Automation Tests (`APIAutomationTest.java`)

**CRUD Operations:**
- **CREATE**: POST /api/users - Create new user
- **READ**: GET /api/users/{id} - Retrieve user
- **UPDATE**: PUT /api/users/{id} - Update user
- **DELETE**: DELETE /api/users/{id} - Delete user

**Negative Test Cases:**
- Invalid endpoint
- Invalid payload (malformed JSON)
- Non-existing user (404 response)

### 3. Mobile Automation Tests (`MobileAutomationTest.java`)

**Calculator Operations:**
- Launch calculator app
- Perform complex arithmetic: (25 + 15) × 3 − 10 = 110
- Validate result = 110
- Clear calculation

**Prerequisites:**
- Appium server running
- Android emulator/device connected

### 4. Windows Automation Tests (`WindowsAutomationTest.java`)

**Notepad Operations:**
- Launch Notepad
- Enter multi-line text
- Save file with dynamic filename
- Close Notepad
- Reopen saved file
- Validate content

**Prerequisites:**
- WinAppDriver running
- Windows 10 or later

## Framework Components

### Base Classes

**BasePage.java**
- Extends POM pattern to all pages
- Provides wrapper methods for common actions
- Handles element interactions and waits

**DriverManager.java**
- Manages Selenium, Appium, and WinAppDriver instances
- Uses ThreadLocal for thread safety
- Provides initialization and teardown methods

### Utility Classes

**CommonUtilities.java**
- Element interaction methods (click, type, hover, etc.)
- Page navigation methods
- JavaScript execution
- Element visibility checks

**WaitUtility.java**
- Explicit wait methods
- Custom wait conditions
- Element visibility/clickability/presence waits
- Page title and URL waits

**ExtentReportManager.java**
- Test report generation
- Test status logging (Pass/Fail/Skip/Info/Warning)
- Screenshot capture capability
- HTML report generation

**APIHelper.java**
- RESTful API request handling
- HTTP method wrappers (GET, POST, PUT, DELETE, PATCH)
- Request/response validation
- Header management

### Configuration Management

**ConfigurationManager.java**
- Loads properties from `config.properties`
- Provides getter methods with default values
- Type conversion (int, long, boolean)
- Runtime configuration access

## Reports

### ExtentReports

Test execution reports are generated in HTML format:

```
test-reports/
├── ExtentReport_yyyy-MM-dd HH-mm-ss.html
```

**Report Features:**
- Overall test summary
- Individual test details
- Pass/Fail/Skip statistics
- Execution timeline
- System information
- Detailed logs for each test

**View Report:**
```bash
# Open the HTML file in browser
start test-reports/ExtentReport_*.html
```

### Logging

Logs are generated in:
```
logs/
└── automation.log
```

## Troubleshooting

### Common Issues

#### 1. WebDriver Not Found
```
Solution: Run 'mvn clean install' to download WebDriverManager
```

#### 2. Appium Connection Failed
```
Solution: 
1. Ensure Appium server is running: appium
2. Check Appium URL in config.properties
3. Verify device is connected: adb devices
```

#### 3. Element Not Found
```
Solution:
1. Check element locators in page objects
2. Add explicit waits in CommonUtilities
3. Verify element is visible/clickable
4. Use browser dev tools (F12) to inspect element
```

#### 4. WinAppDriver Issues
```
Solution:
1. Ensure WinAppDriver.exe is running
2. Windows 10/11 required
3. Check Windows app path in config.properties
```

#### 5. Maven Compilation Error
```
Solution:
1. Verify Java version: java -version
2. Set JAVA_HOME environment variable
3. Run: mvn clean compile
4. Check internet connection for dependency download
```

#### 6. Report Not Generated
```
Solution:
1. Ensure test-reports directory is writable
2. Check ExtentReportManager initialization
3. Verify flushReports() is called in @AfterClass
```

### Debug Mode

Enable debug logging:

```properties
# In config.properties
log.level=DEBUG
```

## Framework Extensions

### Adding New Test Scenarios

1. **Create Page Object**
```java
public class NewPage extends BasePage {
    private static final By ELEMENT = By.id("element_id");
    
    public void performAction() {
        clickElement(ELEMENT);
    }
}
```

2. **Create Test Class**
```java
public class NewTest {
    @BeforeClass
    public void setUp() {
        DriverManager.initializeWebDriver();
    }
    
    @Test
    public void testScenario() {
        // Test implementation
    }
    
    @AfterClass
    public void tearDown() {
        DriverManager.quitWebDriver();
    }
}
```

3. **Add to testng.xml**

### Adding New Page Objects

Follow the Page Object Model pattern:
- Locators as static final constants
- Methods for page interactions
- Assertions in test classes
- Logging in every action

## Best Practices

✅ Use meaningful variable and method names
✅ Keep tests independent and reusable
✅ Follow Single Responsibility Principle
✅ Use explicit waits instead of Thread.sleep()
✅ Log important steps and validations
✅ Maintain test data separately
✅ Use @BeforeClass and @AfterClass for setup/teardown
✅ Group related tests using priority
✅ Avoid hardcoded values in tests
✅ Keep page objects focused on UI interactions

## Git Commands

```bash
# Initialize git (if not done)
git init

# Add files
git add .

# Commit changes
git commit -m "Initial framework setup with Web, API, Mobile, and Windows automation"

# Add remote repository
git remote add origin <repository-url>

# Push to repository
git push -u origin main
```

## Support & Contributing

For issues or contributions:
1. Create a new issue describing the problem
2. Fork the repository
3. Create a feature branch
4. Commit changes with clear messages
5. Submit a pull request

## License

This project is licensed under the MIT License - see LICENSE file for details.

## Author

**Automation Framework Developer**
- Framework: Comprehensive Multi-Platform Automation
- Version: 1.0.0
- Created: 2024

---

For more information, refer to official documentation:
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Appium Documentation](http://appium.io/docs/en/about-appium/intro/)
- [TestNG Documentation](https://testng.org/doc/)
- [RestAssured Wiki](https://github.com/rest-assured/rest-assured/wiki)
- [ExtentReports Documentation](https://www.extentreports.com/docs/versions/5/java/)
