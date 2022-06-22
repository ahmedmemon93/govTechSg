Feature: As the Clerk, I should be able to insert a single record of working class hero into database via an API

  @multipleRecrd @regression
  Scenario: [ADD List] Single record of a working class hero can be added via list
    Given User creates an API request with following values
      | natid     | birthday | gender | name         | tax | salary |
      | G8843844t | 22031990 | M      | Javed Ahmed1 | 200 | 10000  |
      | G8843844g | 22031990 | M      | User number2 | 200 | 10000  |
      | G8843844h | 22031990 | M      | User number3 | 200 | 10000  |
    When user calls end point INSERT_RECORD_MULTIPLE_END_POINT Api with given request body
    Then API should return status code 202
    And response body should have correct values
