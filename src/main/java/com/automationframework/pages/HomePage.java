package com.automationframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automationframework.base.DriverManager;
import com.automationframework.utilities.ExtentReportManager;

import java.time.Duration;
import java.util.Arrays;

public class HomePage extends BasePage {

    private static final By SIGNUP_LOGIN_BUTTON = By.xpath("//a[contains(text(),'Signup') or contains(text(),'Login') or contains(@href,'login')]");
    private static final By LOGOUT_BUTTON = By.xpath("//a[contains(@href, '/logout')]");
    private static final By CART_BUTTON = By.linkText("Cart");
    private static final By ACCOUNT_BUTTON = By.xpath("//a[contains(@href, '/account')]");
    private static final By DELETE_ACCOUNT_BUTTON = By.xpath("//a[contains(@href, '/delete_account')]");
    private static final By PRODUCTS_BUTTON = By.xpath("//a[contains(text(),'Product') or contains(@href,'product') or @href='/products']");
    private static final By PRODUCT_LIST = By.cssSelector("div.product-image-wrapper");
    private static final By ADD_TO_CART_BUTTONS = By.cssSelector("a.add-to-cart");
    private static final By VIEW_CART_BUTTON = By.xpath("//a[@href='/view_cart']");

    public HomePage() {
        super();
    }

