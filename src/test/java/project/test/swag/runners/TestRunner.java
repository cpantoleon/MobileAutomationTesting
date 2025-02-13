package project.test.swag.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "project.test.swag.stepDef",
        plugin = { "pretty", "html:target/Test.html", "json:target/Test.json" },
        monochrome = true
)
public class testRunner {
}
