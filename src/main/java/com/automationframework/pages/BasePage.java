package com.automationframework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import com.automationframework.base.DriverManager;
import com.automationframework.utilities.CommonUtilities;
import com.automationframework.utilities.WaitUtility;

public class BasePage {
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getWebDriver();
    }

    protected void clickElement(org.openqa.selenium.By locator) {
        CommonUtilities.click(locator);
    }

    protected void typeText(org.openqa.selenium.By locator, String text) {
        CommonUtilities.type(locator, text);
    }

    protected String getElementText(org.openqa.selenium.By locator) {
        return CommonUtilities.getText(locator);
    }

    protected String getAttributeValue(org.openqa.selenium.By locator, String attributeName) {
        return CommonUtilities.getAttribute(locator, attributeName);
    }

    protected boolean isElementDisplayed(org.openqa.selenium.By locator) {
        return CommonUtilities.isElementDisplayed(locator);
    }

    protected void waitForElement(org.openqa.selenium.By locator) {
        WaitUtility.waitForElementToBeVisible(locator);
    }

    protected void hoverElement(org.openqa.selenium.By locator) {
        CommonUtilities.hover(locator);
    }

    protected void scrollToElement(org.openqa.selenium.By locator) {
        CommonUtilities.scrollToElement(locator);
    }

    protected void navigateToURL(String url) {
        CommonUtilities.navigateToURL(url);
    }

    protected String getCurrentURL() {
        return CommonUtilities.getCurrentURL();
    }

    protected String getPageTitle() {
        return CommonUtilities.getPageTitle();
    }

    protected void refreshPage() {
        CommonUtilities.refreshPage();
    }

    protected boolean isElementPresent(org.openqa.selenium.By locator) {
        return CommonUtilities.isElementPresent(locator);
    }
}
