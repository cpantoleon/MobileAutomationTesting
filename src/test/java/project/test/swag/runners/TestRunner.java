package project.test.swag.runners;

import io.cucumber.junit.platform.engine.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.picocontainer.PicoFactory;
import org.junit.jupiter.api.BeforeAll;

@Cucumber  // JUnit 5's integration with Cucumber
@CucumberOptions(
        objectFactory = PicoFactory.class,
        features = "src/test/resources/features",
        glue = "project.test.swag.stepDef",
        plugin = { "pretty", "html:target/Test.html", "json:target/Test.json" },
        monochrome = true
)
public class TestRunner {

    @BeforeAll  // JUnit 5's BeforeAll
    public static void setup() {
        System.out.println("Test execution started...");
    }
}
