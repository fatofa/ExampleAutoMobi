@Popular @ProductList
Feature: Mobile Web Mobile Test

  @TC_001
  Scenario: Google Search
    Given Mobile browser is on the google page
    When Enter "panda" in to search box
    Then Result "panda" is shown
    And Following result is shown
      | related |
      | Gấu trúc lớn |
      | Động vật |
