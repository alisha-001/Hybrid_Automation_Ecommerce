package com.automationframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import com.automationframework.utilities.ExtentReportManager;

public class RegisterPage extends BasePage {

    private static final By TITLE_MR = By.id("id_gender1");
    private static final By TITLE_MRS = By.id("id_gender2");
    private static final By PASSWORD = By.id("password");
    private static final By FIRST_NAME = By.id("first_name");
    private static final By LAST_NAME = By.id("last_name");
    private static final By COMPANY = By.id("company");
    private static final By ADDRESS1 = By.id("address1");
    private static final By ADDRESS2 = By.id("address2");
    private static final By COUNTRY = By.id("country");
    private static final By STATE = By.id("state");
    private static final By CITY = By.id("city");
    private static final By ZIPCODE = By.id("zipcode");
    private static final By MOBILE_NUMBER = By.id("mobile_number");
    private static final By CREATE_ACCOUNT_BUTTON = By.xpath("//button[@data-qa='create-account']");
    private static final By ACCOUNT_CREATED_MESSAGE = By.xpath("//h2[@data-qa='account-created']");

    public RegisterPage() {
        super();
    }

    /**
     * Fill registration form
     */
    public void fillRegistrationForm(String title, String password, String firstName, String lastName,
                                     String company, String address1, String address2,
                                     String country, String state, String city, String zipcode, String mobileNumber) {
        try {
            // Select title
            if ("Mr".equalsIgnoreCase(title)) {
                clickElement(TITLE_MR);
            } else if ("Mrs".equalsIgnoreCase(title)) {
                clickElement(TITLE_MRS);
            }

            // Fill other fields
            typeText(PASSWORD, password);
            typeText(FIRST_NAME, firstName);
            typeText(LAST_NAME, lastName);
            typeText(COMPANY, company);
            typeText(ADDRESS1, address1);
            typeText(ADDRESS2, address2);
            
            // Select country
            Select countrySelect = new Select(driver.findElement(COUNTRY));
            countrySelect.selectByValue(country);
            
            typeText(STATE, state);
            typeText(CITY, city);
            typeText(ZIPCODE, zipcode);
            typeText(MOBILE_NUMBER, mobileNumber);

            logger.info("Registration form filled successfully");
            ExtentReportManager.logInfo("Registration form filled successfully");

        } catch (Exception e) {
            logger.error("Failed to fill registration form: " + e.getMessage());
            ExtentReportManager.logFail("Failed to fill registration form: " + e.getMessage());
            throw new RuntimeException("Failed to fill registration form", e);
        }
    }

    /**
     * Create account
     */
    public void createAccount() {
        try {
            clickElement(CREATE_ACCOUNT_BUTTON);
            logger.info("Account creation initiated");
            ExtentReportManager.logInfo("Account creation initiated");
        } catch (Exception e) {
            logger.error("Failed to create account: " + e.getMessage());
            ExtentReportManager.logFail("Failed to create account: " + e.getMessage());
            throw new RuntimeException("Failed to create account", e);
        }
    }

    /**
     * Check if account created message is displayed
     */
    public boolean isAccountCreatedMessageDisplayed() {
        try {
            return isElementDisplayed(ACCOUNT_CREATED_MESSAGE);
        } catch (Exception e) {
            logger.warn("Account created message not displayed");
            return false;
        }
    }

    /**
     * Verify registration page is loaded
     */
    public boolean isRegistrationPageLoaded() {
        try {
            return isElementPresent(TITLE_MR) && isElementPresent(PASSWORD);
        } catch (Exception e) {
            logger.error("Failed to verify registration page: " + e.getMessage());
            return false;
        }
    }
}
