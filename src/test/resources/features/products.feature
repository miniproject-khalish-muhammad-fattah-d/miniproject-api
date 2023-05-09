@ProductsAltaShopAPI
Feature: Products on Alta Shop API

  @AltaShopAPI @NormalPositive @NewProductAS
  Scenario: User can create new product
    Given User call an API "/auth/login" with method "POST" with payload below
      | email       | password       |
      | userEmail   | userPassword   |
    And user get new token
    And User call an API "/products" with method "POST" with payload below
      | name              | description       | price              | categories              |
      | randomProductName | randomDescription | randomProductPrice | randomProductCategories |
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/createProduct.json"

  @AltaShopAPI @NormalPositive @AllProductsAS
  Scenario: User can get all products
    Given User call an API "/products" with method "GET"
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/allProducts.json"

  @AltaShopAPI @NormalPositive @ProductsByIdAS
  Scenario: User can get selected products by id
    Given User call an API "/products/14394" with method "GET"
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/productsById.json"

  @AltaShopAPI @NormalPositive @DeleteProductAS
  Scenario: User can delete selected product by id
    Given User call an API "/products/1" with method "DELETE"
    Then User verify status code is 200
    Then User verify "data" is exist

  @AltaShopAPI @NormalPositive @AssignRatingAS
  Scenario: User can assign a product rating with specific token
    Given User call an API "/auth/login" with method "POST" with payload below
      | email       | password       |
      | userEmail   | userPassword   |
    And user get new token
    And User call an API "/products/14394/ratings" with method "POST" with payload below and specific token
      | count        |
      | randomRating |
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/productsById.json"

  @AltaShopAPI @NormalPositive @ProductRatingsByIdAS
  Scenario: User can get selected product rating by id
    Given User call an API "/products/12887/ratings" with method "GET"
    Then User verify status code is 200
    Then User verify "data" is exist

  @AltaShopAPI @NormalPositive @NewCommentAS
  Scenario: User can create new comment with specific token
    Given User call an API "/auth/login" with method "POST" with payload below
      | email       | password       |
      | userEmail   | userPassword   |
    And user get new token
    And User call an API "/products/2/comments" with method "POST" with payload below and specific token
      | content           |
      | randomDescription |
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/newComment.json"

  @AltaShopAPI @NormalPositive @GetCommentsAS
  Scenario: User can get comments
    Given User call an API "/products/2/comments" with method "GET"
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/getComments.json"