package com.automationframework.web;

import com.automationframework.base.DriverManager;
import com.automationframework.pages.CartPage;
import com.automationframework.pages.CheckoutPage;
import com.automationframework.pages.HomePage;
import com.automationframework.pages.LoginPage;
import com.automationframework.pages.RegisterPage;
import com.automationframework.utilities.ExtentReportManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WebAutomationTest {

    private static final Logger logger = LogManager.getLogger(WebAutomationTest.class);
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private String registeredEmail;
    private String registeredPassword;

    @BeforeClass
    public void setUp() {
        ExtentReportManager.initializeExtentReports();
        // Don't initialize driver here - do it per test method
    }

    @BeforeMethod
    public void setUpTest() {
        DriverManager.initializeWebDriver();
        driver = DriverManager.getWebDriver();
        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
    }

    @AfterMethod
    public void tearDownTest() {
        DriverManager.quitWebDriver();
    }

    @Test(priority = 1)
    public void testUserRegistration() {
        ExtentReportManager.createTest("User Registration Test", "Register a new user account");
        try {
            // Navigate to home page
            homePage.navigateToHomePage();
            assertThat(homePage.isHomePageLoaded()).isTrue();
            ExtentReportManager.logPass("Home page loaded successfully");

            // Click on signup/login
            homePage.clickSignupLogin();
            assertThat(loginPage.isLoginPageLoaded()).isTrue();
            ExtentReportManager.logPass("Login page loaded successfully");

            // Sign up
            registeredEmail = "testuser_" + System.currentTimeMillis() + "@test.com";
            registeredPassword = "TestPassword@123";
            loginPage.signup("Test User", registeredEmail);
            Thread.sleep(1000); // Wait for page to load

            // Fill registration form
            assertThat(registerPage.isRegistrationPageLoaded()).isTrue();
            registerPage.fillRegistrationForm(
                    "Mr",
                    registeredPassword,
                    "Test",
                    "User",
                    "Test Company",
                    "123 Test Street",
                    "Apt 4B",
                    "United States",
                    "New York",
                    "New York",
                    "10001",
                    "2125551234"
            );
            ExtentReportManager.logPass("Registration form filled successfully");

            // Create account
            registerPage.createAccount();
            Thread.sleep(1000);
            assertThat(registerPage.isAccountCreatedMessageDisplayed()).isTrue();
            ExtentReportManager.logPass("Account created successfully");

        } catch (AssertionError e) {
            ExtentReportManager.logFail("Test failed: " + e.getMessage(), e);
            Assert.fail(e.getMessage());
        } catch (Exception e) {
            ExtentReportManager.logFail("Test error: " + e.getMessage(), e);
            Assert.fail("Test error: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testUserLogin() {
        ExtentReportManager.createTest("User Login Test", "Login with created account");
        try {
            // Navigate to login page directly
            driver.navigate().to("https://automationexercise.com/login");
            Thread.sleep(2000);
            
            assertThat(loginPage.isLoginPageLoaded()).isTrue();
            ExtentReportManager.logPass("Login page loaded successfully");

            // Login with valid credentials
            if (registeredEmail == null || registeredPassword == null) {
                registeredEmail = "testuser@test.com";
                registeredPassword = "TestPassword@123";
            }
            loginPage.login(registeredEmail, registeredPassword);
            Thread.sleep(2000);

            // Verify user is logged in by checking home page is loaded
            assertThat(homePage.isHomePageLoaded()).isTrue();
            ExtentReportManager.logPass("User logged in successfully");

        } catch (AssertionError e) {
            ExtentReportManager.logFail("Test failed: " + e.getMessage(), e);
            Assert.fail(e.getMessage());
        } catch (Exception e) {
            ExtentReportManager.logFail("Test error: " + e.getMessage(), e);
            Assert.fail("Test error: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void testAddProductToCart() {
        ExtentReportManager.createTest("Add Product to Cart Test", "Add a product to cart and validate");
        try {
            // Navigate to home page
            homePage.navigateToHomePage();
            assertThat(homePage.isHomePageLoaded()).isTrue();
            ExtentReportManager.logPass("Home page loaded successfully");

            // Add product to cart
            homePage.addProductToCart(0);
            ExtentReportManager.logPass("Product added to cart");

            // Navigate to cart and verify
            homePage.clickCart();
            Thread.sleep(2000); // Wait for cart page to load

            assertThat(cartPage.isCartPageLoaded()).isTrue();
            ExtentReportManager.logPass("Cart page loaded successfully");

            // Check if product is in cart with detailed logging
            boolean productInCart = cartPage.isProductInCart();
            if (!productInCart) {
                // Try refreshing the page and checking again
                driver.navigate().refresh();
                Thread.sleep(2000);
                productInCart = cartPage.isProductInCart();
            }

            assertThat(productInCart).isTrue();
            ExtentReportManager.logPass("Product is in cart");

        } catch (AssertionError e) {
            ExtentReportManager.logFail("Test failed: " + e.getMessage(), e);
            Assert.fail(e.getMessage());
        } catch (Exception e) {
            ExtentReportManager.logFail("Test error: " + e.getMessage(), e);
            Assert.fail("Test error: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void testCheckout() {
        ExtentReportManager.createTest("Checkout Test", "Proceed to checkout and validate order details");
        try {
            ensureUserIsLoggedInForCheckout();

            // Navigate to home page
            homePage.navigateToHomePage();
            assertThat(homePage.isHomePageLoaded()).isTrue();
            ExtentReportManager.logPass("Home page loaded successfully");

            // Add product to cart
            homePage.addProductToCart(0);
            ExtentReportManager.logPass("Product added to cart");

            // Navigate to cart
            homePage.clickCart();
            Thread.sleep(2000);

            assertThat(cartPage.isCartPageLoaded()).isTrue();
            ExtentReportManager.logPass("Cart page loaded successfully");

            // Verify product is in cart
            boolean productInCart = cartPage.isProductInCart();
            if (!productInCart) {
                driver.navigate().refresh();
                Thread.sleep(2000);
                productInCart = cartPage.isProductInCart();
            }
            assertThat(productInCart).isTrue();
            ExtentReportManager.logPass("Product is in cart");

            // Proceed to checkout
            cartPage.clickProceedToCheckout();
            Thread.sleep(2000);

            assertThat(checkoutPage.isCheckoutPageLoaded()).isTrue();
            ExtentReportManager.logPass("Checkout page loaded successfully");

            // Add comment and verify order details
            checkoutPage.addOrderComment("Please deliver carefully");
            String totalAmount = checkoutPage.getTotalAmount();
            assertThat(totalAmount).isNotEmpty();
            ExtentReportManager.logPass("Order summary validated: Total = " + totalAmount);

        } catch (AssertionError e) {
            ExtentReportManager.logFail("Test failed: " + e.getMessage(), e);
            Assert.fail(e.getMessage());
        } catch (Exception e) {
            ExtentReportManager.logFail("Test error: " + e.getMessage(), e);
            Assert.fail("Test error: " + e.getMessage());
        }
    }

    private void ensureUserIsLoggedInForCheckout() throws InterruptedException {
        if (registeredEmail == null || registeredPassword == null) {
            registeredEmail = "checkoutuser_" + System.currentTimeMillis() + "@test.com";
            registeredPassword = "CheckoutPass@123";
            homePage.navigateToHomePage();
            homePage.clickSignupLogin();
            Thread.sleep(2000);

            assertThat(loginPage.isLoginPageLoaded()).isTrue();
            loginPage.signup("Checkout User", registeredEmail);
            Thread.sleep(1000);
            assertThat(registerPage.isRegistrationPageLoaded()).isTrue();

            registerPage.fillRegistrationForm(
                    "Mr",
                    registeredPassword,
                    "Checkout",
                    "User",
                    "Test Company",
                    "123 Checkout Street",
                    "Suite 100",
                    "India",
                    "CheckoutState",
                    "CheckoutCity",
                    "10001",
                    "2125550000"
            );
            registerPage.createAccount();
            Thread.sleep(3000);
            assertThat(registerPage.isAccountCreatedMessageDisplayed()).isTrue();
            ExtentReportManager.logPass("Checkout user registered successfully");

            // Ensure the newly created account is logged in before checkout
            driver.navigate().to("https://automationexercise.com/login");
            Thread.sleep(2000);
            assertThat(loginPage.isLoginPageLoaded()).isTrue();
            loginPage.login(registeredEmail, registeredPassword);
            Thread.sleep(2000);
            assertThat(homePage.isHomePageLoaded()).isTrue();
            ExtentReportManager.logPass("Checkout user registered and logged in successfully");
        } else {
            driver.navigate().to("https://automationexercise.com/login");
            Thread.sleep(2000);
            assertThat(loginPage.isLoginPageLoaded()).isTrue();
            loginPage.login(registeredEmail, registeredPassword);
            Thread.sleep(2000);
            assertThat(homePage.isHomePageLoaded()).isTrue();
            ExtentReportManager.logPass("Checkout user logged in successfully");
        }
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitWebDriver();
        ExtentReportManager.flushReports();
    }
}
