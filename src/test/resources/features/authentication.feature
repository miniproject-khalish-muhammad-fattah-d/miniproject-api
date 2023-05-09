@AuthenticationAltaShopAPI
Feature: Authentication on Alta Shop API

  @AltaShopAPI @NormalPositive @RegisterAS
  Scenario: User can register with valid credentials
    Given User call an API "/auth/register" with method "POST" with payload below
      | email       | password       | fullname       |
      | randomEmail | randomPassword | randomFullname |
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/register.json"

  @AltaShopAPI @NormalPositive @LoginAS
  Scenario: User can login with valid credentials
    Given User call an API "/auth/login" with method "POST" with payload below
      | email       | password       |
      | userEmail   | userPassword   |
    Then User verify status code is 200
    Then User verify "data" is exist

  @AltaShopAPI @NormalPositive @UserInfoAS
  Scenario: User can get user information with specific token
    Given User call an API "/auth/login" with method "POST" with payload below
      | email       | password       |
      | userEmail   | userPassword   |
    And user get new token
    And User call an API "/auth/info" with method "GET" and specific token
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/userInfo.json"