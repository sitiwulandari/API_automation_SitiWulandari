package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = "src/test/java/stepDefinitions/stepDefinitions",
plugin = "json:target/jsonReports/Test-Summary-Report.json")

public class TestRunner {

}
