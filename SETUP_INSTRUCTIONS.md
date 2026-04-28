# Setup and Execution Instructions

## Quick Start Guide

### Prerequisites Checklist

- [ ] Java 11+ installed and JAVA_HOME set
- [ ] Maven 3.6+ installed
- [ ] Git installed (for version control)
- [ ] Chrome/Firefox/Edge browser installed (for web tests)
- [ ] Appium and Android SDK (for mobile tests)
- [ ] WinAppDriver (for Windows desktop tests)

## Installation Steps

### 1. Install Java

```powershell
# Download Java 11+ from https://www.oracle.com/java/technologies/downloads/

# Verify installation
java -version
javac -version

# Set JAVA_HOME (Windows)
# Add to System Environment Variables or run:
$env:JAVA_HOME = "C:\Program Files\Java\jdk-11"
[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Java\jdk-11", "Machine")
```

### 2. Install Maven

```powershell
# Download from https://maven.apache.org/download.cgi
# Extract to a folder, e.g., C:\apache-maven-3.8.1

# Set MAVEN_HOME
$env:MAVEN_HOME = "C:\apache-maven-3.8.1"
[Environment]::SetEnvironmentVariable("MAVEN_HOME", "C:\apache-maven-3.8.1", "Machine")

# Add to PATH
$env:Path += ";C:\apache-maven-3.8.1\bin"

# Verify installation
mvn -version
```

### 3. Install Git

```powershell
# Download from https://git-scm.com/download/win
# Run the installer and follow the prompts

# Verify installation
git --version

# Configure Git
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### 4. Install Appium (for Mobile Testing)

```powershell
# Install Node.js from https://nodejs.org/ (LTS version)

# Install Appium globally
npm install -g appium

# Install Appium Doctor to check dependencies
npm install -g appium-doctor

# Check Appium installation
appium-doctor --android

# Start Appium server (before running mobile tests)
appium
# Server runs on http://localhost:4723
```

### 5. Install WinAppDriver (for Windows Desktop Testing)

```powershell
# Download from https://github.com/microsoft/WinAppDriver/releases
# Extract and run WinAppDriver.exe
# Server runs on http://127.0.0.1:4723

# Note: Windows 10/11 required
# Enable Developer Mode in Settings
```

## Framework Setup

### 1. Download/Clone the Project

```powershell
# Clone from Git
git clone <repository-url>
cd automation-testing-framework

# OR download ZIP and extract
# cd path\to\automation-testing-framework
```

### 2. Install Dependencies

```powershell
# Navigate to project root
cd automation-testing-framework

# Download all dependencies
mvn clean install

# This may take 5-10 minutes on first run
```

### 3. Configure Application Settings

Edit `src\main\resources\config.properties`:

```properties
# Web Browser (chrome, firefox, edge)
browser=chrome
headless=false

# Timeouts (in seconds)
implicitWait=10
explicitWait=15
pageLoadTimeout=20

# API Base URL
api.baseURL=https://reqres.in

# Appium Server URL
appium.server.url=http://localhost:4723

# Mobile Device Name (use: adb devices)
mobile.deviceName=emulator-5554

# Logging Level
log.level=INFO
```

### 4. Update Test Data

Edit `src\main\resources\testdata.json`:

```json
{
  "users": [
    {
      "email": "your.test@email.com",
      "password": "YourTestPassword@123"
    }
  ]
}
```

## Running Tests

### Run All Tests

```powershell
cd automation-testing-framework
mvn clean test
```

### Run Web Tests Only

```powershell
mvn test -Dtest=WebAutomationTest
```

### Run API Tests Only

```powershell
mvn test -Dtest=APIAutomationTest
```

### Run Specific Test Method

```powershell
mvn test -Dtest=WebAutomationTest#testUserRegistration
```

### Run Mobile Tests (requires Appium)

```powershell
# Start Appium server in separate terminal
appium

# In another terminal, run tests
mvn test -Dtest=MobileAutomationTest
```

### Run Windows Desktop Tests (requires WinAppDriver)

```powershell
# Start WinAppDriver.exe

# In another terminal, run tests
mvn test -Dtest=WindowsAutomationTest
```

### Run with Different Browser

```powershell
# Using Firefox
mvn test -Dbrowser=firefox

# Using Edge
mvn test -Dbrowser=edge

# Headless mode
mvn test -Dheadless=true
```

## Git Repository Setup

### Initialize Local Git Repository

```powershell
cd automation-testing-framework