    /**
     * Navigate to home page
     */
    public void navigateToHomePage() {
        navigateToURL("https://automationexercise.com/");
        waitForPageLoad();
        dismissAnyOverlays();
        try {
            Thread.sleep(2000); // Additional wait for dynamic content
        } catch (InterruptedException e) {
            logger.warn("Navigation wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        logger.info("Navigated to home page");
        ExtentReportManager.logInfo("Navigated to home page");
    }

    /**
     * Click on signup/login with improved reliability
     */
    public void clickSignupLogin() {
        try {
            // Wait for page to be fully loaded
            waitForPageLoad();
            // Dismiss any overlays
            dismissAnyOverlays();
            // Additional wait for dynamic content
            Thread.sleep(3000);

            // Try multiple locator strategies for the signup/login button
            java.util.List<By> signupLocators = java.util.Arrays.asList(
                By.xpath("//a[contains(text(),'Signup') or contains(text(),'Login') or contains(@href,'login')]"),
                By.xpath("//a[@href='/login']"),
                By.linkText("Signup / Login"),
                By.partialLinkText("Login"),
                By.xpath("//div[@class='shop-menu']//a[contains(@href,'login')]"),
                By.cssSelector("a[href*='login']")
            );

            boolean clicked = false;
            for (By locator : signupLocators) {
                try {
                    if (isElementPresent(locator)) {
                        clickElement(locator);
                        clicked = true;
                        logger.info("Successfully clicked Signup/Login button using: " + locator);
                        ExtentReportManager.logInfo("Clicked on Signup/Login button");
                        break;
                    }
                } catch (Exception e) {
                    logger.debug("Locator failed: " + locator + " - " + e.getMessage());
                    continue;
                }
            }

            if (!clicked) {
                throw new RuntimeException("Could not find or click Signup/Login button with any locator");
            }

        } catch (InterruptedException e) {
            logger.warn("Signup/Login click wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            logger.error("Failed to click Signup/Login button: " + e.getMessage());
            ExtentReportManager.logFail("Failed to click Signup/Login button: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Click on logout
     */
    public void clickLogout() {
        clickElement(LOGOUT_BUTTON);
        logger.info("Clicked on Logout button");
        ExtentReportManager.logInfo("Clicked on Logout button");
    }

    /**
     * Click on cart
     */
    public void clickCart() {
        clickElement(CART_BUTTON);
        logger.info("Clicked on Cart button");
        ExtentReportManager.logInfo("Clicked on Cart button");
    }

    /**
     * Click on account
     */
    public void clickAccount() {
        clickElement(ACCOUNT_BUTTON);
        logger.info("Clicked on Account button");
        ExtentReportManager.logInfo("Clicked on Account button");
    }

    /**
     * Click on delete account
     */
    public void clickDeleteAccount() {
        clickElement(DELETE_ACCOUNT_BUTTON);
        logger.info("Clicked on Delete Account button");
        ExtentReportManager.logInfo("Clicked on Delete Account button");
    }

    /**
     * Click on products
     */
    public void clickProducts() {
        try {
            // Wait for page to be fully loaded
            waitForPageLoad();
            // Dismiss any overlays
            dismissAnyOverlays();
            // Additional wait for dynamic content
            Thread.sleep(2000);

            clickElement(PRODUCTS_BUTTON);
            logger.info("Clicked on Products button");
            ExtentReportManager.logInfo("Clicked on Products button");
        } catch (InterruptedException e) {
            logger.warn("Products click wait interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            logger.error("Failed to click Products button: " + e.getMessage());
            ExtentReportManager.logFail("Failed to click Products button: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Add product to cart by index with improved modal handling
     */
    /**
 * Add product to cart by index with improved modal handling
 */
public void addProductToCart(int productIndex) {
    try {
        if (!getCurrentURL().contains("/products")) {
            clickProducts();
            Thread.sleep(2000);
        }

        if (!isElementPresent(PRODUCT_LIST)) {
            throw new RuntimeException("Products list is not available on the products page");
        }

        java.util.List<WebElement> addToCartButtons = driver.findElements(ADD_TO_CART_BUTTONS);
        if (productIndex >= 0 && productIndex < addToCartButtons.size()) {
            WebElement targetButton = addToCartButtons.get(productIndex);

            // Scroll into view
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", targetButton);
            Thread.sleep(1000);

            // Try normal click, fallback to JS click if intercepted
            try {
                targetButton.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", targetButton);
                logger.warn("Fallback to JS click due to interception: " + e.getMessage());
            }

            logger.info("Clicked add to cart for product at index " + productIndex);
            ExtentReportManager.logInfo("Added product at index " + productIndex + " to cart");

            Thread.sleep(2000);

            // Handle cart modal
            if (isElementPresent(VIEW_CART_BUTTON)) {
                clickElement(VIEW_CART_BUTTON);
                logger.info("Clicked View Cart after adding product");
                ExtentReportManager.logInfo("Clicked View Cart after adding product");
            } else {
                logger.warn("Cart modal not found, navigating directly to cart");
                navigateToURL("https://automationexercise.com/view_cart");
            }
        } else {
            throw new IllegalArgumentException("Invalid product index: " + productIndex +
                                               ". Available products: " + addToCartButtons.size());
        }
    } catch (Exception e) {
        logger.error("Failed to add product to cart: " + e.getMessage());
        ExtentReportManager.logFail("Failed to add product to cart: " + e.getMessage());
        throw new RuntimeException("Failed to add product to cart", e);
    }
}


    /**
     * Wait for page to load completely
     */
    private void waitForPageLoad() {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            new WebDriverWait(driver, Duration.ofSeconds(30))
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState").equals("complete"));
            Thread.sleep(2000); // Additional wait for dynamic content
        } catch (Exception e) {
            logger.warn("Page load wait interrupted: " + e.getMessage());
        }
    }

    /**
     * Dismiss any overlays or popups that might be blocking elements
     */
    private void dismissAnyOverlays() {
        try {
            WebDriver driver = DriverManager.getWebDriver();
            // Try to dismiss common overlay elements
            java.util.List<String> overlaySelectors = java.util.Arrays.asList(
                    "button[class*='close']",
                    "button[class*='dismiss']",
                    ".modal-backdrop",
                    ".overlay",
                    ".popup",
                    "[data-dismiss='modal']",
                    ".fc-consent-root .fc-cta-do-not-consent",
                    "#dismiss-button",
                    ".close"
            );

            for (String selector : overlaySelectors) {
                try {
                    java.util.List<org.openqa.selenium.WebElement> elements =
                            driver.findElements(By.cssSelector(selector));
                    for (org.openqa.selenium.WebElement element : elements) {
                        if (element.isDisplayed()) {
                            element.click();
                            logger.info("Dismissed overlay: " + selector);
                            Thread.sleep(500);
                            break;
                        }
                    }
                } catch (Exception e) {
                    // Continue to next selector
                }
            }
        } catch (Exception e) {
            logger.warn("Overlay dismissal failed: " + e.getMessage());
        }
    }

    /**
     * Verify home page is loaded
     */
    public boolean isHomePageLoaded() {
        try {
            return isElementPresent(By.id("header"));
        } catch (Exception e) {
            logger.error("Failed to verify home page: " + e.getMessage());
            return false;
        }
    }
}
