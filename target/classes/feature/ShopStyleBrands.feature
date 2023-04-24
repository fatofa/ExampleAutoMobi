Feature: Shop Men in ShopStyle Fashion Cash Back app
  Background: Go to the Shop Men
    Given User goes to the Shop Men

    Scenario Outline: Check brand searching function
      Given User is in the home screen of Shop Men
      When User search the a <brand>
      Then User sees the <brand_name>
      Examples:
        | brand   | brand_name |
        | aer     | aer        |
        | adidas  | adidas     |
