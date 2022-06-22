Feature: As the Clerk, I should be able to insert a single record of working class hero into database via an API

  @singleRecord @regression
  Scenario: AC1 Single record of a working class hero should consist of Natural Id (natid), Name, Gender, Birthday, Salary and Tax paid
    Given User creates a API request with following values
      | jsonPath | field_value  |
      | natid    | G8843844t    |
      | birthday | 22031990     |
      | gender   | M            |
      | name     | Javed Ahmed1 |
      | tax      | 200          |
      | salary   | 10000        |
    When user calls end point INSERT_RECORD_END_POINT Api with given request body
    Then API should return status code 202
    And response body should have correct values
