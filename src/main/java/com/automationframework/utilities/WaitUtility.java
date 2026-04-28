package com.automationframework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automationframework.base.DriverManager;
import com.automationframework.config.ConfigurationManager;

import java.time.Duration;

public class WaitUtility {
    private static final Logger logger = LogManager.getLogger(WaitUtility.class);
    private static final long DEFAULT_TIMEOUT = ConfigurationManager.getLongProperty("explicitWait", 10);

    private WaitUtility() {
    }

    /**
     * Wait for element to be visible
     */
    public static WebElement waitForElementToBeVisible(By locator) {
        return waitForElementToBeVisible(locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementToBeVisible(By locator, long timeoutInSeconds) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            logger.info("Waiting for element to be visible: " + locator);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not visible within timeout: " + locator);
            throw new RuntimeException("Element not visible: " + locator, e);
        }
    }

    /**
     * Wait for element to be clickable
     */
    public static WebElement waitForElementToBeClickable(By locator) {
        return waitForElementToBeClickable(locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementToBeClickable(By locator, long timeoutInSeconds) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            logger.info("Waiting for element to be clickable: " + locator);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            logger.error("Element not clickable within timeout: " + locator);
            throw new RuntimeException("Element not clickable: " + locator, e);
        }
    }

    /**
     * Wait for element to be present
     */
    public static WebElement waitForElementToBePresent(By locator) {
        return waitForElementToBePresent(locator, DEFAULT_TIMEOUT);
    }

    public static WebElement waitForElementToBePresent(By locator, long timeoutInSeconds) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            logger.info("Waiting for element to be present: " + locator);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not present within timeout: " + locator);
            throw new RuntimeException("Element not present: " + locator, e);
        }
    }

    /**
     * Wait for element to disappear
     */
    public static boolean waitForElementToDisappear(By locator) {
        return waitForElementToDisappear(locator, DEFAULT_TIMEOUT);
    }

    public static boolean waitForElementToDisappear(By locator, long timeoutInSeconds) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            logger.info("Waiting for element to disappear: " + locator);
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.warn("Element did not disappear within timeout: " + locator);
            return false;
        }
    }

    /**
     * Wait for title to contain text
     */
    public static boolean waitForTitleToContain(String titlePart) {
        return waitForTitleToContain(titlePart, DEFAULT_TIMEOUT);
    }

    public static boolean waitForTitleToContain(String titlePart, long timeoutInSeconds) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            logger.info("Waiting for title to contain: " + titlePart);
            return wait.until(ExpectedConditions.titleContains(titlePart));
        } catch (Exception e) {
            logger.error("Title did not contain expected text: " + titlePart);
            return false;
        }
    }

    /**
     * Wait for URL to contain text
     */
    public static boolean waitForURLToContain(String urlPart) {
        return waitForURLToContain(urlPart, DEFAULT_TIMEOUT);
    }

    public static boolean waitForURLToContain(String urlPart, long timeoutInSeconds) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            logger.info("Waiting for URL to contain: " + urlPart);
            return wait.until(ExpectedConditions.urlContains(urlPart));
        } catch (Exception e) {
            logger.error("URL did not contain expected text: " + urlPart);
            return false;
        }
    }

    /**
     * Wait for text to be present in element
     */
    public static boolean waitForTextToBePresentInElement(By locator, String text) {
        return waitForTextToBePresentInElement(locator, text, DEFAULT_TIMEOUT);
    }

    public static boolean waitForTextToBePresentInElement(By locator, String text, long timeoutInSeconds) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            logger.info("Waiting for text '" + text + "' to be present in element: " + locator);
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (Exception e) {
            logger.error("Text not present in element within timeout: " + locator);
            return false;
        }
    }

    /**
     * Wait for number of elements to be present
     */
    public static boolean waitForNumberOfElementsToBe(By locator, int numberOfElements) {
        return waitForNumberOfElementsToBe(locator, numberOfElements, DEFAULT_TIMEOUT);
    }

    public static boolean waitForNumberOfElementsToBe(By locator, int numberOfElements, long timeoutInSeconds) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            logger.info("Waiting for " + numberOfElements + " elements with locator: " + locator);
            java.util.List<WebElement> elements = wait.until(ExpectedConditions.numberOfElementsToBe(locator, numberOfElements));
            return elements != null && elements.size() == numberOfElements;
        } catch (Exception e) {
            logger.error("Expected number of elements not found within timeout");
            return false;
        }
    }
}
