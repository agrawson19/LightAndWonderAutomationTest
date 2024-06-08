Feature: ATT End to End testing

  Background:
  Given  login to amazon


  @smoke
 Scenario Outline: Adding a Monitor and laptop Item in Cart and verifying sub total
    And Search field type "<fieldType>"
    And Select <itemNumber> item in the list
    When Add the item to cart
    And Open Cart from the top panel
    Then Verify that the price is identical to the product page
    And Verify that the sub total is identical to the product page
  Examples:
    |fieldType|itemNumber|
    |monitor   |     0    |
    |laptop   |     1    |

  @smoke
  Scenario: Adding a Headphones and Keyboard Items in Cart and verifying sub total
    And Search field type "Headphones"
    And Select 0 item in the list
    When Add the item to cart
    And Open Cart from the top panel
    And Search field type "Keyboard"
    And Select 0 item in the list
    And add More Item To Cart
    And Open Cart from the top panel
    Then Verify that the price is identical to the product page
    And Verify that the sub total is identical to the product page








