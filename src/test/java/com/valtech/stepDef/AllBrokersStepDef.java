package com.valtech.stepDef;

import com.valtech.pages.Broker;
import com.valtech.utils.ConfigReader;
import com.valtech.utils.Driver;

import org.junit.Test;
import org.testng.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AllBrokersStepDef {

    private Broker broker = new Broker();

    List<String> names = new ArrayList<String>();

    public void setUp() {
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.get().manage().window().maximize();
        Driver.get().get(ConfigReader.get("baseurl"));
    }


    @Test
    public void testingAllBrokers() throws InterruptedException {
        setUp();
        getAllNames();

        names.forEach(c->{
            broker.setName(c);
            broker.keyWordInput.sendKeys(c);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Assert.assertTrue(broker.names.size() == 1);

            broker.getPropertiesByName();

            broker.getDetailsButtonByName().click();
            try {
                broker.clearInput();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        tearDown();
    }

    public void tearDown() {
        System.out.println("After hooks");
        Driver.closeDriver();
    }

    public  void getAllNames() throws InterruptedException {
        broker.getAllNames().forEach(c->{
            names.add(c.getText());
        });
    }

}
