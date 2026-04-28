package com.automationframework.pages;

import org.openqa.selenium.By;
import com.automationframework.utilities.ExtentReportManager;

public class CartPage extends BasePage {

    private static final By PRODUCT_IN_CART = By.xpath("//tbody//tr[contains(@id,'product-') or .//td[@class='cart_description']]");
    private static final By PRODUCT_NAME = By.xpath("//tbody//tr[contains(@id,'product-')]//a | //td[@class='cart_description']//a");
    private static final By PRODUCT_PRICE = By.xpath("//tbody//tr[contains(@id,'product-')]//td[@class='cart_price']//p | //td[@class='cart_price']");
    private static final By PRODUCT_QUANTITY = By.xpath("//tbody//tr[contains(@id,'product-')]//td[@class='cart_quantity']//button | //td[@class='cart_quantity']");
    private static final By PRODUCT_TOTAL = By.xpath("//tbody//tr[contains(@id,'product-')]//td[@class='cart_total']//p | //td[@class='cart_total']");
    private static final By PROCEED_TO_CHECKOUT = By.xpath("//a[@class='btn btn-default check_out']");
    private static final By CONTINUE_SHOPPING = By.xpath("//a[@class='btn btn-default continue']");
    private static final By EMPTY_CART_MESSAGE = By.xpath("//p[contains(text(), 'Cart is empty')]");
    private static final By CART_ITEMS_TABLE = By.xpath("//table[@class='table table-condensed']//tbody");

    public CartPage() {
        super();
    }

    /**
     * Check if product is in cart using JavaScript for faster detection
     */
    public boolean isProductInCart() {
        try {
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            
            // Check if empty cart message is present
            Object emptyCheck = js.executeScript(
                "return document.body.innerText.toLowerCase().includes('cart is empty');"
            );
            if (emptyCheck instanceof Boolean && (Boolean) emptyCheck) {
                logger.info("Cart is empty - no products found");
                return false;
            }

            // Count rows in cart table
            Object rowCount = js.executeScript(
                "return document.querySelectorAll('tbody tr').length || 0;"
            );
            int rows = 0;
            if (rowCount instanceof Number) {
                rows = ((Number) rowCount).intValue();
            }
            
            logger.info("Cart contains " + rows + " items");
            boolean hasItems = rows > 0;

            // Also check using traditional XPath for products
            if (!hasItems) {
                try {
                    java.util.List<org.openqa.selenium.WebElement> items = driver.findElements(
                        By.xpath("//table//tbody//tr")
                    );
                    hasItems = items.size() > 0;
                    logger.info("Traditional XPath check found " + items.size() + " cart items");
                } catch (Exception e) {
                    logger.debug("Traditional XPath check failed: " + e.getMessage());
                }
            }

            logger.info("Final cart validation result: " + hasItems);
            return hasItems;

        } catch (Exception e) {
            logger.error("Failed to check if product in cart: " + e.getMessage());
            ExtentReportManager.logWarning("Cart validation error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get product name from cart
     */
    public String getProductName() {
        try {
            return getElementText(PRODUCT_NAME);
        } catch (Exception e) {
            logger.error("Failed to get product name: " + e.getMessage());
            throw new RuntimeException("Failed to get product name", e);
        }
    }

    /**
     * Get product price
     */
    public String getProductPrice() {
        try {
            return getElementText(PRODUCT_PRICE);
        } catch (Exception e) {
            logger.error("Failed to get product price: " + e.getMessage());
            throw new RuntimeException("Failed to get product price", e);
        }
    }

    /**
     * Get product quantity
     */
    public String getProductQuantity() {
        try {
            return getElementText(PRODUCT_QUANTITY);
        } catch (Exception e) {
            logger.error("Failed to get product quantity: " + e.getMessage());
            throw new RuntimeException("Failed to get product quantity", e);
        }
    }

    /**
     * Get product total
     */
    public String getProductTotal() {
        try {
            return getElementText(PRODUCT_TOTAL);
        } catch (Exception e) {
            logger.error("Failed to get product total: " + e.getMessage());
            throw new RuntimeException("Failed to get product total", e);
        }
    }

    /**
     * Click proceed to checkout
     */
    public void clickProceedToCheckout() {
        try {
            clickElement(PROCEED_TO_CHECKOUT);
            logger.info("Clicked on Proceed to Checkout");
            ExtentReportManager.logInfo("Clicked on Proceed to Checkout");
        } catch (Exception e) {
            logger.error("Failed to click proceed to checkout: " + e.getMessage());
            ExtentReportManager.logFail("Failed to click proceed to checkout: " + e.getMessage());
            throw new RuntimeException("Failed to click proceed to checkout", e);
        }
    }

    /**
     * Check if cart is empty
     */
    public boolean isCartEmpty() {
        try {
            return isElementDisplayed(EMPTY_CART_MESSAGE);
        } catch (Exception e) {
            logger.warn("Cart is not empty");
            return false;
        }
    }

    /**
     * Check if cart page is loaded using URL and page content
     */
    public boolean isCartPageLoaded() {
        try {
            String currentUrl = driver.getCurrentUrl();
            boolean isCartUrl = currentUrl.contains("cart") || currentUrl.contains("view_cart");
            logger.info("Cart page URL check: " + currentUrl + " - Is cart page: " + isCartUrl);
            return isCartUrl;
        } catch (Exception e) {
            logger.warn("Failed to verify cart page by URL, trying fallback: " + e.getMessage());
            try {
                // Fallback: check if proceed to checkout button exists (short timeout)
                org.openqa.selenium.support.ui.WebDriverWait wait = 
                    new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(3));
                org.openqa.selenium.WebElement element = wait.until(
                    org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(PROCEED_TO_CHECKOUT)
                );
                return element != null;
            } catch (Exception ex) {
                logger.warn("Cart page checkout button not found within 3 seconds");
                return false;
            }
        }
    }
}
