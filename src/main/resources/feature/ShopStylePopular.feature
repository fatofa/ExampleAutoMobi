Feature: Shop Men in ShopStyle Fashion Cash Back app
  Background: Go to the Shop Men
    Given User goes to the Shop Men

    Scenario: Check the lowest price products in the Shoes
      Given User is in the home screen of Shop Men
      When User chooses the first lowest price product in the Shoes
      Then User sees products name, product price and Buy now button
