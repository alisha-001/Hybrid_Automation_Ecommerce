# Quick Start Guide

## 5-Minute Setup

### Prerequisites
- Java 11+
- Maven 3.6+
- Browser (Chrome, Firefox, or Edge)

### Step 1: Install Dependencies (3 minutes)
```bash
cd automation-testing-framework
mvn clean install
```

### Step 2: Run Sample Tests (2 minutes)
```bash
# Run API tests (no setup needed)
mvn test -Dtest=APIAutomationTest
```

### Step 3: Check Reports
```bash
# Reports generated in test-reports/
# Open: test-reports/ExtentReport_*.html in browser
```

## Complete Test Execution

### Web Automation (automationexercise.com)
```bash
mvn test -Dtest=WebAutomationTest
```
**Tests:**
- User Registration
- User Login
- Add Product to Cart
- Checkout & Order Validation

### API Automation (reqres.in)
```bash
mvn test -Dtest=APIAutomationTest
```
**Tests:**
- Create User (POST)
- Get User (GET)
- Update User (PUT)
- Delete User (DELETE)
- Negative Tests (Invalid endpoint, Invalid payload, Non-existing user)

### Mobile Automation (Android Calculator)
```bash
# Start Appium first
appium

# Run in another terminal
mvn test -Dtest=MobileAutomationTest
```
**Prerequisites:**
- Appium server running
- Android emulator/device connected

### Windows Automation (Notepad)
```bash
# Start WinAppDriver.exe first

# Run in another terminal
mvn test -Dtest=WindowsAutomationTest
```
**Prerequisites:**
- WinAppDriver running
- Windows 10/11

## All Tests
```bash
mvn clean test
```

## Common Commands

### Change Browser
```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

### Headless Mode
```bash
mvn test -Dheadless=true
```

### Custom Timeout
```bash
mvn test -Dexplicit.wait=20
```

### Specific Test Method
```bash
mvn test -Dtest=WebAutomationTest#testUserRegistration
```

### Generate Only Report (No Execution)
```bash
mvn clean compile
```

## Project Structure Highlights

```
✓ src/main/java/com/automationframework/
  ├─ base/           → DriverManager (browser/Appium/WinAppDriver)
  ├─ config/         → ConfigurationManager
  ├─ pages/          → Page Object Models
  ├─ utilities/      → Common methods, waits, reporting
  └─ api/            → API helper

✓ src/test/java/com/automationframework/
  ├─ web/            → Web automation tests
  ├─ api/            → API automation tests
  ├─ mobile/         → Mobile automation tests
  └─ windows/        → Windows automation tests

✓ src/main/resources/
  ├─ config.properties    → Configuration settings
  ├─ testdata.json       → Test data
  └─ log4j2.xml          → Logging config

✓ Test Reports
  └─ test-reports/       → HTML reports (auto-generated)
```

## Framework Features

| Feature | Implementation |
|---------|-----------------|
| **Page Object Model** | BasePage + individual page classes |
| **Web Automation** | Selenium WebDriver 4.15.0 |
| **Mobile Automation** | Appium 9.0.0 |
| **Windows Automation** | WinAppDriver |
| **API Testing** | RestAssured 5.3.2 |
| **Test Framework** | TestNG 7.8.1 |
| **Reporting** | ExtentReports 5.0.9 |
| **Logging** | Log4j 2 |
| **Waits** | Explicit & Implicit |
| **Configuration** | Properties & JSON files |

## File Descriptions

### Main Framework Files

**Base Classes:**
- `DriverManager.java` - Manages all driver instances
- `BasePage.java` - Base for all page objects

**Utilities:**
- `CommonUtilities.java` - Element interactions (click, type, etc.)
- `WaitUtility.java` - Explicit wait implementations
- `ExtentReportManager.java` - Report generation
- `APIHelper.java` - REST API operations

**Page Objects:**
- `HomePage.java` - Home page operations
- `LoginPage.java` - Login/Signup operations
- `RegisterPage.java` - Registration form
- `CartPage.java` - Shopping cart operations
- `CheckoutPage.java` - Checkout operations

**Test Classes:**
- `WebAutomationTest.java` - Web test cases
- `APIAutomationTest.java` - API test cases
- `MobileAutomationTest.java` - Mobile test cases
- `WindowsAutomationTest.java` - Windows test cases

### Configuration Files

- `config.properties` - Application settings
- `testdata.json` - Test data for tests
- `log4j2.xml` - Logging configuration
- `testng.xml` - TestNG suite configuration
- `pom.xml` - Maven project configuration

## Customization

### Change Application URL
```properties
# In config.properties
web.baseURL=https://your-app-url.com
api.baseURL=https://your-api-url.com
```

### Change Wait Times
```properties
explicitWait=20
pageLoadTimeout=30
scriptTimeout=15
```

### Change Browser
```properties
browser=firefox
# or
browser=edge
```

### Add New Test Data
Edit `testdata.json`:
```json
{
  "newTestData": {
    "key": "value"
  }
}
```

## Report Locations

- **Test Reports**: `test-reports/ExtentReport_yyyy-MM-dd HH-mm-ss.html`
- **Logs**: `logs/automation-test.log`
- **Maven Build**: `target/`

## Troubleshooting Quick Fixes

| Issue | Fix |
|-------|-----|
| `mvn: command not found` | Add Maven/bin to PATH |
| `java: command not found` | Add JAVA_HOME/bin to PATH |
| WebDriver not found | Run `mvn clean install` |
| Appium connection failed | Ensure `appium` server running |
| Tests skip/fail | Check locators in page objects |
| Report not generated | Verify test-reports dir is writable |

## Git Commands for Repository

```bash
# Initialize repository
git init
git add .
git commit -m "Initial commit: Automation Framework"

# Add remote
git remote add origin <url>

# Push to repository
git push -u origin main

# Regular workflow
git pull origin main
git add .
git commit -m "message"
git push origin main
```

## Next Steps

1. ✅ Run `mvn clean install` to download dependencies
2. ✅ Update `config.properties` with your URLs
3. ✅ Run API tests: `mvn test -Dtest=APIAutomationTest`
4. ✅ Check reports in `test-reports/`
5. ✅ Explore page objects and test cases
6. ✅ Add your own test scenarios
7. ✅ Push to Git repository
8. ✅ Present to team during review call

## Support

- **Selenium**: https://www.selenium.dev/
- **TestNG**: https://testng.org/
- **Appium**: http://appium.io/
- **RestAssured**: https://rest-assured.io/
- **ExtentReports**: https://www.extentreports.com/

---

**Happy Testing! 🚀**

For detailed information, see `README.md` and `SETUP_INSTRUCTIONS.md`
