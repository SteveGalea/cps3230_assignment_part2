Feature: MarketAlertUm Customised E-commerce Alerts

  In order to make use of the marketalertum website
  As a user or an administrator of marketalertum
  I want to be able to login with valid credentials and then be able to use the customised Alert List
  I want to be able to see a maximum of 5 alerts, with the correct layout and icon.


  Scenario: Valid Login
    Given I am a user of marketalertum
    When I login using valid credentials
    Then I should see my alerts
    And I quit the browser

  Scenario: Invalid Login
    Given I am a user of marketalertum
    When I login using invalid credentials
    Then I should see the login screen again
    And I quit the browser

  Scenario: Alert layout
    Given I am an administrator of the website and I upload 3 alerts
    Given I am a user of marketalertum
    When I view a list of alerts
    Then each alert should contain an icon
    And each alert should contain a heading
    And each alert should contain a description
    And each alert should contain an image
    And each alert should contain a price
    And each alert should contain a link to the original product website
    And I quit the browser

  Scenario: Alert limit
    Given I am an administrator of the website and I upload more than 5 alerts
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 5 alerts
    And I quit the browser

#  Scenario: Car Icon check
#    Given I am an administrator of the website and I upload an alert of type one
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-car.png"
#
#  Scenario: Boat Icon check
#    Given I am an administrator of the website and I upload an alert of type two
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-boat.png"
#
#  Scenario: Property Rent Icon check
#    Given I am an administrator of the website and I upload an alert of type three
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-property-rent.png"
#
#  Scenario: Property Sale Icon check
#    Given I am an administrator of the website and I upload an alert of type four
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-property-sale.png"
#
#  Scenario: Toys Icon check
#    Given I am an administrator of the website and I upload an alert of type five
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-toys.png"
#
#  Scenario: Electronics Icon check
#    Given I am an administrator of the website and I upload an alert of type six
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-electronics.png"

  Scenario Outline: Icon check
    Given I am an administrator of the website and I upload an alert of type <alertType>
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 1 alerts
    And the icon displayed should be <iconFileName>
    And I quit the browser

    Examples:
    |alertType    |iconFileName               |
    |one          |"icon-car.png"             |
    |two          |"icon-boat.png"            |
    |three        |"icon-property-rent.png"   |
    |four         |"icon-property-sale.png"   |
    |five         |"icon-toys.png"            |
    |six          |"icon-electronics.png"     |
#    |-1         |icon-boat.png            |