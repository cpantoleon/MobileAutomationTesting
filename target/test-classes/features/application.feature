@Test
Feature: Swag Labs Store Test
  As a user, I want to be able to view the products and to perform a buy/purchase process

  Background:
    When I enter the username "Valid"
    And I enter the password "Valid"
    And I click the login button

  Scenario: The user do purchase
    When Add a product to the basket
    And Click on the Cart icon
    Then Continue the flow until the order to complete
    And The user complete the order successfully
