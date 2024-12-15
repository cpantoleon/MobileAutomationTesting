package project.test.swag.locators;

import org.openqa.selenium.By;

public class Application {
    public static final By navigatorMenu = By.xpath("//android.view.ViewGroup[@content-desc='test-Menu']//android.widget.ImageView");
    public static final By logoutButton = By.xpath("//android.view.ViewGroup[@content-desc='test-LOGOUT']");
    public static final By Tshirt1 = By.xpath("(//android.view.ViewGroup[@content-desc='test-Item'])[3]/android.view.ViewGroup/android.widget.ImageView");
    public static final By addToCartButton = By.xpath("//android.widget.TextView[@text='ADD TO CART']");
    public static final By cartButton = By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.ImageView");
    public static final By checkoutButton = By.xpath("//android.widget.TextView[@text='CHECKOUT']");
    public static final By continueButton = By.xpath("//android.widget.TextView[@text='CONTINUE']");
    public static final By finishButton = By.xpath("//android.widget.TextView[@text='FINISH']");
    public static final By verificationLabel = By.xpath("//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']");

    public static final By firstName = By.xpath("//android.widget.EditText[@content-desc='test-First Name']");
    public static final By lastName = By.xpath("//android.widget.EditText[@content-desc='test-Last Name']");
    public static final By postalCode = By.xpath("//android.widget.EditText[@content-desc='test-Zip/Postal Code']");
}