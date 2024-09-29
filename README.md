# API-Automation

This project automates the validation of API responses using Cucumber, RestAssured, and JUnit. It verifies that all users in the fictional city of FanCode have completed more than 50% of their tasks from a public API (http://jsonplaceholder.typicode.com/).

### Prerequisites

Java (version 8 or higher)
Maven (version 3.6+)
IDE (e.g., IntelliJ IDEA, Eclipse)
Git (optional, for cloning the project)

### Project Setup

#### Step 1: Clone the Project

If the project is hosted on a version control system like GitHub, clone the repository using:

git clone https://github.com/gokulsunil1999/API-Automation.git
cd API-Automation

If not, download and extract the project files into your workspace.

#### Step 2: Install Dependencies

Ensure that Maven is properly installed by running:
mvn -version

If Maven is installed correctly, proceed to install project dependencies by running the following command inside the project directory:
mvn clean install

Maven will download all the required dependencies listed in the pom.xml file. These include:

Cucumber for writing feature files and step definitions.
RestAssured for making API calls.
JUnit for running the tests.

#### Step 3: Project Structure

src/test/java: Contains step definitions for the test cases and Gherkin .feature files that describe the scenarios.

#### Step 4: Running the Tests

Option 1: Using an IDE
Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
Right-click on the TestRunner.java file (located in src/test/java/maven_group/test/). 
Select Run 'TestRunner' to execute the tests. (for Eclipse - Right click on Maven Project --> Run As --> Maven test

Option 2: Using Maven

To run the tests from the command line, navigate to the project directory and run:
mvn test

This will trigger the Cucumber tests, and you will see the output in the console.

#### Step 5: Viewing Test Results

After running the tests, you can view the results in the console. If you configured a report (e.g., HTML or JSON), it will be available in the target folder.

If you added a plugin for generating an HTML report in TestRunner.java:
plugin = {"pretty", "html:target/cucumber-reports.html"}

You can open the generated report by navigating to target/cucumber-reports.html in your browser to view a more detailed summary of the tests.

Sample report:
<img width="931" alt="CucumberReport" src="https://github.com/user-attachments/assets/98cd7820-3627-41fe-9e8a-685092677616">


#### Troubleshooting
Maven Build Issues:
If you encounter any issues with dependency downloads, try running mvn clean install -U to force update the dependencies.
Test Failures:
Check the console logs to identify the issue. Common issues could be incorrect API responses or assertion failures in the test.
Use the printed debug logs to verify user data and completion percentage.

#### Project Overview
API: The project tests data from http://jsonplaceholder.typicode.com/ API, focusing on /users and /todos.
City Identification: Users from the city FanCode are identified by their latitude and longitude coordinates.
Task Completion Check: The project checks that users from FanCode city have completed more than 50% of their todos tasks.

#### Sample Scenario

Feature: Validate user task completion

  Scenario: Validate if all FanCode city users have completed more than 50% of their todo tasks
    Given the user list is fetched from the API
    And user belongs to the city FanCode
    Then the user's completed task percentage should be greater than 50%


This scenario describes the validation of user task completion percentage from the API.

