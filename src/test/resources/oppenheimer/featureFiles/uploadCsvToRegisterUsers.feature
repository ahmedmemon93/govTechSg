Feature: As the Clerk, I should be able to upload a csv file to a portal so that I can populate the database from a UI

  Background:
    Given user calls post API end point DATABASE_PURGE_END_POINT with given request body
    Then API should return status code 200

    @UI
  Scenario: User can upload a file on from pc to the portal and data is populated in db
    Given User opens Chrome browser and navigates to HOMEPAGE page
    And User upload a csv file having correct records
    Then TableRecords are visible after upload

