package com.valtech.stepDef;

import com.valtech.entities.BrokerScalper;
import com.valtech.pages.Broker;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.util.Collections;
import java.util.List;


public class Tests extends Hooks {

    @DataProvider(name = "arrayListData")
    public Object[][] provideDataFromArrayList() {
        List<String> namesList = BrokerScalper.getAllNames();
        Collections.sort(namesList);

        Object[][] dataArray = new Object[namesList.size()][1];
        for (int i = 0; i < namesList.size(); i++) {
            dataArray[i][0] = namesList.get(i);
        }

        return dataArray;
    }

    @Test(dataProvider = "arrayListData")
    public void get_all_brokers_information_for(String name){
        Broker broker = new Broker();
        SoftAssert softAssertion = new SoftAssert();

        broker.setContextName(name);

        broker.inputName(name);

        broker.isOnlyOne();

        Assert.assertTrue(broker.isProperitesVisible());

        broker.clickDetailsButton();

        softAssertion.assertNotEquals(broker.getAddress(),"");

        softAssertion.assertEquals(broker.getPhoneNumberCount(), 2);

        broker.clearInput();

        softAssertion.assertAll();
    }

}
