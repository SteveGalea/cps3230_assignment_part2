Feature: MarketAlertUm Customised E-commerce Alerts

  In order to make use of the marketalertum website
  As a user or an administrator of marketalertum
  I want to be able to login with valid credentials and then be able to use the customised Alert List
  I want to be able to see a maximum of 5 alerts, with the correct layout and icon.

  Scenario: Valid Login
    Given I am a user of marketalertum
    When I login using valid credentials
    Then I should see my alerts

  Scenario: Invalid Login
    Given I am a user of marketalertum
    When I login using invalid credentials
    Then I should see the login screen again

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

  Scenario: Alert limit
    Given I am an administrator of the website and I upload more than 5 alerts
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 5 alerts
  Scenario Outline: Icon check
    Given I am an administrator of the website and I upload an alert of type <alert-type>
    Given I am a user of marketalertum
    When I view a list of alerts
    Then I should see 1 alerts
    And the icon displayed should be "<icon-file-name>"

    Examples:
    |alert-type   |icon-file-name           |
    |error        |icon-error.png           |
    |1            |icon-car.png             |
    |2            |icon-boat.png            |
    |3            |icon-property-rent.jpg   |
    |4            |icon-property-sale.jpg   |
    |5            |icon-toys.png            |
    |6            |icon-electronics.png     |


#
#  Scenario: Car Icon check
#    Given I am an administrator of the website and I upload an alert of type 1
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-car.png"
#
#  Scenario: Boat Icon check
#    Given I am an administrator of the website and I upload an alert of type 2
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-boat.png"
#
#  Scenario: Property Rent Icon check
#    Given I am an administrator of the website and I upload an alert of type 3
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
##    And the icon displayed should be "icon-property-rent.png"
#    And the icon displayed should be "icon-property-rent.jpg"
#
#  Scenario: Property Sale Icon check
#    Given I am an administrator of the website and I upload an alert of type 4
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
##    And the icon displayed should be "icon-property-sale.png"
#    And the icon displayed should be "icon-property-sale.jpg"
#
#  Scenario: Toys Icon check
#    Given I am an administrator of the website and I upload an alert of type 5
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-toys.png"
#
#  Scenario: Electronics Icon check
#    Given I am an administrator of the website and I upload an alert of type 6
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-electronics.png"
#
#  Scenario: Error Icon check
#    Given I am an administrator of the website and I upload an alert of type error
#    Given I am a user of marketalertum
#    When I view a list of alerts
#    Then I should see 1 alerts
#    And the icon displayed should be "icon-error.png"
