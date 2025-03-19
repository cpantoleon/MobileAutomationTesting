package project.test.swag.stepDef;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import project.test.swag.actions.AppUI;
import project.test.swag.actions.LoginActions;
import project.test.swag.questions.AppQuestions;
import project.test.swag.questions.LoginQuestions;
import project.test.swag.utils.AdbConnectedDevices;
import project.test.swag.utils.DriverCapabilities;
import project.test.swag.utils.YMLConfig;
import project.test.swag.utils.YamlConfigReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class configStepDef {

    LoginActions loginActions;
    LoginQuestions loginQuestions;
    YamlConfigReader yamlConfigReader;
    AppUI appUI;
    YMLConfig ymlConfig;
    AdbConnectedDevices adbConnectedDevices;
    AppQuestions appQuestions;
    static AppiumDriver driver;

    public configStepDef() {
        this.loginActions = new LoginActions();
        this.loginQuestions = new LoginQuestions();
        this.yamlConfigReader = new YamlConfigReader();
        this.ymlConfig = new YMLConfig();
        this.appUI = new AppUI();
        this.appQuestions = new AppQuestions();
        this.adbConnectedDevices = new AdbConnectedDevices();
    }

    public void initializeDriver() throws MalformedURLException {
        if (driver == null) {
            List<String> connectedDevices = adbConnectedDevices.getAdbConnectedDevices();

            if (connectedDevices == null || connectedDevices.isEmpty()) {
                throw new IllegalStateException("No ADB devices are connected. Please connect a device and try again.");
            } else {
                System.out.println("Connected devices: " + connectedDevices);
            }

            YMLConfig.initialize("androidMyPhoneProjectYML.txt");
            String targetDevice = (String) YMLConfig.configuration.get("deviceName");

            String filePath = connectedDevices.contains(targetDevice)
                    ? "androidMyPhoneProjectYML.txt"
                    : "androidEmulatorProjectYML.txt";

            YMLConfig.initialize(filePath);

            DesiredCapabilities capabilities = DriverCapabilities.getCapabilities(YMLConfig.configuration);

            driver = new AndroidDriver(new URL(String.valueOf(YMLConfig.configuration.get("url"))), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }
}
