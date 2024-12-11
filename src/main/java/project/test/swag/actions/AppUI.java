package project.test.swag.actions;

import io.appium.java_client.AppiumDriver;
import project.test.swag.locators.Application;

public class AppUI {

    public void userClicksOnNavigatorMenu(AppiumDriver driver) {
        driver.findElement(Application.navigatorMenu).click();
    }

    public void userClicksOnLogoutOption(AppiumDriver driver) {
        driver.findElement(Application.logoutButton).click();
    }
}
