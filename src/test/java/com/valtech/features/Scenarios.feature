Feature: Valtech Qa validateion task

  Background: I go to the base Url
    When I go to the main page

    @test
    Scenario: Validate single Broker is displayed with correct details
      Given I am on the brokers page
      Then I validate that the search by "Key word" input is preset
      And I input the name "Adriana Tomova"
      Then I should see only one entery
      And I validate that the properties button is present
      Then I click on the Details button
      And validate that the address i present
      And the two calling numbers are preset


#      @test
#      Scenario: Validate that all users have the same Properties
#        Given I am on the brokers page
#        Then I validate that the search by "Key word" input is preset
#        Then I get all the names of the Brokers
#        Then make sure the address, the 2 phone numbers, and the number of properties assigned to the broker are displayed