# Initialize git
git init

# Add all files
git add .

# First commit
git commit -m "Initial commit: Comprehensive automation testing framework with Web, API, Mobile, and Windows automation"

# View commit history
git log --oneline
```

### Create Remote Repository

1. **GitHub**
   - Go to https://github.com/new
   - Create new repository
   - Do NOT initialize with README
   - Copy the HTTPS URL

2. **GitLab**
   - Go to https://gitlab.com/projects/new
   - Create new project
   - Copy the HTTPS URL

3. **Bitbucket**
   - Go to https://bitbucket.org/repo/create
   - Create new repository
   - Copy the HTTPS URL

### Push to Remote Repository

```powershell
# Add remote repository
git remote add origin https://github.com/username/automation-testing-framework.git

# Rename branch to main (if needed)
git branch -M main

# Push to remote
git push -u origin main

# For future pushes
git push

# View remote status
git remote -v
```

### Typical Git Workflow

```powershell
# Before making changes
git pull origin main

# Make changes to files

# Check changed files
git status

# Stage changes
git add .

# Commit changes
git commit -m "Meaningful commit message"

# Push changes
git push origin main
```

## Verify Installation

### Check Project Structure

```powershell
# List key directories
dir src\main\java\com\automationframework
dir src\main\resources
dir src\test\java\com\automationframework
dir test-reports

# Should show all framework components
```

### Test Framework Setup

```powershell
# Compile project
mvn clean compile

# Run only API tests (quickest to verify)
mvn test -Dtest=APIAutomationTest

# Check for test reports
dir test-reports\
```

## Troubleshooting

### Issue: "Maven command not found"

```powershell
# Add Maven to PATH
$env:Path += ";C:\apache-maven-3.8.1\bin"

# Or set permanently in System Properties
# Advanced > Environment Variables > New > Variable name: MAVEN_HOME
```

### Issue: "Java not found"

```powershell
# Check Java installation
java -version

# Set JAVA_HOME if not set
$env:JAVA_HOME = "C:\Program Files\Java\jdk-11"

# Add to PATH
$env:Path += ";$env:JAVA_HOME\bin"
```

### Issue: "Build fails with dependency errors"

```powershell
# Clear Maven cache
mvn clean
del %USERPROFILE%\.m2\repository

# Reinstall dependencies
mvn clean install
```

### Issue: "Chrome driver not found"

```powershell
# WebDriverManager should handle this automatically
# If not, clear Maven cache
mvn dependency:resolve -DdownloadSources

# Ensure Chrome is installed
```

### Issue: "Appium connection refused"

```powershell
# Ensure Appium server is running
appium

# Check port availability
netstat -ano | findstr :4723

# If port in use, change appium.server.url in config.properties
appium --port 4724
```

### Issue: "Device not found in Appium"

```powershell
# List connected devices
adb devices

# Install APK on device/emulator
adb install path\to\app.apk

# Check adb path is in system PATH
```

## Performance Tips

### Optimize Test Execution

```powershell
# Run tests in parallel
# Edit testng.xml:
# <suite name="Suite" parallel="tests" thread-count="4">

# Use Maven for parallel execution
mvn test -Dparallel=methods -DthreadCount=4
```

### Reduce Build Time

```powershell
# Skip tests during compilation
mvn clean package -DskipTests

# Offline mode (if dependencies cached)
mvn -o clean install
```

## Next Steps

1. ✅ Complete framework installation
2. ✅ Update configuration files
3. ✅ Run sample tests to verify setup
4. ✅ Review Page Object Model implementation
5. ✅ Add custom test cases
6. ✅ Configure CI/CD pipeline (Jenkins, GitHub Actions, etc.)
7. ✅ Share with team and integrate into SDLC

## Support Resources

- [Selenium Documentation](https://www.selenium.dev/)
- [TestNG Guide](https://testng.org/doc/)
- [Appium Tutorial](http://appium.io/docs/en/about-appium/intro/)
- [RestAssured Guide](https://github.com/rest-assured/rest-assured/wiki)
- [Maven Documentation](https://maven.apache.org/guides/)
- [ExtentReports](https://www.extentreports.com/)

---

**Note**: For live demonstration during review call, ensure:
- All prerequisites installed
- config.properties updated with correct URLs/credentials
- Test browsers installed
- At least API tests can run (requires no special setup)
- Reports generated successfully

Good luck with your automation testing! 🚀
