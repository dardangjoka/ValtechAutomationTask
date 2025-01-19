package com.valtech.stepDef;
import com.valtech.pages.Broker;
import com.valtech.utils.ConfigReader;
import com.valtech.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class StepDef {
    private Broker broker = new Broker();

    List<String> names = new ArrayList<>();

    String brokerName;

    @When("I go to the main page")
    public void i_go_to_the_main_page() {
        Driver.get().get(ConfigReader.get("baseurl"));

    }

    @Given("I am on the brokers page")
    public void i_am_on_the_brokers_page() {
        Assert.assertEquals("Find your Yavlena broker | Yavlena" , Driver.get().getTitle());
    }

    @Then("I validate that the search by {string} input is preset")
    public void i_validate_that_the_search_by_input_is_preset(String name){
            Assert.assertEquals(broker.getKeyWordInput().getText(), name);

    }

    @Then("I input the name {string}")
    public void i_input_the_name(String name) {
        brokerName = name;
        broker.setName(name);
        broker.keyWordInput.sendKeys(name);
    }

    @Then("I should see only one entery")
    public void i_should_see_only_one_entery() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(broker.names.size() == 1);
    }

    @Then("I validate that the properties button is present")
    public void i_validate_that_the_properties_button_is_present() {
        broker.getPropertiesByName();
    }

    @Then("I click on the Details button")
    public void i_click_on_the_details_button() {
        broker.getDetailsButtonByName().click();
    }

    @Then("validate that the address i present")
    public void validate_that_the_address_i_present() {

    }

    @Then("the two calling numbers are preset")
    public void the_two_calling_numbers_are_preset() throws InterruptedException {

        broker.clearInput();
    }





}
