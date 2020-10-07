package fr.eletutour.kataSG;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features")
public class CucumberIntegrationTest extends SpringIntegrationTest {
}