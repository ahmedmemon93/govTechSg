Feature: GET taxRelief endpoint which returns a list consist of natid, tax relief amount and name

  Background:
    Given user calls post API end point DATABASE_PURGE_END_POINT with given request body
    Then API should return status code 200

  @regression
  Scenario: AC1: Get tax reflief API returns the result with correct information
    And User creates an API request with following values
      | natid     | birthday | gender | name         | tax  | salary |
      | G8843844t | 22031890 | M      | Javed Ahmed1 | 20.9 | 1454   |
    When user calls end point INSERT_RECORD_MULTIPLE_END_POINT Api with given request body
    Then API should return status code 202
    When user calls get API end point TAX_RELIEF_END_POINT with given request body
    Then API should return status code 200
    And Validate relief API response

  @regression
  Scenario: AC3 natid field must be masked from the 5th character onwards with dollar sign ‘$’
    And User creates an API request with following values
      | natid     | birthday | gender | name         | tax  | salary |
      | G8843844t | 22031890 | M      | Javed Ahmed1 | 20.9 | 1454   |
    When user calls end point INSERT_RECORD_MULTIPLE_END_POINT Api with given request body
    Then API should return status code 202
    When user calls get API end point TAX_RELIEF_END_POINT with given request body
    Then API should return status code 200
    And Validate relief API response
    And Validate that tax relief return in relief API is calculated correctly

  @regression
  Scenario: AC2 Verify that tax relief returned in the API is correctly calculated as per input
    And User creates an API request with following values
      | natid     | birthday | gender | name         | tax  | salary |
      | G8843844t | 22031890 | M      | Javed Ahmed1 | 20.9 | 1454   |
    When user calls end point INSERT_RECORD_MULTIPLE_END_POINT Api with given request body
    Then API should return status code 202
    When user calls get API end point TAX_RELIEF_END_POINT with given request body
    Then API should return status code 200
    And Validate relief API response
    And Validate that natid is masked after 5 characters

  @regression
  Scenario: AC5 If the calculated tax relief amount after subjecting to normal rounding rule is more than 0.00 but less than 50.00, the final tax relief amount should be 50.00
    And User creates an API request with following values
      | natid     | birthday | gender | name         | tax  | salary |
      | G8843844t | 22031890 | M      | Javed Ahmed1 | 20.9 | 454    |
    When user calls end point INSERT_RECORD_MULTIPLE_END_POINT Api with given request body
    Then API should return status code 202
    When user calls get API end point TAX_RELIEF_END_POINT with given request body
    Then API should return status code 200
    And Validate relief API response
    And Validate that natid is masked after 5 characters

