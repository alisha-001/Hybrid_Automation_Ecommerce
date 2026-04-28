package com.automationframework.base;

import org.openqa.selenium.*;
import java.util.List;

/**
 * Mock WebElement implementation for testing purposes
 */
public class MockWebElement implements WebElement {
    private static String mockElementText = "";

    public static String getMockElementText() {
        return mockElementText != null ? mockElementText : "";
    }

    public static void setMockElementText(String text) {
        mockElementText = text != null ? text : "";
    }

    @Override
    public void click() {
        // Mock click
    }

    @Override
    public void submit() {
        // Mock submit
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        // Store the text sent to this element
        StringBuilder text = new StringBuilder(getMockElementText());
        for (CharSequence keys : keysToSend) {
            text.append(keys);
        }
        setMockElementText(text.toString());
    }

    @Override
    public void clear() {
        // Mock clear
        setMockElementText("");
    }

    @Override
    public String getTagName() {
        return "mock";
    }

    @Override
    public String getAttribute(String name) {
        return "mock-value";
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getText() {
        return getMockElementText();
    }

    @Override
    public boolean isDisplayed() {
        return true;
    }

    @Override
    public Point getLocation() {
        return new Point(0, 0);
    }

    @Override
    public Dimension getSize() {
        return new Dimension(100, 100);
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(getLocation(), getSize());
    }

    @Override
    public String getCssValue(String propertyName) {
        return "mock-css-value";
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return new MockWebElement();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return List.of(new MockWebElement());
    }
}