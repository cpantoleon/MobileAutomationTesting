@Test
Feature: Swag Labs Login Test
  As a user, I want to be able to log in to the Swag Labs app with valid credentials.

  Scenario: Login with valid credentials
    When I enter the username "Valid"
    And I enter the password "Valid"
    And I click the login button
    Then The user logged in successfully

  Scenario: Login with invalid credentials
    When I enter the username "Invalid"
    And I enter the password "Invalid"
    And I click the login button
    Then An error message is displayed

  Scenario: User logout
    Given I enter the username "Valid"
    And I enter the password "Valid"
    When I click the login button
    And The user logged in successfully
    Then the user clicks to logout button
    And the user logged out successfully
