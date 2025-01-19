package com.valtech.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"html:target/default-cucumber-reports.json",
                "json:target/cucumber.json"},
        publish = true,
        features = "src/test/java/com/valtech/features/",
        glue = "com/valtech/stepDef",
        tags = "@test",
        dryRun = false
)
public class Runner {
}
