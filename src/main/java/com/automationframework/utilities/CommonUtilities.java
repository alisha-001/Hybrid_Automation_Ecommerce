package com.automationframework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.automationframework.base.DriverManager;

public class CommonUtilities {
    private static final Logger logger = LogManager.getLogger(CommonUtilities.class);

    private CommonUtilities() {
    }

    /**
     * Click on an element with enhanced robustness
     */
    public static void click(By locator) {
        try {
            WebElement element = WaitUtility.waitForElementToBeClickable(locator);

            // Scroll element into view
            scrollToElement(locator);

            // Wait a bit for any animations/transitions
            Thread.sleep(500);

            try {
                element.click();
            } catch (Exception clickException) {
                logger.warn("Regular click failed, attempting JavaScript click for: " + locator);
                try {
                    JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getWebDriver();
                    jsExecutor.executeScript("arguments[0].click();", element);
                } catch (Exception jsException) {
                    logger.warn("JavaScript click failed, attempting Actions click for: " + locator);
                    Actions actions = new Actions(DriverManager.getWebDriver());
                    actions.moveToElement(element).click().perform();
                }
            }
            logger.info("Clicked on element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to click on element: " + locator);
            throw new RuntimeException("Failed to click on element: " + locator, e);
        }
    }

    /**
     * Type text in an element
     */
    public static void type(By locator, String text) {
        try {
            WebElement element = WaitUtility.waitForElementToBeVisible(locator);
            element.clear();
            element.sendKeys(text);
            logger.info("Typed text '" + text + "' in element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to type text in element: " + locator);
            throw new RuntimeException("Failed to type text in element: " + locator, e);
        }
    }

    /**
     * Get text from an element
     */
    public static String getText(By locator) {
        try {
            WebElement element = WaitUtility.waitForElementToBeVisible(locator);
            String text = element.getText();
            logger.info("Got text from element: " + locator + " = " + text);
            return text;
        } catch (Exception e) {
            logger.warn("Visibility wait failed for text retrieval, trying presence-based text retrieval: " + locator);
            try {
                WebElement element = WaitUtility.waitForElementToBePresent(locator);
                String text = element.getText();
                logger.info("Got text from present element: " + locator + " = " + text);
                return text;
            } catch (Exception inner) {
                logger.error("Failed to get text from element: " + locator);
                throw new RuntimeException("Failed to get text from element: " + locator, inner);
            }
        }
    }

    /**
     * Get attribute value
     */
    public static String getAttribute(By locator, String attributeName) {
        try {
            WebElement element = WaitUtility.waitForElementToBeVisible(locator);
            String attributeValue = element.getAttribute(attributeName);
            logger.info("Got attribute '" + attributeName + "' from element: " + locator + " = " + attributeValue);
            return attributeValue;
        } catch (Exception e) {
            logger.error("Failed to get attribute from element: " + locator);
            throw new RuntimeException("Failed to get attribute from element: " + locator, e);
        }
    }

    /**
     * Check if element is displayed
     */
    public static boolean isElementDisplayed(By locator) {
        try {
            WebElement element = WaitUtility.waitForElementToBeVisible(locator);
            boolean isDisplayed = element.isDisplayed();
            logger.info("Element displayed status: " + locator + " = " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            logger.warn("Element is not displayed: " + locator);
            return false;
        }
    }

    /**
     * Check if element is enabled
     */
    public static boolean isElementEnabled(By locator) {
        try {
            WebElement element = WaitUtility.waitForElementToBePresent(locator);
            boolean isEnabled = element.isEnabled();
            logger.info("Element enabled status: " + locator + " = " + isEnabled);
            return isEnabled;
        } catch (Exception e) {
            logger.warn("Failed to check if element is enabled: " + locator);
            return false;
        }
    }

    /**
     * Hover over an element
     */
    public static void hover(By locator) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebElement element = WaitUtility.waitForElementToBeVisible(locator);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            logger.info("Hovered over element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to hover over element: " + locator);
            throw new RuntimeException("Failed to hover over element: " + locator, e);
        }
    }

    /**
     * Double click on element
     */
    public static void doubleClick(By locator) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebElement element = WaitUtility.waitForElementToBeClickable(locator);
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform();
            logger.info("Double clicked on element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to double click on element: " + locator);
            throw new RuntimeException("Failed to double click on element: " + locator, e);
        }
    }

    /**
     * Right click on element
     */
    public static void rightClick(By locator) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebElement element = WaitUtility.waitForElementToBeClickable(locator);
            Actions actions = new Actions(driver);
            actions.contextClick(element).perform();
            logger.info("Right clicked on element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to right click on element: " + locator);
            throw new RuntimeException("Failed to right click on element: " + locator, e);
        }
    }

    /**
     * Scroll to element
     */
    public static void scrollToElement(By locator) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            WebElement element = WaitUtility.waitForElementToBePresent(locator);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to scroll to element: " + locator);
            throw new RuntimeException("Failed to scroll to element: " + locator, e);
        }
    }

    /**
     * Execute JavaScript
     */
    public static Object executeJavaScript(String script, Object... args) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            Object result = jsExecutor.executeScript(script, args);
            logger.info("Executed JavaScript successfully");
            return result;
        } catch (Exception e) {
            logger.error("Failed to execute JavaScript");
            throw new RuntimeException("Failed to execute JavaScript", e);
        }
    }

    /**
     * Navigate to URL
     */
    public static void navigateToURL(String url) {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            driver.navigate().to(url);
            logger.info("Navigated to URL: " + url);
        } catch (Exception e) {
            logger.error("Failed to navigate to URL: " + url);
            throw new RuntimeException("Failed to navigate to URL: " + url, e);
        }
    }

    /**
     * Get current URL
     */
    public static String getCurrentURL() {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            String currentUrl = driver.getCurrentUrl();
            logger.info("Current URL: " + currentUrl);
            return currentUrl;
        } catch (Exception e) {
            logger.error("Failed to get current URL");
            throw new RuntimeException("Failed to get current URL", e);
        }
    }

    /**
     * Get page title
     */
    public static String getPageTitle() {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            String title = driver.getTitle();
            logger.info("Page title: " + title);
            return title;
        } catch (Exception e) {
            logger.error("Failed to get page title");
            throw new RuntimeException("Failed to get page title", e);
        }
    }

    /**
     * Refresh page
     */
    public static void refreshPage() {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            driver.navigate().refresh();
            logger.info("Page refreshed");
        } catch (Exception e) {
            logger.error("Failed to refresh page");
            throw new RuntimeException("Failed to refresh page", e);
        }
    }

    /**
     * Wait for a specific time (should be used rarely)
     */
    public static void waitForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
            logger.info("Waited for " + seconds + " seconds");
        } catch (InterruptedException e) {
            logger.error("Wait interrupted");
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Check if element exists
     */
    public static boolean isElementPresent(By locator) {
        try {
            DriverManager.getWebDriver().findElement(locator);
            return true;
        } catch (Exception e) {
            logger.warn("Element not found: " + locator);
            return false;
        }
    }
}
