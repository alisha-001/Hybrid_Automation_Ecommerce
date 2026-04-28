package com.automationframework.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automationframework.base.DriverManager;
import com.automationframework.utilities.ExtentReportManager;

public class CheckoutPage extends BasePage {

    private static final By DELIVERY_ADDRESS = By.xpath("//ul[@id='address_delivery']//p");
    private static final By BILLING_ADDRESS = By.xpath("//ul[@id='address_invoice']//p");
    private static final By ORDER_COMMENT = By.name("message");
    private static final By PLACE_ORDER_BUTTON = By.xpath("//a[@class='btn btn-default check_out']");
    private static final By PAYMENT_METHOD = By.xpath("//p[contains(text(), 'Method')]");
    private static final By PRODUCT_IN_CHECKOUT = By.xpath("//tr[@class='cart_item']");
    private static final By TOTAL_AMOUNT = By.xpath("//td[contains(@class,'amount') or contains(@class,'cart_total') or contains(@class,'total')]");

    public CheckoutPage() {
        super();
    }

    /**
     * Get delivery address
     */
    public String getDeliveryAddress() {
        try {
            return getElementText(DELIVERY_ADDRESS);
        } catch (Exception e) {
            logger.error("Failed to get delivery address: " + e.getMessage());
            throw new RuntimeException("Failed to get delivery address", e);
        }
    }

    /**
     * Get billing address
     */
    public String getBillingAddress() {
        try {
            return getElementText(BILLING_ADDRESS);
        } catch (Exception e) {
            logger.error("Failed to get billing address: " + e.getMessage());
            throw new RuntimeException("Failed to get billing address", e);
        }
    }

    /**
     * Add order comment
     */
    public void addOrderComment(String comment) {
        try {
            typeText(ORDER_COMMENT, comment);
            logger.info("Added order comment: " + comment);
            ExtentReportManager.logInfo("Added order comment: " + comment);
        } catch (Exception e) {
            logger.error("Failed to add order comment: " + e.getMessage());
            ExtentReportManager.logFail("Failed to add order comment: " + e.getMessage());
            throw new RuntimeException("Failed to add order comment", e);
        }
    }

    /**
     * Click place order
     */
    public void clickPlaceOrder() {
        try {
            clickElement(PLACE_ORDER_BUTTON);
            logger.info("Clicked on Place Order");
            ExtentReportManager.logInfo("Clicked on Place Order");
        } catch (Exception e) {
            logger.error("Failed to click place order: " + e.getMessage());
            ExtentReportManager.logFail("Failed to click place order: " + e.getMessage());
            throw new RuntimeException("Failed to click place order", e);
        }
    }

    /**
     * Check if product is in checkout
     */
    public boolean isProductInCheckout() {
        try {
            return isElementPresent(PRODUCT_IN_CHECKOUT);
        } catch (Exception e) {
            logger.error("Failed to check product in checkout: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get total amount
     */
    public String getTotalAmount() {
        try {
            return getElementText(TOTAL_AMOUNT);
        } catch (Exception e) {
            logger.error("Failed to get total amount: " + e.getMessage());
            throw new RuntimeException("Failed to get total amount", e);
        }
    }

    /**
     * Verify checkout page is loaded
     */
public boolean isCheckoutPageLoaded() {
    try {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='address_delivery']")));
        return true;
    } catch (Exception e) {
        logger.error("Checkout page not loaded: " + e.getMessage());
        return false;
    }
}

}
