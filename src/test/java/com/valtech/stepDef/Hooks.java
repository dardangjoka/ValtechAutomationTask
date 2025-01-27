package com.valtech.stepDef;

import com.valtech.utils.ConfigReader;
import com.valtech.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @BeforeTest
    public void setUp() {
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();
        Driver.get().get(ConfigReader.get("baseurl"));
        Driver.get().findElement(By.xpath("//button[contains(text(), 'Understood')]")).click();
        Driver.get().manage().addCookie(new Cookie("test_cookie", "CheckForPermission"));
        //TODO read cookies from a file.
    }

    @AfterTest
    public void tearDown() {
        System.out.println("After hooks");
        Driver.closeDriver();
    }
}
