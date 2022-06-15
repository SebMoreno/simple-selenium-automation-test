@GoogleSearch
Feature: Search a word at Google.com
  As a Google Search user
  I want to search for a word
  So that I can find the relevant pages about it on Google

  Scenario: Search via the feeling lucky button
    Given User is on the Google search page
    When searches the word "Devsavant" with the feeling lucky button
    Then should see the main page of Devsavant
