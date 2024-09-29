package maven_group.test;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
  features = "src/test/java/maven_group/test", // path to the feature files
  glue = {"maven_group.test"},               // path to step definitions
  plugin = {"pretty", "html:target/cucumber-reports.html"} // generates HTML report in target folder
)
public class TestRunner {
}
