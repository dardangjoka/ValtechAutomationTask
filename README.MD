# Valtech QA Task

UI automation suite that will check the information for all the brokers
on: https://www.yavlena.com/en/broker?city=Sofia
and validate against certain criteria.

## Installation

1. Clone or download this repository.
2. Java 17 must be installed.
3. Maven must be installed.
4. To run the suite, run `mvn -Dtest=stepDef.Tests test`.
5. (optionally, you can run `mvn test`)

## Key Implementation Details

1. Selenium WebDriver is used for the web browser controller.
2. WebDriverManager is used to set up Selenium WebDriver.
3. A Singleton design pattern is used to maintain a single instance of the driver.
4. Page Object Model, with PageFactory, is used to locate elements on the page.
5. TDD using dynamic parameterized testing is used to retrieve all data (from TestNG).
6. Test runners utilize TestNG’s runner and XML runner, and can be set up by:
   6.1 Configuring the XML file, or  
   6.2 Configuring the entire Surefire plugin.