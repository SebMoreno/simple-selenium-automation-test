@WorldTimeApi
Feature: WorldTimeApi
  As a user of the world time API
  I want to be able to get the current time in differents timezones and from differents IPs
  So that I can see the time in differents places

  Scenario: Getting the current time in a timezone
    Given User obtains the list of time zones from the API
    When gets the current time for the time zone at index 44
    Then the response should have status code 200
    And the response should have a valid schema

  @FailPath
  Scenario Outline: Fail getting the current time for a given bad IP
    When User obtains the time for the ip "<ip>"
    Then the response should have status code 404
    And the response should have the error message "<expectedMessage>"
    Examples:
      | ip        | expectedMessage |
      | 127.0.0.1 | malformed ip    |
      | o.o.o.l   | malformed ip    |
