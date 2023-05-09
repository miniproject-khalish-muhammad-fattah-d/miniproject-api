@HelloAltaShopAPI
Feature: Authentication on Alta Shop API

  @AltaShopAPI @NormalPositive @HelloAS
  Scenario: User get index hello
    Given User call an API "/hello" with method "GET"
    Then User verify status code is 200
    Then User verify "data" is exist