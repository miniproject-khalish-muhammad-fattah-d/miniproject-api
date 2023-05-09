@ProductCategoriesAltaShopAPI
Feature: Product Categories on Alta Shop API

  @AltaShopAPI @NormalPositive @NewCategoryAS
  Scenario: User can create new categories
    Given User call an API "/categories" with method "POST" with payload below
      | name           | description       |
      | randomCategory | randomDescription |
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/createCategory.json"

  @AltaShopAPI @NormalPositive @AllCategoriesAS
  Scenario: User can get all categories
    Given User call an API "/categories" with method "GET"
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/allCategories.json"

  @AltaShopAPI @NormalPositive @CategoriesByIdAS
  Scenario: User can get categories by id
    Given User call an API "/categories/12656" with method "GET"
    Then User verify status code is 200
    Then User verify response is match with json schema "schema/categoriesById.json"

  @AltaShopAPI @NormalPositive @DeleteCategoryAS
  Scenario: User can delete selected category by id
    Given User call an API "/categories/1" with method "DELETE"
    Then User verify status code is 200
    Then User verify "data" is exist