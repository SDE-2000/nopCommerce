Feature: Login page feature

  Scenario: Login page title
    Given user is on Home page
    When user gets the title of the page
    Then page title should be "nopCommerce demo store. Home page title"

  Scenario: Forgot Password link
    Given user is on Home page
    And user click login
    When user gets the title of the page
    And page title should be "nopCommerce demo store. Login"
    Then forgot your password link should be displayed
