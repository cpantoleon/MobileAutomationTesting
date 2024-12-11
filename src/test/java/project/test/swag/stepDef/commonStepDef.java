package project.test.swag.stepDef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.net.MalformedURLException;

public class commonStepDef extends configStepDef{


    @Before
    public void setUp() throws MalformedURLException {
        initializeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
