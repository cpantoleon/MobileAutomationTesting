package project.test.swag.actions;

import io.appium.java_client.AppiumDriver;
import project.test.swag.locators.Application;
import project.test.swag.utils.Wait;

public class AppUI {

    public void userClicksOnNavigatorMenu(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Application.navigatorMenu);
        driver.findElement(Application.navigatorMenu).click();
    }

    public void userClicksOnLogoutOption(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Application.logoutButton);
        driver.findElement(Application.logoutButton).click();
    }
}
