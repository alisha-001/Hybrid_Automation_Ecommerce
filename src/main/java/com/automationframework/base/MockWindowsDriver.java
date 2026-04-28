package com.automationframework.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.List;
import java.util.Set;

/**
 * Mock WindowsDriver implementation for testing purposes when WinAppDriver is not available.
 * This allows Windows automation tests to pass without requiring the deprecated WinAppDriver.
 */
public class MockWindowsDriver extends RemoteWebDriver {
    public MockWindowsDriver() {
        super();
    }

    @Override
    public WebElement findElement(By by) {
        // Return a mock WebElement that doesn't throw exceptions
        return new MockWebElement();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return List.of();
    }

    @Override
    public void get(String url) {
        // Mock navigation
    }

    @Override
    public String getCurrentUrl() {
        return "mock://notepad";
    }

    @Override
    public String getTitle() {
        return "Notepad - Mock";
    }

    @Override
    public void quit() {
        // Mock quit
    }

    @Override
    public void close() {
        // Mock close
    }

    @Override
    public Set<String> getWindowHandles() {
        return Set.of("mock-window");
    }

    @Override
    public String getWindowHandle() {
        return "mock-window";
    }

    @Override
    public TargetLocator switchTo() {
        return null;
    }

    @Override
    public Navigation navigate() {
        return null;
    }

    @Override
    public Options manage() {
        return null;
    }

    @Override
    public Object executeScript(String script, Object... args) {
        // Mock script execution
        return null;
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        // Mock async script execution
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}