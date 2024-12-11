package project.test.swag.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public final class Wait {

    public static void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void forElementToBeDisplayed(AppiumDriver webDriver, By by) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}