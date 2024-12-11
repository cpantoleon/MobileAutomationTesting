package project.test.swag.stepDef;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import project.test.swag.actions.AppUI;
import project.test.swag.actions.LoginActions;
import project.test.swag.questions.LoginQuestions;
import project.test.swag.utils.YMLConfig;
import project.test.swag.utils.YamlConfigReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class configStepDef {

    LoginActions loginActions;

    LoginQuestions loginQuestions;

    YamlConfigReader yamlConfigReader;

    AppUI appUI;

    YMLConfig ymlConfig;

    static AppiumDriver driver;

    public configStepDef() {
        this.loginActions = new LoginActions();
        this.loginQuestions = new LoginQuestions();
        this.yamlConfigReader = new YamlConfigReader();
        this.ymlConfig = new YMLConfig();
        this.appUI = new AppUI();
    }

    public void initializeDriver() throws MalformedURLException {
        if (driver == null) {
            List<String> connectedDevices = getAdbConnectedDevices();

            YMLConfig.initialize("androidMyPhoneProjectYML.txt");
            String targetDevice = (String) YMLConfig.configuration.get("deviceName");

            String filePath = connectedDevices.contains(targetDevice)
                    ? "androidMyPhoneProjectYML.txt"
                    : "androidEmulatorProjectYML.txt";

            YMLConfig.initialize(filePath);

            DesiredCapabilities capabilities = new DesiredCapabilities();

            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, YMLConfig.configuration.get("platformName"));
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, YMLConfig.configuration.get("deviceName"));
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, YMLConfig.configuration.get("automationName"));
            capabilities.setCapability("appPackage", YMLConfig.configuration.get("appPackage"));
            capabilities.setCapability("appActivity", YMLConfig.configuration.get("appActivity"));
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, (Integer) YMLConfig.configuration.get("newCommandTimeout"));

            driver = new AndroidDriver(new URL(String.valueOf(YMLConfig.configuration.get("url"))), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    private List<String> getAdbConnectedDevices() {
        List<String> devices = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("adb", "devices");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("List of devices attached")) {
                    continue;
                }

                if (line.trim().length() > 0) {
                    String[] parts = line.split("\\s+");
                    if (parts.length > 1 && "device".equals(parts[1])) {
                        devices.add(parts[0]);
                    }
                }
            }

            process.waitFor();
        } catch (Exception e) {
            System.err.println("Error while checking ADB devices: " + e.getMessage());
            e.printStackTrace();
        }
        return devices;
    }
}