package project.test.swag.actions;

import io.appium.java_client.AppiumDriver;
import project.test.swag.locators.Login;
import project.test.swag.locators.Application;
import project.test.swag.utils.YMLConfig;
import project.test.swag.utils.YamlConfigReader;

import java.util.Map;

public class LoginActions {

    public void insertTheUsernameInLoginPage(String user, AppiumDriver driver) {
        switch (user) {
            case "Valid":
                driver.findElement(Login.userName).click();
                driver.findElement(Login.userName).sendKeys((String) YMLConfig.valid.get("userName"));
                break;
            case "Invalid":
                driver.findElement(Login.userName).click();
                driver.findElement(Login.userName).sendKeys((String) YMLConfig.invalid.get("userName"));
                break;
            default:
                throw new IllegalArgumentException("Unknown user type: " + user);
        }
    }

    public void insertThePasswordInLoginPage(String user, AppiumDriver driver) {
        switch (user) {
            case "Valid":
                driver.findElement(Login.passwordField).click();
                driver.findElement(Login.passwordField).sendKeys((String) YMLConfig.valid.get("password"));
                break;
            case "Invalid":
                driver.findElement(Login.passwordField).click();
                driver.findElement(Login.passwordField).sendKeys((String) YMLConfig.invalid.get("password"));
                break;
            default:
                throw new IllegalArgumentException("Unknown user type: " + user);
        }
    }

    public void clickOnLoginButton(AppiumDriver driver) {
        driver.findElement(Login.loginButton).click();
    }
}
