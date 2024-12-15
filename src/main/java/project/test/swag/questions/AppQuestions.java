package project.test.swag.questions;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import project.test.swag.locators.Application;
import project.test.swag.locators.Login;
import project.test.swag.utils.Wait;

public class AppQuestions {
    public void VerifyTheOrderSentSuccessuflly(AppiumDriver driver) {
        WebElement verificationLabelElement = driver.findElement(Application.verificationLabel);
        Wait.forElementToBeDisplayed(driver, Application.verificationLabel);

        Assert.assertTrue("The user did not log in successfully, logo is not visible.", verificationLabelElement.isDisplayed());
    }
}
