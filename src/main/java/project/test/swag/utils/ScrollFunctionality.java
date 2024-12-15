package project.test.swag.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

import java.time.Duration;

public class ScrollFunctionality {

    public static void scrollToElement(AppiumDriver driver, By locator) {
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
        int startY = (int) (height * 0.5);
        int endY = (int) (height * 0.2);

        new TouchAction<>((PerformsTouchActions) driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }
}