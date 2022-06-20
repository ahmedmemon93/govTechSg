Feature: GET endpoint which returns a list consist of natid, tax relief amount and name
  Scenario: Get tax reflief API returns the result with correct information
    Given User creates an API request with following values
      | natid     | birthday | gender | name         | tax | salary |
      | G8843844t | 22031990 | M      | Javed Ahmed1 | 200 | 10000  |
      | G8843844g | 22031990 | M      | User number2 | 200 | 10000  |
      | G8843844h | 22031990 | M      | User number3 | 200 | 10000  |
    When user calls end point INSERT_RECORD_MULTIPLE_END_POINT Api with given request body
    Then API should return status code 202
    When user calls get API end point TAX_RELIEF_END_POINT with given request body
    Then API should return status code 200
    And Validate relief API response

  Scenario: natid field must be masked from the 5th character onwards with dollar sign ‘$’
    Given User creates an API request with following values
      | natid     | birthday | gender | name         | tax | salary |
      | G8843844t | 22031990 | M      | Javed Ahmed1 | 200 | 10000  |
      | G8843844g | 22031990 | M      | User number2 | 200 | 10000  |
      | G8843844h | 22031990 | M      | User number3 | 200 | 10000  |
    When user calls end point INSERT_RECORD_MULTIPLE_END_POINT Api with given request body
    Then API should return status code 202
    When user calls get API end point TAX_RELIEF_END_POINT with given request body
    Then API should return status code 200
    And Validate relief API response

