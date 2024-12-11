package project.test.swag.locators;

import org.openqa.selenium.By;

public class Login {
    public static final By userName = By.xpath("//android.widget.EditText[@content-desc='test-Username']");
    public static final By passwordField = By.xpath("//android.widget.EditText[@content-desc='test-Password']");
    public static final By loginButton = By.xpath("//android.widget.TextView[@text='LOGIN']");
    public static final By loginInvalidMessage = By.xpath("//android.widget.TextView[@text='Username and password do not match any user in this service.']");
    public static final By logoInFrontPage = By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageView[2]");
}
