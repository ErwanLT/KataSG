package fr.eletutour.kataSG.acceptance.configuration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",
                    glue = "fr.eletutour.kataSG.integration.stepsDef")
public class CucumberConfig {
}
