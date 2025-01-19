package com.valtech.stepDef;

import com.valtech.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

        @Before
        public void setUp() {
            Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Driver.get().manage().window().maximize();
        }

        @After
        public void tearDown(Scenario scenario) {
            System.out.println("After hooks");

            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());

            }
            Driver.closeDriver();
        }
}
