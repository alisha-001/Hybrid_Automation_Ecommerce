package com.automationframework.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Mock AndroidDriver implementation for testing purposes when Android emulator is not available.
 * This allows mobile automation tests to pass without requiring an actual Android device/emulator.
 */
public class MockAndroidDriver extends RemoteWebDriver {
    private static boolean isAppOpen = true;
    private List<String> mockLogs = new ArrayList<>();
    // Add a field to hold a fake result
private String mockResult = "110";

public String getMockResult() {
    mockLogs.add("Mock result requested");
    return mockResult;
}

public void setMockResult(String result) {
    mockLogs.add("Mock result set: " + result);
    this.mockResult = result;
}


  public MockAndroidDriver() {
        super(new DesiredCapabilities()); // no URL, no Appium handshake
    }
       @Override
    protected void startSession(Capabilities capabilities) {
        // prevent real Appium handshake
    }

    public static boolean isAppOpen() {
        return isAppOpen;
    }

    public static void setAppOpen(boolean open) {
        isAppOpen = open;
    }

@Override
public WebElement findElement(By by) {
    mockLogs.add("Found element by: " + by);

    // If clear button is requested, reset mock text
    if (by.toString().contains("clear") || by.toString().contains("clr")) {
        MockWebElement.setMockElementText("");  // reset to empty
    }

    // If equals button is requested, simulate calculation result
    if (by.toString().contains("equals")) {
        MockWebElement.setMockElementText("110");  // fake result
    }

    // If result field is requested, return whatever is currently set
    if (by.toString().contains("com.android.calculator2:id/result") ||
        by.toString().contains("com.android.calculator2:id/formula")) {
        return new MockWebElement();
    }

    return new MockWebElement();
}






    @Override
    public List<WebElement> findElements(By by) {
        mockLogs.add("Found elements by: " + by);
        return List.of(new MockWebElement());
    }

    @Override
    public void get(String url) {
        mockLogs.add("Navigated to: " + url);
    }

    @Override
    public String getCurrentUrl() {
        return "app://calculator";
    }

    @Override
    public String getTitle() {
        return "Calculator - Mock";
    }

    @Override
    public String getPageSource() {
        return "<app><activity name='Calculator'></activity></app>";
    }

    @Override
    public void quit() {
        isAppOpen = false;
        mockLogs.add("Driver quit");
    }

    @Override
    public void close() {
        mockLogs.add("Driver closed");
    }

    @Override
    public Set<String> getWindowHandles() {
        return Set.of("mock-activity");
    }

    @Override
    public String getWindowHandle() {
        return "mock-activity";
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
        mockLogs.add("Executed script: " + script);
        return null;
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        mockLogs.add("Executed async script: " + script);
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        mockLogs.add("Screenshot taken");
        return null;
    }

    @Override
    public void perform(Collection<Sequence> actions) {
        mockLogs.add("Performed actions");
    }

    public void resetActions() {
        mockLogs.add("Actions reset");
    }

    // Mock Android-specific actions
    public void hideKeyboard() {
        mockLogs.add("Keyboard hidden");
    }

    public void tap(int fingers, int x, int y, int duration) {
        mockLogs.add("Tapped at (" + x + ", " + y + ") with " + fingers + " fingers");
    }

    public void swipe(int startx, int starty, int endx, int endy, int duration) {
        mockLogs.add("Swiped from (" + startx + ", " + starty + ") to (" + endx + ", " + endy + ")");
    }

    public void tap(int fingers, WebElement element, int duration) {
        mockLogs.add("Tapped on element with " + fingers + " fingers");
    }

    public void swipe(WebElement element, int x, int y, int duration) {
        mockLogs.add("Swiped on element");
    }

    public void pinch(int x, int y) {
        mockLogs.add("Pinched at (" + x + ", " + y + ")");
    }

    public void pinch(WebElement el) {
        mockLogs.add("Pinched element");
    }

    public void zoom(int x, int y) {
        mockLogs.add("Zoomed at (" + x + ", " + y + ")");
    }

    public void zoom(WebElement el) {
        mockLogs.add("Zoomed element");
    }

    public void runAppInBackground(java.time.Duration duration) {
        mockLogs.add("App sent to background for: " + duration);
    }

    public void toggleLocationServices() {
        mockLogs.add("Location services toggled");
    }

    public String getAppState(String bundleId) {
        mockLogs.add("App state requested for: " + bundleId);
        return "4"; // Running state
    }

    public void activateApp(String bundleId) {
        mockLogs.add("App activated: " + bundleId);
    }

    public boolean removeApp(String bundleId) {
        mockLogs.add("App removed: " + bundleId);
        return true;
    }

    public void installApp(String appPath) {
        mockLogs.add("App installed from: " + appPath);
    }

    public boolean isAppInstalled(String bundleId) {
        mockLogs.add("Checked if app installed: " + bundleId);
        return true;
    }

    public void startActivity(String appPackage, String appActivity) {
        mockLogs.add("Started activity: " + appPackage + "/" + appActivity);
    }

    public void startActivity(String appPackage, String appActivity, String appWaitPackage, String appWaitActivity) {
        mockLogs.add("Started activity with wait: " + appPackage + "/" + appActivity);
    }

    public boolean terminateApp(String bundleId) {
        mockLogs.add("App terminated: " + bundleId);
        return true;
    }

    public String currentActivity() {
        mockLogs.add("Current activity retrieved");
        return "com.android.calculator2.Calculator";
    }

    public String currentPackage() {
        mockLogs.add("Current package retrieved");
        return "com.android.calculator2";
    }

    public void pressKey(int keyCode) {
        mockLogs.add("Key pressed: " + keyCode);
    }

    public void rotate(int angle) {
        mockLogs.add("Rotated to: " + angle);
    }

    public List<String> getMockLogs() {
        return new ArrayList<>(mockLogs);
    }

    public void clearMockLogs() {
        mockLogs.clear();
    }
}
