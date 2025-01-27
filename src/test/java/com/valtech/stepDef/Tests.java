package com.valtech.stepDef;

import com.valtech.entities.BrokerScalper;
import com.valtech.pages.Broker;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.util.Collections;
import java.util.List;

/**
 * The {@code Tests} class contains TestNG test cases for validating broker information
 * using data retrieved by the {@link BrokerScalper} utility. It uses a data provider
 * to dynamically supply broker names and verifies various properties of brokers.
 */
public class Tests extends Hooks {

    /**
     * Provides sorted broker names to the test cases.
     * <p>
     * This method retrieves a list of broker names using {@link BrokerScalper#getAllNames()},
     * sorts the list, and returns it as a two-dimensional array suitable for use as a
     * TestNG {@code @DataProvider}.
     * </p>
     *
     * @return A two-dimensional array of broker names. Each row contains one broker name.
     */
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

    /**
     * Test case to verify information and properties of brokers based on the provided name.
     * <p>
     * This test uses the {@link Broker} class to interact with broker details. It performs
     * the following validations:
     * <ul>
     *     <li>Checks that only one broker is displayed for the given name.</li>
     *     <li>Ensures the properties of the broker are visible.</li>
     *     <li>Verifies the broker's address is not empty.</li>
     *     <li>Confirms that the broker has exactly two phone numbers.</li>
     * </ul>
     * </p>
     *
     * @param name The name of the broker to validate, supplied by the {@code arrayListData} data provider.
     */
    @Test(dataProvider = "arrayListData")
    public void get_all_brokers_information_for(String name){
        Broker broker = new Broker();
        SoftAssert softAssertion = new SoftAssert();

        // Set the broker context based on the provided name
        broker.setContextName(name);

        // Input the broker name and verify there is only one result
        broker.inputName(name);

        broker.isOnlyOne();

        // Assert that the broker properties are visible
        Assert.assertTrue(broker.isProperitesVisible());

        broker.clickDetailsButton();

        // Perform additional validations using soft assertions
        softAssertion.assertNotEquals(broker.getAddress(),"");

        softAssertion.assertEquals(broker.getPhoneNumberCount(), 2);

        broker.clearInput();

        //Will thorow an assertion Exception if any of the assertions failed.
        softAssertion.assertAll();
    }

}
