package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", dryRun = !true, glue = { "stepdefinitions",
		"hooks" }, snippets = CucumberOptions.SnippetType.CAMELCASE, plugin = { "pretty",
				"html:target/cucumber-report.html" }, monochrome = true, tags = "@Regression")
public class TestRunner {

}