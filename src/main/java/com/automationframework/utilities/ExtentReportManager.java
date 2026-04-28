package com.automationframework.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {
    private static final Logger logger = LogManager.getLogger(ExtentReportManager.class);
    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    private ExtentReportManager() {
    }

    /**
     * Initialize ExtentReports
     */
    public static void initializeExtentReports() {
        try {
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"));
            String reportPath = System.getProperty("user.dir") + "/test-reports/ExtentReport_" + timeStamp + ".html";

            // Create directory if not exists
            new File(System.getProperty("user.dir") + "/test-reports").mkdirs();

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Automation Test Execution Report");
            sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
            extentReports.setSystemInfo("Test Environment", "Automation Testing Framework");

            logger.info("ExtentReports initialized successfully at: " + reportPath);

        } catch (Exception e) {
            logger.error("Failed to initialize ExtentReports: " + e.getMessage());
            throw new RuntimeException("Failed to initialize ExtentReports", e);
        }
    }

    /**
     * Create a new test
     */
    public static void createTest(String testName) {
        createTest(testName, "");
    }

    /**
     * Create a new test with description
     */
    public static void createTest(String testName, String description) {
        try {
            if (extentReports == null) {
                initializeExtentReports();
            }
            ExtentTest test = extentReports.createTest(testName, description);
            extentTestThreadLocal.set(test);
            logger.info("Test created: " + testName);
        } catch (Exception e) {
            logger.error("Failed to create test: " + e.getMessage());
            throw new RuntimeException("Failed to create test", e);
        }
    }

    /**
     * Get current test
     */
    public static ExtentTest getTest() {
        ExtentTest test = extentTestThreadLocal.get();
        if (test == null) {
            throw new RuntimeException("No test is currently active. Call createTest() first.");
        }
        return test;
    }

    /**
     * Log pass status
     */
    public static void logPass(String message) {
        try {
            getTest().log(Status.PASS, message);
            logger.info("PASS: " + message);
        } catch (Exception e) {
            logger.error("Failed to log pass: " + e.getMessage());
        }
    }

    /**
     * Log fail status
     */
    public static void logFail(String message) {
        try {
            getTest().log(Status.FAIL, message);
            logger.error("FAIL: " + message);
        } catch (Exception e) {
            logger.error("Failed to log fail: " + e.getMessage());
        }
    }

    /**
     * Log fail status with throwable
     */
    public static void logFail(String message, Throwable throwable) {
        try {
            getTest().log(Status.FAIL, message);
            getTest().log(Status.FAIL, throwable);
            logger.error("FAIL: " + message);
        } catch (Exception e) {
            logger.error("Failed to log fail: " + e.getMessage());
        }
    }

    /**
     * Log info status
     */
    public static void logInfo(String message) {
        try {
            getTest().log(Status.INFO, message);
            logger.info("INFO: " + message);
        } catch (Exception e) {
            logger.error("Failed to log info: " + e.getMessage());
        }
    }

    /**
     * Log warning status
     */
    public static void logWarning(String message) {
        try {
            getTest().log(Status.WARNING, message);
            logger.warn("WARNING: " + message);
        } catch (Exception e) {
            logger.error("Failed to log warning: " + e.getMessage());
        }
    }

    /**
     * Log skip status
     */
    public static void logSkip(String message) {
        try {
            getTest().log(Status.SKIP, message);
            logger.info("SKIP: " + message);
        } catch (Exception e) {
            logger.error("Failed to log skip: " + e.getMessage());
        }
    }

    /**
     * Flush/save reports
     */
    public static void flushReports() {
        try {
            if (extentReports != null) {
                extentReports.flush();
                logger.info("Reports flushed successfully");
            }
        } catch (Exception e) {
            logger.error("Failed to flush reports: " + e.getMessage());
        }
    }

    /**
     * Remove test from ThreadLocal
     */
    public static void removeTest() {
        extentTestThreadLocal.remove();
    }
}
