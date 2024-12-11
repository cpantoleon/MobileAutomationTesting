package project.test.swag.questions;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import project.test.swag.locators.Login;
import project.test.swag.utils.Wait;

public class LoginQuestions {
    public void anErrorMessageIsDisplayed(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Login.loginInvalidMessage);
        WebElement errorMessageElement = driver.findElement(Login.loginInvalidMessage);

        Assert.assertTrue("The user log in successfully", errorMessageElement.isDisplayed());
    }

    public void userLoggedInSuccessfully(AppiumDriver driver) {
        WebElement logoElement = driver.findElement(Login.logoInFrontPage);
        Wait.forElementToBeDisplayed(driver, Login.logoInFrontPage);

        Assert.assertTrue("The user did not log in successfully, logo is not visible.", logoElement.isDisplayed());
    }

    public void userLoggedOutSuccessfully(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Login.userName);
        WebElement userNameElement = driver.findElement(Login.userName);

        Assert.assertTrue("The user did not log out successfully", userNameElement.isDisplayed());
    }
}
