package project.test.swag.actions;

import io.appium.java_client.AppiumDriver;
import project.test.swag.locators.Application;
import project.test.swag.locators.Login;
import project.test.swag.utils.ScrollFunctionality;
import project.test.swag.utils.Wait;
import project.test.swag.utils.YMLConfig;

public class AppUI {

    public void userClicksOnNavigatorMenu(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Application.navigatorMenu);
        driver.findElement(Application.navigatorMenu).click();
    }

    public void userClicksOnLogoutOption(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Application.logoutButton);
        driver.findElement(Application.logoutButton).click();
    }

    public void selectAProduct(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Login.logoInFrontPage);
        ScrollFunctionality.scrollToElement(driver, Application.Tshirt1);
        driver.findElement(Application.Tshirt1).click();
    }

    public void addTheProcutToTheBasket(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Login.logoInFrontPage);
        ScrollFunctionality.scrollToElement(driver, Application.addToCartButton);
        driver.findElement(Application.addToCartButton).click();
    }

    public void clickOnTheCartIcon(AppiumDriver driver) {
        driver.findElement(Application.cartButton).click();
    }

    public void clickOnTheCheckoutButton(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Login.logoInFrontPage);
        ScrollFunctionality.scrollToElement(driver, Application.checkoutButton);
        driver.findElement(Application.checkoutButton).click();
    }

    public void fillTheMandatoryInformationFields(AppiumDriver driver, Object firstName, Object lastName, Object zipCode) {
        Wait.forElementToBeDisplayed(driver, Login.logoInFrontPage);
        driver.findElement(Application.firstName).click();
        driver.findElement(Application.firstName).sendKeys((String) firstName);

        driver.findElement(Application.lastName).click();
        driver.findElement(Application.lastName).sendKeys((String) lastName);

        driver.findElement(Application.postalCode).click();
        driver.findElement(Application.postalCode).sendKeys((String) zipCode);
    }

    public void clickOnTheContinueButton(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Login.logoInFrontPage);
        ScrollFunctionality.scrollToElement(driver, Application.continueButton);
        driver.findElement(Application.continueButton).click();
    }

    public void clickOnTheFinishButton(AppiumDriver driver) {
        Wait.forElementToBeDisplayed(driver, Login.logoInFrontPage);
        ScrollFunctionality.scrollToElement(driver, Application.finishButton);
        driver.findElement(Application.finishButton).click();
    }
}
