package project.test.swag.utils;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class DriverCapabilities {

    public static DesiredCapabilities getCapabilities(Map<String, Object> config) {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, config.get("platformName"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, config.get("deviceName"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, config.get("automationName"));
        capabilities.setCapability("appPackage", config.get("appPackage"));
        capabilities.setCapability("appActivity", config.get("appActivity"));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, (Integer) config.get("newCommandTimeout"));

        return capabilities;
    }
}
