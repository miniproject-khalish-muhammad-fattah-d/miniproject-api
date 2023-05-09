@OrdersAltaShopAPI
Feature: Orders on Alta Shop API

  @AltaShopAPI @NormalPositive @NewOrderAS
  Scenario: User can create new order for specific user
    Given User call an API "/auth/login" with method "POST" with payload below
      | email       | password       |
      | userEmail   | userPassword   |
    And user get new token
    And User call an API "/orders" with method "POSTarray" with payload below and specific token
      | product_id       | quantity        |
      | randomProductId  | randomQuantity  |
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/createOrder.json"

  @AltaShopAPI @NormalPositive @AllOrdersAS
  Scenario: User can get all orders with specific token
    Given User call an API "/auth/login" with method "POST" with payload below
      | email       | password       |
      | userEmail   | userPassword   |
    And user get new token
    And User call an API "/orders" with method "GET" and specific token
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/allOrders.json"

  @AltaShopAPI @NormalPositive @OrdersByIdAS
  Scenario: User can get orders by id with specific token
    Given User call an API "/auth/login" with method "POST" with payload below
      | email       | password       |
      | userEmail   | userPassword   |
    And user get new token
    And User call an API "/orders/11036" with method "GET" and specific token
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/ordersById.json"