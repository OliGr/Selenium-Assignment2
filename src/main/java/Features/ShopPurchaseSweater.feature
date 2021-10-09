Feature: Online shop purchase

  Scenario: User can purchase an item of specific size and select the right payment method

    Given user is logged in

    When user navigates to clothes page

    And user selects product

    And selects the right size

    And selects the right quantity

    And adds the product to cart

    And proceeds to checkout

    And confirms address

    And selects delivery method

    And selects payment method and orders with an obligation to pay

    Then user takes a screenshot to confirm the purchase

    And user quits browsing



