Feature: ATT End to End testing

  Background:
  Given  login to amazon

  @smoke
  Scenario: Adding a Monitor Item in Cart and verifying sub total
    And Search field type "Monitor"
    And Select the first item in the list
    When Add the item to cart
    And Open Cart from the top panel
    Then Verify that the price is identical to the product page
    And Verify that the sub total is identical to the product page




