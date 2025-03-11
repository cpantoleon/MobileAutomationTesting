package project.test.swag.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;

public class ScrollFunctionality {

    public static void scrollToElement(AppiumDriver driver, By locator) {
        if (isKeyboardShown(driver)) {
            hideKeyboard(driver);
        }

        while (!isElementPresent(driver, locator)) {
            scroll(driver);
        }
    }

    private static boolean isElementPresent(AppiumDriver driver, By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private static void scroll(AppiumDriver driver) {
        int height = driver.manage().window().getSize().height;
        int width = driver.manage().window().getSize().width;

        int startX = width / 2;
        int startY = (int) (height * 0.7);
        int endY = (int) (height * 0.4);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(100)))
                .addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(scroll));
    }

    private static boolean isKeyboardShown(AppiumDriver driver) {
        try {
            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;
                return androidDriver.isKeyboardShown();
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error checking keyboard visibility: " + e.getMessage());
            return false;
        }
    }

    private static void hideKeyboard(AppiumDriver driver) {
        try {
            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;
                androidDriver.hideKeyboard();
            } else {
                System.out.println("Keyboard hiding is not supported on this platform.");
            }
        } catch (Exception e) {
            System.out.println("Error hiding keyboard: " + e.getMessage());
        }
    }
}
