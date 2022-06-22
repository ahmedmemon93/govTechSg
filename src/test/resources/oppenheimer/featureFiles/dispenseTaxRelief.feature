Feature: As the Governor, I should be able to see a button on the screen so that I can dispense tax relief for my working class heroes

  Background:
    Given user calls post API end point DATABASE_PURGE_END_POINT with given request body
    Then API should return status code 200

  Scenario: Dispense button is present with Correct properties
    When User opens Chrome browser and navigates to HOMEPAGE page
    Then Dispense button is present in red color with color code rgba(220, 53, 69, 1)
