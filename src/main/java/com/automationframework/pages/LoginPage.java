package com.automationframework.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automationframework.base.DriverManager;
import com.automationframework.utilities.ExtentReportManager;
import com.automationframework.utilities.WaitUtility;

public class LoginPage extends BasePage {

    private static final By LOGIN_EMAIL = By.xpath("//input[@data-qa='login-email' or @name='email' or @id='login-email' or contains(@name,'email') or contains(@id,'email') or @type='email']");
    private static final By LOGIN_EMAIL_FALLBACK = By.xpath("//input[contains(@placeholder,'email') or contains(@placeholder,'Email') or @type='email']");
    private static final By LOGIN_FORM = By.xpath("//form[contains(translate(@action,'LOGIN','login'),'login') or contains(@class,'login') or contains(@id,'login')]");
    private static final By LOGIN_PASSWORD = By.xpath("//input[@data-qa='login-password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@data-qa='login-button']");
    private static final By SIGNUP_NAME = By.xpath("//input[@data-qa='signup-name']");
    private static final By SIGNUP_EMAIL = By.xpath("//input[@data-qa='signup-email']");
    private static final By SIGNUP_BUTTON = By.xpath("//button[@data-qa='signup-button']");
    private static final By LOGIN_ERROR = By.xpath("//p[contains(text(), 'Your email or password is incorrect')]");

    public LoginPage() {
        super();
    }

/**
 * Login with email and password
 */
public void login(String email, String password) {
    try {
        WebDriver driver = DriverManager.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find the login email field using primary or fallback locators
        By emailLocator = LOGIN_EMAIL;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_EMAIL));
        } catch (Exception e) {
            logger.warn("Primary login email locator not visible, trying fallback: " + e.getMessage());
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_EMAIL_FALLBACK));
            emailLocator = LOGIN_EMAIL_FALLBACK;
        }

        typeText(emailLocator, email);

        // Wait for password field
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_PASSWORD));
        typeText(LOGIN_PASSWORD, password);

        // Wait for login button and click
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        clickElement(LOGIN_BUTTON);

        logger.info("Logged in with email: " + email);
        ExtentReportManager.logInfo("Logged in with email: " + email);
    } catch (Exception e) {
        logger.error("Failed to login: " + e.getMessage());
        ExtentReportManager.logFail("Failed to login: " + e.getMessage());
        throw new RuntimeException("Failed to login", e);
    }
}


    /**
     * Signup for new account
     */
    public void signup(String name, String email) {
        try {
            typeText(SIGNUP_NAME, name);
            typeText(SIGNUP_EMAIL, email);
            clickElement(SIGNUP_BUTTON);
            logger.info("Signed up with email: " + email);
            ExtentReportManager.logInfo("Signed up with email: " + email);
        } catch (Exception e) {
            logger.error("Failed to signup: " + e.getMessage());
            ExtentReportManager.logFail("Failed to signup: " + e.getMessage());
            throw new RuntimeException("Failed to signup", e);
        }
    }

    /**
     * Check if login error is displayed
     */
    public boolean isLoginErrorDisplayed() {
        try {
            return isElementDisplayed(LOGIN_ERROR);
        } catch (Exception e) {
            logger.warn("Login error not displayed");
            return false;
        }
    }

    /**
     * Verify login page is loaded
     */
    public boolean isLoginPageLoaded() {
        try {
            // Primary check: login email field visible
            WaitUtility.waitForElementToBeVisible(LOGIN_EMAIL, 10);
            return true;
        } catch (Exception e) {
            logger.warn("Primary login email locator failed: " + e.getMessage());
        }

        try {
            // Secondary check: fallback input or login form present
            WaitUtility.waitForElementToBeVisible(LOGIN_EMAIL_FALLBACK, 10);
            return true;
        } catch (Exception e) {
            logger.warn("Fallback login email locator failed: " + e.getMessage());
        }

        try {
            WaitUtility.waitForElementToBeVisible(LOGIN_FORM, 10);
            return true;
        } catch (Exception e) {
            logger.error("Login page not loaded: " + e.getMessage());
            return false;
        }
    }
}
