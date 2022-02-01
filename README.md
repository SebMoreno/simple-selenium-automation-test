# Devsavant Automation Test

This project contains a structure ready to implement automated test cases using Cucumber and Selenium along with the following components:

- JUnit Assert
- Cucumber
- Selenium Webdriver
- Chrome Driver
- java.net.http
- org.json.simple

## Setting up yourself

- Make sure you have JDK 11 or newer installed in your environment
- Maven 3.3 or newer is also required
- Once you have cloned the project execute `mvn package` command from project root directory in order for the dependencies to be resolved
- You should have Chrome or Chromium installed in your system. Follow these steps in order to install **chromedriver**:

    1. Chromedriver has to match the Chrome/Chromium version in your system. You can download it from: [https://chromedriver.chromium.org/downloads]
    2. Unzip the binary file and place it under `/usr/local/bin`
        - It can be located under any directory that is part of the `$PATH` or the directory should be added to the `$PATH` environment variable
        - `System.setProperty("webdriver.chrome.driver", "path-to-chromedriver");` can also be used as long as chromedriver binary file makes part of your solution. Otherwise your project may not run in other systems.

## Test

### Part 1

1. Make sure your environment is set up accordingly to the above part
2. Fork this project into your personal account
3. Clone the project repository into your environment
4. Create a new branch in order to commit your work in it

### Part 2

1. Go to http://worldtimeapi.org/pages/examples
2. Check how http://worldtimeapi.org/api/timezone endpoint works
    1. Define one **negative** scenario that you would test
    2. What would you assert?
    3. Write that scenario using Gherkin language in `timezone.feature` file
3. Check how http://worldtimeapi.org/api/timezone/America/Bogota works    
    1. Define two **negative** scenarios that you would test
    2. What would you assert on each?
    3. Write them in your feature file using `Scenario Outline` clause
4. Add a description to the feature file and the scenarios

### Part 3

1. Implement the test steps in `StepDefinitions.java` file
2. Your tests should run and show results when executing `mvn test` command
3. Add a mechanism to run all the tests related with world time API only
4. Add a mechanism to run only the tests in the `Scenario Outline` clause

### Part 4

1. In a separated feature file create a new test that covers the following user story:
```
As a user, I want to go to Google home page
and type the term "Devsavant" in the search box
and click on the "I'm Feeling Lucky" button
so that I would be taken to the Devsavant's website
```
2. Add a description to the test and to the scenario
3. Implement the test in a separated `*.java` file using Selenium
4. Implement a screen capture of the browser when the Devsavant website is opened
5. Your test should run and show results along with the other tests when executing `mvn test` command
6. Add a mechanism to run only this test

### Part 5

1. Modify the `README.md` fileto add the following information
    - Your name, surname and email
    - How to run the World Time API tests only
    - How to run the Scenario Outline tests only
    - How to run the Devsavant website test only
    - Any other description you consider relevant from your implementation of this challenge
2. Push your work to the repo
3. Create a merge request to the main branch
4. Merge the changes to the main branch without deleting the source branch

### Optional Bonus

Implement a library to show test results in an HTML file that is part of the repo. Explain your implementation and how to execute it in the `README.md` file.

Best of luck!
