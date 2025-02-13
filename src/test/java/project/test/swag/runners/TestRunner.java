package project.test.swag.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.picocontainer.PicoFactory;
import org.junit.jupiter.api.BeforeAll;  // <-- JUnit 5 BeforeAll
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        objectFactory = PicoFactory.class,
        features = "src/test/resources/features",
        glue = "project.test.swag.stepDef",
        plugin = { "pretty", "html:target/Test.html", "json:target/Test.json" },
        monochrome = true
)
public class TestRunner {

        @BeforeAll  // <-- Change to BeforeAll for JUnit 5
        public static void setup() {
                System.out.println("Test execution started...");
        }
}
